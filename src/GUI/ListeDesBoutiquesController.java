package GUI;

import Entités.Boutique;
import Services.BoutiqueServices;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ListeDesBoutiquesController implements Initializable{
     

    @FXML
    private TableColumn<Boutique, String> Gcolumn;
     

    @FXML
    private TableColumn<Boutique, String> Vcolumn;

    @FXML
    private Button btn1;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnupdate;

    @FXML
    private TableColumn<Boutique, String> desccolumn;

    @FXML
    private TableColumn<Boutique, String> emailcolumn;

    @FXML
    private TableColumn<Boutique, String> liencolumn;

    @FXML
    private TableColumn<Boutique, String> nomcolumn;

    @FXML
    private TableColumn<Boutique, String> numFcolumn;

    @FXML
    private TableColumn<Boutique, String> numTcolumn;

    @FXML
    private TableView<Boutique> table;

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtdesc;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtg;

    @FXML
    private TextField txtlien;

    @FXML
    private TextField txtnumf;

    @FXML
    private TextField txtnumt;

    @FXML
    private TextField txtv;
    @FXML
    private Button btn11;
    
    

    @FXML
    void Créer(ActionEvent event) {
        try {
              Parent page1 = FXMLLoader.load(getClass().getResource("../GUI/ajouterboutique.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ListeDesBoutiquesController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
 
    @FXML
    void Modifier(ActionEvent event) {
        String nom,email,lien,desc,governerat,ville,num_t , num_f;
      
    /*nom=txtNom.getText();
    email=txtemail.getText(); 
    lien = txtlien.getText(); 
    desc=txtdesc.getText();
    num_t = txtnumt.getText();
    num_f = txtnumf.getText();
    governerat=txtg.getText();
    ville=txtv.getText();*/
    
    Boutique selectedLN =  table.getSelectionModel().getSelectedItem();
    if (selectedLN == null) {
        // Afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Impossible de modifier la boutique ");
        alert.setContentText("Veuillez sélectionner une boutique à modifier !");
        alert.showAndWait();
    }
    
    else if (txtNom.getText().isEmpty()|| txtemail.getText().isEmpty()|| txtlien.getText().isEmpty()|| txtdesc.getText().isEmpty()||txtnumt.getText().isEmpty()||txtnumf.getText().isEmpty()||txtg.getText().isEmpty()||txtv.getText().isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Impossible de modifier la boutique");
        alert.setContentText("Les champs la boutiques sont vide !");
        alert.showAndWait();
    }
    /*else if (isValid(email)) {
         Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("information");
        alert.setHeaderText(null);
        alert.setContentText("entrer une adresse email valide!");
    }*/
        
    else{
        /*nom=selectedLN.getNom();
        email=selectedLN.getEmail();
        lien=selectedLN.getLien();
        desc=selectedLN.getDescription();
        num_t=selectedLN.getNum_telephone();
        num_f=selectedLN.getNum_fixe();
        governerat=selectedLN.getGovernerat();
        ville=selectedLN.getVille();*/
        selectedLN.setNom(txtNom.getText());
        selectedLN.setEmail(txtemail.getText());
        selectedLN.setLien(txtlien.getText());
        selectedLN.setDescription(txtdesc.getText());
        selectedLN.setNum_telephone(txtnumt.getText());
        selectedLN.setNum_fixe(txtnumf.getText());
        selectedLN.setGovernerat(txtg.getText());
        selectedLN.setVille(txtv.getText());
        BoutiqueServices bs = new BoutiqueServices();
        bs.modifierBoutique(selectedLN);
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("information");
        alert1.setHeaderText(null);
        alert1.setContentText("Boutique modfiée!");
        alert1.showAndWait();
        table.refresh();
    }
    
    }

    
    
    @FXML
    void Supprimer(ActionEvent event) {
        Boutique selectedLN =  table.getSelectionModel().getSelectedItem();
         if (selectedLN == null) {
        // Afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Impossible de supprimer la boutique ");
        alert.setContentText("Veuillez sélectionner une boutique à supprimer !");
        alert.showAndWait();
    }
        
       //System.out.println(selectedLN.getId_b());
       BoutiqueServices bs = new BoutiqueServices();
        bs.supprimerBoutique(selectedLN);
        show();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("information");
        alert.setHeaderText(null);
        alert.setContentText("Boutique suprimée!");
        alert.showAndWait();
    }
    public void show(){
        ObservableList<Boutique> listeB = FXCollections.observableArrayList();
        BoutiqueServices bs=new BoutiqueServices();
        listeB=bs.afficherBoutiques();
        nomcolumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        emailcolumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        liencolumn.setCellValueFactory(new PropertyValueFactory<>("lien"));
        desccolumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        numTcolumn.setCellValueFactory(new PropertyValueFactory<>("num_telephone"));
        numFcolumn.setCellValueFactory(new PropertyValueFactory<>("num_fixe"));
        Gcolumn.setCellValueFactory(new PropertyValueFactory<>("governerat"));
        Vcolumn.setCellValueFactory(new PropertyValueFactory<>("ville"));
        table.setItems(listeB);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
    show();
}

    @FXML
    private void watch(ActionEvent event) {
        try {
              Parent page1 =  FXMLLoader.load(getClass().getResource("../GUI/Videoboutique.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AjouterboutiqueController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
   
}

