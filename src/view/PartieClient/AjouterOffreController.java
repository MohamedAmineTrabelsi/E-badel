package view.PartieClient;

import entities.Categorie;
import entities.Marque;
import entities.article;
import entities.SCategorie;
import entities.offre;
import entities.user;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.user.CategorieCRUD;
import services.user.MarqueCRUD;
import services.user.OffreServices;
import services.user.SCategorieCRUD;

public class AjouterOffreController implements Initializable {
    
     @FXML
    private ComboBox<String> etat;
    @FXML
    private Button insert_image;
    @FXML
    private AnchorPane main;
    @FXML
    private ComboBox<Categorie> listecategorie;
    @FXML
    private TextField titre_offre;
    @FXML
    private ComboBox<SCategorie> listesouscategorie;
    @FXML
    private ComboBox<Marque> listemarque;
    @FXML
    private TextField periode_utilisation;
    @FXML
    private TextField description;
  
    @FXML
    private TextField file_path;
    @FXML
    private TextField numero_tel;
    @FXML
    private TextField produit_propose;
        @FXML
    private TextField bonus;
   
    private article articlee;
 private static user userr;
    
    OffreServices os = new OffreServices();
    
     ObservableList<SCategorie> mesSCategorie = FXCollections.observableArrayList();
    SCategorieCRUD ss = new SCategorieCRUD();

    ObservableList<Categorie> mesCategorie = FXCollections.observableArrayList();
    CategorieCRUD cc = new CategorieCRUD();
    
    ObservableList<Marque> mesMarque = FXCollections.observableArrayList();
    MarqueCRUD mm = new MarqueCRUD();

        Scene scene;
        Parent root;
        Stage stage;
    @FXML
    private Button envoyer_offre;
    @FXML
    private Button cancel;

       
         

    @FXML
     void ajouteroffre(ActionEvent event) throws IOException{
         article a=articlee;
         
         int id_article = a.getId_article();
         String titre_offree = titre_offre.getText();
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
        String numero_tell = numero_tel.getText();
        String bonuss = bonus.getText();
        String produit_proposee = produit_propose.getText();

        
         if (titre_offree == null || titre_offree.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Le Titre d'offre est vide.");
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
        
        if (bonuss == null){
            bonuss="no bonus";
        }
        
        if(numero_tell == null || numero_tell.length()!=8) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Le numéro de téléphone doit contenir 8 chiffres.");
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
       int idu = userr.getIdu();
        offre o = new offre(idu,id_article,titre_offree,categorie,scategorie,marque,produit_proposee,periode_utilisationn,etatt,descriptionn,bonuss,file_pathh,Integer.parseInt(numero_tell));
        os.ajouterOffre(o);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Votre Offre a été envoyer");
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
        	file_path.setText(fichierSelectionne.getName());
            
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
         
         setArticle(articlee);
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

    
     public void setArticle(article article) {
        this.articlee = article;
    }
     public void setUser(user user) {
        this.userr = user;
    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
       root = loader.load();
       AcceuilController secondController = loader.getController();
        secondController.setUser(userr);
   ((Node) event.getSource()).getScene().setRoot(root);
     System.out.println(userr.getIdu());
    }
}

   
    

