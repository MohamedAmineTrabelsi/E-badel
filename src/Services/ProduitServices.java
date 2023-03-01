/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ConnectionBD.myConnection;
import Entités.Boutique;
import Entités.Produit;
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
 * @author Ayoub
 */
public class ProduitServices implements IproduitCRUD{
    Connection myConn = myConnection.getInstance().getConnection();

    @Override
    public void ajouterProduit(Produit p) {
        Boutique b=new Boutique();
        try {
            //String req_index = "select nom_b from Boutique ";
            //PreparedStatement pst = myConn.prepareStatement(req_index);
            String sql="INSERT INTO produit(titre,prix,description,nom_boutique,etat,image_p) VALUES(?,?,?,?,?,?) "; 
            PreparedStatement ste= myConn.prepareStatement(sql);
            ste.setString(1,p.getTitre());
            ste.setString(2,p.getPrix());
            ste.setString(3,p.getDescription());
            ste.setString(4,p.getNom_boutique());
            ste.setString(5,p.getEtat());
            ste.setString(6,p.getImage_p());
            int rowsInserted = ste.executeUpdate();
            if (rowsInserted > 0) {
    System.out.println("une nouvelle produit a été insérée avec succés ");
}
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
       
    }

    @Override
    public void modifierProduit(Produit p) {
        try {
        String sql="UPDATE produit SET titre=?, prix=?, description=?, etat=? WHERE id_P = ?";
        PreparedStatement ste= myConn.prepareStatement(sql);
            ste.setString(1,p.getTitre());
            ste.setString(2,p.getPrix());
            ste.setString(3,p.getDescription());
            ste.setInt(4,p.getId_P());
            ste.setString(5,p.getEtat());
            int rowsUpdated = ste.executeUpdate();
         if (rowsUpdated > 0) {
    System.out.println("Un produit existant a été mis à jour avec succès !");
}
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
       

    @Override
    public void supprimerProduit(Produit p) {
        try{
 
        String sql = "DELETE FROM produit WHERE id_P=?";
        PreparedStatement statement = myConn.prepareStatement(sql);
        statement.setInt(1,p.getId_P());
      int rowsDeleted = statement.executeUpdate();
   if (rowsDeleted > 0) {
    System.out.println("Une produit a été supprimé avec succès !");
  }
 } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
      

    @Override
    public ObservableList<Produit> afficherProduits() {
        ObservableList<Produit> listeP = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM Produit ";
            Statement statement = myConn.createStatement();
            ResultSet result = statement.executeQuery(sql);
                    

 
    int count = 0;
    while (result.next()){
        int id_P=result.getInt("id_P");
        String titre=result.getString("titre");
        String prix=result.getString("prix");
        String description=result.getString("description");
        String nom_boutique=result.getString("nom_boutique");
        String etat=result.getString("etat");
        //String image=result.getString("image_p");
        Produit nouvelleproduit =new Produit(id_P,titre, prix, description,nom_boutique,etat);
        //System.out.println("nouvelleproduit:"+nouvelleproduit);
        listeP.add(nouvelleproduit);
       
    }}  catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       return listeP; 
    
    }
    public ObservableList<String> selectnomB() {
        ObservableList<String> listE = FXCollections.observableArrayList();

        try {
            String query = "select nom_b from Boutique";
            PreparedStatement ps = myConn.prepareStatement(query);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                String current = result.getString("nom_b");
                listE.add(current);
            }
            ps.close();
            result.close();
        } catch (SQLException ex) {
        }
        return listE;

   
   
     }
    public ObservableList<String> filecombBox()
    {
         ObservableList<String> list2 =FXCollections.observableArrayList();
             
      try {
            String req = "select nom_b from Boutique ";
            PreparedStatement ps = myConn.prepareStatement(req);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                list2.add(rst.getString("nom_b"));
      
            }
          
    
    }   catch (SQLException ex) {
            System.err.println(ex.getMessage());
    }
     return list2;
     
    }
    
        
    }
    
    
   
    

    
    

