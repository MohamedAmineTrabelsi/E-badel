/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.entites;



/**
 *
 * @author messaoudi
 */


import org.controlsfx.control.Rating;

public class Reviews {
    private int id;
    private String article_name;

    private String comment;
    private double rate;
    private Rating starRating;
    
     public Reviews() {
    }

    public Reviews(String article_name, String comment, Rating starRating,double rate) {
        this.article_name = article_name;
       
        this.comment = comment;
        this.starRating = starRating;
         this.rate = rate;
    }

    public String getArticle_name() {
        return article_name;
    }

    public void setArticle_name(String article_name) {
        this.article_name = article_name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

   
    

   


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    


   

    

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Rating getStarRating() {
        return starRating;
    }

    public void setStarRating(Rating starRating) {
        this.starRating = starRating;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", article_id=" + article_name +
               
                ", comment='" + comment + '\'' +
                ", starRating=" + starRating +
                 ", rate=" + rate +
                '}';
    }
}
