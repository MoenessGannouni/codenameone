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
import com.mycompany.entites.snack;
import com.mycompany.services.SnackService;
import com.mycompany.services.servicecinema;
import java.util.ArrayList;

/**
 *
 * @author rayen
 */
public class snacktest extends BaseForm {

    public snacktest(Resources res) {

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

    ArrayList<snack> produits = SnackService.getInstance().getAllsnack(43);
        System.out.println("cccc"+produits);       

        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
