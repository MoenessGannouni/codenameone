/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author kortb
 */
public class ReservationSnack {
    private float idResSnack;
    private float quantite;
    private float prix;
    private float idReservation;
    private float idSnack;

    public ReservationSnack(float quantite, float prix, float idReservation, float idSnack) {
        this.quantite = quantite;
        this.prix = prix;
        this.idReservation = idReservation;
        this.idSnack = idSnack;
    }

    public ReservationSnack(float idResSnack, float quantite, float prix, float idReservation, float idSnack) {
        this.idResSnack = idResSnack;
        this.quantite = quantite;
        this.prix = prix;
        this.idReservation = idReservation;
        this.idSnack = idSnack;
    }
    
    
   
    public ReservationSnack() {
    }

    public float getIdResSnack() {
        return idResSnack;
    }

    public void setIdResSnack(float idResSnack) {
        this.idResSnack = idResSnack;
    }

    public float getQuantite() {
        return quantite;
    }

    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public float getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(float idReservation) {
        this.idReservation = idReservation;
    }

    @Override
    public String toString() {
        return "ReservationSnack{" + "idResSnack=" + idResSnack + ", quantite=" + quantite + ", prix=" + prix + ", idReservation=" + idReservation + '}';
    }

    public float getIdSnack() {
        return idSnack;
    }

    public void setIdSnack(float idSnack) {
        this.idSnack = idSnack;
    }
    
    
    
}
