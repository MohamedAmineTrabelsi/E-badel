/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.PartieClient;

import Config.Datasource;
import Config.Ebadel;
import entities.article;
import entities.user;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.user.UserService;


/**
 * FXML Controller class
 *
 * @author 
 */
public class ClientPartieController implements Initializable {
 public static user u1 = new user();
UserService us= new UserService();

    
   
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private user userr;
   
   
    /**
     * Initializes the controller class.
     */
   
   
    @FXML
    private Label username;
      ArrayList<Integer> IDs=new ArrayList<Integer>();
static String email;
   
    @FXML
    private Button modifiercompte;
   
  
 
 public user setUser(String email) {
     
  UserService u= new UserService();
     
  u1= u.getUserByEmail(email);
               System.out.println("sssssssssssss"+u1);

         username.setText(u1.getEmail());
         return u1;
    }

 
         @FXML
public void logout(ActionEvent event) throws Exception {               
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
public void articles(ActionEvent event) throws Exception {               
   user userconnected=u1;
    
           FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
 Parent root = loader.load();
       AcceuilController secondController = loader.getController();
             secondController.setUser(userconnected);
            ((Node) event.getSource()).getScene().setRoot(root);
    
System.out.println(userconnected.getIdu());
    
}

 @FXML
    public void mesarticles(ActionEvent event) throws IOException {
        user userconnected=u1;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MesArticles.fxml"));
     Parent root = loader.load();
       MesArticlesController secondController = loader.getController();
        secondController.setUser(userconnected);
   ((Node) event.getSource()).getScene().setRoot(root);
     System.out.println(userconnected.getIdu());
    }
    
      @FXML
    private void link10(ActionEvent event) 
    {
     
           UserService u= new UserService();
   user u10=  u.getUserByEmail(username.getText());

    try {
                      FXMLLoader loader1 = new FXMLLoader(getClass().getResource("modifierCompte.fxml"));
            Parent root1 = loader1.load();
            ModifierCompteController controller = loader1.getController();
            controller.setUser(username.getText());
            username.getScene().setRoot(root1);
    } catch(Exception e) {
        e.printStackTrace();
    }

    }
    
    public void setData(user user) {
   user = u1;
}
   
 public void setUserr(user user) {
        this.userr = user;
    }

   @Override
    public void initialize(URL url, ResourceBundle rb) {
                   //System.out.println("125:::"+u1);  
                    UserService u= new UserService();
   user u10=  u.getUserByEmail(username.getText());
setUserr(u10);
 username.setText(u1.getEmail());
    }
    public int userId() {
        int id =u1.getIdu();
        return id;
    }
    @FXML
    private void participer(ActionEvent event) {
        /*
        try {
              Parent page1 =  FXMLLoader.load(getClass().getResource("ParticiperJeux.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(JeuxController.class.getName()).log(Level.SEVERE, null, ex);
            }
    */
}
   
    }
 
        
   

  

   
    
  