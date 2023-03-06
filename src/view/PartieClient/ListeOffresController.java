/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.PartieClient;

import entities.article;
import entities.offre;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import services.user.OffreServices;

/**
 * FXML Controller class
 *
 * @author allal
 */
public class ListeOffresController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private TableColumn<offre,String> categorie;
    @FXML
    private TableColumn<offre,String> sous_categorie;
    @FXML
     private TableColumn<offre,String>marque;

    @FXML
    private Button details;
    @FXML
    private ImageView imagee;
    @FXML
    private Button retourner;
    @FXML
    private TableView<offre> tableoffres;
    @FXML
    private TableColumn<offre,String> titre_offre;
    @FXML
    private TableColumn<offre,String>produit_propose;
    @FXML
    private Button gethelp;
    @FXML
    private Button accepter;
    @FXML
    private Button refuser;
    
      private article articlee;
      private static user userr;
      
      Scene scene;
        Parent root;
        Stage stage;
   

    /**
     * Initializes the controller class.
     */
   
  
    
      OffreServices os = new OffreServices();

      public void afficherOffres() throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MesArticles.fxml"));
       root = loader.load();
       MesArticlesController secondController = loader.getController();
       article ar = new article();
        ar= secondController.getSelectedArticle();
      int ida=  ar.getId_article();

    ObservableList<offre> tableoffre = FXCollections.observableArrayList();
    tableoffre = os.afficherListeDesoffres(ida);
    titre_offre.setCellValueFactory(new PropertyValueFactory<>("titre")); 
    produit_propose.setCellValueFactory(new PropertyValueFactory<>("produit_propose")); 
    categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
    sous_categorie.setCellValueFactory(new PropertyValueFactory<>("sous_categorie"));
    marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
    tableoffres.setItems(tableoffre);

    }
    
    
    @FXML
    private void retournerr(ActionEvent event) throws IOException {
     
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MesArticles.fxml"));
       root = loader.load();
       MesArticlesController secondController = loader.getController();
        secondController.setUser(userr);
   ((Node) event.getSource()).getScene().setRoot(root);
     System.out.println(userr.getIdu());
    }

    @FXML
    private void gethelp(ActionEvent event) {
    }

    @FXML
    private void details(ActionEvent event) {
         if(tableoffres.getSelectionModel().getSelectedItem() !=null){
        offre of=tableoffres.getSelectionModel().getSelectedItem();
        String offreDetails =of.toString();       
    Popup popup = new Popup();
    popup.setAutoHide(true);
    popup.setX(500);
    popup.setY(500);
    Label label = new Label(offreDetails);
    label.setStyle( "-fx-background-color: #f2f2f2;"+
    "-fx-border-color: #d9d9d9;"+
    "-fx-border-width: 1px;"+
    "-fx-padding: 10px;"+
    "-fx-font-size: 14px;"+
    "-fx-font-weight: bold;"
            );
    popup.getContent().add(label);
    popup.show(tableoffres.getScene().getWindow(), 300, 200);
    popup.setAnchorX(tableoffres.getScene().getWindow().getX() + tableoffres.getLayoutX() + tableoffres.getWidth() / 2 - popup.getWidth() / 3);
    popup.setAnchorY(tableoffres.getScene().getWindow().getY() + tableoffres.getLayoutY() + tableoffres.getHeight() / 2 - popup.getHeight() / 3);
    popup.getContent().add(label);
    popup.show(tableoffres.getScene().getWindow());
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
    private void refuser(ActionEvent event) {
    }
    
      @FXML
    private void accepter(ActionEvent event) {
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

      setUser(userr);
      setArticle(articlee);
        try {
            // System.out.println(userr.toString());
            //System.out.println(articlee.toString());
            afficherOffres();
        } catch (IOException ex) {
            Logger.getLogger(ListeOffresController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    } 
       public void setUser(user user) {
        this.userr = user;
    }
    public void setArticle(article article) {
        this.articlee = article;
    }

  
    
}
