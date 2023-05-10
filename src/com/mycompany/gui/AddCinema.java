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
import com.mycompany.entites.cinema;
import com.mycompany.entites.salle;
import com.mycompany.services.FilmService;
import com.mycompany.services.SalleService;
import com.mycompany.services.servicecinema;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author selim
 */
public class AddCinema extends BaseForm {

    private Form current;

    public AddCinema(Resources res) {

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
        ////////////////////////////////// 

        Tabs swipe = new Tabs();

        Label s1 = new Label();
        Label s2 = new Label();

      

        TextField nom = new TextField("", "entrer nom !");
        nom.setUIID("name");

        TextField localisation = new TextField("", "entrer localisation !");
        localisation.setUIID("localisation");;

        TextField photo = new TextField("", "entrer photo !");
        photo.setUIID("photo");

        TextField description = new TextField("", "entrer description !");
        description.setUIID("description");

        Button btnAjouter = new Button("Ajouter");

        btnAjouter.addActionListener((e) -> {
            if ((nom.getText().length() == 0) || (photo.getText().length() == 0) || (description.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();

                    String nomsend = nom.getText();
                    String locali = localisation.getText();

                    String photo2 = photo.getText();
                    String descrp = description.getText();

                    cinema r = new cinema(1, nomsend, locali, descrp,photo2);
                    System.out.println("data  reclamation == " + r);

                    servicecinema.getInstance().ajoutcinema(r);

                    iDialog.dispose();

                    refreshTheme();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        addAll(nom, localisation, description, photo,btnAjouter);
    }

}
