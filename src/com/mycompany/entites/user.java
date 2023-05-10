/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author MOEµNESS
 */
public class user {
    private int id_user;
    private String email;
    private String password;
    private String nom;
    private String prenom;
    private String date_naissance;
    private String pseudo;
    private int tel;
    private String roles;
    private float montant;
    private int id_badge;

    public user(int id_user, String email, String password, String nom, String prenom, String date_naissance, String pseudo, int tel, String roles, float montant, int id_badge) {
        this.id_user = id_user;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.pseudo = pseudo;
        this.tel = tel;
        this.roles = roles;
        this.montant = montant;
        this.id_badge = id_badge;
    }

    public user(String email, String password, String nom, String prenom, String date_naissance, String pseudo, int tel, String roles, float montant, int id_badge) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.pseudo = pseudo;
        this.tel = tel;
        this.roles = roles;
        this.montant = montant;
        this.id_badge = id_badge;
    }

    public int getId_badge() {
        return id_badge;
    }

    public void setId_badge(int id_badge) {
        this.id_badge = id_badge;
    }

    
    
    
    
    
    //Getters Setters
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }
    
    
}