/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Abonnement;
import com.mycompany.entites.Badge;
import com.mycompany.services.AbonnementService;
import com.mycompany.services.BadgeService;
import java.util.ArrayList;

/**
 *
 * @author Home
 */
public class ListBadgeForm extends BaseForm {

    public ListBadgeForm (Resources res) {
        
        super("List Badges", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("List Badges");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Image cinemaImage = res.getImage("cinema.jpg").scaled(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight() / 3);
            Label cinemaLabel = new Label(cinemaImage);
            cinemaLabel.setUIID("Container");
            cinemaLabel.getAllStyles().setMarginTop(2);
            add(cinemaLabel);

        
        ArrayList<Badge> tasks = BadgeService.getInstance().getAllBadges();
        for (Badge b : tasks) {
         /*   TextField title = new TextField("Abonement number: " + a.getId_abonnement()+"Type Abonnement"+a.getType());
                     Button sup = new Button("Delete");
                   sup.addActionListener((evt) -> {
                   AbonnementService.getInstance().deleteAbonnement(a.getId_abonnement());
                    Dialog.show("Alert", "Delete Abonnement ?", new Command("OK"));
                    Dialog.show("Success", "Abonnement deleted successfully", new Command("OK"));
                   
                    });
                   add(title);
        }*/
         
         
              
                  Label i = new Label();
                  
                 
         Label spl = new Label("Badge id : " + "  " + b.getId_badge()); 
         spl.getAllStyles().setFgColor(0x00000);
         Label spl2 = new Label("Badge type: " + "  " + b.getType());
         spl2.getAllStyles().setFgColor(0x00000);
     
        
  
         
         
         Button sup = new Button("Delete");
                              
                sup.addActionListener((evt) -> {
                   BadgeService.getInstance().deleteBadge(b.getId_badge());
                    Dialog.show("Alert", "Delete Badge ?", new Command("OK"));
                    Dialog.show("Success", "Badge deleted successfully", new Command("OK"));
                     
                    });
              
                 
                 
                  
                     
                   
        addAll(spl,spl2,sup);
          
                
        

        }
         Button add = new Button("add badge");
                              
                add.addActionListener((evt) -> {
                  new AddBadgeForm().show();
                    });
        addAll(add);
        
        
        
        
        
    }
    
    
}
