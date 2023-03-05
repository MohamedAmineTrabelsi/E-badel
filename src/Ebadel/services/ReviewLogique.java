/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.services;


import Ebadel.entites.Reviews;
import Ebadel.utils.myConnection;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import org.controlsfx.control.Rating;

/**
 *
 * @author messaoudi
 */






public class ReviewLogique {
    
    Connection myConn =null;
    
   
  /* public List<Reviews> getAllReviews() throws SQLException {
         List<Reviews> reviews = new ArrayList<>();
           myConn = myConnection.getInstance().getConnection();
         
      
          
         ObservableList<Reviews> listreviews =FXCollections.observableArrayList();
        
        
   try {
        Statement statement = myConn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT reviews.id, reviews.comment, reviews.rating, " +
                "reviews.article_id, reviews.user_id from reviews " +
                "INNER JOIN article ON reviews.article_id = article.id "+
                "INNER JOIN user ON reviews.user_id = user.id");

            while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String comment = resultSet.getString("comment");
            int rating = resultSet.getInt("rating");
            int articleId = resultSet.getInt("article_id");
            int userId = resultSet.getInt("user_id");
            

            Reviews review = new Reviews( articleId, userId, comment, rating);
            listreviews.add(review);
             }}
    catch (SQLException ex){
            System.out.println(ex);
            
        }
        
        

       

        return listreviews;
    }

    
*/

    public void create(Reviews r) {
        
          myConn = myConnection.getInstance().getConnection();
        try {
            String query = "INSERT INTO reviews (user_id, article_id, comment,rate) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStmt = myConn.prepareStatement(query);
            preparedStmt.setInt(1, r.getUser_id());
            preparedStmt.setInt(2, r.getArticle_id());
            preparedStmt.setString(3, r.getComment());
            preparedStmt.setDouble(4, r.getRate());
            
           
           
            preparedStmt.execute();
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de la review : " + e.getMessage());
        }
    }

  /*  public void modifierReview(int review_id, int user_id, int article_id, String comment, Rating starRating) {
        try {
            String query = "UPDATE reviews SET user_id = ?, article_id = ?, comment = ?, star_rating = ? WHERE id = ?";
            PreparedStatement preparedStmt = myConn.prepareStatement(query);
            preparedStmt.setInt(1, user_id);
            preparedStmt.setInt(2, article_id);
            preparedStmt.setString(3, comment);
            preparedStmt.setInt(4, (int) starRating.getRating());
            preparedStmt.setInt(5, review_id);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification de la review : " + e.getMessage());
        }
    }*/

  /*  public void supprimerReview(int review_id) {
        try {
            String query = "DELETE FROM reviews WHERE id = ?";
            PreparedStatement preparedStmt = myConn.prepareStatement(query);
            preparedStmt.setInt(1, review_id);
            preparedStmt.execute();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de la review : " + e.getMessage());
        }
    }*/

    public ObservableList<Reviews> getAllReviews() {
            ObservableList<Reviews> reviewList = FXCollections.observableArrayList();
               myConn = myConnection.getInstance().getConnection();
            try {
            String query = "SELECT * FROM reviews";
            Statement stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                int article_id = rs.getInt("article_id");
                String comment = rs.getString("comment");
                Double rate = rs.getDouble("rate");
               

                Reviews review = new Reviews( user_id, article_id,comment,null,rate);
                reviewList.add(review);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'affichage des reviews : " + e.getMessage());
        }

        return reviewList;
    }


   
   
   
   
   
   
   
    
    
    
    
    }
