/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.gui;

import Ebadel.entites.Reviews;
import Ebadel.services.ReviewLogique;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author messaoudi
 */
public class AddReviewController implements Initializable {

    @FXML
    private TextField tfComment;
   
    @FXML
    private ComboBox<String> tfArticleId;
    @FXML
    private Rating tfRating;

    /**
     * Initializes the controller class.
     */
     private ReviewLogique reviewService;
    @FXML
    private Button btnAdd;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
         ObservableList<Reviews> reviewList = FXCollections.observableArrayList();

         reviewService = new ReviewLogique();
        // Retrieve the list of reviews from the database
       
      
        
            ObservableList<String> produits = FXCollections.observableArrayList("Service client", "Service technique", "Service commercial", "Poste");
            tfArticleId.getItems().addAll(produits);
            tfArticleId.getSelectionModel().select("Service client");
        
        
    } 
    
    
    
    
    
    
     private void showReviewDetails(Reviews review) {
        if (review != null) {
        
            tfArticleId.setValue(review.getArticle_name());
            tfComment.setText(review.getComment());
            tfRating.setRating(review.getStarRating().getRating());
        } else {
            tfArticleId.setValue("");
           
            tfComment.setText("");
            tfRating.setRating(0);
        }
    }
    
    @FXML
   private void ajouterReview(ActionEvent event) {
    // Get the input values
    
    String articleIdText = tfArticleId.getValue();
    String comment = tfComment.getText();
    double rating = tfRating.getRating();

    // Validate the input values
    if ( articleIdText.isEmpty() || comment.isEmpty()) {
        // Show an error message if any of the required fields are empty
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText("Please fill in all the required fields.");
        alert.showAndWait();
        return;
    }

   

    if (comment.length() > 500) {
        // Show an error message if the comment exceeds 500 characters
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a comment that is no longer than 500 characters.");
        alert.showAndWait();
        return;
    }

    if (rating < 0 || rating > 5) {
        // Show an error message if the rating is not between 0 and 5
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a rating between 0 and 5.");
        alert.showAndWait();
        return;
    }
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Success");
    alert.setHeaderText(null);
    alert.setContentText("Review added successfully!");
    alert.showAndWait();


    
    Reviews review = new Reviews(articleIdText,  comment, null, rating);

    reviewService.create(review);
    
  

   
  
    tfArticleId.setValue("");
    tfComment.setText("");
    tfRating.setRating(0);
}
    
}
