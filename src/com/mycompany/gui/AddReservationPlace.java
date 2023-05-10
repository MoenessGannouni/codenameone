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
import com.mycompany.entities.ReservationPlace;
import com.mycompany.entities.ReservationSnack;
import com.mycompany.services.serviceReservationPlace;
import com.mycompany.services.serviceReservationSnack;

/**
 *
 * @author kortb
 */
public class AddReservationPlace extends Form{
    private Form current;

    public AddReservationPlace(Resources res,Form previous) {
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
        
        TextField Seat = new TextField("", "entrer Seat!!");
        Seat.setUIID("quantite");

        TextField Prix = new TextField("", "entrer Prix!!");
        Prix.setUIID("idreservation");

        TextField idRes = new TextField("", "entrer id Reservation!!");
        idRes.setUIID("projection");
        
        

        Button btnAjouter = new Button("Ajouter");

        btnAjouter.addActionListener((e) -> {
            if ((Seat.getText().length()==0) || (Prix.getText().length()==0) || (idRes.getText().length())==0)
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            else{
            try {
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog iDialog = ip.showInfiniteBlocking();

                String seat = Seat.getText();
                float prix = Float.parseFloat(Prix.getText());
                float id_Res = Float.parseFloat(idRes.getText());

                ReservationPlace r = new ReservationPlace(id_Res,seat,prix);
                System.out.println(r);
                serviceReservationPlace.getInstance().ajoutReservation(r);

                iDialog.dispose();

                refreshTheme();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            }
        });

        addAll(Seat, Prix,idRes, btnAjouter);
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}
