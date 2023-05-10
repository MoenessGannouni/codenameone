/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Reservation;
import com.mycompany.entities.ReservationPlace;
import com.mycompany.services.serviceReservation;
import com.mycompany.services.serviceReservationSnack;

/**
 *
 * @author kortb
 */
public class ModifierReservationSnack extends Form{
 private ReservationPlace reservation;

    public ModifierReservationSnack(Resources res) {

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier Reservation");
        getContentPane().setScrollVisible(false);


        TextField idressnack = new TextField("", "entrer id reservation snack!!");
        idressnack.setUIID("id");
        
        TextField quantite = new TextField("", "entrer quantite!!");
        quantite.setUIID("quantite");

        TextField idreservation = new TextField("", "entrer id reservation!!");
        idreservation.setUIID("idreservation");

        TextField prix = new TextField("", "entrer prix!!");
        prix.setUIID("projection");
        
        TextField idsnack = new TextField("", "entrer idsnack!!");
        idsnack.setUIID("idsnack");
        
        
        Button btnModifier = new Button("Modifier");
        btnModifier.addActionListener(e -> {
            try {
                float id_ressnack = Float.parseFloat(idressnack.getText());
                float Quantite = Float.parseFloat(quantite.getText());
                float id_reservation = Float.parseFloat(idreservation.getText());
                float Prix = Float.parseFloat(prix.getText());
                float idSnack = Float.parseFloat(idsnack.getText());
                
                boolean success = serviceReservationSnack.getInstance().modifierReservation(id_ressnack,Prix,id_reservation,idSnack,Quantite);

                if (success) {
                    Dialog.show("Success", "Reservation modified successfully", "OK", null);
                } else {
                    Dialog.show("Error", "Failed to modify reservation", "OK", null);
                }
            } catch (NumberFormatException ex) {
                // Handle parsing errors (e.g., invalid integer or float values)
                Dialog.show("Error", "Invalid input values", "OK", null);
            }
        });

        addAll(idressnack, quantite, idreservation, prix,idsnack, btnModifier);
    }   
}
