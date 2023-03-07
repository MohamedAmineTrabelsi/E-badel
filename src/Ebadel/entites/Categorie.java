/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.entites;

/**
 *
 * @author trabe
 */
public class Categorie {
    private int id_c;
    private String nom_c;

    public Categorie() {
    }

    public Categorie(String nom_c) {
        this.nom_c = nom_c;
    }

    public int getId_c() {
        return id_c;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    public String getNom_c() {
        return nom_c;
    }

    public void setNom_c(String nom_c) {
        this.nom_c = nom_c;
    }

    @Override
    public String toString() {
        return  nom_c;
    }

}
