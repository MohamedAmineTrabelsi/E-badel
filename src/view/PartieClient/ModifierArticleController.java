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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.user.CategorieCRUD;
import services.user.MarqueCRUD;
import services.user.SCategorieCRUD;

/**
 * FXML Controller class
 *
 * @author allal
 */

public class ModifierArticleController implements Initializable {
    
     
     private static user userr;
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
    private Button btnAjout;
    @FXML
    private Button insert_image;
    @FXML
    private TextField file_path;
    @FXML
    private ImageView image_view;

     private article articlee;
    @FXML
    private Button cancel;
    
    
     private Scene scene;
 private Parent root; 
 private Stage stage;
    
       ObservableList<SCategorie> mesSCategorie = FXCollections.observableArrayList();
    SCategorieCRUD ss = new SCategorieCRUD();

    ObservableList<Categorie> mesCategorie = FXCollections.observableArrayList();
    CategorieCRUD cc = new CategorieCRUD();
    
    ObservableList<Marque> mesMarque = FXCollections.observableArrayList();
    MarqueCRUD mm = new MarqueCRUD();
     
    
    /**
     * Initializes the controller class.
     */
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
        setArticle(articlee);
        System.out.println(articlee.toString());
        setArticleAttributs();


        
        
    }    
     public void setUser(user user) {
        this.userr = user;
    }

  
    @FXML
    private void modifierarticle(ActionEvent event) {
    }

    @FXML
    private void insertImage(ActionEvent event) {
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

    
     public void setArticle(article article) {
        this.articlee = article;
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
    private void cancel(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("MesArticles.fxml"));
       Parent root = loader.load();
       MesArticlesController secondController = loader.getController();
        secondController.setUser(userr);
   ((Node) event.getSource()).getScene().setRoot(root);
     System.out.println(userr.getIdu());
        
    }
     public void setArticleAttributs(){
         nom_article.setText("sfvfbfbf");
        /*
        nom_article.setText(articleSelected.getNom_article());
        //listecategorie.setValue(articlee.getCategorie());
         //listesouscategorie.setValue(articlee.getSous_categorie());
        //listemarque.setValue(articlee.getMarque());
        periode_utilisation.setText(articleSelected.getPeriode_dutilisation());
        etat.setValue(articleSelected.getEtat());
        description.setText(articleSelected.getDescription());
        file_path.setText(articleSelected.getImage());
        String picture ="file:" + articleSelected.getImage().toString();

        Image image = new Image(picture, 110, 110, false, true);

        image_view.setImage(image);
        */
        
        
    }
}

