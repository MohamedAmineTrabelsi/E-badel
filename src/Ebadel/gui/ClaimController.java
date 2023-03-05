/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.gui;

import Ebadel.services.ClaimLogique;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import Ebadel.entites.Reclamations;
import Ebadel.services.BadWordFilter;
import Ebadel.services.EmailService;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author messaoudi
 */




public class ClaimController implements Initializable {
    @FXML
    private TableView<Reclamations> table_Claim;
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
   
    @FXML
    private TableColumn<Reclamations, String> contactID;
    @FXML
    private TableColumn<Reclamations, String> DestinatireID;
    @FXML
    private TableColumn<Reclamations, String> TypeID;
    @FXML
    private TableColumn<Reclamations, String> StautID;
    @FXML
    private TableColumn<Reclamations, String> DescriptionID;
    @FXML
    private TableColumn<Reclamations, LocalDate> DateID;
    @FXML
    private Button btnAdd;

    
      private ClaimLogique claimService;
    @FXML
    private Button sendMailButton;
     private EmailService mailService;
    @FXML
    private ComboBox<String> filtredRec;
    @FXML
    private Button btnDelete;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         mailService = new EmailService();

        sendMailButton.setOnAction(event -> {
            try {
                mailService.sendMail("hassen.messaoudi@esprit.tn", "Example Subject", "Example Body");
                System.out.println("Email sent successfully.");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
        
    
        
        claimService =new ClaimLogique();
        List<Reclamations> reclamations = claimService.getAllReclamations();
    if (reclamations != null) {
        ObservableList<Reclamations> reclamationData = FXCollections.observableArrayList(reclamations);
        table_Claim.setItems(reclamationData);
    }
        
         // relier col to Reclamation properties
    contactID.setCellValueFactory(new PropertyValueFactory<>("contact"));
    DestinatireID.setCellValueFactory(new PropertyValueFactory<>("destinataire"));
    TypeID.setCellValueFactory(new PropertyValueFactory<>("type"));
    StautID.setCellValueFactory(new PropertyValueFactory<>("statut"));
    DescriptionID.setCellValueFactory(new PropertyValueFactory<>("description"));
    DateID.setCellValueFactory(new PropertyValueFactory<>("date"));

    // Load data into table
   
        
        
    claimService = new ClaimLogique();
    ObservableList<String> destinataires = FXCollections.observableArrayList("Service client", "Service technique", "Service commercial", "Poste");
    ObservableList<String> types = FXCollections.observableArrayList("Réclamation", "Demande de renseignement", "Demande de service", "Grave", "Problème");
    ObservableList<String> statuts = FXCollections.observableArrayList("Nouvelle", "En cours", "Terminée", "Cloturée");
   
    select_Destinataire.getItems().addAll(destinataires);
    select_Destinataire.getSelectionModel().select("Service client");
    filtredRec.setItems(select_Destinataire.getItems());
    select_type.getItems().addAll(types);
    select_type.getSelectionModel().select("Réclamation");
    select_statut.getItems().addAll(statuts);
    select_statut.getSelectionModel().select("Nouvelle");
  

        // TODO
    }  
    
     public void showReclamations(){
      
    contactID.setCellValueFactory(new PropertyValueFactory<>("contact"));
    DestinatireID.setCellValueFactory(new PropertyValueFactory<>("destinataire"));
    TypeID.setCellValueFactory(new PropertyValueFactory<>("type"));
    StautID.setCellValueFactory(new PropertyValueFactory<>("statut"));
    DescriptionID.setCellValueFactory(new PropertyValueFactory<>("description"));
    DateID.setCellValueFactory(new PropertyValueFactory<>("date"));

    
      claimService =new ClaimLogique();
        List<Reclamations> reclamations = claimService.getAllReclamations();
    if (reclamations != null) {
        ObservableList<Reclamations> reclamationData = FXCollections.observableArrayList(reclamations);
        table_Claim.setItems(reclamationData);
    }
    
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
    clearFields();
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Réclamation envoyée");
    alert.setHeaderText(null);
    alert.setContentText("La réclamation a été envoyée avec succès !!!");
    alert.showAndWait();
    showReclamations();
        
       
    }
    
    
    
    private void clearFields() {
    col_contact.setText("");
    select_Destinataire.getSelectionModel().clearSelection();
    select_type.getSelectionModel().clearSelection();
    select_statut.getSelectionModel().clearSelection();
    tfDesc.setText("");
}
    
    private void loadReclamations() {
    ObservableList<Reclamations> reclamations = FXCollections.observableArrayList();
    reclamations.addAll(claimService.getAllReclamations());
    table_Claim.setItems(reclamations);

    // Create a filter predicate that checks if the reclamations' destinataire matches the selected destinataire filter
    Predicate<Reclamations> destinataireFilter = reclamation -> {
        String selectedDestinataire = filtredRec.getSelectionModel().getSelectedItem();
        if (selectedDestinataire == null || selectedDestinataire.isEmpty()) {
            return true; // No filter selected, so all reclamations pass
        }
        return reclamation.getDestinataire().equals(selectedDestinataire);
    };

    // Apply the filter to the table items
      table_Claim.setPredicate(destinataireFilter);
    }


    @FXML
    private void FiltredByDestinataire(ActionEvent event) {
        
        String destinataire = filtredRec.getValue();
     if (destinataire == null) {
        loadReclamations();
        return;
    }
    ObservableList<Reclamations> filteredList = FXCollections.observableArrayList();
    List<Reclamations> reclamationList = claimService.getAllReclamations();
    for (Reclamations reclamation : reclamationList) {
        if (reclamation.getDestinataire().equals(destinataire)) {
            filteredList.add(reclamation);
        }
    }
    table_Claim.setItems(filteredList);
        
    }

    @FXML
    private void DeleteReclamation(ActionEvent event) {
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Confirmation");
          alert.setHeaderText("Are you sure you want to delete the selected reclamation?");
           Optional<ButtonType> result = alert.showAndWait();
             if (result.isPresent() && result.get() == ButtonType.OK) {
    // Delete the selected reclamation
      Reclamations reclamation = getSelectedReclamation();
      if (reclamation != null) {
        claimService.deleteReclamation(reclamation.getContact());
        showReclamations();
        clearFields();
    }


            
        }
    }
    
    private Reclamations getSelectedReclamation() {
        Reclamations reclamation = null;
        ObservableList<Reclamations> reclamations = claimService.getAllReclamations();
        int selectedIndex = table_Claim.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < reclamations.size()) {
            reclamation = reclamations.get(selectedIndex);
        }
        return reclamation;
    }
    
}
