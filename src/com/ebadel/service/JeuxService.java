/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebadel.service;

import com.ebadel.connection.myConnection;
import com.ebadel.entity.Jeux;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author achou
 */
public class JeuxService implements JeuxInteService{
    Connection myConn = myConnection.getInstance().getConnection();

    @Override
    public void ajouterJeux(Jeux p) {
        System.out.println("aa");
                try {
            String sql="INSERT INTO Jeux  VALUES (null,?,?,?,?,?,?,?)"; 
            PreparedStatement ste= myConn.prepareStatement(sql);
            ste.setInt(1,p.getNb_per());
            ste.setString(2,p.getTitre_j());
            ste.setString(3,p.getType_j());
            ste.setString(4,p.getDate_deb_j());
            ste.setString(5,p.getDate_fin_j());
            ste.setString(6,p.getImage_p());
            ste.setInt(7,p.getPrix());
            int rowsInserted = ste.executeUpdate();
            if (rowsInserted > 0) {
    System.out.println("A new Jeux was inserted successfully!");
}
        } catch (SQLException ex) {
            System.out.println(ex);
        } //To change body of generated methods, choose Tools | Templates.
    }

     @Override
    public void modifierJeux(int id_j,int nb_per, String titre_j, String type_j, String date_deb_j, String date_fin_j,String image_p,int prix) {
                 try {
        String sql="UPDATE jeux SET nb_per=?, titre_j=?, type_j=?,date_deb_j=?,date_fin_j=?,image_p=?,prix=? WHERE id_j=?";
        PreparedStatement ste= myConn.prepareStatement(sql);
        
            ste.setInt(1,nb_per);
            ste.setString(2,titre_j);
            ste.setString(3,type_j);
            ste.setString(4,date_deb_j);
            ste.setString(5,date_fin_j);
            ste.setString(6,image_p);
            ste.setInt(7,prix);
            ste.setInt(8,id_j);
            int rowsUpdated = ste.executeUpdate();
         if (rowsUpdated > 0) {
    System.out.println("An existing jeux was updated successfully!");
}
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerJeux(int id_j) {
                
      try{
 
        String sql = "DELETE FROM Jeux WHERE id_j=?";
        PreparedStatement statement = myConn.prepareStatement(sql);
        statement.setInt(1,id_j);
      int rowsDeleted = statement.executeUpdate();
   if (rowsDeleted > 0) {
    System.out.println("A Jeux was deleted successfully!");
  }
 } catch (SQLException ex) {
            System.out.println(ex);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Jeux> afficherJeu() {
        
            System.out.println("here 1");
      List<Jeux> jeux= new ArrayList<>();
      String req="SELECT * FROM Jeux";
      try {
             //exec
             Statement st=myConn.createStatement();
             ResultSet resultSet= st.executeQuery(req);
             while(resultSet.next())
             {
                 System.out.println("jeu :::::"+resultSet.getString("id_j"));
                 //String nom, String prenom, String sexe, String date,String email, String login, String mdp, String role
                 jeux.add(new Jeux(Integer.valueOf(resultSet.getString("id_j")),Integer.valueOf(resultSet.getString("nb_per")),resultSet.getString("type_j"),resultSet.getString("titre_j"),resultSet.getString("date_deb_j"),resultSet.getString("date_fin_j"),resultSet.getString("image_p"),Integer.valueOf(resultSet.getString("prix"))));
             }
         } catch (SQLException ex) {
             System.out.println("the exception that has occured"+ex);
         }
      return jeux;
        
    } 

    @Override
    public void afficherJeux() {
        
                try {
            String sql = "SELECT * FROM Jeux";
 
Statement statement = myConn.createStatement();
ResultSet result = statement.executeQuery(sql);
 
int count = 0;
 
while (result.next()){
    int nb_per = result.getInt("nb_per");
    String titre_j = result.getString("titre_j");
    String type_j = result.getString("type_j");
    String date_deb_j = result.getString("date_deb_j");
    String date_fin_j = result.getString("date_fin_j");
    String image_p = result.getString("image_p");
    int prix = result.getInt("prix");
    
 
    String output = "Jeux %d : %d | %s | %s | %s | %s | %s | %d ";
    System.out.println(String.format(output, ++count, nb_per, titre_j, type_j,date_deb_j,date_fin_j,image_p,prix));
}
         } catch (SQLException ex) {
            System.out.println("the exception that has occured"+ex);
        }
        
    }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    @Override
    public ObservableList<Jeux> afficherJeuxx() {
        
        ObservableList<Jeux> listeB = FXCollections.observableArrayList();
           try {
            String sql = "SELECT * FROM Jeux";
            Statement statement = myConn.createStatement();
            ResultSet result = statement.executeQuery(sql);
                    

 
    int count = 0;
    while (result.next()){
    int id_j=result.getInt("id_j");
    int nb_per = result.getInt("nb_per");
    String titre_j = result.getString("titre_j");
    String type_j = result.getString("type_j");
    String date_deb_j = result.getString("date_deb_j");
    String date_fin_j=result.getString("date_fin_j");
    String image_p=result.getString("image_p");
    int prix=result.getInt("prix");
 
    Jeux b1=new Jeux(id_j,nb_per, titre_j, type_j, date_deb_j, date_fin_j, image_p, prix);
    listeB.add(b1);
               //String output = "boutique %d : %s | %s | %s | %s | %d | %d | %s | %s  ";
    //System.out.println(String.format(output, ++count, nom, email, lien,description,num_telephone,num_fixe,governerat,ville));
    }
    
            } catch (SQLException ex) {
            System.out.println(ex);
            }

    return listeB;
        
    }
       
    }
    
   
    

