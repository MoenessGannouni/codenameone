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
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.cinema;
import com.mycompany.services.servicecinema;
import java.util.ArrayList;

/**
 *
 * @author rayen
 */
public class ListCinema extends BaseForm {

    public ListCinema(Resources res) {

        super("List Projections", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("List Projections");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });

        Image cinemaImage = res.getImage("cinema.jpg").scaled(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight() / 3);
        Label cinemaLabel = new Label(cinemaImage);
        cinemaLabel.setUIID("Container");
        cinemaLabel.getAllStyles().setMarginTop(2);
        add(cinemaLabel);

        ArrayList<cinema> cinemas = servicecinema.getInstance().getAllCinema();
        for (cinema r : cinemas) {

            Label lab = new Label(r.getNom());
            Style labelStyle = lab.getAllStyles();
            labelStyle.setFgColor(0x000000); // couleur du texte en noir
            labelStyle.setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM)); // police en gras, taille moyenne
            System.out.println("cine" + r);
            Button title = new Button("ajouter salle à " + r.getNom());
            Button update = new Button("update " + r.getId_cinema());

            Button delete = new Button("delete " + r.getNom());
            Button voirplus = new Button("voir les salles " + r.getId_cinema());
            Button location = new Button("voirlocation " + r.getLocalisation());

            
            /*  title.addActionListener(e -> {
                float idres = r.getId_user();
                System.out.println(idres);
                new (this,idres).show();
            });*/
            addAll(lab, title, delete, voirplus,update,location);

            title.addActionListener(e -> {
                float idsalle = r.getId_cinema();
                new AddSalle(res, idsalle).show();
            });

            
delete.addActionListener((evt) -> {
                Message m = new Message("Your cinema has been deleted successfully !");

                if (Dialog.show("Confirmation", "cinema deleted successfully", "supprimer", "non")) {
                    servicecinema.getInstance().deleteCinema(r.getId_cinema());
new ListCinema(res).show();
                } else {
                    System.out.println(" annulé.");
                }

                //   Dialog.show("Alert", "Delete cinema ?", new Command("OK"));
                //    Dialog.show("Success", "cinema deleted successfully", new Command("OK"));
                // Display.getInstance().sendMessage(new String[] {"rima.benhmida@esprit.tn"}, "Confirmation", m);
            });
            voirplus.addActionListener(e -> {
                float idsalle = r.getId_cinema();
                new ListSalle(res, idsalle).show();
            });

            location.addActionListener(e -> {
                String locationweb = r.getLocalisation();
                new apimap(res, locationweb).show();
            });
 update.addActionListener(e -> {
                     float id = r.getId_cinema();

                String nom = r.getNom();
                String description = r.getDescription();
                String localisa = r.getLocalisation();
     System.out.println(id+nom+ description+localisa);
                new updatecinema(res, id,nom, description,localisa).show();
            });
        }

        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
