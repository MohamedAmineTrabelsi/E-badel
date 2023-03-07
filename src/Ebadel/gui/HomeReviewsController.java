/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.gui;

import Ebadel.entites.Reviews;
import Ebadel.services.ReviewLogique;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author messaoudi
 */
public class HomeReviewsController implements Initializable {

    @FXML
    private TableView<Reviews> table_review;
   
    @FXML
    private TableColumn<Reviews, String> col_article;
    @FXML
    private TableColumn<Reviews, Double> col_rate;
    @FXML
    private TableColumn<Reviews, String> col_comment;

    /**
     * Initializes the controller class.
     */
      private ReviewLogique reviewService;
      Reviews rate = new Reviews();
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         ObservableList<Reviews> reviewList = FXCollections.observableArrayList();

         reviewService = new ReviewLogique();
        // Retrieve the list of reviews from the database
        reviewList = reviewService.getAllReviews();
        table_review.setItems(reviewList);
       
      

        col_article.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getArticle_name()));
        col_comment.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getComment()));
        col_rate.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getRate()));
        col_rate.setCellFactory(column -> new TableCell<Reviews, Double>() {
    @Override
    protected void updateItem(Double rating, boolean empty) {
        super.updateItem(rating, empty);
        if (empty || rating == null) {
            setText("");
        } else {
            Rating ratingControl = new Rating();
            ratingControl.setRating(rating);
            setGraphic(ratingControl);
        }
    }
});

      //  table_review.getSelectionModel().selectedItemProperty().addListener(
        //        (observable, oldValue, newValue) -> showReviewDetails(newValue));

    } 

    @FXML
    private void supprimerReview(ActionEvent event) {
        
        
         
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Confirmation");
          alert.setHeaderText("Are you sure you want to delete Review?");
           Optional<ButtonType> result = alert.showAndWait();
             if (result.isPresent() && result.get() == ButtonType.OK) {
    // Delete the selected reclamation
      Reviews review = getSelectedReview();
      if (review != null) {
        reviewService.supprimerReview(review.getArticle_name());
        showReviews();
    }
    }
    }
    
     private Reviews getSelectedReview() {
        Reviews review = null;
        ObservableList<Reviews> reviews = reviewService.getAllReviews();
        int selectedIndex = table_review.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < reviews.size()) {
            review = reviews.get(selectedIndex);
        }
        return review;
    }
     
     public void showReviews(){
      
    
        col_article.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getArticle_name()));
        col_comment.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getComment()));
        col_rate.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getRate()));
        col_rate.setCellFactory(column -> new TableCell<Reviews, Double>() {
    @Override
    protected void updateItem(Double rating, boolean empty) {
        super.updateItem(rating, empty);
        if (empty || rating == null) {
            setText("");
        } else {
            Rating ratingControl = new Rating();
            ratingControl.setRating(rating);
            setGraphic(ratingControl);
        }
    }
    });

     ObservableList<Reviews> reviewList = FXCollections.observableArrayList();

         reviewService = new ReviewLogique();
        // Retrieve the list of reviews from the database
        reviewList = reviewService.getAllReviews();
        table_review.setItems(reviewList);}
     
     

    @FXML
    private void modifierReview(ActionEvent event) {
        Reviews selectedReview = table_review.getSelectionModel().getSelectedItem();
        
        if (selectedReview == null) {
       Alert alert = new Alert(Alert.AlertType.WARNING);
       alert.setTitle("Warning");
       alert.setHeaderText("No review selected");
       alert.setContentText("Please select a review to update");
       alert.showAndWait();
    return;
}
    
        
         Dialog<Reviews> dialog = new Dialog<>();
    dialog.setTitle("Modify Review");

    // Set the button types
    ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

    // Create the grid pane 
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));

    // Add the controls to the grid pane
    TextField articleNameField = new TextField(selectedReview.getArticle_name());
    TextArea commentArea = new TextArea(selectedReview.getComment());
    Rating ratingControl = new Rating();
    ratingControl.setRating(selectedReview.getRate());
    grid.add(new Label("Article Name:"), 0, 0);
    grid.add(articleNameField, 1, 0);
    grid.add(new Label("Comment:"), 0, 1);
    grid.add(commentArea, 1, 1);
    grid.add(new Label("Rate:"), 0, 2);
    grid.add(ratingControl, 1, 2);

    // Enable/disable save button depending on whether the article name field is empty
    Node saveButton = dialog.getDialogPane().lookupButton(saveButtonType);
    saveButton.setDisable(true);
    articleNameField.textProperty().addListener((observable, oldValue, newValue) -> {
    saveButton.setDisable(newValue.trim().isEmpty());
});

    // Set the dialog content
    dialog.getDialogPane().setContent(grid);

    // Request focus on the article name field by default
    Platform.runLater(() -> articleNameField.requestFocus());

    // Convert the result to a review object when the save button is clicked
    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == saveButtonType) {
            Reviews modifiedReview = new Reviews();
            modifiedReview.setArticle_name(articleNameField.getText());
            modifiedReview.setComment(commentArea.getText());
            modifiedReview.setRate(ratingControl.getRating());
        return modifiedReview;
    }
    return null;
});

Optional<Reviews> result = dialog.showAndWait();
if (result.isPresent()) {
    Reviews modifiedReview = result.get();
    // Call the ReviewLogique's modifierReview method to update the selected review in the database
    reviewService.modifierReview( modifiedReview.getArticle_name(), modifiedReview.getComment(), modifiedReview.getRate());
    // Refresh the table view with the updated reviews
    showReviews();
}

     
       
    }

    // ...
}

  
    
        
        
        
    
 

