/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebadel.entity;



/**
 *
 * @author ASUS
 */
public class Participation {
    private int id;
    private int userId;
    private int gameId;

    public Participation() {
    }

    public Participation(int userId, int gameId) {
        this.userId = userId;
        this.gameId = gameId;
    }

    public Participation(int id, int userId, int gameId) {
        this.id = id;
        this.userId = userId;
        this.gameId = gameId;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @Override
    public String toString() {
        return "Participation{" + "id=" + id + ", userId=" + userId + ", gameId=" + gameId + '}';
    }

    

  
}
