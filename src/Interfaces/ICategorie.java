/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import entities.Categorie;
import javafx.collections.ObservableList;

/**
 *
 * @author trabe
 */
public interface ICategorie {
    public boolean ajouterCategorie(Categorie c);
    public boolean modifierCategorie(Categorie c,String nom_c);
    public void supprimerCategorie(Categorie c);
    public ObservableList<Categorie> afficherCategorie();
}
