/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import java.sql.Connection;
import services.user.ArticleServices;
import services.user.OffreServices;
import view.PartieClient.ClientPartieController;
import view.PartieClient.MesArticlesController;

/**
 *
 * @author allal
 */
public class mainJava {
     public static void main(String[] args) {
 
         ArticleServices as = new ArticleServices();
        System.out.println( as.afficherArticlesParId(842));
       // MesArticlesController ma = new MesArticlesController();
        // ma.afficherArticles();
       //  ClientPartieController cp = new ClientPartieController();
        OffreServices os = new OffreServices();
        os.afficherListeDesoffres(33);
}
}