/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.user;

import Config.Datasource;
import Interfaces.IMarque;
import entities.Marque;
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
 * @author trabe
 */
public class MarqueCRUD implements IMarque{
     Connection myConn = Datasource.getInstance().getCnx();
    private ObservableList<Marque> mesMarques = FXCollections.observableArrayList();
    @Override
    public boolean ajouterMarque(Marque m) {
        String sql="insert into marque values (null,?,?,?)";
         boolean resultat = false;
       try {   
            PreparedStatement ste= myConn.prepareStatement(sql);
            ste.setString(1, m.getNom_m());
            ste.setString(2, m.getNom_c());
            ste.setString(3, m.getNom_s_c());
            ste.executeUpdate();
            resultat = true;
            System.out.println(m);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
       return resultat;
    }

    @Override
    public boolean modifierMarque(Marque m, String nom_m,String nom_c, String nom_s_c) {
       String sql="UPDATE marque SET nom_m=? WHERE nom_m=? AND nom_c=? AND nom_s_c=?";
       boolean resultat = false;
          try {
        PreparedStatement ste= myConn.prepareStatement(sql);
            ste.setString(1,nom_m);
            ste.setString(2,m.getNom_m());
            ste.setString(3,nom_c);
            ste.setString(4,nom_s_c);
            int rowsUpdated = ste.executeUpdate();
         if (rowsUpdated > 0) {
             System.out.println("Une marque existante a été mise à jour avec succès !");
             resultat = true;
         }
                 // Mettre à jour la liste des marque
        mesMarques = afficherMarque();
            } catch (SQLException ex) {
            System.out.println(ex);
        } 
          return resultat;
    }

    @Override
    public void supprimerMarque(Marque m) {
        String sql = "DELETE FROM marque WHERE nom_m=?";
       try{
        PreparedStatement ste= myConn.prepareStatement(sql);
        ste.setString(1,m.getNom_m());
      int rowsDeleted = ste.executeUpdate();
   if (rowsDeleted > 0) {
    System.out.println("Une marque a été supprimée avec succès !");
  }
 } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public ObservableList<Marque> afficherMarque() {
      mesMarques.clear();
        try {
            String sql = "SELECT * FROM marque";
            Statement statement = myConn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                Marque mm = new Marque();
                mm.setId_m(result.getInt("id_m"));
                mm.setNom_m(result.getString("nom_m"));
                mm.setNom_c(result.getString("nom_c"));
                mm.setNom_s_c(result.getString("nom_s_c"));
                mesMarques.add(mm);
                System.out.println(mm);  
            }
         } catch (SQLException ex) {
            System.out.println(ex);
            }
      return mesMarques;   
    }    
    
    public ObservableList<Marque> afficherMarqueParSCategorie(String a) {
    //ObservableList<SCategorie> mesSCategorie = FXCollections.observableArrayList();
    mesMarques.clear(); // Effacer la liste pour la remplir à nouveau
    try {
        String sql = "SELECT marque.id_m, marque.nom_m, marque.nom_c, marque.nom_s_c FROM marque JOIN souscategorie ON marque.nom_s_c = souscategorie.nom_s_c where souscategorie.nom_s_c='"+a+"';";
        Statement statement = myConn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()){
            Marque mm = new Marque();
            mm.setId_m(result.getInt("id_m"));
            mm.setNom_m(result.getString("nom_m"));
            mm.setNom_c(result.getString("nom_c"));
            mm.setNom_s_c(result.getString("nom_s_c"));
            mesMarques.add(mm); 
        }
    } catch (SQLException ex) {
        System.out.println(ex);
    }
    return mesMarques;   
}

}
