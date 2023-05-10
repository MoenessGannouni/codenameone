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
import com.mycompany.entities.Reservation;
import com.mycompany.services.serviceReservation;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author kortb
 */
public class AddReservation extends Form {
    private Form current;

    public AddReservation(Form previous) {
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Reservation");
        getContentPane().setScrollVisible(false);

        Tabs swipe = new Tabs();

        Label s1 = new Label();
        Label s2 = new Label();


        TextField film = new TextField("", "entrer film!!");
        film.setUIID("film");

        TextField user = new TextField("", "entrer user!!");
        user.setUIID("user");

        TextField projection = new TextField("", "entrer projection!!");
        projection.setUIID("projection");

        Button btnAjouter = new Button("Ajouter");

        btnAjouter.addActionListener((e) -> {
            if ((film.getText().length()==0)||(user.getText().length()==0) || (projection.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            else{
            try {
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog iDialog = ip.showInfiniteBlocking();

                float id_film = Float.parseFloat(film.getText());
                float id_user = Float.parseFloat(user.getText());
                float id_projection = Float.parseFloat(projection.getText());

                Reservation r = new Reservation((int)id_user,(int) id_film,(int) id_projection);
                System.out.println("data  reclamation == " + r);

                serviceReservation.getInstance().ajoutReservation(r);

                iDialog.dispose();

                refreshTheme();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            }
        });

        addAll(film, user, projection, btnAjouter);
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}
