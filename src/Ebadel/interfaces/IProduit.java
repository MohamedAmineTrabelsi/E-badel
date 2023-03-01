/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.interfaces;

import Ebadel.entites.Produit;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author trabe
 */
public interface IProduit {
    public boolean ajouterSCategorie(Produit p);
    public void modifierSCategorie(Produit p, String titre, String image, Date date, Float prix, String description, String nom_c, String nom_s_c, String nom_m);
    public void supprimerSCategorie(Produit p);
    public List<Produit> afficherSCategorie(); 
}
