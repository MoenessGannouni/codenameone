package com.mycompany.gui;


import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Film;
import com.mycompany.services.FilmService;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author selim
 */
public class ListFilm  extends BaseForm {
    
    public ListFilm (Resources res) {
        
        super("List Films", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("List Films");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Image cinemaImage = res.getImage("cinema.jpg").scaled(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight() / 3);
        Label cinemaLabel = new Label(cinemaImage);
        cinemaLabel.setUIID("Container");
        cinemaLabel.getAllStyles().setMarginTop(2);
        add(cinemaLabel);
        
        
        
        
        
        //////////////////////////////////////////////////////////////////
        ArrayList<Film> tasks = FilmService.getInstance().getAllFilms();
        for (Film f : tasks) {
            Button title = new Button(f.getNom());
            title.addActionListener(e -> {
                FilmDetail.idFilm = f.getId_film();
                new FilmDetail(res).show();
            });
            add(title);
        }
        
    }
    
}
