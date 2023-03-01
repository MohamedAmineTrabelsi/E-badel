/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import ConnectionBD.myConnection;
import Entités.Boutique;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class BoutiqueServices implements services.IboutiqueCRUD{
    Connection myConn = myConnection.getInstance().getConnection();
     @Override
    public void ajouterBoutique(Boutique b) {
         try {
            String sql="INSERT INTO boutique VALUES(null,?,?,?,?,?,?,?,?,?)"; 
            PreparedStatement ste= myConn.prepareStatement(sql);
            ste.setString(1,b.getNom());
            ste.setString(2,b.getImage_b());
            ste.setString(3,b.getEmail());
            ste.setString(4,b.getLien());
            ste.setString(5,b.getDescription());
            ste.setString(6,b.getNum_telephone());
            ste.setString(7,b.getNum_fixe());
            ste.setString(8,b.getGovernerat());
            ste.setString(9,b.getVille());
            int rowsInserted = ste.executeUpdate();
            if (rowsInserted > 0) {
    System.out.println("A new shop was inserted successfully!");
}
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
 
     @Override
    public void modifierBoutique(Boutique b) {
              try {
        String sql="UPDATE boutique SET nom_b=?, email_b=?, lien=?, description=?, num_telephone=?, num_fixe=?, governerat=?, ville=? WHERE id_b=?";
        PreparedStatement ste= myConn.prepareStatement(sql);
            ste.setString(1,b.getNom());
            ste.setString(2,b.getEmail());
            ste.setString(3,b.getLien());
            ste.setString(4,b.getDescription());
            ste.setString(5,b.getNum_telephone());
            ste.setString(6,b.getNum_fixe());
            ste.setString(7,b.getGovernerat());
            ste.setString(8,b.getVille());
            ste.setInt(9,b.getId_b());
            int rowsUpdated = ste.executeUpdate();
         if (rowsUpdated > 0) {
    System.out.println("Une boiutique existante a été mis à jour avec succès !");
}
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }

     @Override
    public void supprimerBoutique(Boutique b) {
           try{
 
        String sql = "DELETE FROM boutique WHERE id_b=?";
        PreparedStatement statement = myConn.prepareStatement(sql);
        statement.setInt(1,b.getId_b());
      int rowsDeleted = statement.executeUpdate();
   if (rowsDeleted > 0) {
    System.out.println("Une boutique a été supprimé avec succès !");
  }
 } catch (SQLException ex) {
            System.out.println(ex);
        }
       
    }

     @Override
    public ObservableList<Boutique> afficherBoutiques() {
        ObservableList<Boutique> listeB = FXCollections.observableArrayList();
           try {
            String sql = "SELECT * FROM boutique";
            Statement statement = myConn.createStatement();
            ResultSet result = statement.executeQuery(sql);
                    

 
    int count = 0;
    while (result.next()){
    int id_b=result.getInt("id_b");
    String nom = result.getString("nom_b");
    String email = result.getString("email_b");
    String lien = result.getString("lien");
    String description = result.getString("description");
    String num_telephone=result.getString("num_telephone");
    String num_fixe=result.getString("num_fixe");
    String governerat=result.getString("governerat");
    String ville=result.getString("ville");
    Boutique b1=new Boutique(id_b, nom, email, lien, description, num_telephone, num_fixe, governerat, ville);
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

    
    

