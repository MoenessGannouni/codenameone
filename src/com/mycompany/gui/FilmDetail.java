/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Film;
import com.mycompany.services.FilmService;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author selim
 */
public class FilmDetail extends BaseForm{
    
    public static float idFilm;
    
    public FilmDetail(Resources res) {
                
        super("Movie", BoxLayout.y());
        Film film = FilmService.getInstance().getFilm(idFilm);
            Toolbar tb = new Toolbar(true);
            setToolbar(tb);
            getTitleArea().setUIID("Container");
            setTitle(film.getNom());
            getContentPane().setScrollVisible(false);
            
            super.addSideMenu(res);
            tb.addSearchCommand(e -> {});
            
            Image cinemaImage = res.getImage("cinema.jpg").scaled(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight() / 3);
            Label cinemaLabel = new Label(cinemaImage);
            cinemaLabel.setUIID("Container");
            cinemaLabel.getAllStyles().setMarginTop(2);
            add(cinemaLabel);
            
            
            
            Label title = new Label();
            title.setText(film.getNom());
            add(title);
            Label story = new Label();
            story.setText(film.getDescription());
            add(story);

        

    }
    
}
