/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.login_signup;

import Config.Ebadel;
import entities.user;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import services.user.LoginAndSignupService;
import services.user.UserService;
import view.adminPanel.UserListController;
import view.PartieClient.ModifierCompteController;
import view.PartieClient.ClientPartieController;





public class SignupController implements Initializable {
   boolean ok = true;
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
 
    @FXML
    private AnchorPane left_main;
    @FXML
    private Button submitButton1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
     @FXML
   public void insertImage(){
        
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
    public void signup(ActionEvent event) throws SQLException, Exception  {

      boolean ok = true;
         boolean flag=false;
     ArrayList<user> user5 = new ArrayList<>();
          ArrayList<user> user6= new ArrayList<>();
 Window owner = submitButton.getScene().getWindow();
System.out.println("date"+DateNaissance.getValue());
   if (Prenom.getText().isEmpty()==true) { 
   
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your Prenom id");
             ok = false;
                             

                     }
  
    if (DateNaissance.getValue()==null) {
              
      
             showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a DateNaissance");
                 ok = false;
                      return;
         
            
          }  
       if (DateNaissance.getValue()==null) {
              
      
             showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a DateNaissance");
                 ok = false;
                      return;
         
            
          }  
         String str20=DateNaissance.getValue().toString();  
     Date date11=Date.valueOf(str20);
      String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
       Date date20=Date.valueOf(timeStamp);
      if (date11.after(date20)) { 
   
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "date invalide par rapport a la date System");
           
                  ok = false;      
                       return;

                     }

        if ((Email.getText().isEmpty())||((!Email.getText().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")))) {
         
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your email id");
          
              ok = false;
                   return;
            
           
        }
           if (image_view.getImage() == null ) {
        
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your Image id");
        
              ok = false;
                   return;
           
        }
           if (Nom.getText().isEmpty()==true) {
   
             showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a Nom");
              
            ok = false;
                 return;
            
          }
         if (Cin.getText().isEmpty()==true){
            
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your Cin id");
          
             ok = false;
                  return;
         }
        if (Password.getText().isEmpty()==true){
     
             showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Password vide");
             
                  ok = false;
                 return;
          }
        if(Password.getText().length()<4){
          showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "changer votre Password");
             
                  ok = false;
                 return;
        
        }
        
        
        
               if (DateNaissance.getValue().toString().isEmpty()==true) {
                 System.out.println("date invalide");
  
             showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a DateNaissance");
                   ok = false;
              return;
            
          }  
               
                        int monEntier1 = 0;
        boolean ok1 = true;                                                            
                                                                                                if(!Cin.getText().toString().isEmpty()==true){
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
                                                                                                        }
                                                                                      
     
        if (((Cin.getText().length()!=8)||(ok1==false))) {
            
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
               " Cin est invalide");
        
         ok = false;
           
        }
     
      
         int monEntier = 0;
                                                                           if(!Tel.getText().toString().isEmpty()){
                                                                                        try
                                                                                                 {
                                                                                                    monEntier = Integer.parseInt(Tel.getText());
                                                                                        // s'il ne contient que des chiffres (0 à 9) c'est ok sauf si les limites int sont dépassées
                                                                                        // sinon une exception est levée
                                                                                                 }
                                                                                                     catch(NumberFormatException nfe)
                                                                                                    {
                                                                                                       ok = false;
                                                                                                    }}
                                                                                       
        System.out.println("password+++++++"+Password.getText());
  if ((Tel.getText().length()!=8||(ok==false))) {
         
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Nb de Tel faible ou invalide");
            
              ok = false;
           
        }
   UserService us5 =new UserService();
   user5=(ArrayList<user>) us5.getuserbycin(Integer.parseInt(Cin.getText()));
   user6=(ArrayList<user>) us5.getuserbyTel(Integer.parseInt(Tel.getText()));
   if(user5.size()>0){
   
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "cin est deja utilise");
                 ok = false;
                      return;
   
   }
      if(user6.size()>0){
   
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Tel est deja utilise");
                 ok = false;
                      return;
   
   }
  if( ok==true){
   String str2=DateNaissance.getValue().toString();  
     Date date1=Date.valueOf(str2);


  user u1=new user(Cin.getText(),Nom.getText(),Prenom.getText(),Tel.getText(),Email.getText(),Password.getText(),file_path.getText(),date1);

   

                  LoginAndSignupService loginSignup = new LoginAndSignupService();
         flag = loginSignup.Signup(u1);
 
  }
  if( flag==false){
    showAlert(Alert.AlertType.ERROR, owner,"Form Error!","Email deja utilisé" );
       return;
  }
     else{
          
     
  UserService us=new UserService();
           user u23=us.getUserByEmail(Email.getText().toString());
           link1(u23);
           
             showAlert(Alert.AlertType.CONFIRMATION,owner,"Form Valide","Signup Successful");
                return;
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
    

public void link1(user user) throws Exception {               
 if(user.getRole()==1){
       try {
   
          
            //Personne.user = ;
            //Personne.user.get
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/adminPanel/UserList.fxml"));
            Parent root = loader.load();
            UserListController controller = loader.getController();
            controller.setUser(Email.getText());
            //Personne.user = ;
            //Personne.user.get
            Email.getScene().setRoot(root);
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
            controller.setUser(Email.getText());
            //Personne.user = ;
            //Personne.user.get
            Email.getScene().setRoot(root1);
    } catch(Exception e) {
        e.printStackTrace();
    }
 }

}


  

    @FXML
    private void login(ActionEvent event) {
       try {
                  final Node source = (Node) event.getSource();

            Ebadel.stg.close();  
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/login_signup/login.fxml"));
            Parent root = (Parent) fxmlLoader.load();
           final Stage stage = (Stage) source.getScene().getWindow();
            stage.setScene(new Scene(root));  
             Ebadel.stg.close();  
            stage.show();
           
    } catch(Exception e) {
        e.printStackTrace();
    }
    }
   @FXML
    private void link99(MouseEvent event) {
       
                  
                        Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
        
            //-******
           try {
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login_signup/login.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
         Ebadel.stg.close();   
    } catch(Exception e) {
        e.printStackTrace();
    }
}
}