/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebadel.gui;

import com.ebadel.connection.myConnection;
import com.ebadel.service.JeuxInteService;
import com.ebadel.entity.Jeux;
import com.ebadel.service.JeuxService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author achou
 */
public class JeuxController implements Initializable {
    
    Connection myConn = myConnection.getInstance().getConnection();
    
    JeuxInteService jeuxInterface;
    
      @FXML
    private ListView<String> ListViewJeux;

    @FXML
    private Button addM;
    
        @FXML
    private Button triJeux;

    @FXML
    private TextField date_deb_j;

    @FXML
    private TextField date_fin_j;

    @FXML
    private Button deleteM;


    @FXML
    private TextField nb_per;

    @FXML
    private TextField titre_j;

    @FXML
    private TextField type_j;
    
        @FXML
    private TextField prix;
        
    private TextField image_p;

    @FXML
    private Button updateMateriel;

    int id_select;
    
    JeuxService pservice= new JeuxService();

    @FXML
    private Button btnv;
    @FXML
    private TextField txtURL;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
      List<Jeux> jeux = pservice.afficherJeu();
      
      ListViewJeux.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
          @Override
          public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
              
              
              String Selected = ListViewJeux.getSelectionModel().getSelectedItem();
              System.out.println("the selected of the line "+Selected);
              
