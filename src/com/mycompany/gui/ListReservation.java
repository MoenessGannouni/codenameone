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
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Reservation;
import com.mycompany.services.serviceReservation;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author kortb
 */
public class ListReservation extends Form {

    public ListReservation(Resources res,Form previous) {
        ArrayList<Reservation> reservations = serviceReservation.getInstance().getAllReservation();
        for (Reservation r : reservations) {
            Button title = new Button("reservation number: " + r.getId_reservation());
                     Button sup = new Button("Delete");
                    sup.addActionListener((evt) -> {
                   serviceReservation.getInstance().deleteReservation(r.getId_reservation());
                    Dialog.show("Alert", "Delete Reservation  ?", new Command("OK"));
                    Dialog.show("Success", "Reservation deleted successfully", new Command("OK"));
                     Message m = new Message("Your Reservation has been deleted successfully !");
                    });
            title.addActionListener(e -> {
                float idres = r.getId_user();
                System.out.println(idres);
                new ReservationDetail(this,idres,res).show();
            });
            addAll(title,sup);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
