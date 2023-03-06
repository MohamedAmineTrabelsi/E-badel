/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.adminPanel;


import entities.Categorie;
import entities.SCategorie;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.user.CategorieCRUD;
import services.user.SCategorieCRUD;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class GestionSCategorieController implements Initializable {

    @FXML
    private TextField txtSC;
    @FXML
    private ComboBox<String> LCategorie;
    @FXML
    private Button btnAjout;
    @FXML
    private TableView<SCategorie> table_s;
    @FXML
    private TableColumn<SCategorie, Integer> id_s_c;
    @FXML
    private TableColumn<SCategorie, String> nom_c;
    @FXML
    private TableColumn<SCategorie, String> nom_s_c;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsupprime;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LCategorie.setItems(getCategorieList());

        showSCategorie();
    }

    ObservableList<SCategorie> mesSCategorie = FXCollections.observableArrayList();
    SCategorieCRUD ss = new SCategorieCRUD();

    @FXML
    private void ajoutersouscategorie(ActionEvent event) {
        /*String categnom =LCategorie.getValue();
        String sc_name = txtSC.getText();
        SCategorieCRUD aa =new SCategorieCRUD();
        SCategorie sc =new SCategorie(categnom, sc_name);
        aa.ajouterSCategorie(sc);
        showSCategorie();*/
        String categnom = LCategorie.getValue();
        String sc_name = txtSC.getText();
        SCategorieCRUD aa = new SCategorieCRUD();
        if (sc_name.isEmpty()) {
// Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible d'ajouter la sous-catégorie");
            alert.setContentText("Le nom de la sous-catégorie est vide !");
            alert.showAndWait();
        } else if (categnom == null) {
// Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible d'ajouter la sous-catégorie");
            alert.setContentText("La catégorie est obligatoire !");
            alert.showAndWait();
        } else {
            SCategorie sc = new SCategorie(categnom, sc_name);
            aa.ajouterSCategorie(sc);
            showSCategorie();
        }
    }

    public ObservableList<String> getCategorieList() {
        ObservableList<String> obscateg = FXCollections.observableArrayList();
        CategorieCRUD categ = new CategorieCRUD();
        ObservableList<Categorie> categBase = categ.afficherCategorie();
        for (int i = 0; i < categBase.size(); i++) {
            obscateg.add(categBase.get(i).getNom_c());
        }

        return obscateg;
    }

    public void showSCategorie() {
        mesSCategorie = ss.afficherSCategorie();
        table_s.setItems(mesSCategorie);
        id_s_c.setCellValueFactory(new PropertyValueFactory<SCategorie, Integer>("id_s_c"));
        nom_c.setCellValueFactory(new PropertyValueFactory<SCategorie, String>("nom_c"));
        nom_s_c.setCellValueFactory(new PropertyValueFactory<SCategorie, String>("nom_s_c"));
    }

    @FXML
    private void modifiersouscategorie(ActionEvent event) {
        String selectedCategorie = LCategorie.getValue();
        SCategorie selectedSCategorie = table_s.getSelectionModel().getSelectedItem();
        String newNomSCategorie = txtSC.getText();
        if (selectedSCategorie == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de modifier la sous-catégorie");
            alert.setContentText("Veuillez sélectionner une sous-catégorie à modifier !");
            alert.showAndWait();
        } else if (newNomSCategorie.isEmpty()) {
            // Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de modifier la sous-catégorie");
            alert.setContentText("Le nom de la sous-catégorie est vide !");
            alert.showAndWait();
        } else {
            boolean success = ss.modifierSCategorie(selectedSCategorie, newNomSCategorie, selectedCategorie);
            if (!success) {
                // Afficher un message d'erreur
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Impossible de modifier la sous-catégorie");
                alert.setContentText("La sous-catégorie existe déjà dans la base de données !");
                alert.showAndWait();
            } else {
                // Actualiser la liste des sous-catégories affichée dans le TableView
                showSCategorie();
            }
        }
    }

    @FXML
    private void supprimersouscategorie(ActionEvent event) {
        SCategorie selectedSCategorie = table_s.getSelectionModel().getSelectedItem();
        if (selectedSCategorie == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de supprimer la sous-catégorie");
            alert.setContentText("Veuillez sélectionner une sous-catégorie à supprimer !");
            alert.showAndWait();
        } else {
            ss.supprimerSCategorie(selectedSCategorie);
            showSCategorie();
        }
    }
}
