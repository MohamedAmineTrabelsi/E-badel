package entities;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author allal
 */
public class offre {
    private int Idu;
    private int id_offre;
    private int id_article;
    private String titre;
    private String categorie;
    private String sous_categorie;	
    private String marque;
    private String produit_propose;	
    private String periode_dutilisation;
    private String etat_produit_propose;		
    private String description;
    private String bonus;
    private String image;
    private int num_tel;

    public offre() {
    }

    public offre(int Idu, int id_article, String titre, String categorie, String sous_categorie, String marque, String produit_propose, String periode_dutilisation, String etat_produit_propose, String description, String bonus, String image, int num_tel) {
        this.Idu = Idu;
        this.id_article = id_article;
        this.titre = titre;
        this.categorie = categorie;
        this.sous_categorie = sous_categorie;
        this.marque = marque;
        this.produit_propose = produit_propose;
        this.periode_dutilisation = periode_dutilisation;
        this.etat_produit_propose = etat_produit_propose;
        this.description = description;
        this.bonus = bonus;
        this.image = image;
        this.num_tel = num_tel;
    }

    public int getIdu() {
        return Idu;
    }

    public void setIdu(int Idu) {
        this.Idu = Idu;
    }
   
    

    public int getId_offre() {
        return id_offre;
    }

    public int getId_article() {
        return id_article;
    }

    public String getTitre() {
        return titre;
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

    public String getProduit_propose() {
        return produit_propose;
    }

    public String getPeriode_dutilisation() {
        return periode_dutilisation;
    }

    public String getEtat_produit_propose() {
        return etat_produit_propose;
    }

    public String getDescription() {
        return description;
    }

    public String getBonus() {
        return bonus;
    }

    public String getImage() {
        return image;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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

    public void setProduit_propose(String produit_propose) {
        this.produit_propose = produit_propose;
    }

    public void setPeriode_dutilisation(String periode_dutilisation) {
        this.periode_dutilisation = periode_dutilisation;
    }

    public void setEtat_produit_propose(String etat_produit_propose) {
        this.etat_produit_propose = etat_produit_propose;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    @Override
    public String toString() {
        return  " Titre d'offre : " + titre + 
                "\n Categorie : " + categorie + 
                "\n Sous categorie : " + sous_categorie + 
                "\n Marque : " + marque +
                "\n Produit proposé " + produit_propose +
                "\n Periode d'utilisation : " + periode_dutilisation +
                "\n Etat : " + etat_produit_propose+ 
                "\n description : " + description +
                "\n Bonus : " + bonus+ 
                "\n Numéro télephone : " + num_tel;
               
    }
    }
    
    
    
