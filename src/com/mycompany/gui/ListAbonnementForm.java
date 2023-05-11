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
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Abonnement;
import com.mycompany.entites.Projection;
import com.mycompany.services.AbonnementService;
import com.mycompany.services.ProjectionService;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Home
 */
public class ListAbonnementForm extends BaseForm {

    public ListAbonnementForm (Resources res) {
        
        super("List Abonnements", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("List Abonnements");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Image cinemaImage = res.getImage("cinema.jpg").scaled(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight() / 3);
            Label cinemaLabel = new Label(cinemaImage);
            cinemaLabel.setUIID("Container");
            cinemaLabel.getAllStyles().setMarginTop(2);
            add(cinemaLabel);

        
        ArrayList<Abonnement> tasks = AbonnementService.getInstance().getAllAbonnements();
        for (Abonnement a : tasks) {
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
                  
                 
         Label spl = new Label("Abonnement id : " + "  " + a.getId_abonnement()); 
         spl.getAllStyles().setFgColor(0x00000);
         Label spl2 = new Label("Abonnement type: " + "  " + a.getType());
         spl2.getAllStyles().setFgColor(0x00000);
         Label sp3 = new Label("Date Debut: " + "  " + a.getDateDebut());
         spl2.getAllStyles().setFgColor(0x00000);
          Label sp31 = new Label("Date Expiration: " + "  " + a.getDateExpiration());
         spl2.getAllStyles().setFgColor(0x00000);
  
         
         
         Button sup = new Button("Delete");
                  Button upd = new Button("Update");
                 
             
                sup.addActionListener((evt) -> {
                   AbonnementService.getInstance().deleteAbonnement(a.getId_abonnement());
                    Dialog.show("Alert", "Delete Abonnement ?", new Command("OK"));
                    Dialog.show("Success", "Abonnement deleted successfully", new Command("OK"));
                     
                    });
                 upd.addActionListener((evt) -> {
                       // new ModifAbonnnementForm(a.getId_abonnement()).show();
                    });
                 
                  
                     
                   
        addAll(spl,spl2,sup,upd,sp3,sp31)
                ;}
        
        
        
        
        
    }
    
        
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    
}
