/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.user;


import Config.Datasource;
import Interfaces.IArticle;
import entities.article;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author allal
 */
public class ArticleServices implements IArticle{
    
  Connection myConn = Datasource.getInstance().getCnx();
   MailSenderArticles ms= new MailSenderArticles();

    @Override
    public void ajouterArticle(article a) {
         try {
            String sql="INSERT INTO article(Idu,nom_article,categorie,sous_categorie,marque,periode_utilistation,etat,description,image)"
                    + " VALUES(?,?,?,?,?,?,?,?,?)"; 
            PreparedStatement ste= myConn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ste.setInt(1,a.getIdu());
            ste.setString(2,a.getNom_article());
            ste.setString(3,a.getCategorie());
            ste.setString(4,a.getSous_categorie());
            ste.setString(5,a.getMarque());
            ste.setString(6,a.getPeriode_dutilisation());
            ste.setString(7,a.getEtat());
            ste.setString(8,a.getDescription());
            ste.setString(9,a.getImage());
            ste.executeUpdate();
         
     System.out.println("Ur Atricle was inserted !");
                        
                        String to = "allala.azaiz@gmail.com";
                        String subject = "Nouvelle Article";
			String body = "<html><center><img src=\"https://i.ibb.co/NTJ3J77/a.png\" alt=\"Girl in a jacket\" height=50%;width=50%></center></html>"
                    + "<html><center><h2>bienvenue sur notre site  Ebadel</h2> <br><h4>donner une seconde vie a vos article ! au lieu de le jetter </h4></center></br></html>"
                    + "<html><center><h3>Votre Article a été ajouté avec succès</h3></center></html>";
			ms.sendMailAjouterArticle(to, subject, body);
        } catch (Exception ex) {
          Logger.getLogger(ArticleServices.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    @Override
    public void modifierArticle(article a) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void supprimerArticle(article a) {
          String sql = "DELETE FROM article WHERE id_article=?";
       try{
        PreparedStatement ste= myConn.prepareStatement(sql);
        ste.setInt(1,a.getId_article());
      int rowsDeleted = ste.executeUpdate();
   if (rowsDeleted > 0) {
    System.out.println("Un Article a été supprimée avec succès !");
  }
 } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public ObservableList<article> afficherArticles() {
        ObservableList<article>Lesarticles= FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM article";
            Statement statement = myConn.createStatement();
            ResultSet result = statement.executeQuery(sql);
while (result.next()){
    article a = new article();
    a.setId_article(result.getInt("id_article"));
    a.setNom_article(result.getString("nom_article"));
    a.setCategorie(result.getString("categorie"));
    a.setSous_categorie(result.getString("sous_categorie"));
    a.setMarque(result.getString("marque"));
    a.setPeriode_dutilisation(result.getString("periode_utilistation"));
    a.setEtat(result.getString("etat"));
    a.setDescription(result.getString("description"));
    a.setImage(result.getString("image"));
    Lesarticles.add(a);
   // a.toString();
}
         } catch (SQLException ex) {
            System.out.println(ex);
        }
      return Lesarticles;   
    }
    
     public article afficherArticleDetails(int a) {
         article articlee = new article();
    try {
        String sql = "SELECT * FROM article WHERE id_article ="+a ;
        Statement statement = myConn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()){
              
              articlee.setId_article(result.getInt("id_article"));
              articlee.setNom_article(result.getString("nom_article"));
              articlee.setCategorie(result.getString("categorie"));
              articlee.setSous_categorie(result.getString("sous_categorie"));
              articlee.setMarque(result.getString("marque"));
              articlee.setPeriode_dutilisation(result.getString("periode_utilistation"));
              articlee.setEtat(result.getString("etat"));
              articlee.setDescription(result.getString("description"));
              articlee.setImage(result.getString("image")); 
        }
    } catch (SQLException ex) {
        System.out.println(ex);
    }
    return articlee;   
}

    public ObservableList<article> afficherArticlesParId(int idu) {
         ObservableList<article>Mesarticles= FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM article WHERE Idu="+idu;
            Statement statement = myConn.createStatement();
            ResultSet result = statement.executeQuery(sql);
while (result.next()){
    article a = new article();
    a.setId_article(result.getInt("id_article"));
    a.setNom_article(result.getString("nom_article"));
    a.setCategorie(result.getString("categorie"));
    a.setSous_categorie(result.getString("sous_categorie"));
    a.setMarque(result.getString("marque"));
    a.setPeriode_dutilisation(result.getString("periode_utilistation"));
    a.setEtat(result.getString("etat"));
    a.setDescription(result.getString("description"));
    a.setImage(result.getString("image"));
    Mesarticles.add(a);
}
         } catch (SQLException ex) {
            System.out.println(ex);
        }
      return Mesarticles;   
    }
    }
    
    
  
