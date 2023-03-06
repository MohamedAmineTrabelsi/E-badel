/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author allal
 */
public class article {
    private int Idu;
    private int id_article;
    private String nom_article;
    private String categorie;		
    private String sous_categorie;
    private String marque;		
    private String periode_dutilisation;
    private String etat;
    private String description;
    private String image;

    public article(String nom_article, String categorie, String sous_categorie, String marque, String periode_dutilisation, String etat, String description, String image) {
        this.nom_article = nom_article;
        this.categorie = categorie;
        this.sous_categorie = sous_categorie;
        this.marque = marque;
        this.periode_dutilisation = periode_dutilisation;
        this.etat = etat;
        this.description = description;
        this.image = image;
    }

    public article(int Idu, String nom_article, String categorie, String sous_categorie, String marque, String periode_dutilisation, String etat, String description, String image) {
        this.Idu = Idu;
        this.nom_article = nom_article;
        this.categorie = categorie;
        this.sous_categorie = sous_categorie;
        this.marque = marque;
        this.periode_dutilisation = periode_dutilisation;
        this.etat = etat;
        this.description = description;
        this.image = image;
    }

    public article() {
    }

    public int getId_article() {
        return id_article;
    }

    public String getNom_article() {
        return nom_article;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getSous_categorie() {
        return sous_categorie;
    }

    public String getMarque() {
        return marque;
    }

    public String getPeriode_dutilisation() {
        return periode_dutilisation;
    }

    public String getEtat() {
        return etat;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public void setNom_article(String nom_article) {
        this.nom_article = nom_article;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setSous_categorie(String sous_categorie) {
        this.sous_categorie = sous_categorie;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setPeriode_dutilisation(String periode_dutilisation) {
        this.periode_dutilisation = periode_dutilisation;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
     @Override
    public String toString() {
        return  " Nom Article : " + nom_article + 
                "\n Categorie : " + categorie + 
                "\n Sous categorie : " + sous_categorie + 
                "\n Marque : " + marque +
                "\n Periode d'utilisation : " + periode_dutilisation +
                "\n Etat : " + etat + 
                "\n description : " + description ;
               
    }

    public int getIdu() {
        return Idu;
    }

    public void setIdu(int Idu) {
        this.Idu = Idu;
    }
    
}
