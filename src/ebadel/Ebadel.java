
package ebadel;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author achou
 */
public class Ebadel extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       FXMLLoader loader =new FXMLLoader(getClass().getResource("../com/ebadel/gui/Participation.fxml"));
       
       

     Parent root=loader.load();
     Scene scene=new Scene(root);
     primaryStage.setTitle("EJeu");
     primaryStage.setScene(scene);
     primaryStage.show();
    }
    

 
    
   public static void main(String[] args) {
           launch(args);
        
     
    }

    
    
}
