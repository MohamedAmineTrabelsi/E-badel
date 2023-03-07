/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.PartieClient;

import entities.article;
import entities.user;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;
import javafx.stage.Stage;
import services.user.ArticleServices;

/**
 * FXML Controller class
 *
 * @author allal
 */
public class MesArticlesController implements Initializable {

    @FXML
    private TableView<article> tablearticlesParId;
    @FXML
    private TableColumn<String, article> nom_article;
    @FXML
    private TableColumn<String, article> categorie;
    @FXML
    private TableColumn<String, article>sous_categorie;
    @FXML
    private TableColumn<String, article> marque;
    @FXML
    private Button modifArticle;
    @FXML
    private Button details;
    @FXML
    private Button suppArticle;
    @FXML
    private ImageView imagee;
    @FXML
    private Button retourner;
    
    private static user userr;
    @FXML
    private Button consuleroffre;
    
     private ModifierArticleController ModifierArticleController;
     private ListeOffresController ListeOffresController;
    
    
    private article articlee =new article();
    
        Scene scene;
        Parent root;
        Stage stage;
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        try {
            afficherArticlesParId();
        } catch (IOException ex) {
            Logger.getLogger(MesArticlesController.class.getName()).log(Level.SEVERE, null, ex);
        }
       setUser(userr);
    }   
    
    ArticleServices as = new ArticleServices();
 

    public void afficherArticlesParId() throws IOException{
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientPartie.fxml"));
       root = loader.load();
       ClientPartieController secondController = loader.getController();
        int idu = secondController.userId();
        
    ObservableList<article> mestablearticless = FXCollections.observableArrayList();
    mestablearticless = as.afficherArticlesParId(idu);
    //mestablearticless = as.afficherArticles();
    nom_article.setCellValueFactory(new PropertyValueFactory<>("nom_article")); 
    categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
    sous_categorie.setCellValueFactory(new PropertyValueFactory<>("sous_categorie"));
    marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
    tablearticlesParId.setItems(mestablearticless);

    }
    


    @FXML
    private void modifArticle(ActionEvent event) throws IOException {
        
        if(tablearticlesParId.getSelectionModel().getSelectedItem() !=null){
            article selectedarticle=as.afficherArticleDetails(tablearticlesParId.getSelectionModel().getSelectedItem().getId_article());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierArticle.fxml"));
          root = loader.load();
         ModifierArticleController secondController = loader.getController();
        secondController.setArticle(selectedarticle);
        
         ModifierArticleController secondController2 = loader.getController();
        secondController2.setUser(userr);
        
   ((Node) event.getSource()).getScene().setRoot(root);
     System.out.println(userr.getIdu());
     System.out.println(selectedarticle.getNom_article());

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
    private void afficherDetails(ActionEvent event) {
        if(tablearticlesParId.getSelectionModel().getSelectedItem() !=null){
        article ar=as.afficherArticleDetails(tablearticlesParId.getSelectionModel().getSelectedItem().getId_article());
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
    popup.show(tablearticlesParId.getScene().getWindow(), 300, 200);
    popup.setAnchorX(tablearticlesParId.getScene().getWindow().getX() + tablearticlesParId.getLayoutX() + tablearticlesParId.getWidth() / 2 - popup.getWidth() / 3);
    popup.setAnchorY(tablearticlesParId.getScene().getWindow().getY() + tablearticlesParId.getLayoutY() + tablearticlesParId.getHeight() / 2 - popup.getHeight() / 3);
    popup.getContent().add(label);
    popup.show(tablearticlesParId.getScene().getWindow());
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
    private void suppArticle(ActionEvent event) throws IOException {

    if (tablearticlesParId.getSelectionModel().getSelectedItem() == null) {
 
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Impossible de supprimer l'article");
        alert.setContentText("Veuillez sélectionner un article à supprimer !");
        alert.showAndWait();
    } else {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure ?");
        alert.setContentText("cet article sera supprimé !");
        alert.showAndWait();
                article selectedarticlee = tablearticlesParId.getSelectionModel().getSelectedItem();
        as.supprimerArticle(selectedarticlee);
        afficherArticlesParId();
    }
        
    }

    @FXML
    private void retournerr(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientPartie.fxml"));
       root = loader.load();
       ClientPartieController secondController = loader.getController();
        secondController.setUserr(userr);
   ((Node) event.getSource()).getScene().setRoot(root);
     System.out.println(userr.getIdu());

    }

  public void setUser(user user) {
        MesArticlesController.userr = user;
    }

    @FXML
    private void consulterOffres(ActionEvent event) throws IOException {
        
         if(tablearticlesParId.getSelectionModel().getSelectedItem() !=null){
             article ar=as.afficherArticleDetails(tablearticlesParId.getSelectionModel().getSelectedItem().getId_article());
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeOffres.fxml"));
          root = loader.load();
   
          ListeOffresController secondController = loader.getController();
        secondController.setArticle(ar);
        
        ListeOffresController secondController2 = loader.getController();
        secondController2.setUser(userr);
        
   ((Node) event.getSource()).getScene().setRoot(root);
   System.out.println("uuuuuuuuuuuuuuuuuuu"+ar.getId_article());
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
   a = as.afficherArticleDetails(tablearticlesParId.getSelectionModel().getSelectedItem().getId_article());
}
      public article getSelectedArticle() {
        return tablearticlesParId.getSelectionModel().getSelectedItem();
    }

    private void SetArticle(article article) {
        this.articlee=article;
    }
         
    }
    
