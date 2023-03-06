/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.PartieClient;

import entities.article;
import entities.user;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import services.user.ArticleServices;

/**
 * FXML Controller class
 *
 * @author allal
 */

public class AcceuilController implements Initializable {
    
     @FXML
    private BorderPane bp;


    @FXML
    private TableView<article> tablearticles;

    @FXML
    private TableColumn<article, String> nom_article;

    @FXML
    private TableColumn<article, String> categorie;

    @FXML
    private TableColumn<article, String> sous_categorie;

    @FXML
    private TableColumn<article, String> marque;
    @FXML
    private Button offre;
    @FXML
    private Button details;
    @FXML
    private Button ajouter_article;
    @FXML
    private ImageView imagee;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Button review;
    
    private AjouterOffreController AjouterOffreController ;
    @FXML
    private Button retourner;

 private static user userr;
 
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherArticles();
        setUser(userr);

    } 
    
  
  ArticleServices as = new ArticleServices();

    public void afficherArticles(){
  
    ObservableList<article> tablearticless = FXCollections.observableArrayList();
    tablearticless = as.afficherArticles(); 
            
    nom_article.setCellValueFactory(new PropertyValueFactory<>("nom_article")); 
    categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
    sous_categorie.setCellValueFactory(new PropertyValueFactory<>("sous_categorie"));
    marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
    tablearticles.setItems(tablearticless);
} 

     @FXML
    public void afficherDetails(){
         if(tablearticles.getSelectionModel().getSelectedItem() !=null){
        article ar=as.afficherArticleDetails(tablearticles.getSelectionModel().getSelectedItem().getId_article());
        String articleDetails =ar.toString();       
    Popup popup = new Popup();
    popup.setAutoHide(true);
    popup.setX(500);
    popup.setY(500);
    Label label = new Label(articleDetails);
    label.setStyle( "-fx-background-color: #f2f2f2;"+
    "-fx-border-color: #d9d9d9;"+
    "-fx-border-width: 1px;"+
    "-fx-padding: 10px;"+
    "-fx-font-size: 14px;"+
    "-fx-font-weight: bold;"
            );
    popup.getContent().add(label);
    popup.show(tablearticles.getScene().getWindow(), 300, 200);
    popup.setAnchorX(tablearticles.getScene().getWindow().getX() + tablearticles.getLayoutX() + tablearticles.getWidth() / 2 - popup.getWidth() / 3);
    popup.setAnchorY(tablearticles.getScene().getWindow().getY() + tablearticles.getLayoutY() + tablearticles.getHeight() / 2 - popup.getHeight() / 3);
    popup.getContent().add(label);
    popup.show(tablearticles.getScene().getWindow());
}
         else
         {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText("vous devez sélectionner un article!");
        alert.showAndWait();
         }
         }
    
     @FXML
     public void switchToAjouterArticle(ActionEvent event) throws IOException {
 Stage stage;
 Scene scene;
 Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterArticle.fxml"));
       root = loader.load();
       AjouterArticleController secondController = loader.getController();
        secondController.setUser(userr);
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
     System.out.println(userr.getIdu());
 }

    @FXML
    public void switchToAjouterOffre(ActionEvent event) throws IOException {
         if(tablearticles.getSelectionModel().getSelectedItem() !=null){
             article selectedarticle =as.afficherArticleDetails(tablearticles.getSelectionModel().getSelectedItem().getId_article());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterOffre.fxml"));
         Parent root = loader.load();
         AjouterOffreController secondController = loader.getController();
        secondController.setArticle(selectedarticle);
        
        AjouterOffreController secondController2 = loader.getController();
        secondController2.setUser(userr);
        
   ((Node) event.getSource()).getScene().setRoot(root);
     System.out.println(userr.getIdu());

}
       else
         {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText("vous devez sélectionner un article!");
        alert.showAndWait();
         }
         }  

public void setData(article a) {
   a = as.afficherArticleDetails(tablearticles.getSelectionModel().getSelectedItem().getId_article());
}

    @FXML
    private void retournerr(ActionEvent event) throws IOException {

 Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientPartie.fxml"));
       root = loader.load();
       ClientPartieController secondController = loader.getController();
        secondController.setUserr(userr);
   ((Node) event.getSource()).getScene().setRoot(root);
     System.out.println(userr.getIdu());

    }
  public void setUser(user user) {
        this.userr = user;
    }
}