/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ProjectionService;
import java.util.StringTokenizer;
import com.mycompany.entites.Film;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entites.salle;
import com.mycompany.services.FilmService;
import com.mycompany.services.SalleService;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author selim
 */
public class AddSalle extends BaseForm{
                 private Form current;
                 
                 
                 
                 

    public AddSalle(Resources res,float id) {
        
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
         
         System.out.println(id);
         
         
         
         

     

        Tabs swipe = new Tabs();

        Label s1 = new Label();
        Label s2 = new Label();


        TextField sallename = new TextField("", "entrer nom!!");
        sallename.setUIID("sallename");

        TextField longeur = new TextField("", "entrer longeur!!");
        longeur.setUIID("longeur");

        TextField largeur = new TextField("", "entrer largeur!!");
       largeur.setUIID("largeur");

        Button btnAjouter = new Button("Ajouter");

        btnAjouter.addActionListener((e) -> {
            if ((sallename.getText().length()==0)||(longeur.getText().length()==0) || (largeur.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            else{
            try {
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog iDialog = ip.showInfiniteBlocking();

                String nomsend = sallename.getText();
                float longeursend = Float.parseFloat(longeur.getText());
                float largeursend = Float.parseFloat(largeur.getText());
                float idcinema = id;

                salle r = new salle(nomsend,longeursend,largeursend,idcinema);
                System.out.println("data  reclamation == " + r);

                SalleService.getInstance().ajoutsalle(r);

                iDialog.dispose();

                refreshTheme();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            }
        });

        addAll(sallename, longeur, largeur, btnAjouter);
    }
         
         
         
         
         
         
         
    }

    
    

