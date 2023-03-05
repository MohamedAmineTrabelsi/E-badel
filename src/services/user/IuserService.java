/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.user;


import java.sql.SQLException;
import entities.user;
import entities.user;

import java.util.List;
/**
 *
 * @author remo
 */
public interface IuserService {
    void ajouter(user user) throws SQLException;;
    void modifier(int id,user user) throws SQLException;;
    void supprimer(int id) throws SQLException;
    List<user> afficher() throws SQLException;
    
    public int nbUsers() throws SQLException;
}