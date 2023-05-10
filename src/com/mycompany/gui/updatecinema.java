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
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.salle;
import com.mycompany.services.SalleService;
import com.mycompany.services.servicecinema;

/**
 *
 * @author rayen
 */
public class updatecinema extends BaseForm {
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

                 private Form current;
                 
                 
                 
                 

    public updatecinema(Resources res,float Id_cine,String nom,String description,String localisation) {
        
super("List Projections", BoxLayout.y());
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
         ////////////////////////////////// 
         
         System.out.println(Id_cine);
         
         
         
         

     

        Tabs swipe = new Tabs();

        Label s1 = new Label();
        Label s2 = new Label();


        TextField cinename = new TextField("", "entrer nom!!");
        cinename.setUIID("nomcine");

        TextField descriptioncine = new TextField("", "entrer description!!");
        descriptioncine.setUIID("description");

         TextField localisationn = new TextField("", "entrer localisationn!!");
        localisationn.setUIID("localisationn");

        Button modif = new Button("modif");

        modif.addActionListener((e) -> {
            if ((cinename.getText().length()==0)||(descriptioncine.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            else{
            try {
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog iDialog = ip.showInfiniteBlocking();

                String cinenom = cinename.getText();
                String descript = descriptioncine.getText();
                
                String loc = localisationn.getText();

                servicecinema.getInstance().modifierCinema(Id_cine,cinenom,descript,loc);

                iDialog.dispose();

                refreshTheme();
                new ListCinema(res).show();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            }
        });

        addAll(cinename, descriptioncine, localisationn,modif);
    }
         
         
         
         
         
         
         
    }

    
    

   
