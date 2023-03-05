/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebadel.service;

import com.ebadel.entity.Jeux;
import java.util.Date;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author achou
 */
public interface JeuxInteService {
     public void ajouterJeux(Jeux p);
    public void modifierJeux(int p_id,int nb_per,String titre_j,String type_j,String date_deb_j,String date_fin_j,String image_p , int prix );
     public void supprimerJeux(int id_j);
     public void afficherJeux();
     public List<Jeux> afficherJeu();
public ObservableList<Jeux> afficherJeuxx();
    
}
