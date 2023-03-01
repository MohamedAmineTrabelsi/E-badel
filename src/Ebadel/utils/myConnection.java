/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author allal
 */
public class myConnection {
    String url="jdbc:mysql://localhost:3306/badel";
    String login="root";
    String pwd="";
    private Connection cnx;
    private static myConnection instance;

    private myConnection() {
        try {
         cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }
    
    public static myConnection getInstance(){
        if(instance == null){
            instance = new myConnection();
        }
         return instance;
    }
}
