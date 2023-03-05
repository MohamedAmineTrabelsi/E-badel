/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebadel.entity;

import java.util.Date;

/**
 *
 * @author achou
 */
public class Jeux {
    private int id_j;
    private int nb_per;
    private String titre_j;
    private String type_j;
    private String date_deb_j;
    private String date_fin_j ;
    private String image_p;
    private int prix;
    

    public void setImage_p(String image_p) {
        this.image_p = image_p;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getImage_p() {
        return image_p;
    }

    public int getPrix() {
        return prix;
    }

    public int getId_j() {
        return id_j;
    }

    public Jeux(int nb_per, String titre_j, String type_j, String date_deb_j, String date_fin_j, String image_p, int prix) {
        this.nb_per = nb_per;
        this.titre_j = titre_j;
        this.type_j = type_j;
        this.date_deb_j = date_deb_j;
        this.date_fin_j = date_fin_j;
        this.image_p = image_p;
        this.prix = prix;
       
    }

    public String getTitre_j() {
        return titre_j;
    }

    public String getType_j() {
        return type_j;
    }

    public String getDate_deb_j() {
        return date_deb_j;
    }

    public String getDate_fin_j() {
        return date_fin_j;
    }

    public void setId_j(int id_j) {
        this.id_j = id_j;
    }

    public void setTitre_j(String titre_j) {
        this.titre_j = titre_j;
    }

    public void setType_j(String type_j) {
        this.type_j = type_j;
    }

    public void setDate_deb_j(String date_deb_j) {
        this.date_deb_j = date_deb_j;
    }

    public void setDate_fin_j(String date_fin_j) {
        this.date_fin_j = date_fin_j;
    }

    public int getNb_per() {
        return nb_per;
    }

    public void setNb_per(int nb_per) {
        this.nb_per = nb_per;
    }

    public Jeux() {
    }


    public Jeux(int id_j, int nb_per, String titre_j, String type_j, String date_deb_j, String date_fin_j,String image_p,int prix) {
        this.id_j = id_j;
        this.nb_per = nb_per;
        this.titre_j = titre_j;
        this.type_j = type_j;
        this.date_deb_j = date_deb_j;
        this.date_fin_j = date_fin_j;
       this.image_p = image_p;
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Jeux{" + "id_j=" + id_j + ", nb_per=" + nb_per + ", titre_j=" + titre_j + ", type_j=" + type_j + ", date_deb_j=" + date_deb_j + ", date_fin_j=" + date_fin_j + ", image_p=" + image_p + ", prix=" + prix + '}';
    }



 
    
    
}
