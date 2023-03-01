
package ebade1;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Ebade1 extends Application {
    public static Stage stg;
    @Override 
    public void start(Stage primaryStage) 
    {
        try {
            Ebade1.stg = primaryStage;
            //FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/CRUDProduit.fxml"));
            // loader= new FXMLLoader(getClass().getResource("../GUI/ajouterboutique.fxml"));
            FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/ListeDesBoutiques.fxml"));
            


            
            Parent root= loader.load();
            Scene scene= new Scene(root);
            primaryStage.setTitle("Bievennue");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Ebade1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

 
    
   public static void main(String[] args) {
           launch(args);
          
           
        
     
        
     
         
      
    }
   
        
}