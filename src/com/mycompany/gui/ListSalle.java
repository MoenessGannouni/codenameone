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
import com.mycompany.entites.salle;
import com.mycompany.services.SalleService;
import com.mycompany.services.servicecinema;
import java.util.ArrayList;

/**
 *
 * @author rayen
 */
public class ListSalle extends BaseForm{
    public ListSalle(Resources res,float id) {
                  
super("List salle", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("List Projections");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Image cinemaImage = res.getImage("cinema.jpg").scaled(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight() / 3);
            Label cinemaLabel = new Label(cinemaImage);
            cinemaLabel.setUIID("Container");
            cinemaLabel.getAllStyles().setMarginTop(2);
            add(cinemaLabel);
          
          
          
        ArrayList<salle> salles = SalleService.getInstance().getsalle(id);
        for (salle r : salles) {
            
            Label lab = new Label(r.getNom());
            Style labelStyle = lab.getAllStyles();
labelStyle.setFgColor(0x000000); // couleur du texte en noir
labelStyle.setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM)); // police en gras, taille moyenne

                     Button title = new Button("ajouter projection à " + r.getNom());
             Button delete = new Button("delete " + r.getNom());

                     
                   delete.addActionListener((evt) -> {
                                        Message m = new Message("Your salle has been deleted successfully !");

                   if (Dialog.show("Confirmation", "salle deleted successfully", "supprimer", "non")) {
                               SalleService.getInstance().deleteSalle(r.getId_salle());


        } else {
            System.out.println(" annulé.");
        }
                   
                 //   Dialog.show("Alert", "Delete cinema ?", new Command("OK"));
                //    Dialog.show("Success", "cinema deleted successfully", new Command("OK"));
                       // Display.getInstance().sendMessage(new String[] {"rima.benhmida@esprit.tn"}, "Confirmation", m);
                    });
          /*  title.addActionListener(e -> {
                float idres = r.getId_user();
                System.out.println(idres);
                new (this,idres).show();
            });*/
           addAll(lab,title,delete);
           
           title.addActionListener(e -> {
                float idsalle = r.getId_cinema();
                 new AddSalle(res,idsalle).show();
            });
           
           
        }

        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
