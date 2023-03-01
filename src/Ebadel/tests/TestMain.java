/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.tests;

import Ebadel.entites.Categorie;
import Ebadel.entites.SCategorie;
import Ebadel.services.CategorieCRUD;
import Ebadel.services.MarqueCRUD;
import Ebadel.services.ProduitCRUD;
import Ebadel.services.SCategorieCRUD;

/**
 *
 * @author trabe
 */
public class TestMain {
  public static void main(String[] args) {
      /* try {
           myConexBD = DriverManager.getConnection(url,login, password);
           System.out.println("connection reussie");
           Statement ste= myConexBD.createStatement();
       } catch (SQLException ex) {
           System.out.println(ex);
       }
   */
     // Categorie p1 =new Categorie("table");
     // CategorieCRUD pservice= new CategorieCRUD();
     //pservice.ajouterCategorie(p1);
     //pservice.supprimerCategorie(p1);
     // pservice.modifierCategorie(p1,"chossure");
     // pservice.afficherCategorie();
     /* SCategorieCRUD a=new SCategorieCRUD();
      a.afficherSCategorieParCategorie("ww");*/
      MarqueCRUD a=new MarqueCRUD();
      a.afficherMarqueParSCategorie("Ordinateurs");
      
      ProduitCRUD p =new ProduitCRUD();
      p.getnbProdParCateg("informatiques");
       }  
}
