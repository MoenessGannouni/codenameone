/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites;

import java.util.Date;

/**
 *
 * @author Home
 */
public class Abonnement {
    private int id_abonnement;
    private int id_user;
    private String type;
    private Date dateDebut;
    private Date dateExpiration;  

    public Abonnement() {
    }

    public Abonnement(String type) {
        this.type = type;
    }
      public Abonnement(int id_abonnement) {
        this.id_abonnement = id_abonnement;
    }

    public Abonnement(int id_user, String type, Date dateDebut, Date dateExpiration) {
        this.id_user = id_user;
        this.type = type;
        this.dateDebut = dateDebut;
        this.dateExpiration = dateExpiration;
    }

    public Abonnement(String type, Date dateDebut, Date dateExpiration) {
        this.type = type;
        this.dateDebut = dateDebut;
        this.dateExpiration = dateExpiration;

    }
    

    public Abonnement(int id_abonnement, int id_user, String type, Date dateDebut, Date dateExpiration) {
        this.id_abonnement = id_abonnement;
        this.id_user = id_user;
        this.type = type;
        this.dateDebut = dateDebut;
        this.dateExpiration =dateExpiration;
    }

    public Abonnement(String type,int id_user) {
        this.id_user=id_user;
        this.type=type;
    }

    public int getId_abonnement() {
        return id_abonnement;
    }

    public void setId_abonnement(int id_abonnement) {
        this.id_abonnement = id_abonnement;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
        
    }
    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
        
    }

    public Date getDateExpiration() {
        return  dateExpiration;
    }


    
    
}
