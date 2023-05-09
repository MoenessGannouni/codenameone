/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.projection;

import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Film;
import com.mycompany.entites.Projection;
import com.mycompany.gui.BaseForm;
import com.mycompany.gui.film.FilmDetail;
import com.mycompany.services.FilmService;
import com.mycompany.services.ProjectionService;
import java.util.ArrayList;

/**
 *
 * @author selim
 */
public class ListProjection  extends BaseForm {

    public ListProjection (Resources res) {
        
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

        
        ArrayList<Projection> tasks = ProjectionService.getInstance().getAllProjections();
        for (Projection p : tasks) {
            Film film = FilmService.getInstance().getFilm(p.getId_film());
            Label title = new Label(film.getNom());
            Label date = new Label(p.getDate_debut().substring(0, 10) + " " + p.getDate_debut().substring(11, 16));
            Button btn = new Button("Show");
            Button btn1 = new Button("Réserver");
            addAll(title, date, btn, btn1);
            
            btn.addActionListener(e-> {
            ProjectionDetail.idProjection = p.getId_projection();
                new ProjectionDetail(res).show();
            });
        }
        
        
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    
    
}
