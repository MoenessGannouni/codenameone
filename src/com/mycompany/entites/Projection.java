/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites;

/**
 *
 * @author selim
 */
public class Projection {

    private float id_projection;
    private float id_salle;
    private float id_film;
    private String date_debut;
    private String date_fin;
    private float nbr_places;
    private boolean diffuse;
    public Projection() {
    }

    public Projection(float id_projection, float id_salle, float id_film, String date_debut, String date_fin, float nbr_places, boolean diffuse) {
        this.id_projection = id_projection;
        this.id_salle = id_salle;
        this.id_film = id_film;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nbr_places = nbr_places;
        this.diffuse = diffuse;
    }

    public Projection(float id_salle, float id_film, String date_debut, String date_fin, float nbr_places, boolean diffuse) {
        this.id_salle = id_salle;
        this.id_film = id_film;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nbr_places = nbr_places;
        this.diffuse = diffuse;
    }

    @Override
    public String toString() {
        return "Projection{" +
                "id_projection=" + id_projection +
                ", id_salle=" + id_salle +
                ", id_film=" + id_film +
                ", date_debut='" + date_debut + '\'' +
                ", date_fin='" + date_fin + '\'' +
                ", nbr_places=" + nbr_places +
                ", diffuse=" + diffuse +
                '}';
    }

    public float getId_projection() {
        return id_projection;
    }

    public void setId_projection(float id_projection) {
        this.id_projection = id_projection;
    }

    public float getId_salle() {
        return id_salle;
    }

    public void setId_salle(float id_salle) {
        this.id_salle = id_salle;
    }

    public float getId_film() {
        return id_film;
    }

    public void setId_film(float id_film) {
        this.id_film = id_film;
    }

    public boolean isDiffuse() {
        return diffuse;
    }

    public void setDiffuse(boolean diffuse) {
        this.diffuse = diffuse;
    }

    public float getNbr_places() {
        return nbr_places;
    }

    public void setNbr_places(float nbr_places) {
        this.nbr_places = nbr_places;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }
}
