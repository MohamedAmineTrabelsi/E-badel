/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.gui;

import Ebadel.entites.Categorie;
import Ebadel.entites.Marque;
import Ebadel.entites.SCategorie;
import Ebadel.services.CategorieCRUD;
import Ebadel.services.MarqueCRUD;
import Ebadel.services.ProduitCRUD;
import Ebadel.services.SCategorieCRUD;
//import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import javafx.scene.input.MouseEvent;
//import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
//import javax.print.attribute.standard.Media;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class AccueilController implements Initializable {

    @FXML
    private ComboBox<Categorie> LCategorie;
    @FXML
    private ComboBox<SCategorie> LSCategorie;
    @FXML
    private ComboBox<Marque> LMarque;
    @FXML
    private Button btnchercher;

    ObservableList<SCategorie> mesSCategorie = FXCollections.observableArrayList();
    SCategorieCRUD ss = new SCategorieCRUD();

    ObservableList<Categorie> mesCategorie = FXCollections.observableArrayList();
    CategorieCRUD cc = new CategorieCRUD();

    ObservableList<Marque> mesMarque = FXCollections.observableArrayList();
    MarqueCRUD mm = new MarqueCRUD();
    ProduitCRUD p = new ProduitCRUD();
    @FXML
    private TextField txtprod;
    @FXML
    private ImageView btnchercherN;
    @FXML
    private ImageView btnactualiser;
    @FXML
    private ImageView logo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCategListe();
        LCategorie.valueProperty().addListener((obs, oldVal, newVal) -> {
            // Handle value change here
            if (newVal != null) {
                initSCategListe(newVal.toString());
            }
        });
        LSCategorie.valueProperty().addListener((obs, oldVal, newValS) -> {
            // Handle value change here
            if (newValS != null) {
                initMarqueListe(newValS.toString());
            }
        });
        // Ajouter un écouteur pour la touche Entrée sur le TextField txtprod
        txtprod.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                chercherProduitParNom(null);
            }
        });
        btnchercherN.setOnMouseClicked(e -> {
            chercherProduitParNom(null);
        });
        btnactualiser.setOnMouseClicked(e -> {
            ResetFilter(null);
        });
    }

    public void initCategListe() {
        mesCategorie = cc.afficherCategorie();
        LCategorie.setItems(mesCategorie);
    }

    public void initSCategListe(String a) {
        mesSCategorie = ss.afficherSCategorieParCategorie(a);
        LSCategorie.setItems(mesSCategorie);
    }

    public void initMarqueListe(String a) {
        mesMarque = mm.afficherMarqueParSCategorie(a);
        LMarque.setItems(mesMarque);
    }

    @FXML
    private void chercherproduit(ActionEvent event) {

        if (LCategorie.getValue() == null
                && LSCategorie.getValue() == null
                && LMarque.getValue() == null) {
            System.out.println("aucun filtre selectionné");
            //   showProduit();
        } else if (LCategorie.getValue() != null
                && LSCategorie.getValue() == null
                && LMarque.getValue() == null) {
            p.getProduitByCateg(LCategorie.getValue().getNom_c());
            //showProduit();
        } else if (LCategorie.getValue() != null
                && LSCategorie.getValue() != null
                && LMarque.getValue() == null) {
            p.getProduitByCategandSCateg(LCategorie.getValue().getNom_c(), LSCategorie.getValue().getNom_s_c());
            //showProduit();
        } else if (LCategorie.getValue() != null
                && LSCategorie.getValue() != null
                && LMarque.getValue() != null) {
            p.getProduitByCategandSCategandMarque(LCategorie.getValue().getNom_c(), LSCategorie.getValue().getNom_s_c(), LMarque.getValue().getNom_m());
            //showProduit();
        }

    }



    @FXML
    private void chercherProduitParNom(MouseEvent event) {
        // Vérifier si le champ de texte n'est pas vide
        if (txtprod.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de rechercher le produit");
            alert.setContentText("Le nom du produit est obligatoire !");
            alert.showAndWait();
        }
        p.getProduitParTitre(txtprod.getText());
    }

    @FXML
    private void publiciter(MouseEvent event) {
        try {
            // Charger la nouvelle scène FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Musique.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle fenêtre
            Stage newStage = new Stage();
            newStage.setTitle("Publiciter");
            newStage.setScene(new Scene(root));

            // Afficher la nouvelle fenêtre
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final double SCALE_DELTA = 1.2;

    @FXML
    private void MouseExited(MouseEvent event) {
        btnactualiser.setScaleX(1.0);
        btnactualiser.setScaleY(1.0);
    }

    @FXML
    private void MouseEntered(MouseEvent event) {
        btnactualiser.setScaleX(SCALE_DELTA);
        btnactualiser.setScaleY(SCALE_DELTA);
    }

    @FXML
    private void mouseExited(MouseEvent event) {
        logo.setScaleX(1.0);
        logo.setScaleY(1.0);
    }

    @FXML
    private void mouseEntred(MouseEvent event) {
        logo.setScaleX(SCALE_DELTA);
        logo.setScaleY(SCALE_DELTA);
    }

    @FXML
    private void mouseEXchercher(MouseEvent event) {
        btnchercherN.setScaleX(1.0);
        btnchercherN.setScaleY(1.0);
    }

    @FXML
    private void mouseEcherhcer(MouseEvent event) {
        btnchercherN.setScaleX(SCALE_DELTA);
        btnchercherN.setScaleY(SCALE_DELTA);
    }

    @FXML
    private void ResetFilter(MouseEvent event) {
        LCategorie.setValue(null);
        LSCategorie.setValue(null);
        LMarque.setValue(null);
    }
}
