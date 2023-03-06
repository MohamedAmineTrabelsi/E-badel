/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.adminPanel;

import entities.Categorie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.user.CategorieCRUD;
/**
 * FXML Controller class
 *
 * @author trabe
 */
public class GestionCategorieController implements Initializable {

    @FXML
    private TextField txtcategorie;

    @FXML
    private TableView<Categorie> table;

    @FXML
    private TableColumn<Categorie, String> nom_c;

    @FXML
    private Button btn_ajout;

    @FXML
    private Button btn_modif;

    @FXML
    private Button btn_supprime;
    
 ObservableList<Categorie>mesCategorie = FXCollections.observableArrayList();
 
 CategorieCRUD cc = new CategorieCRUD();
 
    @FXML
    void ajoutercategorie(ActionEvent event) {
 /*       String c_name=txtcategorie.getText();
        Categorie c = new Categorie(c_name);       
        cc.ajouterCategorie(c);
        showCategorie();
        }*/
     String c_name = txtcategorie.getText();
    if (c_name.isEmpty()) {
        // Afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Impossible d'ajouter la catégorie");
        alert.setContentText("Le nom de la catégorie est vide !");
        alert.showAndWait();
    } else {
        Categorie c = new Categorie(c_name);
        boolean success = cc.ajouterCategorie(c);
        if (!success) {
            // Afficher un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible d'ajouter la catégorie");
            alert.setContentText("La catégorie existe déjà dans la base de données !");
            alert.showAndWait();
        } else {
            // Actualiser la liste des catégories affichée dans le TableView
            showCategorie();
        }
    }
    }

    @FXML
    void modifiercategorie(ActionEvent event) {
  /*      String c_name=txtcategorie.getText();
        Categorie c = new Categorie(c_name);    
        //cc.modifierCategorie(c);;
        showCategorie();*/
      Categorie selectedCategorie = table.getSelectionModel().getSelectedItem();
    String newNomCategorie = txtcategorie.getText();
    if (selectedCategorie == null) {
        // Afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Impossible de modifier la catégorie");
        alert.setContentText("Veuillez sélectionner une catégorie à modifier !");
        alert.showAndWait();
    } else if (newNomCategorie.isEmpty()) {
        // Afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Impossible de modifier la catégorie");
        alert.setContentText("Le nom de la catégorie est vide !");
        alert.showAndWait();
    } else {
        boolean success=cc.modifierCategorie(selectedCategorie, newNomCategorie);
        if (!success) {
            // Afficher un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de modifier la catégorie");
            alert.setContentText("La catégorie existe déjà dans la base de données !");
            alert.showAndWait();
        } else {
            // Actualiser la liste des catégories affichée dans le TableView
            showCategorie();
        }
      }
    }

    @FXML
    void supprimercategorie(ActionEvent event) {
/*        String c_name=txtcategorie.getText();
        Categorie c = new Categorie(c_name);    
        cc.supprimerCategorie(c);
       // showCategorie();
        showCategorie();*/
Categorie selectedCategorie = table.getSelectionModel().getSelectedItem();
    if (selectedCategorie == null) {
        // Afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Impossible de supprimer la catégorie");
        alert.setContentText("Veuillez sélectionner une catégorie à supprimer !");
        alert.showAndWait();
    } else {
        cc.supprimerCategorie(selectedCategorie);
        showCategorie();
    }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
            showCategorie();
                // Ajouter un EventHandler sur le TableView pour écouter le clic sur une ligne
    table.setOnMouseClicked(event -> {
        if (event.getClickCount() == 2) { // Double clic
            Categorie selectedCategorie = table.getSelectionModel().getSelectedItem();
            if (selectedCategorie != null) {
                txtcategorie.setText(selectedCategorie.getNom_c());
            }
        }
    });
    }    
    public void showCategorie(){
       mesCategorie = cc.afficherCategorie();
        table.setItems(mesCategorie);
        nom_c.setCellValueFactory(new PropertyValueFactory<Categorie, String>("nom_c")); 
    }
}
