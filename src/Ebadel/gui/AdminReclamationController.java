/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.gui;

import Ebadel.entites.Reclamations;
import Ebadel.services.ClaimLogique;
import Ebadel.services.EmailService;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javax.mail.MessagingException;


/**
 * FXML Controller class
 *
 * @author messaoudi
 */
public class AdminReclamationController implements Initializable {

    @FXML
    private TableView<Reclamations> table_Claim;
   
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

    
    private ClaimLogique claimService;
    @FXML
    private Button sendMailButton;
     private EmailService mailService;
  
    @FXML
    private Button btnDelete;
   
    @FXML
    private ComboBox<String> btnfiltre;
    @FXML
    private  TableColumn<Reclamations, Button> col_delete = new TableColumn<>("btnDelete"); ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    ObservableList<String> destinataires = FXCollections.observableArrayList("Service client", "Service technique", "Service commercial", "Poste");

    btnfiltre.setItems(destinataires);
        
        
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
    
   
   col_delete.setCellFactory(cellFactory);
   
    }   
    Callback<TableColumn<Reclamations, Button>, TableCell<Reclamations, Button>> cellFactory = new Callback<TableColumn<Reclamations, Button>, TableCell<Reclamations, Button>>() {
    @Override
    public TableCell<Reclamations, Button> call( TableColumn<Reclamations, Button> col) {
        return new  TableCell<Reclamations, Button>() {
             final Button btnDelete = new Button("Button");

            {
                btnDelete.setOnAction((ActionEvent event) -> {
                    Reclamations reclamation = getTableView().getItems().get(getIndex());
                            claimService.deleteReclamation(reclamation.getContact());

                });
            }

            @Override
            public void updateItem(Button item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnDelete);
                }
            }
        };
       
    }
};
    
    
    

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
    private void DeleteRec(ActionEvent event) {
        
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

    @FXML
    private void FiltreRec(ActionEvent event) {
        
          String destinataire = btnfiltre.getValue();
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
    
     private void loadReclamations() {
    ObservableList<Reclamations> reclamations = FXCollections.observableArrayList();
    reclamations.addAll(claimService.getAllReclamations());
    table_Claim.setItems(reclamations);

    // Create a filter predicate that checks if the reclamations' destinataire matches the selected destinataire filter
    Predicate<Reclamations> destinataireFilter = reclamation -> {
        String selectedDestinataire = btnfiltre.getSelectionModel().getSelectedItem();
        if (selectedDestinataire == null || selectedDestinataire.isEmpty()) {
            return true; // No filter selected, so all reclamations pass
        }
        return reclamation.getDestinataire().equals(selectedDestinataire);
    };

    // Apply the filter to the table items
      table_Claim.setPredicate(destinataireFilter);
    }
    
}
