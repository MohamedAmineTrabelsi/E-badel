/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import entities.Marque;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author trabe
 */
public interface IMarque {
    public boolean ajouterMarque(Marque m);
    public boolean modifierMarque(Marque m, String nom_m, String nom_c, String nom_s_c);
    public void supprimerMarque(Marque m);
    public ObservableList<Marque> afficherMarque();
}
