
package Ebadel.gui;


import Ebadel.entites.Reviews;

import Ebadel.services.ReviewLogique;
import static de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon.STAR;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author messaoudi
 */
public class ReviewController implements Initializable {
    @FXML
    private TableView<Reviews> table_review;
    @FXML
    private TableColumn<Reviews, Integer> col_user;
    @FXML
    private TableColumn<Reviews, Integer> col_article;
    
    @FXML
    private TableColumn<Reviews, String> col_comment;
   
    @FXML
    private TextField tfComment;
    @FXML
    private TableColumn<Reviews, Double> col_rate;
   
     
      
   

   
  

    
    private ReviewLogique reviewService;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField tfUserId;
    @FXML
    private TextField tfArticleId;
    @FXML
    private Rating tfRating;//ratingControl
    @FXML
    private Label msg;
    
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     
        
        ObservableList<Reviews> reviewList = FXCollections.observableArrayList();

         reviewService = new ReviewLogique();
        // Retrieve the list of reviews from the database
        reviewList = reviewService.getAllReviews();
        table_review.setItems(reviewList);
       
      

        col_article.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getArticle_id()));
        col_user.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getUser_id()));
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

        table_review.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showReviewDetails(newValue));

    
    }
    
    
    private void showReviewDetails(Reviews review) {
        if (review != null) {
            tfArticleId.setText(Integer.toString(review.getArticle_id()));
            tfUserId.setText(Integer.toString(review.getUser_id()));
            tfComment.setText(review.getComment());
            tfRating.setRating(review.getStarRating().getRating());
        } else {
            tfArticleId.setText("");
            tfUserId.setText("");
            tfComment.setText("");
            tfRating.setRating(0);
        }
    }
    
    
     @FXML
   private void ajouterReview(ActionEvent event) {
    // Get the input values
    String userIdText = tfUserId.getText();
    String articleIdText = tfArticleId.getText();
    String comment = tfComment.getText();
    double rating = tfRating.getRating();

    // Validate the input values
    if (userIdText.isEmpty() || articleIdText.isEmpty() || comment.isEmpty()) {
        // Show an error message if any of the required fields are empty
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText("Please fill in all the required fields.");
        alert.showAndWait();
        return;
    }

    int userId;
    try {
        userId = Integer.parseInt(userIdText);
        if (userId <= 0) {
            // Show an error message if the user ID is not a positive integer
            throw new NumberFormatException();
        }
    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a valid user ID.");
        alert.showAndWait();
        return;
    }

    int articleId;
    try {
        articleId = Integer.parseInt(articleIdText);
        if (articleId <= 0) {
            // Show an error message if the article ID is not a positive integer
            throw new NumberFormatException();
        }
    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a valid article ID.");
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

    // Create a new Review object with the input values
    Reviews review = new Reviews(articleId, userId, comment, null, rating);

    // Call the service method to add the new review
    reviewService.create(review);
    
   // Get the current list of reviews from the table view
    ObservableList<Reviews> reviewsList = table_review.getItems();

// Add the new review to the list
    reviewsList.add(review);

// Refresh the table view to reflect the changes
    table_review.refresh();


    // Clear the input fields after adding the review
    tfUserId.setText("");
    tfArticleId.setText("");
    tfComment.setText("");
    tfRating.setRating(0);
}

}
    
    
    
    
    

   
    

   

    

   
    

  
       
      
    

