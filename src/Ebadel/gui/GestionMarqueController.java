/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.gui;

import Ebadel.entites.Categorie;
import Ebadel.entites.Marque;
import Ebadel.entites.SCategorie;
import Ebadel.services.CategorieCRUD;
import Ebadel.services.MarqueCRUD;
import Ebadel.services.SCategorieCRUD;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class GestionMarqueController implements Initializable {

    @FXML
    private ComboBox<Categorie> Lcategorie;
    @FXML
    private ComboBox<SCategorie> LSCategorie;
    @FXML
    private TextField txt_m;
    @FXML
    private TableView<Marque> table_m;
    @FXML
    private TableColumn<Marque, String> nom_m;
    @FXML
    private TableColumn<Marque, String> nom_c;
    @FXML
    private TableColumn<Marque, String> nom_s_c;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsupprime;

    ObservableList<SCategorie> mesSCategorie = FXCollections.observableArrayList();
    SCategorieCRUD ss = new SCategorieCRUD();

    ObservableList<Categorie> mesCategorie = FXCollections.observableArrayList();
    CategorieCRUD cc = new CategorieCRUD();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCategListe();
        Lcategorie.valueProperty().addListener((obs, oldVal, newVal) -> {
    // Handle value change here
            initSCategListe(newVal.toString());
});
        showMarque();
    }
    ObservableList<Marque>mesMarque = FXCollections.observableArrayList();
    MarqueCRUD mm = new MarqueCRUD();
       public void showMarque(){
        mesMarque = mm.afficherMarque();
        table_m.setItems(mesMarque);
        nom_m.setCellValueFactory(new PropertyValueFactory<Marque, String>("nom_m"));
        nom_c.setCellValueFactory(new PropertyValueFactory<Marque, String>("nom_c")); 
        nom_s_c.setCellValueFactory(new PropertyValueFactory<Marque, String>("nom_s_c"));
    }
    @FXML
    private void ajoutermarque(ActionEvent event) {
       Categorie categnom =Lcategorie.getValue();
        SCategorie scategnom =LSCategorie.getValue();
        String sc_name = txt_m.getText();
        MarqueCRUD mm =new MarqueCRUD();
        Marque m =new Marque(sc_name, categnom.getNom_c(), scategnom.getNom_s_c());
        mm.ajouterMarque(m);
        showMarque();
   
    }

    @FXML
    private void modifiermarque(ActionEvent event) {
        Marque selectedMarque = table_m.getSelectionModel().getSelectedItem();
        String newNomMarque = txt_m.getText();
        if (selectedMarque == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de modifier la marque");
            alert.setContentText("Veuillez sélectionner une marque à modifier !");
            alert.showAndWait();
        } else if (newNomMarque.isEmpty()) {
            // Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de modifier la marque");
            alert.setContentText("Le nom de la marque est vide !");
            alert.showAndWait();
        } else {
            boolean success = mm.modifierMarque(selectedMarque, newNomMarque,  selectedMarque.getNom_c(),selectedMarque.getNom_s_c());
            if (!success) {
                // Afficher un message d'erreur
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Impossible de modifier la marque");
                alert.setContentText("La marque existe déjà dans la base de données !");
                alert.showAndWait();
            } else {
                // Actualiser la liste des marque affichée dans le TableView
                showMarque();
            }
        }
    }

    @FXML
    private void supprimermarque(ActionEvent event) {
        Marque selectedMarque = table_m.getSelectionModel().getSelectedItem();
        if (selectedMarque == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de supprimer la marque");
            alert.setContentText("Veuillez sélectionner une marque à supprimer !");
            alert.showAndWait();
        } else {
            mm.supprimerMarque(selectedMarque);
            showMarque();
        }
    }
    
    public void initCategListe(){
        mesCategorie=cc.afficherCategorie();
        Lcategorie.setItems(mesCategorie);
    }
     public void initSCategListe(String a){
        mesSCategorie=ss.afficherSCategorieParCategorie(a);
        LSCategorie.setItems(mesSCategorie);
    }
}
