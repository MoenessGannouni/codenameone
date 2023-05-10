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
import com.mycompany.entities.Reservation;
import com.mycompany.entities.ReservationPlace;
import com.mycompany.services.serviceReservation;
import com.mycompany.services.serviceReservationPlace;

/**
 *
 * @author kortb
 */
public class ReservationPlaceDetail extends Form{
     public ReservationPlaceDetail(Form previous, float idres,Resources res) {
    ReservationPlace reservation = serviceReservationPlace.getInstance().getReservation(idres);

    if (reservation != null) {
        Label idLabel = new Label("Reservation place ID: " + reservation.getIdResPlace());
        add(idLabel);

        Label userLabel = new Label("reservation ID: " + reservation.getIdReservation());
        add(userLabel);

        Label filmLabel = new Label("Prix: " + reservation.getPrix());
        add(filmLabel);

        Label priceLabel = new Label("Seats: " + reservation.getCoordonnee());
        add(priceLabel);
        
    } else {
        System.out.println("Failed to retrieve reservation");
    }

    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
}

}
