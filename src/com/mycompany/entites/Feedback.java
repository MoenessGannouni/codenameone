/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entites;

/**
 *
 * @author acer
 */
public class Feedback {
     private float idFeedback;
     private String feedback;
    private float idUser;
    private float idfilm;
    private String date;

    public Feedback() {
    }

    public Feedback(float idFeedback) {
        this.idFeedback = idFeedback;
    }

    public Feedback(float idFeedback, String feedback, float idUser, float idfilm, String date) {
        this.idFeedback = idFeedback;
        this.feedback = feedback;
        this.idUser = idUser;
        this.idfilm = idfilm;
        this.date = date;
    }

    public Feedback(String feedback, float idUser, float idfilm, String date) {
        this.feedback = feedback;
        this.idUser = idUser;
        this.idfilm = idfilm;
        this.date = date;
    }

    public Feedback(String feedback, float idUser, float idfilm) {
        this.feedback = feedback;
        this.idUser = idUser;
        this.idfilm = idfilm;
    }

    public float getIdFeedback() {
        return idFeedback;
    }

    public String getFeedback() {
        return feedback;
    }

    public float getIdUser() {
        return idUser;
    }

    public float getIdfilm() {
        return idfilm;
    }

    public String getDate() {
        return date;
    }

    public void setIdFeedback(float idFeedback) {
        this.idFeedback = idFeedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setIdUser(float idUser) {
        this.idUser = idUser;
    }

    public void setIdfilm(float idfilm) {
        this.idfilm = idfilm;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Feedback{" + "idFeedback=" + idFeedback + ", feedback=" + feedback + ", idUser=" + idUser + ", idfilm=" + idfilm + ", date=" + date + '}';
    }
    
    
    
}
