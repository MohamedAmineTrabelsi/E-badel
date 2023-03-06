/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.PartieClient;


import entities.Categorie;
import entities.Marque;
import entities.SCategorie;
import entities.article;
import entities.user;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.user.ArticleServices;
import services.user.CategorieCRUD;
import services.user.MarqueCRUD;
import services.user.SCategorieCRUD;



/**
 * FXML Controller class
 *
 * @author allal
 */
public class AjouterArticleController implements Initializable {
    
    
    @FXML
    private AnchorPane interface_aa;
   
    @FXML
    private ComboBox<Categorie> listecategorie;

    @FXML
    private TextField nom_article;

    @FXML
    private ComboBox<SCategorie> listesouscategorie;

    @FXML
    private ComboBox<Marque> listemarque;

    @FXML
    private TextField periode_utilisation;

    @FXML
    private ComboBox<String> etat;

    @FXML
    private TextField description;


    
    @FXML
    private TextField file_path;

    @FXML
    private Button insert_image;
    
    @FXML
    private Button btnAjout;
    @FXML
    private ImageView image_view;
    
 
 private Scene scene;
 private Parent root; 
 private Stage stage;
 private static user userr;
    
    ObservableList<SCategorie> mesSCategorie = FXCollections.observableArrayList();
    SCategorieCRUD ss = new SCategorieCRUD();

    ObservableList<Categorie> mesCategorie = FXCollections.observableArrayList();
    CategorieCRUD cc = new CategorieCRUD();
    
    ObservableList<Marque> mesMarque = FXCollections.observableArrayList();
    MarqueCRUD mm = new MarqueCRUD();
    @FXML
    private Button returnacceuil;
 
    
  

    @FXML
     void ajouterarticle(ActionEvent event) throws IOException{
        String nom_articlee = nom_article.getText();
         String categorie;
         String scategorie;
         String marque;
        if (listecategorie.getValue().getNom_c() ==null){
             categorie = "categorie";
        }else
        {
        categorie =listecategorie.getValue().getNom_c();
        }
        if(listesouscategorie.getValue().getNom_s_c()==null){
             scategorie= "scategorie"; 
        }else {
        scategorie =listesouscategorie.getValue().getNom_s_c();
        }
        if(listemarque.getValue().getNom_m() ==null){
            marque = "marque";
        }else {
            marque =listemarque.getValue().getNom_m();
        }
        String periode_utilisationn = periode_utilisation.getText();
        String etatt =etat.getValue();
        String descriptionn = description.getText();
        
        String file_pathh = file_path.getText();
        ArticleServices as =new ArticleServices();
        
         if (nom_articlee == null || nom_articlee.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Le nom de l'article est vide.");
            alert.showAndWait();
            return;
        }

        if (categorie.equals("categorie")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("La catégorie n'est pas sélectionnée.");
            alert.showAndWait();
            return;
        }

        if (scategorie.equals("scategorie")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("La sous catégorie n'est pas sélectionnée.");
            alert.showAndWait();
            return;
        }

        if (marque.equals("marque")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("La marque  n'est pas sélectionnée.");
            alert.showAndWait();
            return;
        }

        if (periode_utilisationn == null|| periode_utilisationn.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("La période d'utilisation est invalide.");
            alert.showAndWait();
            return;
        }

        if (etatt == null || etatt.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("L'état n'est pas sélectionné.");
            alert.showAndWait();
            return;
        }

        if (descriptionn == null|| descriptionn.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("La description est vide.");
            alert.showAndWait();
            return;
        }

        if (file_pathh == null || file_pathh.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Le chemin de l'image est vide.");
            alert.showAndWait();
            return;
        }
else 
      {
          int idu =userr.getIdu();
        article a =new article(idu,nom_articlee, categorie,scategorie,marque,periode_utilisationn,etatt,descriptionn,file_pathh);
        as.ajouterArticle(a);
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Votre article a été inséré");
        alert.showAndWait();
         
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
       root = loader.load();
       AcceuilController secondController = loader.getController();
        secondController.setUser(userr);
   ((Node) event.getSource()).getScene().setRoot(root);
     System.out.println(userr.getIdu());
     }
     }
     
        @FXML
   public void insertImage(){
       
         FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Sélectionnez un fichier PNG");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp", "*.ico","*.jfif"));
    File fichierSelectionne = fileChooser.showOpenDialog(stage);

    if (fichierSelectionne != null) {
        try {
            String imagePath = fichierSelectionne.toURI().toURL().toString();
            Image image = new Image(imagePath);
            image_view.setImage(image);
            file_path.setText(fichierSelectionne.getName());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
       
       
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> etats = FXCollections.observableArrayList("Comme neuf", "Très bon", "Bon" , "Acceptable", "Mauvais");
        etat.getItems().addAll(etats);
        
        initCategListe();
        listecategorie.valueProperty().addListener((obs, oldVal, newVal) -> {
          
            initSCategListe(newVal.toString());
        });
        listesouscategorie.valueProperty().addListener((obs, oldVal, newValS) -> {
        
            initMarqueListe(newValS.toString());
        });
      setUser(userr);
    }
    
      public void initCategListe() {
        mesCategorie = cc.afficherCategorie();
        listecategorie.setItems(mesCategorie);
    }

    public void initSCategListe(String a) {
        mesSCategorie = ss.afficherSCategorieParCategorie(a);
        listesouscategorie.setItems(mesSCategorie);
    }

    public void initMarqueListe(String a) {
        mesMarque = mm.afficherMarqueParSCategorie(a);
        listemarque.setItems(mesMarque);
    }

    @FXML
    private void returnAcceuil(ActionEvent event) throws IOException {
  
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
       root = loader.load();
       AcceuilController secondController = loader.getController();
        secondController.setUser(userr);
   ((Node) event.getSource()).getScene().setRoot(root);
     System.out.println(userr.getIdu());
  
    }
    
     public void setUser(user user) {
        this.userr = user;
    }
    
}
