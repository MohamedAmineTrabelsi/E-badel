/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.interfaces;


import Ebadel.entites.SCategorie;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author trabe
 */
public interface ISCategorie {
    public boolean ajouterSCategorie(SCategorie s);
    public boolean modifierSCategorie(SCategorie s,String nom_c, String nom_s_c);
    public void supprimerSCategorie(SCategorie s);
    public ObservableList<SCategorie> afficherSCategorie();
}
