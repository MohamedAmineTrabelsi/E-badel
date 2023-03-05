/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebadel.gui;

import com.ebadel.entity.Jeux;
import com.ebadel.service.JeuxService;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author achou
 */
public class ParticiperJeuxController implements Initializable {

    @FXML
    private TableColumn<Jeux, String> nbrcolumn;
    @FXML
    private TableColumn<Jeux, String> titrecolumn;
    @FXML
    private TableColumn<Jeux, String> typecolumn;
    @FXML
    private TableColumn<Jeux, String> date_debutcolumn;
    @FXML
    private TableColumn<Jeux, String> date_fincolumn;
    @FXML
    private TableColumn<Jeux, String> imagecolumn;
    @FXML
    private TableColumn<Jeux, String> prixcolumn;
    @FXML
    private TableView<Jeux> tableparticipant;
    
    
    
    private Jeux selectedJeux;
    public void show(){
        ObservableList<Jeux> ListeB = FXCollections.observableArrayList();
        JeuxService js = new JeuxService();
        ListeB=js.afficherJeuxx();
        nbrcolumn.setCellValueFactory(new PropertyValueFactory<>("id_j"));
        titrecolumn.setCellValueFactory(new PropertyValueFactory<>("titre_j"));
        typecolumn.setCellValueFactory(new PropertyValueFactory<>("type_j"));
        date_debutcolumn.setCellValueFactory(new PropertyValueFactory<>("date_deb_j"));
        date_fincolumn.setCellValueFactory(new PropertyValueFactory<>("date_fin_j"));
        imagecolumn.setCellValueFactory(new PropertyValueFactory<>("image_p"));
        prixcolumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tableparticipant.setItems(ListeB);

        
        
         tableparticipant.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedJeux = newValue;
        });
        
    }
    
    
    // La méthode à appeler lorsque l'utilisateur clique sur le bouton Participer
    @FXML
    private void participer() {
        if (selectedJeux != null) {
            // Appeler le service de participation aux jeux avec le jeu sélectionné
            JeuxService js = new JeuxService();
        //    js.participer(selectedJeux);

            // Réinitialiser le jeu sélectionné
            selectedJeux = null;

            // Rafraîchir la tableview pour afficher les changements
            show();
        }
    }

    /**
     * Initializes the controller class.
     */
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        show();
        
         
        // TODO
    }    
    
}
