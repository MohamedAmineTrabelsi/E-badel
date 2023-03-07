/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.tests;

import Ebadel.utils.myConnection;
import java.io.IOException;
import java.sql.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author allal
 */
public class Ebadel extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
     launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
      Connection mc = myConnection.getInstance().getCnx();
       try {
            Parent root = FXMLLoader.
                   // load(getClass().getResource("/Ebadel/gui/GestionCategorie.fxml"));
                  // load(getClass().getResource("/Ebadel/gui/GestionSCategorie.fxml"));
                   // load(getClass().getResource("/Ebadel/gui/GestionMarque.fxml"));
                  load(getClass().getResource("/Ebadel/gui/Accueil.fxml"));
                 //load(getClass().getResource("/Ebadel/gui/Musique.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Ebadel");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
