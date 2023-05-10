/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites;

/**
 *
 * @author Home
 */
public class Badge {
    private int id_badge;
    private String type;
    private int nbr_reservation;
  

    public Badge() {
    }

    public Badge(int id_badge, String type, int nbr_reservation, int id_user) {
        this.id_badge = id_badge;
        this.type = type;
        this.nbr_reservation = nbr_reservation;
      
    }

    public Badge(String type, int nbr_reservation, int id_user) {
        this.type = type;
        this.nbr_reservation = nbr_reservation;
        
    }

    public Badge(String type, int nbr_reservation) {
        this.type = type;
        this.nbr_reservation = nbr_reservation;
    }

    public Badge(int i) {
        this.id_badge=i;
    }
    
    

    public int getId_badge() {
        return id_badge;
    }

    public void setId_badge(int id_badge) {
        this.id_badge = id_badge;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Badge{" + "id_badge=" + id_badge + ", type=" + type + ", nbr_reservation=" + nbr_reservation +  + '}';
    }
    

    public int getNbr_reservation() {
        return nbr_reservation;
    }

    public void setNbr_reservation(int nbr_reservation) {
        this.nbr_reservation = nbr_reservation;
    }
    
}
