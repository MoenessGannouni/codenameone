/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Reservation;
import com.mycompany.services.serviceReservation;

/**
 *
 * @author kortb
 */
public class ModifierReservation extends Form {
    private Reservation reservation;

    public ModifierReservation(Resources res) {

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier Reservation");
        getContentPane().setScrollVisible(false);

        /*TextField id = new TextField(String.valueOf(reservation.getId_reservation()), "ID", 20, TextField.ANY);
        TextField film = new TextField(String.valueOf(reservation.getId_film()), "Film", 20, TextField.ANY);
        TextField user = new TextField(String.valueOf(reservation.getId_user()), "User", 20, TextField.ANY);
        TextField projection = new TextField(String.valueOf(reservation.getId_projection()), "Projection", 20, TextField.ANY);*/

        TextField id = new TextField("", "entrer id!!");
        id.setUIID("id");
        
        TextField film = new TextField("", "entrer film!!");
        film.setUIID("film");

        TextField user = new TextField("", "entrer user!!");
        user.setUIID("user");

        TextField projection = new TextField("", "entrer projection!!");
        projection.setUIID("projection");
        
        Button btnModifier = new Button("Modifier");
        btnModifier.addActionListener(e -> {
            try {
                float id_res = Float.parseFloat(id.getText());
                float id_film = Float.parseFloat(film.getText());
                float id_user = Float.parseFloat(user.getText());
                float id_projection = Float.parseFloat(projection.getText());
                
                boolean success = serviceReservation.getInstance().modifierReservation((int)id_res,(int)id_film,(int)id_user,(int)id_projection);

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

        addAll(id, film, user, projection, btnModifier);
    }
}


