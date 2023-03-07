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
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class GestionMarqueController implements Initializable {

    @FXML
    private ComboBox<Categorie> Lcategorie;
    @FXML
    private ComboBox<SCategorie> LSCategorie;
    @FXML
    private TextField txt_m;
    @FXML
    private TableView<Marque> table_m;
    @FXML
    private TableColumn<Marque, String> nom_m;
    @FXML
    private TableColumn<Marque, String> nom_c;
    @FXML
    private TableColumn<Marque, String> nom_s_c;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsupprime;

    ObservableList<SCategorie> mesSCategorie = FXCollections.observableArrayList();
    SCategorieCRUD ss = new SCategorieCRUD();

    ObservableList<Categorie> mesCategorie = FXCollections.observableArrayList();
    CategorieCRUD cc = new CategorieCRUD();
    @FXML
    private Button btnNavC;
    @FXML
    private Button btnNavSC;
    @FXML
    private Button stat11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCategListe();
        Lcategorie.valueProperty().addListener((obs, oldVal, newVal) -> {
            // Handle value change here
            initSCategListe(newVal.toString());
        });
        showMarque();
    }
    ObservableList<Marque> mesMarque = FXCollections.observableArrayList();
    MarqueCRUD mm = new MarqueCRUD();

    public void showMarque() {
        mesMarque = mm.afficherMarque();
        table_m.setItems(mesMarque);
        nom_m.setCellValueFactory(new PropertyValueFactory<Marque, String>("nom_m"));
        nom_c.setCellValueFactory(new PropertyValueFactory<Marque, String>("nom_c"));
        nom_s_c.setCellValueFactory(new PropertyValueFactory<Marque, String>("nom_s_c"));
    }

    @FXML
    private void ajoutermarque(ActionEvent event) {
// Get the selected category and subcategory
        Categorie categnom = Lcategorie.getValue();
        SCategorie scategnom = LSCategorie.getValue();

        // Vérifie si une catégorie et une sous-catégorie ont été sélectionnées
        if (categnom == null || scategnom == null) {
            // Affiche un message d'erreur à l'utilisateur
            Alert alert = new Alert(AlertType.ERROR, "Veuillez sélectionner une catégorie et une sous-catégorie.");
            alert.showAndWait();
            return;
        }

        // Récupère le nom de la marque entré par l'utilisateur
        String m_name = txt_m.getText();

        // Vérifie si le nom de la marque est vide
        if (m_name.trim().isEmpty()) {
            // Affiche un message d'erreur à l'utilisateur
            Alert alert = new Alert(AlertType.ERROR, "Veuillez entrer un nom de marque.");
            alert.showAndWait();
            return;
        }
// Vérifie si le nom de la marque est trop court
        if (m_name.length() < 3) {
            // Affiche un message d'erreur à l'utilisateur
            Alert alert = new Alert(AlertType.ERROR, "Le nom de la marque doit contenir au moins 3 caractères.");
            alert.showAndWait();
            return;
        }

// Vérifie si le nom de la marque contient des caractères invalides
        if (!m_name.matches("^[a-zA-Z0-9\\-\\_\\s]+$")) {
            // Affiche un message d'erreur à l'utilisateur
            Alert alert = new Alert(AlertType.ERROR, "Le nom de la marque ne doit contenir que des lettres, des chiffres, des espaces, des tirets et des underscores.");
            alert.showAndWait();
            return;
        }

        // Crée un nouvel objet Marque avec le nom de la marque entré par l'utilisateur
        Marque m = new Marque(m_name, categnom.getNom_c(), scategnom.getNom_s_c());

        // Appelle la méthode pour ajouter la marque à la base de données
        MarqueCRUD mm = new MarqueCRUD();
        mm.ajouterMarque(m);

        // Rafraîchit la vue pour afficher la liste mise à jour des marques
        showMarque();
    }

    @FXML
    private void modifiermarque(ActionEvent event) {
        Marque selectedMarque = table_m.getSelectionModel().getSelectedItem();
        String newNomMarque = txt_m.getText();
        if (selectedMarque == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de modifier la marque");
            alert.setContentText("Veuillez sélectionner une marque à modifier !");
            alert.showAndWait();
        } else if (newNomMarque.isEmpty()) {
            // Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de modifier la marque");
            alert.setContentText("Le nom de la marque est vide !");
            alert.showAndWait();
        } else {
            boolean success = mm.modifierMarque(selectedMarque, newNomMarque, selectedMarque.getNom_c(), selectedMarque.getNom_s_c());
            if (!success) {
                // Afficher un message d'erreur
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Impossible de modifier la marque");
                alert.setContentText("La marque existe déjà dans la base de données !");
                alert.showAndWait();
            } else {
                // Actualiser la liste des marque affichée dans le TableView
                showMarque();
            }
        }
    }

    @FXML
    private void supprimermarque(ActionEvent event) {
        Marque selectedMarque = table_m.getSelectionModel().getSelectedItem();
        if (selectedMarque == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de supprimer la marque");
            alert.setContentText("Veuillez sélectionner une marque à supprimer !");
            alert.showAndWait();
        } else {
            mm.supprimerMarque(selectedMarque);
            showMarque();
        }
    }

    public void initCategListe() {
        mesCategorie = cc.afficherCategorie();
        Lcategorie.setItems(mesCategorie);
    }

    public void initSCategListe(String a) {
        mesSCategorie = ss.afficherSCategorieParCategorie(a);
        LSCategorie.setItems(mesSCategorie);
    }

    @FXML
    private void naviguervers_categ(ActionEvent event) {
        try {
            // Charger la nouvelle scène FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionCategorie.fxml"));
            Parent root = loader.load();
            // Définir la nouvelle racine de la scène actuelle
            ((Node) event.getSource()).getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void naviguervers_scateg(ActionEvent event) {
        try {
            // Charger la nouvelle scène FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionSCategorie.fxml"));
            Parent root = loader.load();
            // Définir la nouvelle racine de la scène actuelle
            ((Node) event.getSource()).getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    ProduitCRUD p = new ProduitCRUD();

    @FXML
    private void afficherStatistiqueM(ActionEvent event) {
        PieDataset datasettt = createDatasettt();
        JFreeChart charttt = createChart(datasettt);
        ChartViewer viewerrr = new ChartViewer(charttt);
        Scene sceneee = new Scene(viewerrr);
        Stage primaryStage = new Stage();
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);
        primaryStage.setTitle("Statistique du nombre des produits par marque");
        primaryStage.setScene(sceneee);
        primaryStage.show();

    }

    private PieDataset createDatasettt() {
        DefaultPieDataset datasettt = new DefaultPieDataset();
        for (Marque marque : mm.afficherMarque()) {
            datasettt.setValue(marque.getNom_m(), p.getnbProdParMarque(marque.getNom_m()));

        }
        return datasettt;
    }

    private static JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "", dataset, false, true, false);
        chart.setBackgroundPaint(Color.LIGHT_GRAY);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setOutlineVisible(false);
        plot.setSectionPaint("Informatiques", Color.BLUE);
        /*  plot.setSectionPaint("Voiture", Color.BLUE);
        plot.setSectionPaint("Immobilier", Color.GREEN);
        plot.setSectionPaint("Logiaze", Color.YELLOW);
        plot.setSectionPaint("Esqqs", Color.CYAN);*/
        plot.setLabelFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        //Custom labels https://stackoverflow.com/a/17507061/230513
        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
                "{0}: {2}", new DecimalFormat("0"), new DecimalFormat("0.0%"));
        plot.setLabelGenerator(gen);
        return chart;
    }
}
