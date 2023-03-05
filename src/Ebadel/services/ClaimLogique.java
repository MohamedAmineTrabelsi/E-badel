/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.services;

import Ebadel.entites.Reclamations;
import Ebadel.utils.myConnection;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author messaoudi
 * 
 */


public class ClaimLogique {
    
    
     Connection myConn =null;
    
    
    
    public void addReclamation(Reclamations reclamation) {
          myConn = myConnection.getInstance().getConnection();
        String query = "INSERT INTO reclamation (contact, destinataire, type, statut, description, date) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = myConn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, reclamation.getContact());
            preparedStatement.setString(2, reclamation.getDestinataire());
            preparedStatement.setString(3, reclamation.getType());
            preparedStatement.setString(4, reclamation.getStatut());
            preparedStatement.setString(5, reclamation.getDescription());
            preparedStatement.setDate(6, Date.valueOf(reclamation.getDate()));
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                reclamation.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public ObservableList<Reclamations> getAllReclamations() {
         myConn = myConnection.getInstance().getConnection();
         ObservableList<Reclamations> reclamations = FXCollections.observableArrayList();
    try (
         Statement stmt = myConn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM reclamation")) {
        while (rs.next()) {
            Reclamations reclamation = new Reclamations(
                    rs.getString("contact"),
                    rs.getString("destinataire"),
                    rs.getString("type"),
                    rs.getString("statut"),
                    rs.getString("description"),
                    rs.getDate("date").toLocalDate()
            );
            reclamations.add(reclamation);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return reclamations;
}
    
    public void deleteReclamation(String contact) {
        String query = "DELETE FROM reclamation WHERE contact=?";
        try (PreparedStatement preparedStatement = myConn.prepareStatement(query)) {
            preparedStatement.setString(1, contact);
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
       
    
}
