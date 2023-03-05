/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebadel.gui;

import com.ebadel.entity.Participation;
import com.ebadel.entity.User;
import com.ebadel.entity.MailSender;
import com.ebadel.service.ParticipationService;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ParticipationController implements Initializable {
    @FXML
    private Button deletePart;
    @FXML
    private Button addPart;
   
    @FXML
    private ComboBox<Integer> userComboBox;
    @FXML
    private ComboBox<Integer> gameComboBox;
        int id_select;
    @FXML
    private TableView<Participation> tableViewParticipations;
    @FXML
    private TableColumn<Participation, Integer> columnParticipationId;
    @FXML
    private TableColumn<Participation, Integer> columnUserId;
    @FXML
    private TableColumn<Participation, Integer> columnGameId;
    ParticipationService ps = new ParticipationService();
    private Spinner<Integer> randomSpinner;
    @FXML
    private TextField randomTextField;
    @FXML
    private Button tirer;
    int ppt;
    
    private void initializeSpinner() {
    SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999, 0);
    randomSpinner.setValueFactory(valueFactory);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        List<Integer> userIds = ps.getAllUserIds();
        
    List<Integer> gameIds = ps.getAllGameIds();
    userComboBox.getItems().addAll(userIds);
    gameComboBox.getItems().addAll(gameIds);

             
    // Initialize the table columns
    columnParticipationId.setCellValueFactory(new PropertyValueFactory<>("Participation ID"));
    columnUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
    columnGameId.setCellValueFactory(new PropertyValueFactory<>("gameId"));

    // Populate the table with all participations from the database
    List<Participation> participationList = ps.afficherParticipa();
    ObservableList<Participation> observableParticipationList = FXCollections.observableArrayList(participationList);
    tableViewParticipations.setItems(observableParticipationList);
                  
    }

   
    
    
private void updateParticipationList() {
    // Retrieve the latest list of participations from the database
    List<Participation> participationList = ps.afficherParticipa();

    // Clear the existing items in the table view
    tableViewParticipations.getItems().clear();

    // Add the updated list of participations to the table view
    tableViewParticipations.getItems().addAll(participationList);
}

    @FXML
    private void deletePart(MouseEvent event) {
         Participation participation = tableViewParticipations.getSelectionModel().getSelectedItem();
    if (participation != null) {
        ps.supprimerParticipation(participation.getId());
        updateParticipationList();
    }
    }

    @FXML
    private void addParticipation(MouseEvent event) {
         int selectedUser = userComboBox.getValue();
    int selectedGame = gameComboBox.getValue();
    Participation newParticipation = new Participation(selectedUser, selectedGame);
    ps.ajouterParticipation(newParticipation);
    updateParticipationList();
    }

    @FXML
    private void selectRandomParticipation(MouseEvent event) throws Exception {
        MailSender ms = new MailSender();
       int size = tableViewParticipations.getItems().size();
    if(size > 0) {
        int randomIndex = new Random().nextInt(size);
        Participation randomParticipation = tableViewParticipations.getItems().get(randomIndex);
        randomTextField.setText("" + randomParticipation.getUserId());
        ppt = randomParticipation.getUserId();
         String destinataire = null;
        destinataire = ps.getEmailUser(ppt);
        User usr = new User();
        usr=ps.getUserById(ppt);
        ms.sendMail(usr);
    } else {
        randomTextField.setText("No Participations to Select From");
    }
    }

    

   







   






    
}
