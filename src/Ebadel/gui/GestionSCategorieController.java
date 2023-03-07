/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.gui;

import Ebadel.entites.Categorie;
import Ebadel.entites.SCategorie;
import Ebadel.services.CategorieCRUD;
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
public class GestionSCategorieController implements Initializable {

    @FXML
    private TextField txtSC;
    @FXML
    private ComboBox<String> LCategorie;
    @FXML
    private Button btnAjout;
    @FXML
    private TableView<SCategorie> table_s;
    private TableColumn<SCategorie, Integer> id_s_c;
    @FXML
    private TableColumn<SCategorie, String> nom_c;
    @FXML
    private TableColumn<SCategorie, String> nom_s_c;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsupprime;
    @FXML
    private Button btnNavM;
    @FXML
    private Button btnNavC;
    @FXML
    private Button stat1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LCategorie.setItems(getCategorieList());

        showSCategorie();
    }

    ObservableList<SCategorie> mesSCategorie = FXCollections.observableArrayList();
    SCategorieCRUD ss = new SCategorieCRUD();

    @FXML
    private void ajoutersouscategorie(ActionEvent event) {
        String categnom = LCategorie.getValue();
        String sc_name = txtSC.getText();
        SCategorieCRUD aa = new SCategorieCRUD();
        if (sc_name.isEmpty()) {
// Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible d'ajouter la sous-catégorie");
            alert.setContentText("Le nom de la sous-catégorie est vide !");
            alert.showAndWait();
        } else if (categnom == null) {
// Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible d'ajouter la sous-catégorie");
            alert.setContentText("La sous-catégorie est obligatoire !");
            alert.showAndWait();
        } else if (sc_name.length() < 3) {
            // Afficher un message d'erreur à l'utilisateur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible d'ajouter la sous-catégorie");
            alert.setContentText("Le nom de la sous-catégorie doit contenir au moins 3 caractères !");
            alert.showAndWait();
            return;
        } else if (!sc_name.matches("^[a-zA-Z0-9\\-\\_\\s]+$")) {
            // Afficher un message d'erreur à l'utilisateur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible d'ajouter la sous-catégorie");
            alert.setContentText("Le nom de la sous-catégorie ne doit contenir que des lettres, des chiffres, des espaces, des tirets et des underscores !");
            alert.showAndWait();
            return;
        } 
         else {
            SCategorie sc = new SCategorie(categnom, sc_name);
            aa.ajouterSCategorie(sc);
            showSCategorie();
        }
    }

    public ObservableList<String> getCategorieList() {
        ObservableList<String> obscateg = FXCollections.observableArrayList();
        CategorieCRUD categ = new CategorieCRUD();
        ObservableList<Categorie> categBase = categ.afficherCategorie();
        for (int i = 0; i < categBase.size(); i++) {
            obscateg.add(categBase.get(i).getNom_c());
        }

        return obscateg;
    }

    public void showSCategorie() {
        mesSCategorie = ss.afficherSCategorie();
        table_s.setItems(mesSCategorie);
        nom_c.setCellValueFactory(new PropertyValueFactory<SCategorie, String>("nom_c"));
        nom_s_c.setCellValueFactory(new PropertyValueFactory<SCategorie, String>("nom_s_c"));
    }

    @FXML
    private void modifiersouscategorie(ActionEvent event) {
        SCategorie selectedSCategorie = table_s.getSelectionModel().getSelectedItem();
        String newNomSCategorie = txtSC.getText();
        if (selectedSCategorie == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de modifier la sous-catégorie");
            alert.setContentText("Veuillez sélectionner une sous-catégorie à modifier !");
            alert.showAndWait();
        } else if (newNomSCategorie.isEmpty()) {
            // Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de modifier la sous-catégorie");
            alert.setContentText("Le nom de la sous-catégorie est vide !");
            alert.showAndWait();
        } else {
            boolean success = ss.modifierSCategorie(selectedSCategorie, newNomSCategorie, selectedSCategorie.getNom_c());
            if (!success) {
                // Afficher un message d'erreur
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Impossible de modifier la sous-catégorie");
                alert.setContentText("La sous-catégorie existe déjà dans la base de données !");
                alert.showAndWait();
            } else {
                // Actualiser la liste des sous-catégories affichée dans le TableView
                showSCategorie();
            }
        }
    }

    @FXML
    private void supprimersouscategorie(ActionEvent event) {
        SCategorie selectedSCategorie = table_s.getSelectionModel().getSelectedItem();
        if (selectedSCategorie == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de supprimer la sous-catégorie");
            alert.setContentText("Veuillez sélectionner une sous-catégorie à supprimer !");
            alert.showAndWait();
        } else {
            ss.supprimerSCategorie(selectedSCategorie);
            showSCategorie();
        }
    }

    @FXML
    private void naviguervers_marque(ActionEvent event) {
        try {
            // Charger la nouvelle scène FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionMarque.fxml"));
            Parent root = loader.load();
            // Définir la nouvelle racine de la scène actuelle
            ((Node) event.getSource()).getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void naviguervers_categorie(ActionEvent event) {
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
    ProduitCRUD p = new ProduitCRUD();

    @FXML
    private void afficherStatistiqueS(ActionEvent event) {
        PieDataset datasett = createDatasett();
        JFreeChart chartt = createChart(datasett);
        ChartViewer viewerr = new ChartViewer(chartt);
        Scene scenee = new Scene(viewerr);
        Stage primaryStage = new Stage();
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);
        primaryStage.setTitle("Statistique du nombre des produits par sous-categorie");
        primaryStage.setScene(scenee);
        primaryStage.show();
    }

    private PieDataset createDatasett() {
        DefaultPieDataset datasett = new DefaultPieDataset();
        for (SCategorie scategorie : ss.afficherSCategorie()) {
            datasett.setValue(scategorie.getNom_s_c(), p.getnbProdParSCateg(scategorie.getNom_s_c()));

        }
        return datasett;
    }

    private static JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "", dataset, false, true, false);
        chart.setBackgroundPaint(Color.LIGHT_GRAY);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setOutlineVisible(false);
        plot.setSectionPaint("Informatiques", Color.BLUE);
        /*plot.setSectionPaint("Voiture", Color.BLUE);
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
