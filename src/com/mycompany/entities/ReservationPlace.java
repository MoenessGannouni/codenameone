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
public class ReservationPlace {
    private float idResPlace;
    private float idReservation;
    private String coordonnee;
    private float prix;

    public ReservationPlace(float idResPlace, float idReservation, String coordonnee, float prix) {
        this.idResPlace = idResPlace;
        this.idReservation = idReservation;
        this.coordonnee = coordonnee;
        this.prix = prix;
    }

    public ReservationPlace(float idReservation, String coordonnee, float prix) {
        this.idReservation = idReservation;
        this.coordonnee = coordonnee;
        this.prix = prix;
    }

    public ReservationPlace() {
    }

    public float getIdResPlace() {
        return idResPlace;
    }

    public void setIdResPlace(float idResPlace) {
        this.idResPlace = idResPlace;
    }

    public float getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(float idReservation) {
        this.idReservation = idReservation;
    }

    public String getCoordonnee() {
        return coordonnee;
    }

    public void setCoordonnee(String coordonnee) {
        this.coordonnee = coordonnee;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "ReservationPlace{" + "idResPlace=" + idResPlace + ", idReservation=" + idReservation + ", coordonnee=" + coordonnee + ", prix=" + prix + '}';
    }

   
    
    
}
