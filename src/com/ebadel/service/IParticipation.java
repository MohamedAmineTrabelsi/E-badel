/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebadel.service;

import com.ebadel.entity.Participation;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public interface IParticipation {
     public void ajouterParticipation(Participation  p);
    public void modifierParticipation(int id,int userId,int gameId);
     public void supprimerParticipation(int id_j);
     public void afficherParticipation();
     public List<Participation> afficherParticipat();
public ObservableList<Participation> afficherParticipa();
}
