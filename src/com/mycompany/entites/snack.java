/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites;

/**
 *
 * @author rayen
 */
public class snack {

    private float id_snack;
    private String nom;
    private float prix;
    private float quantite;
    private String photo;
    private float id_cinema;

    
    public snack() {
    }

    public snack(String nom, float prix, float quantite, String photo, float id_cinema) {
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.photo = photo;
        this.id_cinema = id_cinema;
    }
    
      public snack(String nom, float prix, int quantite, String photo) {
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.photo = photo;
    }

    public float getId_snack() {
        return id_snack;
    }

    public void setId_snack(float id_snack) {
        this.id_snack = id_snack;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public float getQuantite() {
        return quantite;
    }

    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }



    public float getId_cinema() {
        return id_cinema;
    }

    public void setId_cinema(float id_cinema) {
        this.id_cinema = id_cinema;
    }

    public snack(float id_snack, String nom, float prix, float quantite, String photo, float id_cinema) {
        this.id_snack = id_snack;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.photo = photo;
        this.id_cinema = id_cinema;
    }

    @Override
    public String toString() {
        return  " nom=" + nom + ", prix=" + prix  ;
    }
    
    

}
