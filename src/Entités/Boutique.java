/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entités;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Boutique {
    private int id_b;
    private String nom;
    private String image_b;
    private String email;
    private String lien;
    private String description;
    private String num_telephone;
    private String num_fixe;
    private String governerat ;
    private String ville;
    private List<Produit> produit;
    private List<Boutique> boutique;

    public Boutique(String nom) {
        this.nom = nom;
    }
    
    
    
    
    public Boutique() {
    }

    public Boutique(int id_b, String nom, String email, String lien, String description, String num_telephone, String num_fixe, String governerat, String ville) {
        this.id_b = id_b;
        this.nom = nom;
        this.email = email;
        this.lien = lien;
        this.description = description;
        this.num_telephone = num_telephone;
        this.num_fixe = num_fixe;
        this.governerat = governerat;
        this.ville = ville;
        this.produit = new ArrayList<>();
    }
    

    public Boutique( String nom, String image_b, String email, String lien, String description, String num_telephone, String num_fixe, String governerat, String ville) {
        
        this.nom = nom;
        this.image_b = image_b;
        this.email = email;
        this.lien = lien;
        this.description = description;
        this.num_telephone = num_telephone;
        this.num_fixe = num_fixe;
        this.governerat = governerat;
        this.ville = ville;
    }

   

    

    public int getId_b() {
         return id_b;
    }

    public String getNom() {
        return nom;
    }

    public String getImage_b() {
        return image_b;
    }

    public String getEmail() {
        return email;
    }

    public String getLien() {
        return lien;
    }

    public String getDescription() {
        return description;
    }

    public String getNum_telephone() {
        return num_telephone;
    }

    public String getNum_fixe() {
        return num_fixe;
    }

    public String getGovernerat() {
        return governerat;
    }

    public String getVille() {
        return ville;
    }

  
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setImage_b(String image_b) {
        this.image_b = image_b;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNum_telephone(String num_telephone) {
        this.num_telephone = num_telephone;
    }

    public void setNum_fixe(String num_fixe) {
        this.num_fixe = num_fixe;
    }

    public void setGovernerat(String governerat) {
        this.governerat = governerat;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Boutique other = (Boutique) obj;
        if (this.id_b != other.id_b) {
            return false;
        }
      
        return Objects.equals(this.nom, other.nom);
    }
    
    /*public void ajouterProduit(Produit produit) {
        produit.add // ajouter un produit à la liste
    }*/
    
    public List<Produit> getProduits() {
        return produit; // retourner la liste de produits associés à cette boutique
    }
    public List<Boutique> getBoutique() {
        return boutique; // retourner la liste de produits associés à cette boutique
    }

   
    
    
}
