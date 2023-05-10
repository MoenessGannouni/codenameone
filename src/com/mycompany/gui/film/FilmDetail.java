/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.film;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.html.HTMLComponent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Film;
import com.mycompany.gui.BaseForm;
import com.mycompany.gui.Trailer;
import com.mycompany.services.FilmService;
import com.mycompany.services.ProjectionService;
import java.io.IOException;

/**
 *
 * @author selim
 */
public class FilmDetail extends BaseForm {

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
        tb.addSearchCommand(e -> {
        });

        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight()), true);
        URLImage urlImage = URLImage.createToStorage(placeholder, film.getNom() + ".png", film.getPoster());
        Label img = new Label(urlImage);
        img.setUIID("Container");
        img.getAllStyles().setMarginTop(2);
        add(img);
        
        Button trailer = new Button("See Trailer");
        trailer.addActionListener(e-> {
            new Trailer(this, film.getTrailer()).show();
        });
        add(trailer);

        Label title = new Label();
        title.setText("Title :");
        title.setUIID("largeBoldSystemFont");
        add(title);

        Label titlefilm = new Label();
        titlefilm.setText(film.getNom());
        titlefilm.setUIID("smallPlainSystemFont");
        add(titlefilm);

        Label cat = new Label();
        cat.setText("Categorie :");
        cat.setUIID("largeBoldSystemFont");
        add(cat);

        Label catfilm = new Label();
        catfilm.setText(film.getCategorie());
        catfilm.setUIID("smallPlainSystemFont");
        catfilm.setUIID("MultiLine");
        add(catfilm);

        Label time = new Label();
        time.setText("Run Time :");
        time.setUIID("largeBoldSystemFont");
        add(time);

        Label timefilm = new Label();
        timefilm.setText(Integer.valueOf((int) film.getDuree()) / 60 + "h " + Integer.valueOf((int) film.getDuree()) % 60 + "min");
        timefilm.setUIID("smallPlainSystemFont");
        add(timefilm);

        Label date = new Label();
        date.setText("Release Date :");
        date.setUIID("largeBoldSystemFont");
        add(date);

        Label datefilm = new Label();
        datefilm.setText(film.getReleaseDate().substring(0, 10));
        datefilm.setUIID("smallPlainSystemFont");
        add(datefilm);

        Label story = new Label();
        story.setText("Story :");
        story.setUIID("largeBoldSystemFont");
        add(story);

        SpanLabel storyfilm = new SpanLabel();
        storyfilm.setText(film.getDescription());
        storyfilm.setUIID("largeBoldSystemFont");
        add(storyfilm);

        Button btnDelete = new Button("Delete Film");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                FilmService.getInstance().delete(film.getId_film());
                Dialog.show("Success", "Connection accepted", new Command("OK"));
                new ListFilm(res).show();
            }
        });
        addAll(btnDelete);

    }

}
