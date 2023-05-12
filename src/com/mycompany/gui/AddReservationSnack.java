/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.reservation_snack;
import com.mycompany.entities.Reservation;
import com.mycompany.entities.ReservationSnack;
import com.mycompany.services.serviceReservation;
import com.mycompany.services.serviceReservationSnack;

/**
 *
 * @author kortb
 */
public class AddReservationSnack extends Form{
    private Form current;

    public AddReservationSnack(Resources res,Form previous) {
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Reservation");
        getContentPane().setScrollVisible(false);

        Tabs swipe = new Tabs();

        Label s1 = new Label();
        Label s2 = new Label();


        //TextField idressnack = new TextField("", "entrer id reservation snack!!");
        //idressnack.setUIID("id");
        
        TextField quantite = new TextField("", "entrer quantite!!");
        quantite.setUIID("quantite");

        TextField idreservation = new TextField("", "entrer id reservation!!");
        idreservation.setUIID("idreservation");

        TextField prix = new TextField("", "entrer prix!!");
        prix.setUIID("projection");
        
        TextField idsnack = new TextField("", "entrer idsnack!!");
        idsnack.setUIID("idsnack");

        Button btnAjouter = new Button("Ajouter");

        btnAjouter.addActionListener((e) -> {
            if ((quantite.getText().length()==0) || (idreservation.getText().length()==0) || (prix.getText().length())==0)
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            else{
            try {
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog iDialog = ip.showInfiniteBlocking();

                float Quantite = Float.parseFloat(quantite.getText());
                float id_reservation = Float.parseFloat(idreservation.getText());
                float Prix = Float.parseFloat(prix.getText());
                float idSnack = Float.parseFloat(idsnack.getText());

              /*  ReservationSnack r = new ReservationSnack(Quantite,Prix,id_reservation,idSnack);
                for(ReservationSnack rr:editpanier.snackreservation1iste){
                    serviceReservationSnack.getInstance().ajoutReservation(rr);
                }*/
             //   System.out.println(r);
               // serviceReservationSnack.getInstance().ajoutReservation(r);

                iDialog.dispose();

                refreshTheme();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            }
        });

        addAll(quantite, idreservation, prix,idsnack, btnAjouter);
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}
