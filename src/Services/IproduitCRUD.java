/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entités.Produit;
import javafx.collections.ObservableList;

/**
 *
 * @author Ayoub
 */
public interface IproduitCRUD {

    /**
     *
     * @param p
     */
    public void ajouterProduit(Produit p);
     public void modifierProduit(Produit p);
     public void supprimerProduit(Produit p);
     public  ObservableList<Produit> afficherProduits();
     
    
}
