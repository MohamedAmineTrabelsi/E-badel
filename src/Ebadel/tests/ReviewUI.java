/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.tests;

import Ebadel.entites.Reviews;
import Ebadel.services.ReviewLogique;
import Ebadel.utils.myConnection;
import java.sql.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;

/**
 *
 * @author messaoudi
 */
public class ReviewUI extends Application {
    
    
    
 
    
 @Override
    public void start(Stage stage) throws Exception {
        Parent root;
       // root = FXMLLoader.load(getClass().getClassLoader().getResource("\\Ebadel\\gui\\Review.fxml"));
      //root = FXMLLoader.load(getClass().getClassLoader().getResource("\\Ebadel\\gui\\Claim.fxml"));
       //root = FXMLLoader.load(getClass().getClassLoader().getResource("\\Ebadel\\gui\\AdminReclamation.fxml"));
       // root = FXMLLoader.load(getClass().getClassLoader().getResource("\\Ebadel\\gui\\AddClaim.fxml"));
        //  root = FXMLLoader.load(getClass().getClassLoader().getResource("\\Ebadel\\gui\\AddReview.fxml"));
        // root = FXMLLoader.load(getClass().getClassLoader().getResource("\\Ebadel\\gui\\HomeReviews.fxml"));
         root = FXMLLoader.load(getClass().getClassLoader().getResource("\\Ebadel\\gui\\navbar.fxml"));

        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    

   

        // Create the table view for displaying reviews
       
     public static void main(String[] args) {
        launch(args);
    }

}
