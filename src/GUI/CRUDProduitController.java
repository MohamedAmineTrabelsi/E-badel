package GUI;

import Entités.Produit;
import Services.ProduitServices;
import java.awt.Insets;
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javax.swing.JFileChooser;
import ConnectionBD.myConnection;
import java.sql.Connection;

public class CRUDProduitController implements Initializable{
    ProduitServices ps1=new ProduitServices();
              
     ObservableList<String> liste = ps1.filecombBox();

    @FXML
    private Button btnAdd;

    @FXML
    private TextField txtTitre;

    @FXML
    private TextField txtdecs;

    @FXML
    private TextField txtprix;
    //private TextField txtNum;
    @FXML
    private ComboBox<String> boutique ;
     
    @FXML
    private TableColumn<Produit, String> titrecolumn;
    @FXML
    private TableColumn<Produit, String> prixcolumn;
    @FXML
    private TableColumn<Produit, String> desccolumn;
    @FXML
    private TableColumn<Produit, String> nomBcolumn;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsupp;
    @FXML
    private TableView<Produit> tableP;
    @FXML
    private TableColumn<Produit, String> etatcolumn;
    @FXML
    private TextField txtetat;
    @FXML
    private TextField txturl;
    @FXML
    private TextField stat;
    
    Connection myConn = myConnection.getInstance().getConnection();
             

    @FXML
    void ajouterProduit(ActionEvent event) {
        String titre,prix,description,etat,image;
        titre=txtTitre.getText();
        prix=txtprix.getText(); 
        description = txtdecs.getText();
        
         if (txtTitre.getText().isEmpty()|| txtprix.getText().isEmpty()|| txtdecs.getText().isEmpty() ){
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
         Produit p2 = new Produit();
         p2.setTitre(txtTitre.getText());
         p2.setPrix(txtprix.getText());
         p2.setDescription(txtdecs.getText());
         String nomboutique = boutique.getSelectionModel().getSelectedItem();
         p2.setNom_boutique(nomboutique);
         p2.setEtat(txtetat.getText());
         String[] split_list = txturl.getText().split("\\\\");
         image = "/img/"+split_list[split_list.length-1];
         p2.setImage_p(image);
         ps1.ajouterProduit(p2);
         //Produit p2 = new Produit(titre,prix,description,nomboutique,etat,image);
         Alert alert = new Alert(AlertType.INFORMATION);
         alert.setContentText("produit ajouté !");
         alert.showAndWait();
    
    }
    

    @FXML
    private void boutiquecomb(ActionEvent event) {
        ObservableList<String> nomB = FXCollections.observableArrayList();
        nomB.addAll(ps1.selectnomB());
        boutique.setItems(nomB);
}

    @FXML
    private void modifierProduit(ActionEvent event) {
         Produit selectedProduit = tableP.getSelectionModel().getSelectedItem();
         if (selectedProduit == null) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setContentText("Veuillez sélectionner un produit à modifier !");
        alert.showAndWait();
        return;
    }
    txtTitre.setText(selectedProduit.getTitre());
    txtprix.setText(selectedProduit.getPrix());
    txtdecs.setText(selectedProduit.getDescription());
    txtetat.setText(selectedProduit.getEtat());
    
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Modifier le produit");
    dialog.setHeaderText(null);
     GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.add(new Label("Titre :"), 0, 0);
    grid.add(txtTitre, 1, 0);
    grid.add(new Label("Prix :"), 0, 1);
    grid.add(txtprix, 1, 1);
    grid.add(new Label("Description :"), 0, 2);
    grid.add(txtdecs, 1, 2);
    grid.add(new Label("Etat :"), 0, 3);
    grid.add(txtetat, 1, 3);
    dialog.getDialogPane().setContent(grid);
    ButtonType buttonAnnuler = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
    ButtonType buttonEnregistrer = new ButtonType("Enregistrer", ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(buttonAnnuler, buttonEnregistrer);
    Optional<ButtonType> result = dialog.showAndWait();
    if (result.isPresent() && result.get() == buttonEnregistrer) {
        // Mettre à jour les données de l'utilisateur sélectionné
        selectedProduit.setTitre(txtTitre.getText());
        selectedProduit.setPrix(txtprix.getText());
        selectedProduit.setDescription(txtdecs.getText());
        selectedProduit.setEtat(txtetat.getText());
        
        ProduitServices ps = new ProduitServices();
        ps.modifierProduit(selectedProduit);
        tableP.refresh();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("produit modifié !");
        alert.showAndWait();
        
    }
    
   
    }

    @FXML
    private void supprimerProduit(ActionEvent event) {
         Produit selectedLN =  tableP.getSelectionModel().getSelectedItem();
          if (selectedLN == null) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setContentText("Veuillez sélectionner un produit à supprimer !");
        alert.showAndWait();
        return;
    }
       //System.out.println(selectedLN.getId_b());
        ProduitServices ps1=new ProduitServices();
        ps1.supprimerProduit(selectedLN);
        show();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("information");
        alert.setHeaderText(null);
        alert.setContentText("produit suprimé!");
        alert.showAndWait();
        tableP.refresh();
        
    }
     @Override
    public void initialize(URL location, ResourceBundle resources) {
        
               boutique.setItems(liste);
               show();
               
       
    }
    
     public void show(){
        ObservableList<Produit> listeP = FXCollections.observableArrayList();
        ProduitServices ps = new ProduitServices();
        listeP=ps.afficherProduits();
        titrecolumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        prixcolumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        desccolumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        nomBcolumn.setCellValueFactory(new PropertyValueFactory<>("nom_boutique"));
        etatcolumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tableP.setItems(listeP);
        tableP.refresh();
        
    }

    @FXML
    private void upload(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename =f.getAbsolutePath();
        txturl.setText(filename);
    }

    @FXML
    private void Stat(ActionEvent event) {
         try {
            ResultSet rs= myConn.createStatement().executeQuery("SELECT AVG(etat='bonne') AS t FROM `produit`");
            while(rs.next())
            {
            float s=rs.getFloat("t")*100;
            String s1=Float.toString(s);
            stat.setText(s1);
            
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        
        
          
    }
     
    

}

   