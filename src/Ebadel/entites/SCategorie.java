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
public class SCategorie {
    private int id_s_c;
    private String nom_c; 
    private String nom_s_c; 

    public SCategorie() {
    }

    public SCategorie(String nom_c, String nom_s_c) {
        this.nom_c = nom_c;
        this.nom_s_c = nom_s_c;
    }

    public int getId_s_c() {
        return id_s_c;
    }

    public void setId_s_c(int id_s_c) {
        this.id_s_c = id_s_c;
    }

    public String getNom_c() {
        return nom_c;
    }

    public void setNom_c(String nom_c) {
        this.nom_c = nom_c;
    }

    public String getNom_s_c() {
        return nom_s_c;
    }

    public void setNom_s_c(String nom_s_c) {
        this.nom_s_c = nom_s_c;
    }

    @Override
    public String toString() {
        return nom_s_c;
    }
    
    //klsmqjdu
}
