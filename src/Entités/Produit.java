/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entités;

import java.util.Objects;

/**
 *
 * @author asus
 */
public class Produit {
     private int id_P;
    private String titre;
    private String prix;
    private String description;
    private String nom_boutique;
     private String etat;
    private String image_p;

    public Produit(String titre, String prix, String description, String nom_boutique, String etat) {
        this.titre = titre;
        this.prix = prix;
        this.description = description;
        this.nom_boutique = nom_boutique;
        this.etat = etat;
    }

    public Produit(String titre, String prix, String description) {
        this.titre = titre;
        this.prix = prix;
        this.description = description;
    }
   
    

    

   
    

    

    public Produit(String titre, String prix, String description, String image_p, String nom_boutique, String etat) {
        this.titre = titre;
        this.prix = prix;
        this.description = description;
        this.image_p = image_p;
        this.nom_boutique = nom_boutique;
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Produit{" + "titre=" + titre + ", prix=" + prix + ", description=" + description + ", image_p=" + image_p + ", nom_boutique=" + nom_boutique + ", etat=" + etat + '}';
    }

   

    

   
    


    public Produit(int id_P, String titre, String prix, String description, String nom_boutique,String etat) {
        this.id_P = id_P;
        this.titre = titre;
        this.prix = prix;
        this.description = description;
        this.nom_boutique = nom_boutique;
        this.etat = etat;
    }
   
    
    

    public Produit() {
        
    }

    

   

    public String getNom_boutique() {
        return nom_boutique;
    }

    public void setNom_boutique(String nom_boutique) {
        this.nom_boutique = nom_boutique;
    }
    

    public int getId_P() {
        return id_P;
    }

    public String getTitre() {
        return titre;
    }

    public String getPrix() {
        return prix;
    }

    public String getDescription() {
        return description;
    }

    public String getImage_p() {
        return image_p;
    }

    

   

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage_p(String image_p) {
        this.image_p = image_p;
    }
     public String getEtat(){
        return this.etat;
    }
    
    public void setEtat(String etat){
        this.etat = etat;
    }

  

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.titre);
        hash = 89 * hash + Objects.hashCode(this.description);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produit other = (Produit) obj;
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        return Objects.equals(this.description, other.description);
    }

   

   
    
    
    
    
}