              id_select = Integer.valueOf(ListViewJeux.getSelectionModel().getSelectedItem().substring(5,9).split(" ")[1]);
              System.out.println("the selected id loula :"+id_select);
              try{
                  
              String query = "SELECT * fROM Jeux WHERE id_j =?";
              PreparedStatement pst = myConn.prepareStatement(query);
              pst.setInt(1,id_select);
              ResultSet rs = pst.executeQuery();
              
              
              while(rs.next()){
                  nb_per.setText(rs.getString("nb_per"));
                  titre_j.setText(rs.getString("titre_j"));
                  type_j.setText(rs.getString("type_j"));
                  date_deb_j.setText(rs.getString("date_deb_j"));
                  date_fin_j.setText(rs.getString("date_fin_j"));
                  image_p.setText(rs.getString("image_p"));
                  prix.setText(rs.getString("prix"));
                  
              }
              pst.close();
              rs.close();
              
              }catch(Exception e){ System.out.println(e); }
              
              
              
              
          
          }
      });
      
      for(int i=0;i<jeux.size();i++){
          String viewMateriel= "Id :  "+jeux.get(i).getId_j()+"    nb_per "+ jeux.get(i).getNb_per()+"    type_j :  "+ jeux.get(i).getType_j()+"    titre_j :  "+ jeux.get(i).getTitre_j()+"    date_deb_j :  "+ jeux.get(i).getDate_deb_j()+"    date_fin_j :  "+ jeux.get(i).getDate_fin_j()+"    image_p "+ jeux.get(i).getImage_p()+"    prix "+ jeux.get(i).getPrix();
          ListViewJeux.getItems().add(viewMateriel);
      }
      
      
    }
        // TODO
    
    @FXML
    void addMateriel(MouseEvent event) {
         Jeux jeux =new Jeux();
        
        Jeux produit =new Jeux();
        
        
        if(((nb_per.getText().isEmpty()) == true)
               
                || (type_j.getText().isEmpty() == true)
                || (titre_j.getText().isEmpty() == true) ||
                (date_deb_j.getText().isEmpty() == true)||
                 (date_fin_j.getText().isEmpty() == true))
              
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("champs non validés");
            alert.setContentText("Verifiez vos champs svp!!");
            alert.showAndWait();
        }
        else if((nb_per.getText().matches(".*[a-zA-z].*")))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Qeulques champs accepte que les nombres");
            alert.setContentText("Verifiez vos champs svp!!");
            alert.showAndWait();
        }
        
        else{
        jeux.setNb_per(Integer.parseInt(nb_per.getText()));
        jeux.setTitre_j(titre_j.getText());
        jeux.setType_j(type_j.getText());
        jeux.setDate_deb_j(date_deb_j.getText());
        jeux.setDate_fin_j(date_fin_j.getText());
        String[] split_list = txtURL.getText().split("\\\\");
        String image = "/img/"+split_list[split_list.length-1];
        jeux.setImage_p(image);
        jeux.setPrix(Integer.parseInt(prix.getText()));
        
        








        pservice.ajouterJeux(jeux);
        List<Jeux> jeuxList = pservice.afficherJeu();
        ListViewJeux.getItems().clear();
        for(int i=0;i<jeuxList.size();i++){
          String viewString= "Id :  "+jeuxList.get(i).getId_j()+"    nb_per "+ jeuxList.get(i).getNb_per()+"   titre_j :  "+ jeuxList.get(i).getTitre_j()+"    type_j :  "+ jeuxList.get(i).getType_j()+"    date_deb_j :  "+ jeuxList.get(i).getDate_deb_j()+"    date_fin_j :  "+ jeuxList.get(i).getDate_fin_j()+"image :  "+jeuxList.get(i).getImage_p()+"    prix "+ jeuxList.get(i).getPrix();
          ListViewJeux.getItems().add(viewString);
        }}
    }

    @FXML
    void deleteMateriel(MouseEvent event) {
        
        Jeux jeux =new Jeux();
          
      

       pservice.supprimerJeux(id_select);
       List<Jeux> jeuxList = pservice.afficherJeu();
       ListViewJeux.getItems().clear();
       for(int i=0;i<jeuxList.size();i++){
          String viewString= "Id :  "+jeuxList.get(i).getId_j()+"    nb_per "+ jeuxList.get(i).getNb_per()+"    titre_j :  "+ jeuxList.get(i).getTitre_j()+"    type_j :  "+ jeuxList.get(i).getType_j()+"    date_deb_j :  "+ jeuxList.get(i).getDate_deb_j()+"    date_fin_j :  "+ jeuxList.get(i).getDate_fin_j()+"image :  "+jeuxList.get(i).getImage_p()+"    prix "+ jeuxList.get(i).getPrix();
          
          ListViewJeux.getItems().add(viewString);
      }

    }

    @FXML
    void updateMateriel(MouseEvent event) {
        
        Jeux jeux =new Jeux();
    if(((nb_per.getText().isEmpty()) == true)
                || (titre_j.getText().isEmpty() == true)
                || (type_j.getText().isEmpty() == true) ||
                (date_deb_j.getText().isEmpty() == true)||
                        (date_fin_j.getText().isEmpty() == true))
        {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Repetez svp");
            alert.setHeaderText("champs non validés");
            alert.setContentText("Verifiez vos champs svp!!");
            alert.showAndWait();
        }
    else{
        jeux.setNb_per(Integer.parseInt(nb_per.getText()));
        jeux.setTitre_j(titre_j.getText());
        jeux.setType_j(type_j.getText());
        jeux.setDate_deb_j(date_deb_j.getText());
        jeux.setDate_fin_j(date_fin_j.getText());
        String[] split_list = txtURL.getText().split("\\\\");
        String image = "/img/"+split_list[split_list.length-1];
        jeux.setImage_p(image);
        jeux.setPrix(Integer.parseInt(prix.getText()));

        pservice.modifierJeux(id_select,Integer.valueOf(jeux.getNb_per()),jeux.getTitre_j(),jeux.getType_j(),jeux.getDate_deb_j(),jeux.getDate_fin_j(),jeux.getImage_p(),Integer.valueOf(jeux.getPrix()));
        List<Jeux> jeuxList = pservice.afficherJeu();
       ListViewJeux.getItems().clear();
       for(int i=0;i<jeuxList.size();i++){
          String viewString= "Id :  "+jeuxList.get(i).getId_j()+"    nb_per "+ jeuxList.get(i).getNb_per()+"    titre_j :  "+ jeuxList.get(i).getTitre_j()+"    type_j :  "+ jeuxList.get(i).getType_j()+"    date_deb_j :  "+ jeuxList.get(i).getDate_deb_j()+"    date_fin_j :  "+ jeuxList.get(i).getDate_fin_j() +"image :  "+jeuxList.get(i).getImage_p()+ "prix "+ jeuxList.get(i).getPrix();
          
          ListViewJeux.getItems().add(viewString);
      }
    }

    }

    @FXML
    private void video(ActionEvent event) throws IOException {
try {
              Parent page1 =  FXMLLoader.load(getClass().getResource("../gui/Video.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(JeuxController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void importer(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename =f.getAbsolutePath();
        txtURL.setText(filename);
    }

    
}
