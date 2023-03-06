package Interfaces;

import entities.offre;
import javafx.collections.ObservableList;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author allal
 */
public interface IOffre {
     public void ajouterOffre(offre o);
     public ObservableList<offre> afficherListeDesoffres(int id_article);
     public void supprimerOffre(offre o);
    
    
}
