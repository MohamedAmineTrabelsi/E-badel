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
public class Marque {
    
    private int id_m;
    private String nom_m; 
    private String nom_c;
    private String nom_s_c;

    public Marque() {
    }

    public Marque(String nom_m, String nom_c, String nom_s_c) {
        this.nom_m = nom_m;
        this.nom_c = nom_c;
        this.nom_s_c = nom_s_c;
    }


    public int getId_m() {
        return id_m;
    }

    public void setId_m(int id_m) {
        this.id_m = id_m;
    }

    public String getNom_m() {
        return nom_m;
    }

    public void setNom_m(String nom_m) {
        this.nom_m = nom_m;
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
        return nom_m ;
    }
}
