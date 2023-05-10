/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.ReservationPlace;
import com.mycompany.entities.ReservationSnack;
import com.mycompany.services.serviceReservationPlace;
import com.mycompany.services.serviceReservationSnack;

/**
 *
 * @author kortb
 */
public class ReservationSnackDetail extends Form{
       public ReservationSnackDetail(Form previous, float idres,Resources res) {
    ReservationSnack reservation = serviceReservationSnack.getInstance().getReservation(idres);

    if (reservation != null) {
        Label idLabel = new Label("Reservation Snack ID: " + reservation.getIdResSnack());
        add(idLabel);

        Label userLabel = new Label("Snack ID: " + reservation.getIdSnack());
        add(userLabel);

        Label filmLabel = new Label("Prix: " + reservation.getPrix());
        add(filmLabel);

        Label priceLabel = new Label("Quantite: " + reservation.getQuantite());
        add(priceLabel);
        
    } else {
        System.out.println("Failed to retrieve reservation");
    }

    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
}
}
