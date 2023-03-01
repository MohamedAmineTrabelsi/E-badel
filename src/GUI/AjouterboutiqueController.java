package GUI;
import Entités.Boutique;
import Entités.Produit;
import Services.BoutiqueServices;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
public class AjouterboutiqueController implements  Initializable{

    @FXML
    private Label nom;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextField urlTF;
    @FXML
    private ComboBox<String> Gouv;
    @FXML
    private ComboBox<String> Ville;
    
    //private final String[] Gouvernorat = {"Monastir", "Sousse", "Nabeul", "Bizert "};
      //Gouv = new ComboBox<>(FXCollections.observableArrayList(values));
       private boolean isValid(String email) {
        String EMAIL_PATTERN = 
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
      private boolean isvalid(String num){
           //String phonenumberstr = Integer.toString(num);
           if(num.length()!=8){
               return false;
           }
           for (int i =0; i<num.length();i++){
               if(!Character.isDigit(num.charAt(i))){
                   return false;
           }
           }
           return !"12345678".equals(num);
 
    }
       
       
      @FXML
    private Button btnChoisir;
      @FXML
    private ImageView imageview;
    @FXML
    private TextField textdesc;
    private TextField txtG;

    private TextField txtV;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtlien;

    @FXML
    private TextField txtnom;

    @FXML
    private TextField txtnumF;

    @FXML
    private TextField txtnumT;
     @FXML
    void ajouter(ActionEvent event){
        boolean test=true;
        String nom,email,lien,desc,num_t ,num_f ;
        nom=txtnom.getText();
        email=txtemail.getText(); 
        lien = txtlien.getText(); 
        desc=textdesc.getText();
        num_t = txtnumT.getText();
        num_f = txtnumF.getText();
        
        
        //governerat=txtG.getText();
        //ville=txtV.getText();
       
        if (txtnom.getText().isEmpty()||  txtemail.getText().isEmpty()|| txtlien.getText().isEmpty()|| textdesc.getText().isEmpty()|| txtnumT.getText().isEmpty() || txtnumF.getText().isEmpty() || urlTF.getText().isEmpty()) {
             {
              //test=false;
              Alert alert1 = new Alert(AlertType.WARNING);
             alert1.setTitle("oops");
             alert1.setHeaderText(null);
             alert1.setContentText("remplir tous les champs");
             alert1.showAndWait();
             return;
             
                 }
        }
        
        if(isValid(email)==false) {
            test= false ; 
             Alert alert = new Alert(AlertType.ERROR);
             alert.setTitle("Invalid Email");
             alert.setHeaderText(null);
             alert.setContentText("entrer une adresse email valide");
             alert.show();
             txtemail.requestFocus();
             txtemail.selectAll();
             return;
          }  
        if((isvalid(num_t)==false)&&(isvalid(num_f))==false) {
            test= false ; 
             Alert alert1 = new Alert(AlertType.ERROR);
             alert1.setTitle("Invalid numero");
             alert1.setHeaderText(null);
             alert1.setContentText("entrer une numero valide");
             alert1.show();
             txtemail.requestFocus();
             txtemail.selectAll();
             return;
             
}

        if(test==true ){
            
            Boutique nouvelleBoutique = new Boutique();
            nouvelleBoutique.setNom(txtnom.getText());
             String[] split_list = urlTF.getText().split("\\\\");
            String image = "/img/"+split_list[split_list.length-1];
            nouvelleBoutique.setImage_b(image);
            nouvelleBoutique.setEmail(txtemail.getText());
            nouvelleBoutique.setLien(txtlien.getText());
            nouvelleBoutique.setDescription(textdesc.getText());
            nouvelleBoutique.setNum_telephone(txtnumT.getText());
            nouvelleBoutique.setNum_fixe(txtnumF.getText());
            String governerat = Gouv.getValue();
            nouvelleBoutique.setGovernerat(governerat);
            String ville = Ville.getValue();
            nouvelleBoutique.setVille(ville);
            BoutiqueServices bs = new BoutiqueServices();
            bs.ajouterBoutique(nouvelleBoutique);
            Alert alert2 = new Alert(AlertType.CONFIRMATION);
            alert2.setTitle("Information Dialog");
            alert2.setHeaderText(null);
            alert2.setContentText("Boutique insérée avec succés!");
            alert2.show();
             Stage boutiqueInfoStage = new Stage();
             BorderPane root = new BorderPane();
             VBox boutiqueInfoBox = new VBox();
             Button creerProduitButton = new Button();
             creerProduitButton.setText("gérer votre boutique");
            ImageView boutiqueImageView = new ImageView(new Image("file:///C:/Users/asus/OneDrive/Pictures/Ebadel.png"));
      
            Label nomBoutiqueLabel = new Label("Nom de la boutique : " + nouvelleBoutique.getNom());
            Label numeroTelephoneLabel = new Label("Numéro de téléphone : " + nouvelleBoutique.getNum_telephone());
            boutiqueInfoBox.getChildren().addAll(boutiqueImageView, nomBoutiqueLabel, numeroTelephoneLabel);
            root.setLeft(boutiqueImageView);
            root.setBottom(boutiqueInfoBox);
            root.setRight(creerProduitButton);
            Scene boutiqueScene = new Scene(root);
            boutiqueInfoStage.setScene(boutiqueScene);
            boutiqueInfoStage.showAndWait();
            creerProduitButton.setPrefWidth(100);
            creerProduitButton.setPrefHeight(50);
            
            creerProduitButton.setOnAction(actionEvent -> {
                try {
                Parent page2 =  FXMLLoader.load(getClass().getResource("../GUI/gérerBoutique.fxml"));
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AjouterboutiqueController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
           
           
        });
  
            }
             /*try {
              Parent page1 =  FXMLLoader.load(getClass().getResource("../GUI/gérerBoutique.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AjouterboutiqueController.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }
    
    

    
      /*void choisir(ActionEvent event) {
      /*FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Choisir une image");
       // Filtrage des fichiers pour n'afficher que les images
            FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter(
                   "Images", "*.png", "*.jpg", "*.gif");
          boolean add = fileChooser.getExtensionFilters().add(imageFilter);
          File selectedFile = fileChooser.showOpenDialog(btnChoisir.getScene().getWindow());
         if (selectedFile != null) {
                Image selectedImage = new Image(selectedFile.toURI().toString());
                 imageview.setImage(selectedImage);
                 
        
    }*/
    
    
        
        
        
    
     
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    ObservableList<String> gouvernorat = FXCollections.observableArrayList("Monastir", "Sousse", "Bizert" , "Nabeul", "Ariana");
    ObservableList<String> Villes = FXCollections.observableArrayList("Sahline", "Sahloul", "Hammemt","charguia");
     Gouv.getItems().addAll(gouvernorat);
     Ville.getItems().addAll(Villes);
     
     
     
    }

    @FXML
    private void importer(ActionEvent event) {
        
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename =f.getAbsolutePath();
        urlTF.setText(filename);
    }

  
}
