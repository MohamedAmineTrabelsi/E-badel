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
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
public class AccueilController implements Initializable {

    @FXML
    private ComboBox<Categorie> LCategorie;
    @FXML
    private ComboBox<SCategorie> LSCategorie;
    @FXML
    private ComboBox<Marque> LMarque;
    @FXML
    private Button btnaller;
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
    private Button stat;
    @FXML
    private TextField txtprod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCategListe();
        LCategorie.valueProperty().addListener((obs, oldVal, newVal) -> {
            // Handle value change here
            initSCategListe(newVal.toString());
        });
        LSCategorie.valueProperty().addListener((obs, oldVal, newValS) -> {
            // Handle value change here
            initMarqueListe(newValS.toString());
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
    private void afficherStatistique(ActionEvent event) {
        PieDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartViewer viewer = new ChartViewer(chart);
        Scene scene = new Scene(viewer);
        Stage primaryStage = new Stage();
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);
        primaryStage.setTitle("Statistique Categorie");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void chercherProduitParNom(ActionEvent event) {
        p.getProduitParTitre(txtprod.getText());
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
