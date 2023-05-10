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
import com.mycompany.services.serviceReservation;
import static com.mycompany.services.serviceReservation.getInstance;

/**
 *
 * @author kortb
 */
public class ReservationDetail extends Form {

   // public static float idres;
    
   public ReservationDetail(Form previous, float idres,Resources res) {
    Reservation reservation = serviceReservation.getInstance().getReservation(idres);

    if (reservation != null) {
        Label idLabel = new Label("Reservation ID: " + reservation.getId_reservation());
        add(idLabel);

        Label userLabel = new Label("User ID: " + reservation.getId_user());
        add(userLabel);

        Label filmLabel = new Label("Film ID: " + reservation.getId_film());
        add(filmLabel);

        Label priceLabel = new Label("Price: " + reservation.getPrix_final());
        add(priceLabel);
        
    } else {
        System.out.println("Failed to retrieve reservation");
    }

    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
}

}
