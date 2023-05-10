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
public class Reservation {
   private float id_reservation;
   private float prix_final;
   private float id_user;
   private float id_film;
   private boolean state;
   private String start_time;
   private String end_time;
   private float id_projection;
  
    public Reservation() {
    }

    public Reservation(float id_reservation, float prix_final, float id_user, float id_film, boolean state, String start_time, String end_time, float id_projection) {
        this.id_reservation = id_reservation;
        this.prix_final = prix_final;
        this.id_user = id_user;
        this.id_film = id_film;
        this.state = state;
        this.start_time = start_time;
        this.end_time = end_time;
        this.id_projection = id_projection;
    }

    public Reservation(float id_user, float id_film, boolean state, String start_time, String end_time, float id_projection) {
        this.id_user = id_user;
        this.id_film = id_film;
        this.state = state;
        this.start_time = start_time;
        this.end_time = end_time;
        this.id_projection = id_projection;
    }

    public Reservation(float id_user, float id_film, boolean state, float id_projection) {
        this.prix_final = 0f;
        this.id_user = id_user;
        this.id_film = id_film;
        this.state = state;
        this.id_projection = id_projection;
    }

    public Reservation(float prix_final, float id_user, float id_film, boolean state, String start_time, String end_time, float id_projection) {
        this.prix_final = prix_final;
        this.id_user = id_user;
        this.id_film = id_film;
        this.state = state;
        this.start_time = start_time;
        this.end_time = end_time;
        this.id_projection = id_projection;
    }

    public Reservation(float prix_final, float iduser, float idfilm, boolean state, float id_projection) {
         this.prix_final = prix_final;
        this.id_user = id_user;
        this.id_film = id_film;
        this.state = state;
       this.id_projection = id_projection;
    }

    public Reservation(float id_user, float id_film, float id_projection) {
        this.id_user = id_user;
        this.id_film = id_film;
        this.id_projection = id_projection;
    }
    

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public float getId_user() {
        return id_user;
    }

    public void setId_user(float id_user) {
        this.id_user = id_user;
    }

    public float getId_film() {
        return id_film;
    }

    public void setId_film(float id_film) {
        this.id_film = id_film;
    }

    public float getId_projection() {
        return id_projection;
    }

    public void setId_projection(float id_projection) {
        this.id_projection = id_projection;
    }

    
    

    public void setId_reservation(float id_reservation) {
        this.id_reservation = id_reservation;
    }

    public float getPrix_final() {
        return prix_final;
    }

    public void setPrix_final(float prix_final) {
        this.prix_final = prix_final;
    }

    public float getId_reservation() {
        return id_reservation;
    }

   
   

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    @Override
    public String toString() {
        return "reservation{" + "id_reservation=" + id_reservation + ", prix_final=" + prix_final + ", id_user=" + id_user + ", id_film=" + id_film + ", state=" + state + ", start_time=" + start_time + ", end_time=" + end_time + ", id_projection=" + id_projection + '}';
    }

 public String getDescription() {
        // Return a description based on the reservation data
        // Example implementation:
        return "Reservation for film with ID " + id_film;
    }

    // Add getCodeName() method
    public String getCodeName() {
        // Return a code name based on the reservation data
        // Example implementation:
        return "Reservation #" + id_reservation;
    }
}
