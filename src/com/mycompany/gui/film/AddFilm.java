/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.film;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.util.Callback;
import com.mycompany.gui.BaseForm;
import com.mycompany.services.FilmService;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import java.util.StringTokenizer;

/**
 *
 * @author selim
 */
public class AddFilm extends BaseForm {
        
    public AddFilm(Resources res) {
        
        super("Add Film", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Add Film");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Image cinemaImage = res.getImage("cinema.jpg").scaled(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight() / 3);
            Label cinemaLabel = new Label(cinemaImage);
            cinemaLabel.setUIID("Container");
            cinemaLabel.getAllStyles().setMarginTop(2);
            add(cinemaLabel);
        
        TextField inputSearch = new TextField("", "IMDB url");
        Button btnSearch = new Button("Ajouter");
        
        Label l = new Label("Film Title");
        Label title = new Label();
        
        addAll(inputSearch, btnSearch, l, title);
        
        btnSearch.addActionListener((ActionEvent e) -> {
            StringTokenizer st = new StringTokenizer(inputSearch.getText(), "/");
            int count = 0;
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                if (count == 3) {
                    System.out.println(token);
                    getMovie(token);
                }
                count++;
            }
        });
        

    }
    

    public void getMovie(String id_imdb) {
    String url = "https://api.themoviedb.org/3/movie/" + id_imdb + "?api_key=bc707c1f4e36344270536a932b5f6a58";
}
    
    
}
