/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.services;

import Ebadel.entites.Produit;
import Ebadel.interfaces.IProduit;
import Ebadel.utils.myConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trabe
 */
public class ProduitCRUD implements IProduit {

    myConnection cnx = myConnection.getInstance();

    @Override
    public boolean ajouterSCategorie(Produit p) {
        String sql = "insert into produit values (null,?,?,?,?,?,?,?,?)";
        boolean resultat = false;
        try {
            PreparedStatement ste = cnx.getCnx().prepareStatement(sql);
            ste.setString(1, p.getTitre());
            ste.setString(2, p.getImage());
            ste.setDate(3, p.getDate());
            ste.setFloat(4, p.getPrix());
            ste.setString(5, p.getDescription());
            ste.setString(6, p.getNom_c());
            ste.setString(7, p.getNom_s_c());
            ste.setString(8, p.getNom_m());
            ste.executeUpdate();
            resultat = true;
            System.out.println(p);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return resultat;
    }

    @Override
    public void modifierSCategorie(Produit p, String titre, String image, Date date, Float prix, String description, String nom_c, String nom_s_c, String nom_m) {
        String sql = "UPDATE produit SET titre=?,image=?,prix=?,description=?,nom_c=?,nom_s_c=?,nom_m=? WHERE titre=?";
        try {
            PreparedStatement ste = cnx.getCnx().prepareStatement(sql);
            ste.setString(1, titre);
            ste.setString(2, image);
            ste.setFloat(3, prix);
            ste.setDate(4, date);
            ste.setString(5, description);
            ste.setString(6, nom_c);
            ste.setString(7, nom_s_c);
            ste.setString(8, nom_m);
            ste.setString(9, p.getTitre());
            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Un produit existant a été mise à jour avec succès !");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void supprimerSCategorie(Produit p) {
        String sql = "DELETE FROM produit WHERE titre=?";
        try {
            PreparedStatement ste = cnx.getCnx().prepareStatement(sql);
            ste.setString(1, p.getTitre());
            int rowsDeleted = ste.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Un produit a été supprimé avec succès !");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Produit> afficherSCategorie() {
        List<Produit> mesProduit = new ArrayList<>();
        try {
            String sql = "SELECT * FROM produit";
            Statement statement = cnx.getCnx().createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                Produit p = new Produit();
                p.setId_p(result.getInt("id_p"));
                p.setTitre(result.getString("titre"));
                p.setImage(result.getString("image"));
                p.setDate(result.getDate("date"));
                p.setPrix(result.getFloat("prix"));
                p.setDescription(result.getString("description"));
                p.setNom_c(result.getString("nom_c"));
                p.setNom_s_c(result.getString("nom_s_c"));
                p.setNom_m(result.getString("nom_m"));
                mesProduit.add(p);
                //System.out.println(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return mesProduit;
    }

    public List<Produit> getProduitByCateg(String nomCateg) {
        List<Produit> mesProduit = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `produit` WHERE nom_c='" + nomCateg + "'";
            Statement statement = cnx.getCnx().createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                Produit p = new Produit();
                p.setId_p(result.getInt("id_p"));
                p.setTitre(result.getString("titre"));
                p.setImage(result.getString("image"));
                p.setDate(result.getDate("date"));
                p.setPrix(result.getFloat("prix"));
                p.setDescription(result.getString("description"));
                p.setNom_c(result.getString("nom_c"));
                p.setNom_s_c(result.getString("nom_s_c"));
                p.setNom_m(result.getString("nom_m"));
                mesProduit.add(p);
                System.out.println(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return mesProduit;
    }

    public List<Produit> getProduitByCategandSCateg(String nomCateg, String nomSCateg) {
        List<Produit> mesProduit = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `produit` WHERE nom_c='" + nomCateg + "' and nom_s_c='" + nomSCateg + "'";
            Statement statement = cnx.getCnx().createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                Produit p = new Produit();
                p.setId_p(result.getInt("id_p"));
                p.setTitre(result.getString("titre"));
                p.setImage(result.getString("image"));
                p.setDate(result.getDate("date"));
                p.setPrix(result.getFloat("prix"));
                p.setDescription(result.getString("description"));
                p.setNom_c(result.getString("nom_c"));
                p.setNom_s_c(result.getString("nom_s_c"));
                p.setNom_m(result.getString("nom_m"));
                mesProduit.add(p);
                System.out.println(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return mesProduit;
    }

    public List<Produit> getProduitByCategandSCategandMarque(String nomCateg, String nomSCateg, String nomMarq) {
        List<Produit> mesProduit = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `produit` WHERE nom_c='" + nomCateg + "' and nom_s_c='" + nomSCateg + "' and nom_m='" + nomMarq + "'";
            Statement statement = cnx.getCnx().createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                Produit p = new Produit();
                p.setId_p(result.getInt("id_p"));
                p.setTitre(result.getString("titre"));
                p.setImage(result.getString("image"));
                p.setDate(result.getDate("date"));
                p.setPrix(result.getFloat("prix"));
                p.setDescription(result.getString("description"));
                p.setNom_c(result.getString("nom_c"));
                p.setNom_s_c(result.getString("nom_s_c"));
                p.setNom_m(result.getString("nom_m"));
                mesProduit.add(p);
                System.out.println(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return mesProduit;
    }

    public List<Produit> getProduitParTitre(String titre) {
        List<Produit> mesProduit = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `produit` WHERE titre='" + titre + "'";
            Statement statement = cnx.getCnx().createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                Produit p = new Produit();
                p.setId_p(result.getInt("id_p"));
                p.setTitre(result.getString("titre"));
                p.setImage(result.getString("image"));
                p.setDate(result.getDate("date"));
                p.setPrix(result.getFloat("prix"));
                p.setDescription(result.getString("description"));
                p.setNom_c(result.getString("nom_c"));
                p.setNom_s_c(result.getString("nom_s_c"));
                p.setNom_m(result.getString("nom_m"));
                mesProduit.add(p);
                System.out.println(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return mesProduit;
    }

    public double getnbProdParCateg(String nomCateg) {
        double nb = 0.0;
        String requet = "SELECT count(*) FROM `produit` where nom_c='" + nomCateg + "';";
        try {
            Statement statement = cnx.getCnx().createStatement();
            ResultSet result = statement.executeQuery(requet);
            result.next();
            nb = result.getDouble("COUNT(*)");
            System.out.println(nb);
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return nb;
    }

    public double getnbProdParSCateg(String nomSCateg) {
        double nb = 0.0;
        String requet = "SELECT count(*) FROM `produit` where nom_s_c='" + nomSCateg + "';";
        try {
            Statement statement = cnx.getCnx().createStatement();
            ResultSet result = statement.executeQuery(requet);
            result.next();
            nb = result.getDouble("COUNT(*)");
            System.out.println(nb);
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return nb;
    }

    public double getnbProdParMarque(String nomMarq) {
        double nb = 0.0;
        String requet = "SELECT count(*) FROM `produit` where nom_m='" + nomMarq + "';";
        try {
            Statement statement = cnx.getCnx().createStatement();
            ResultSet result = statement.executeQuery(requet);
            result.next();
            nb = result.getDouble("COUNT(*)");
            System.out.println(nb);
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return nb;
    }
}
