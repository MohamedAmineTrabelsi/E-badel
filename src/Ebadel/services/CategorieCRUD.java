/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.services;

import Ebadel.entites.Categorie;
import Ebadel.interfaces.ICategorie;
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


public class CategorieCRUD implements ICategorie{
myConnection  cnx =myConnection.getInstance();
    @Override
    public boolean ajouterCategorie(Categorie c) {
 /*        String sql="insert into categorie values (null,?)";
         boolean resultat = false;
       try {
           
            PreparedStatement ste= cnx.getCnx().prepareStatement(sql);
            ste.setString(1, c.getNom_c());
            ste.executeUpdate();
            resultat = true;
            System.out.println(c);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
       return resultat;*/
    String sql = "SELECT * FROM categorie WHERE nom_c = ?";
    boolean resultat = false;

    // Vérifier si le nom de catégorie est vide
    if (c.getNom_c().isEmpty()) {
        System.out.println("Erreur : le nom de catégorie ne peut pas être vide !");
        return resultat;
    }

    // Vérifier si le nom de catégorie existe déjà
    try {
        PreparedStatement verifStmt = cnx.getCnx().prepareStatement(sql);
        verifStmt.setString(1, c.getNom_c());
        ResultSet verifResult = verifStmt.executeQuery();

        if (verifResult.next()) {
            System.out.println("Erreur : le nom de catégorie existe déjà !");
            return resultat;
        }
    } catch (SQLException ex) {
        System.out.println(ex);
        return resultat;
    }

    // Ajouter la catégorie
    sql = "INSERT INTO categorie VALUES (null, ?)";

    try {
        PreparedStatement ajoutStmt = cnx.getCnx().prepareStatement(sql);
        ajoutStmt.setString(1, c.getNom_c());
        ajoutStmt.executeUpdate();
        resultat = true;
        System.out.println(c);
    } catch (SQLException ex) {
        System.out.println(ex);
    }

    return resultat;
    }

    @Override
    public boolean modifierCategorie(Categorie c, String nom_c) {
        String sql="UPDATE categorie SET nom_c=? WHERE nom_c=?";
        boolean resultat = false;
          try {
        PreparedStatement ste= cnx.getCnx().prepareStatement(sql);
            ste.setString(1,nom_c);
            ste.setString(2,c.getNom_c());
            int rowsUpdated = ste.executeUpdate();
         if (rowsUpdated > 0) {
             resultat = true;
             System.out.println("Une catégorie existante a été mise à jour avec succès !");
         }
            } catch (SQLException ex) {
            System.out.println(ex);
        } 
          return resultat;
    }
    
    @Override
    public void supprimerCategorie(Categorie c) {
     String sql = "DELETE FROM categorie WHERE nom_c=?";
       try{
        PreparedStatement ste= cnx.getCnx().prepareStatement(sql);
        ste.setString(1,c.getNom_c());
      int rowsDeleted = ste.executeUpdate();
   if (rowsDeleted > 0) {
    System.out.println("Une catégorie a été supprimée avec succès !");
  }
 } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public ObservableList<Categorie> afficherCategorie() {
        ObservableList<Categorie>mesCategorie = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM categorie";
            Statement statement = cnx.getCnx().createStatement();
            ResultSet result = statement.executeQuery(sql);
while (result.next()){
    Categorie c = new Categorie();
    c.setId_c(result.getInt("id_c"));
    c.setNom_c(result.getString("nom_c"));
    mesCategorie.add(c);
    System.out.println(c);  
}
         } catch (SQLException ex) {
            System.out.println(ex);
        }
      return mesCategorie;   
    }
    
    
}
