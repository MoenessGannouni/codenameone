/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entites;

/**
 *
 * @author rayen
 */
public class salle {

    private float id_salle;
    private String nom ;
    private float longueur;
    private float largeur;
    private float id_cinema;
        private boolean  acces;

    public boolean isAcces() {
        return acces;
    }

    public void setAcces(boolean acces) {
        this.acces = acces;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
        


    public float getId_salle() {
        return id_salle;
    }

    public void setId_salle(float id_salle) {
        this.id_salle = id_salle;
    }

    public float getLongueur() {
        return longueur;
    }

    public void setLongueur(float longueur) {
        this.longueur = longueur;
    }

    public float getLargeur() {
        return largeur;
    }

    public void setLargeur(float largeur) {
        this.largeur = largeur;
    }

    public float getId_cinema() {
        return id_cinema;
    }

    public void setId_cinema(float id_cinema) {
        this.id_cinema = id_cinema;
    }

    public salle(float id_salle, String nom,float longueur, float largeur, float id_cinema, boolean acces) {
        this.id_salle = id_salle;
        this.longueur = longueur;
        this.largeur = largeur;
        this.id_cinema = id_cinema;
        this.acces = acces;
        this.nom=nom;
    }

    public salle(String nom,float longueur, float largeur, float id_cinema, boolean acces) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.id_cinema = id_cinema;
        this.acces = acces;
                this.nom=nom;

    }
    
    
      public salle(String nom,float longueur, float largeur, float id_cinema) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.id_cinema = id_cinema;
                this.nom=nom;

    }

    public salle() {
    }

    @Override
    public String toString() {
        return   "nom=" + nom  ;
    }

    

   
    
    
    

}
