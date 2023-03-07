/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.gui;

import Ebadel.entites.Reclamations;
import Ebadel.services.BadWordFilter;
import Ebadel.services.ClaimLogique;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author messaoudi
 */
public class AddClaimController implements Initializable {

    @FXML
    private TextField col_contact;
    @FXML
    private ComboBox<String> select_type;
    @FXML
    private ComboBox<String> select_statut;
    @FXML
    private ComboBox<String> select_Destinataire;
    @FXML
    private TextArea tfDesc;

    /**
     * Initializes the controller class.
     */
    
    
    private ClaimLogique claimService;
    @FXML
    private Button btnAffiche;
    @FXML
    private Button btnAdd;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
       claimService =new ClaimLogique();
     
        
     // relier col to Reclamation properties
  
    // Load data into table
   
        
        
    claimService = new ClaimLogique();
    ObservableList<String> destinataires = FXCollections.observableArrayList("Service client", "Service technique", "Service commercial", "Poste");
    ObservableList<String> types = FXCollections.observableArrayList("Réclamation", "Demande de renseignement", "Demande de service", "Grave", "Problème");
    ObservableList<String> statuts = FXCollections.observableArrayList("Nouvelle", "En cours", "Terminée", "Cloturée");
   
    select_Destinataire.getItems().addAll(destinataires);
    select_Destinataire.getSelectionModel().select("Service client");
    select_type.getItems().addAll(types);
    select_type.getSelectionModel().select("Réclamation");
    select_statut.getItems().addAll(statuts);
    select_statut.getSelectionModel().select("Nouvelle");
  
   
    }
    
    @FXML
    private void AjouterReclamation(ActionEvent event) {
 
      List<String> badWords = Arrays.asList("asba", "rou7 nyk", "nik ta mère");
      
      
      
    String contact = col_contact.getText();
    String destinataire = select_Destinataire.getValue();
    String type = select_type.getValue();
    String statut = select_statut.getValue();
    String description = tfDesc.getText();
    String filteredDescription = BadWordFilter.getCensoredText(description);
    LocalDate date = LocalDate.now();
    Instant instant = Instant.from(date.atStartOfDay(ZoneId.systemDefault()));
  
    for (String badWord : badWords) {
    if (filteredDescription.toLowerCase().contains(badWord)) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Contenu inapproprié");
        alert.setHeaderText(null);
        alert.setContentText("La réclamation contient un contenu inapproprié !");
        alert.showAndWait();
        return;
    }
}
   
    
    if (contact.isEmpty() || destinataire == null || type == null || statut == null || filteredDescription.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Champs vides");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();
        return;
    }

    Reclamations reclamation = new Reclamations(contact, destinataire, type, statut, filteredDescription, date);
    claimService.addReclamation(reclamation);
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Réclamation envoyée");
    alert.setHeaderText(null);
    alert.setContentText("La réclamation a été envoyée avec succès !!!");
    alert.showAndWait();
    
    clearFields();
        
       
    }
    
     private void clearFields() {
    col_contact.setText("");
    select_Destinataire.getSelectionModel().clearSelection();
    select_type.getSelectionModel().clearSelection();
    select_statut.getSelectionModel().clearSelection();
    tfDesc.setText("");
}
     
      
   
    

 @FXML
    private void AfficheReclamation(ActionEvent event) {
        try {
            // Charger la nouvelle scène FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminReclamation.fxml"));
            Parent root = loader.load();
            // Définir la nouvelle racine de la scène actuelle
            ((Node) event.getSource()).getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
        
    }
    
    
        
        
       
   
    
    
    
    
    
    
  
   

    
 
        
        
        
 
    
    

