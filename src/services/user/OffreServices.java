/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.user;

import Config.Datasource;
import Interfaces.IOffre;
import entities.article;
import entities.offre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author allal
 */
public class OffreServices implements IOffre{
     Connection myConn = Datasource.getInstance().getCnx();

    @Override
    public void ajouterOffre(offre o) {
       try {
            String sql="INSERT INTO offre(Idu,id_article,titre,categorie,sous_categorie,marque,produit_propose,periode_dutilisation,etat_produit_propose,description,bonus,image,num_tel)"
                    + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
            PreparedStatement ste= myConn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ste.setInt(1,o.getIdu());
            ste.setInt(2,o.getId_article());
            ste.setString(3,o.getTitre());
            ste.setString(4,o.getCategorie());
            ste.setString(5,o.getSous_categorie());
            ste.setString(6,o.getMarque());
            ste.setString(7,o.getProduit_propose());
            ste.setString(8,o.getPeriode_dutilisation());
            ste.setString(9,o.getEtat_produit_propose());
            ste.setString(10,o.getDescription());
            ste.setString(11,o.getBonus());
            ste.setString(12,o.getImage());
            ste.setInt(13,o.getNum_tel());
            ste.executeUpdate();
         
         
    System.out.println("Ur offre was sent!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public ObservableList<offre> afficherListeDesoffres(int id_article) {
   ObservableList<offre>MesOffres= FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM offre WHERE id_article="+id_article;
            Statement statement = myConn.createStatement();
            ResultSet result = statement.executeQuery(sql);
while (result.next()){
    offre o = new offre();
    o.setIdu(result.getInt("Idu"));
    o.setId_offre(result.getInt("id_offre"));
    o.setId_article(result.getInt("id_article"));
    o.setTitre(result.getString("titre"));
    o.setCategorie(result.getString("categorie"));
    o.setSous_categorie(result.getString("sous_categorie"));
    o.setMarque(result.getString("marque"));
    o.setProduit_propose(result.getString("produit_propose"));
    o.setPeriode_dutilisation(result.getString("periode_dutilisation"));
    o.setEtat_produit_propose(result.getString("etat_produit_propose"));
    o.setDescription(result.getString("description"));
    o.setBonus(result.getString("bonus"));
    o.setImage(result.getString("image"));
    o.setNum_tel(result.getInt("num_tel"));
    
    MesOffres.add(o);
}
         } catch (SQLException ex) {
            System.out.println(ex);
        }
      return MesOffres;   
    }
    
      @Override
    public void supprimerOffre(offre o) {
          String sql = "DELETE FROM offre WHERE id_offre=?";
       try{
        PreparedStatement ste= myConn.prepareStatement(sql);
        ste.setInt(1,o.getId_offre());
      int rowsDeleted = ste.executeUpdate();
   if (rowsDeleted > 0) {
    System.out.println("Offre supprimé !");
  }
 } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
        
    }
    

