/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import Ebadel.entites.Categorie;
import Ebadel.services.CategorieCRUD;
import Ebadel.services.ProduitCRUD;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.text.DecimalFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
public class GestionCategorieController implements Initializable {

    @FXML
    private TextField txtcategorie;

    @FXML
    private TableView<Categorie> table;

    @FXML
    private TableColumn<Categorie, String> nom_c;

    @FXML
    private Button btn_ajout;

    @FXML
    private Button btn_modif;

    @FXML
    private Button btn_supprime;

    ObservableList<Categorie> mesCategorie = FXCollections.observableArrayList();

    CategorieCRUD cc = new CategorieCRUD();
    @FXML
    private Button btnNavSC;
    @FXML
    private Button btnback;
    @FXML
    private Button btnstat;


    @FXML
    void ajoutercategorie(ActionEvent event) {
        String c_name = txtcategorie.getText();
        if (c_name.isEmpty()) {
// Afficher un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible d'ajouter la catégorie");
            alert.setContentText("Le nom de la catégorie est vide !");
            alert.showAndWait();
        } else if (c_name.length() < 3) {
// Afficher un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible d'ajouter la catégorie");
            alert.setContentText("Le nom de la catégorie doit contenir au moins 3 caractères.");
            alert.showAndWait();
        } else if (!c_name.matches("^[a-zA-Z0-9\\-\\_\\s]+$")) {
// Afficher un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible d'ajouter la catégorie");
            alert.setContentText("Le nom de la catégorie ne doit contenir que des lettres, des chiffres, des espaces, des tirets et des underscores.");
            alert.showAndWait();
        } else {
            Categorie c = new Categorie(c_name);
            boolean success = cc.ajouterCategorie(c);
            if (!success) {
// Afficher un message d'erreur
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Impossible d'ajouter la catégorie");
                alert.setContentText("La catégorie existe déjà dans la base de données !");
                alert.showAndWait();
            } else {
// Actualiser la liste des catégories affichée dans le TableView
                showCategorie();
            }
        }
    }

    @FXML
    void modifiercategorie(ActionEvent event) {
        Categorie selectedCategorie = table.getSelectionModel().getSelectedItem();
        String newNomCategorie = txtcategorie.getText();
        if (selectedCategorie == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de modifier la catégorie");
            alert.setContentText("Veuillez sélectionner une catégorie à modifier !");
            alert.showAndWait();
        } else if (newNomCategorie.isEmpty()) {
            // Afficher un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de modifier la catégorie");
            alert.setContentText("Le nom de la catégorie est vide !");
            alert.showAndWait();
        } else {
            boolean success = cc.modifierCategorie(selectedCategorie, newNomCategorie);
            if (!success) {
                // Afficher un message d'erreur
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Impossible de modifier la catégorie");
                alert.setContentText("La catégorie existe déjà dans la base de données !");
                alert.showAndWait();
            } else {
                // Actualiser la liste des catégories affichée dans le TableView
                showCategorie();
            }
        }
    }

    @FXML
    void supprimercategorie(ActionEvent event) {
        Categorie selectedCategorie = table.getSelectionModel().getSelectedItem();
        if (selectedCategorie == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de supprimer la catégorie");
            alert.setContentText("Veuillez sélectionner une catégorie à supprimer !");
            alert.showAndWait();
        } else {
            cc.supprimerCategorie(selectedCategorie);
            showCategorie();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCategorie();
        // Ajouter un EventHandler sur le TableView pour écouter le clic sur une ligne
        table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Double clic
                Categorie selectedCategorie = table.getSelectionModel().getSelectedItem();
                if (selectedCategorie != null) {
                    txtcategorie.setText(selectedCategorie.getNom_c());
                }
            }
        });
    }

    public void showCategorie() {
        mesCategorie = cc.afficherCategorie();
        table.setItems(mesCategorie);
        nom_c.setCellValueFactory(new PropertyValueFactory<Categorie, String>("nom_c"));
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

    @FXML
    private void goback(ActionEvent event) {
    }
    ProduitCRUD p = new ProduitCRUD();

    @FXML
    private void afficherStatistiqueC(ActionEvent event) {
        PieDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartViewer viewer = new ChartViewer(chart);
        Scene scene = new Scene(viewer);
        Stage primaryStage = new Stage();
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);
        primaryStage.setTitle("Statistique du nombre des produits par categorie");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Categorie categorie : cc.afficherCategorie()) {
            dataset.setValue(categorie.getNom_c(), p.getnbProdParCateg(categorie.getNom_c()));

        }
        return dataset;
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
        // Custom labels https://stackoverflow.com/a/17507061/230513
        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
                "{0}: {2}", new DecimalFormat("0"), new DecimalFormat("0.0%"));
        plot.setLabelGenerator(gen);
        return chart;
    }
}
