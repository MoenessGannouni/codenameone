/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Reservation;
import com.mycompany.entities.ReservationPlace;
import com.mycompany.services.serviceReservation;
import com.mycompany.services.serviceReservationPlace;
import java.util.ArrayList;

/**
 *
 * @author kortb
 */
public class ListReservationPlace extends Form{
     public ListReservationPlace(Resources res,Form previous) {
        ArrayList<ReservationPlace> reservations = serviceReservationPlace.getInstance().getAllReservationPlace();
        for (ReservationPlace r : reservations) {
            Button title = new Button("reservation number: " + r.getIdResPlace());
                     Button sup = new Button("Delete");
                    sup.addActionListener((evt) -> {
                   serviceReservation.getInstance().deleteReservation(r.getIdReservation());
                    Dialog.show("Alert", "Delete Reservation Place ?", new Command("OK"));
                    Dialog.show("Success", "Reservation Place deleted successfully", new Command("OK"));
                     Message m = new Message("Your Reservation Place has been deleted successfully !");
                    });
            title.addActionListener(e -> {
                float idres = r.getIdReservation();
                System.out.println(idres);
                new ReservationPlaceDetail(this,idres,res).show();
            });
            addAll(title,sup);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
