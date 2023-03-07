/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author messaoudi
 */
public class NavbarController implements Initializable {

    @FXML
    private Button addbtn;
    @FXML
    private Button btnRec;
    @FXML
    private Button btnAjoutR;
    @FXML
    private Button btnAffRate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void EcireReclamation(ActionEvent event) {
        
        try {
            // Charger la nouvelle scène FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddClaim.fxml"));
            Parent root = loader.load();
            // Définir la nouvelle racine de la scène actuelle
            ((Node) event.getSource()).getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
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

    @FXML
    private void AjoutReview(ActionEvent event) {
        
        try {
            // Charger la nouvelle scène FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddReview.fxml"));
            Parent root = loader.load();
            // Définir la nouvelle racine de la scène actuelle
            ((Node) event.getSource()).getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
    }

    @FXML
    private void AfficheReviews(ActionEvent event) {
        
        try {
            // Charger la nouvelle scène FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeReviews.fxml"));
            Parent root = loader.load();
            // Définir la nouvelle racine de la scène actuelle
            ((Node) event.getSource()).getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
    
}
