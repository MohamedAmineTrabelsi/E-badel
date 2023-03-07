/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.entites;

import java.sql.Date;


/**
 *
 * @author trabe
 */
public class Produit {
    
    private int id_p;
    private String titre; 
    private String image; 
    private Date date;
    private Float prix;
    private String description; 
    private String nom_c;
    private String nom_s_c;
    private String nom_m;

    public Produit() {
    }

    public Produit(String titre, String image, Date date, Float prix, String description, String nom_c, String nom_s_c, String nom_m) {
        this.titre = titre;
        this.image = image;
        this.date = date;
        this.prix = prix;
        this.description = description;
        this.nom_c = nom_c;
        this.nom_s_c = nom_s_c;
        this.nom_m = nom_m;
    }

    public int getId_p() {
        return id_p;
    }

    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom_c() {
        return nom_c;
    }

    public void setNom_c(String nom_c) {
        this.nom_c = nom_c;
    }

    public String getNom_s_c() {
        return nom_s_c;
    }

    public void setNom_s_c(String nom_s_c) {
        this.nom_s_c = nom_s_c;
    }

    public String getNom_m() {
        return nom_m;
    }

    public void setNom_m(String nom_m) {
        this.nom_m = nom_m;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_p=" + id_p + ", titre=" + titre + ", image=" + image + ", date=" + date + ", prix=" + prix + ", description=" + description + ", nom_c=" + nom_c + ", nom_s_c=" + nom_s_c + ", nom_m=" + nom_m + '}';
    } 
    //hahahah
}
