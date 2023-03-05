/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebadel.service;

import com.ebadel.connection.myConnection;
import com.ebadel.entity.Jeux;
import com.ebadel.entity.Participation;
import com.ebadel.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class ParticipationService implements IParticipation{

        Connection myConn = myConnection.getInstance().getConnection();

    @Override
    public void ajouterParticipation(Participation p) {
System.out.println("aa");
                try {
            String sql="INSERT INTO Participation  VALUES (null,?,?)"; 
            PreparedStatement ste= myConn.prepareStatement(sql);
            ste.setInt(1,p.getUserId());
            ste.setInt(2,p.getGameId());
            
            int rowsInserted = ste.executeUpdate();
            if (rowsInserted > 0) {
    System.out.println("A new participation was inserted successfully!");
}
        } catch (SQLException ex) {
            System.out.println(ex);
        } //To change body of generated methods, choose Tools | Templates.    }
    }
    
    @Override
    public void modifierParticipation(int id, int userId, int gameId) {
         try {
        String sql="UPDATE Participation SET id_user=?, id_jeu=? WHERE id=?";
        PreparedStatement ste= myConn.prepareStatement(sql);
        
            ste.setInt(1,userId);
            ste.setInt(2,gameId);
            ste.setInt(3,id);
            int rowsUpdated = ste.executeUpdate();
         if (rowsUpdated > 0) {
    System.out.println("An existing participation was updated successfully!");
}
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }

    @Override
    public void supprimerParticipation(int id) {
try{
 
        String sql = "DELETE FROM participation WHERE id=?";
        PreparedStatement statement = myConn.prepareStatement(sql);
        statement.setInt(1,id);
      int rowsDeleted = statement.executeUpdate();
   if (rowsDeleted > 0) {
    System.out.println("A participation was deleted successfully!");
  }
 } catch (SQLException ex) {
            System.out.println(ex);
        }    }

    @Override
    public void afficherParticipation() {
    try {
            String sql = "SELECT * FROM Participation";
 
Statement statement = myConn.createStatement();
ResultSet result = statement.executeQuery(sql);
 
int count = 0;
 
while (result.next()){
    int id_user = result.getInt("id_user");
    int id_jeu = result.getInt("id_jeu");
    
    
 
    String output = "Participation %d : %d | %d | %d ";
    System.out.println(String.format(output, ++count, id_user, id_jeu));
}
         } catch (SQLException ex) {
            System.out.println("the exception that has occured"+ex);
        }
    }

    @Override
    public List<Participation> afficherParticipat() {
 System.out.println("here 1");
      List<Participation> participations= new ArrayList<>();
      String req="SELECT * FROM Participation";
      try {
             //exec
             Statement st=myConn.createStatement();
             ResultSet resultSet= st.executeQuery(req);
             while(resultSet.next())
             {
                 System.out.println("Participation :::::"+resultSet.getString("id"));
                 //String nom, String prenom, String sexe, String date,String email, String login, String mdp, String role
                 participations.add(new Participation(Integer.valueOf(resultSet.getString("id")),Integer.valueOf(resultSet.getString("id_user")),Integer.valueOf(resultSet.getString("id_jeu"))));
             }
         } catch (SQLException ex) {
             System.out.println("the exception that has occured"+ex);
         }
      return participations;   
    }

    @Override
    public ObservableList<Participation> afficherParticipa() {
 ObservableList<Participation> listeB = FXCollections.observableArrayList();
           try {
            String sql = "SELECT * FROM Participation";
            Statement statement = myConn.createStatement();
            ResultSet result = statement.executeQuery(sql);
                    

 
    int count = 0;
    while (result.next()){
    int id=result.getInt("id");
    int id_user = result.getInt("id_user");
    int id_jeu=result.getInt("id_jeu");
 
    Participation b1=new Participation(id,id_user, id_jeu);
    listeB.add(b1);
               //String output = "boutique %d : %s | %s | %s | %s | %d | %d | %s | %s  ";
    //System.out.println(String.format(output, ++count, nom, email, lien,description,num_telephone,num_fixe,governerat,ville));
    }
    
            } catch (SQLException ex) {
            System.out.println(ex);
            }

    return listeB;
        
    }   
    public List<Integer> getAllUserIds() {
    try {
        String query = "SELECT idu FROM user";
        PreparedStatement preparedStatement = myConn.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Integer> userIds = new ArrayList<>();
        while (resultSet.next()) {
            userIds.add(resultSet.getInt("idu"));
        }
        return userIds;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

public List<Integer> getAllGameIds() {
    try {
        String query = "SELECT id_j FROM jeux";
        PreparedStatement preparedStatement = myConn.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Integer> gameIds = new ArrayList<>();
        while (resultSet.next()) {
            gameIds.add(resultSet.getInt("id_j"));
        }
        return gameIds;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
public String getEmailUser(int id)
{
    String st = null;
    ResultSet resultSet = null;
    try {
        String query = "SELECT Email from user where idu="+ id +"";
        PreparedStatement preparedStatement = myConn.prepareStatement(query);
         resultSet = preparedStatement.executeQuery();
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return String.valueOf(resultSet);
}

public User getUserById(int id) 
{
 User u=new User();
    try {
            String sql = "SELECT * FROM user where idu="+ id +"";

Statement statement = myConn.createStatement();
ResultSet rs = statement.executeQuery(sql);

int count = 0;
 
while (rs.next()){
   u.setIdu(rs.getInt(1));
                  u.setCin( rs.getString(2));
                u.setNom(rs.getString(3));
                u.setPrenom(rs.getString(4));      
                    u.setTel(rs.getString(5));
            
              u.setEmail( rs.getString(6));
               u.setPassword(rs.getString(7));
                u.setImage(rs.getString(8));
                   u.setRole(rs.getInt(9));
                u.setDateNaissance(rs.getDate(10));
                 
    
}
         } catch (SQLException ex) {
            System.out.println("the exception that has occured"+ex);
        }
    return u;
}
}

    

