/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import entities.article;
import javafx.collections.ObservableList;

/**
 *
 * @author allal
 */
public interface IArticle {
     public void ajouterArticle(article a);
     public void modifierArticle(article a);
     public void supprimerArticle(article a);
     public ObservableList<article>afficherArticles();
    
}
