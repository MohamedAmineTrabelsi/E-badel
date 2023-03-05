/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.PartieClient;

import entities.user;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import services.user.LoginAndSignupService;
import services.user.UserService;
import view.PartieClient.ClientPartieController;

/**
 * FXML Controller class
 *
 * @author medal
 */
public class ModifierCompteController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private Button submitButton;
    @FXML
    private TextField Cin;
    @FXML
    private TextField Nom;
    @FXML
    private TextField Prenom;
    @FXML
    private TextField Tel;
    @FXML
    private TextField Email;
    @FXML
    private DatePicker DateNaissance;
    @FXML
    private PasswordField Password;
    @FXML
    private Label file_path;
    @FXML
    private ImageView image_view;
    @FXML
    private Button insert_image;
user u1 = new user();
    /**
     * Initializes the controller class.
     */
     public user setUser(String username) {
  UserService u= new UserService();
       
        user u1 = u.getUserByEmail(username);
         Cin.setText(String.valueOf(u1.getCin()));
        Nom.setText(String.valueOf(u1.getNom()));
       
      
        Prenom.setText(u1.getPrenom());
        Tel.setText(String.valueOf(u1.getTel()));
        Email.setText(u1.getEmail());
        DateNaissance.setValue(u1.getDateNaissance().toLocalDate());
file_path.setText(u1.getImage().toString());
    System.out.println("image"+u1.getImage().toString());

  
         
    String picture ="file:" + u1.getImage().toString();

        Image image = new Image(picture, 110, 110, false, true);

        image_view.setImage(image);

         return u1;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    


    @FXML
    private void insertImage(ActionEvent event) {
         FileChooser open = new FileChooser();
        
        Stage stage = (Stage)left_main.getScene().getWindow();
        
        File file = open.showOpenDialog(stage);
        
        if(file != null){
            
            String path = file.getAbsolutePath();
            
            path = path.replace("\\", "\\\\");
            
            file_path.setText(path);

            Image image = new Image(file.toURI().toString(), 110, 110, false, true);
            
            image_view.setImage(image);
            
        }else{
            
            System.out.println("NO DATA EXIST!");
            
        }
    }
 @FXML
    private void Valider(ActionEvent event) throws Exception {
   
 Window owner = submitButton.getScene().getWindow();

boolean flag=false;
        if ((Email.getText().isEmpty())||((!Email.getText().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")))) {
        
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your email id");
            return;
           
        }
        if ((Password.getText().isEmpty())||(Password.getText().length()<4)) {
    
             showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a password");
            return;
            
          }
               if (DateNaissance.getValue().toString().isEmpty()) {
    
             showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a DateNaissance");
            return;
            
          }  
               
                        int monEntier1 = 0;
        boolean ok1 = true;
                                                                                        try
                                                                                                 {
                                                                                                    monEntier1 = Integer.parseInt(Cin.getText());
                                                                                        // s'il ne contient que des chiffres (0 à 9) c'est ok sauf si les limites int sont dépassées
                                                                                        // sinon une exception est levée
                                                                                                 }
                                                                                                     catch(NumberFormatException nfe)
                                                                                                    {
                                                                                                       ok1= false;
                                                                                                    }
     
        if (Cin.getText().isEmpty()||((Cin.getText().length()!=8)||(ok1==false))) {
        
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your Cin id");
            return;
           
        }
        if (Nom.getText().isEmpty()) {
    
             showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a password");
            return;
            
          }
         if (image_view.getImage() == null ) {
        
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your Image id");
            return;
           
        }
              if (Prenom.getText().isEmpty()) {

           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your Prenom id");
                                 return;

                     }
 
         int monEntier = 0;
        boolean ok = true;
                                                                                        try
                                                                                                 {
                                                                                                    monEntier = Integer.parseInt(Tel.getText());
                                                                                        // s'il ne contient que des chiffres (0 à 9) c'est ok sauf si les limites int sont dépassées
                                                                                        // sinon une exception est levée
                                                                                                 }
                                                                                                     catch(NumberFormatException nfe)
                                                                                                    {
                                                                                                       ok = false;
                                                                                                    }
               if (Tel.getText().isEmpty()||(Tel.getText().length()!=8||(ok==false))) {
        
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your Tel id");
            return;
           
        }                                                                         
 
        String str2=DateNaissance.getValue().toString();  
     Date date1=Date.valueOf(str2);





                  UserService us = new UserService();
              
        
 
        
  user u10=new user(Cin.getText(),Nom.getText(),Prenom.getText(),Tel.getText(),Email.getText(),Password.getText(),file_path.getText(),date1);
                user u2 = us.getUserByEmail(Email.getText().toString());          
  us.modifier(u2.getIdu(), u10);     //  boolean flag = loginSignup.Signup(u1);
           
     link1(u2);
    }
    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
  


public void link1(user user) throws Exception {
  if (user.getRole()==0){
      
    try {
     
            //Personne.user = ;
            //Personne.user.get
                      FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/view/PartieClient/ClientPartie.fxml"));
            Parent root1 = loader1.load();
            ClientPartieController controller = loader1.getController();
            controller.setUser(user.getEmail());
            //Personne.user = ;
            //Personne.user.get
            Email.getScene().setRoot(root1);
    } catch(Exception e) {
        e.printStackTrace();
    }

    }   else{
    try {
     
            //Personne.user = ;
            //Personne.user.get
                      FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/view/adminPanel/home.fxml"));
            Parent root1 = loader1.load();
            ClientPartieController controller = loader1.getController();
            controller.setUser(user.getEmail());
            //Personne.user = ;
            //Personne.user.get
            Email.getScene().setRoot(root1);
    } catch(Exception e) {
        e.printStackTrace();
    }


}
 }

   }

  
