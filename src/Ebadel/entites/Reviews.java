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
    private int article_id;
    private int user_id;
    private String comment;
    private double rate;
    private Rating starRating;

    public Reviews(int article_id, int user_id, String comment, Rating starRating,double rate) {
        this.article_id = article_id;
        this.user_id = user_id;
        this.comment = comment;
        this.starRating = starRating;
         this.rate = rate;
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

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
                ", article_id=" + article_id +
                ", user_id=" + user_id +
                ", comment='" + comment + '\'' +
                ", starRating=" + starRating +
                 ", rate=" + rate +
                '}';
    }
}
