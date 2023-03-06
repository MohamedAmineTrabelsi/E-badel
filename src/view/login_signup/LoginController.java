/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.login_signup;

import Config.Ebadel;
import entities.user;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import services.user.LoginAndSignupService;
import services.user.UserService;
import view.adminPanel.UserListController;
import view.adminPanel.HomeController;
import view.PartieClient.ModifierCompteController;
import view.PartieClient.ClientPartieController;

/**
 *
 * @author medal
 */
public class LoginController implements Initializable {
        public static  Stage stg1;
   @FXML
    private TextField emailIdField;
   @FXML
    private PasswordField passwordField;
    @FXML   
    private Button submitButton;
   
   @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
   @FXML
    public void login(ActionEvent event) throws SQLException, Exception  {

     
 Window owner = submitButton.getScene().getWindow();


        if (emailIdField.getText().isEmpty()||((!emailIdField.getText().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")))) {
        
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your email id");
            return;
           
        }
        if (passwordField.getText().isEmpty()) {
    
             showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a password");
            return;
            
          
        }

        String emailId = emailIdField.getText();
        String password = passwordField.getText();

            LoginAndSignupService loginSignup = new LoginAndSignupService();
        boolean flag = loginSignup.login(emailId, password);

        if (!flag) {
            showAlert(Alert.AlertType.ERROR, owner,"Form Error!","Please enter correct Email and Password" );
        } else {
          
           UserService us=new UserService();
           user u1=us.getUserByEmail(emailId);
           link1(u1);
        }
    }
    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

  @FXML
    private void link(ActionEvent event) {
       try {
                  final Node source = (Node) event.getSource();

            Ebadel.stg.close();  
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("captcha.fxml"));
            Parent root = (Parent) fxmlLoader.load();
           final Stage stage = (Stage) source.getScene().getWindow();
            stage.setScene(new Scene(root));  
             Ebadel.stg.close();  
            stage.show();
           
    } catch(Exception e) {
        e.printStackTrace();
    }
    }
 


public void link1(user user) throws Exception {               
 if(user.getRole()==1){
       try {
   
          
            //Personne.user = ;
            //Personne.user.get
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/adminPanel/home.fxml"));
            Parent root = loader.load();
            HomeController controller = loader.getController();
            controller.setUser(emailIdField.getText());
                    emailIdField.getScene().setRoot(root);
                    
            //Personne.user = ;
            //Personne.user.get
    
        } catch (IOException ex) {
         Ebadel.stg.close();   
    } catch(Exception e) {
        e.printStackTrace();
    }
}else{
    try {
     
            //Personne.user = ;
            //Personne.user.get
                      FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/view/PartieClient/ClientPartie.fxml"));
            Parent root1 = loader1.load();
            ClientPartieController controller = loader1.getController();
            controller.setUser(emailIdField.getText());
            //Personne.user = ;
            //Personne.user.get
            emailIdField.getScene().setRoot(root1);
    } catch(Exception e) {
        e.printStackTrace();
    }
 }
}
}