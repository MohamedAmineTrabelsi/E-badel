/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.services;

import Ebadel.entites.Categorie;
import Ebadel.entites.SCategorie;
import Ebadel.interfaces.ISCategorie;
import Ebadel.utils.myConnection;
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
public class SCategorieCRUD implements ISCategorie{
    myConnection  cnx =myConnection.getInstance();
    // Déclaration de la variable de classe mesSCategorie
    private ObservableList<SCategorie> mesSCategorie = FXCollections.observableArrayList();
    @Override
    public boolean ajouterSCategorie(SCategorie s) {
               String sql="insert into souscategorie values (null,?,?)";
         boolean resultat = false;
       try {   
            PreparedStatement ste= cnx.getCnx().prepareStatement(sql);
            ste.setString(1, s.getNom_c());
            ste.setString(2, s.getNom_s_c());
            ste.executeUpdate();
            resultat = true;
            System.out.println(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
       return resultat;
    }

    @Override
    public boolean modifierSCategorie(SCategorie s, String nom_s_c, String nom_c) {
                String sql="UPDATE souscategorie SET nom_s_c=?,nom_c=? WHERE nom_s_c=?";
          boolean resultat = false;
          try {
        PreparedStatement ste= cnx.getCnx().prepareStatement(sql);
            ste.setString(1,nom_s_c);
            ste.setString(2,nom_c);
            ste.setString(3,s.getNom_s_c());
            int rowsUpdated = ste.executeUpdate();
         if (rowsUpdated > 0) {
             System.out.println("Une sous-catégorie existante a été mise à jour avec succès !");
             resultat = true;
         }
                 // Mettre à jour la liste des sous-catégories
        mesSCategorie = afficherSCategorie();
            } catch (SQLException ex) {
            System.out.println(ex);
        } 
          return resultat;
    }

    @Override
    public void supprimerSCategorie(SCategorie s) {
       String sql = "DELETE FROM souscategorie WHERE nom_s_c=?";
       try{
        PreparedStatement ste= cnx.getCnx().prepareStatement(sql);
        ste.setString(1,s.getNom_s_c());
      int rowsDeleted = ste.executeUpdate();
   if (rowsDeleted > 0) {
    System.out.println("Une sous-catégorie a été supprimée avec succès !");
  }
 } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public ObservableList<SCategorie> afficherSCategorie() {
    //ObservableList<SCategorie> mesSCategorie = FXCollections.observableArrayList();
    mesSCategorie.clear(); // Effacer la liste pour la remplir à nouveau
    try {
        String sql = "SELECT * FROM souscategorie";
        Statement statement = cnx.getCnx().createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()){
            SCategorie sc = new SCategorie();
            sc.setId_s_c(result.getInt("id_s_c"));
            sc.setNom_c(result.getString("nom_c"));
            sc.setNom_s_c(result.getString("nom_s_c"));
            mesSCategorie.add(sc);
            System.out.println(sc);  
        }
    } catch (SQLException ex) {
        System.out.println(ex);
    }
    return mesSCategorie;   
}
     public ObservableList<SCategorie> afficherSCategorieParCategorie(String a) {
    //ObservableList<SCategorie> mesSCategorie = FXCollections.observableArrayList();
    mesSCategorie.clear(); // Effacer la liste pour la remplir à nouveau
    try {
        String sql = "SELECT souscategorie.id_s_c, souscategorie.nom_c, souscategorie.nom_s_c FROM souscategorie JOIN categorie ON souscategorie.nom_c = categorie.nom_c where categorie.nom_c='"+a+"';";
        Statement statement = cnx.getCnx().createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()){
            SCategorie sc = new SCategorie();
            sc.setId_s_c(result.getInt("id_s_c"));
            sc.setNom_c(result.getString("nom_c"));
            sc.setNom_s_c(result.getString("nom_s_c"));
            mesSCategorie.add(sc);
            System.out.println(sc);  
        }
    } catch (SQLException ex) {
        System.out.println(ex);
    }
    return mesSCategorie;   
}
}
