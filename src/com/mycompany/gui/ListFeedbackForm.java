/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Feedback;
import com.mycompany.services.FeedbackService;
import java.util.ArrayList;

/**
 *
 * @author acer
 */
public class ListFeedbackForm extends BaseForm{
    public ListFeedbackForm (Resources res) {
        
        super("List Feedbacks", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("List Feedbacks");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Image cinemaImage = res.getImage("cinema.jpg").scaled(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight() / 3);
            Label cinemaLabel = new Label(cinemaImage);
            cinemaLabel.setUIID("Container");
            cinemaLabel.getAllStyles().setMarginTop(2);
            add(cinemaLabel);

        
        ArrayList<Feedback> tasks = FeedbackService.getInstance().getAllFeedbacks();
        for (Feedback a : tasks) {
       
         
         
              
                  Label i = new Label();
                  
                 
         Label spl = new Label("Feedback id : " + "  " + a.getIdFeedback()); 
         spl.getAllStyles().setFgColor(0x00000);
         Label spl2 = new Label("feedback: " + "  " + a.getFeedback());
         spl2.getAllStyles().setFgColor(0x00000);
         Label sp3 = new Label("id user : " + "  " + a.getIdUser()); 
         spl.getAllStyles().setFgColor(0x00000);
         Label sp4 = new Label("id film : " + "  " + a.getIdfilm()); 
         spl.getAllStyles().setFgColor(0x00000);
         Label sp5 = new Label("Date : " + "  " + a.getDate());
         spl2.getAllStyles().setFgColor(0x00000);
          
  
         
         
         Button sup = new Button("Delete");
                  Button upd = new Button("Update");
                 
             
                sup.addActionListener((evt) -> {
                   FeedbackService.getInstance().deleteFeedback((int) a.getIdFeedback());
                    Dialog.show("Alert", "Delete feedback ?", new Command("OK"));
                    Dialog.show("Success", "feedback deleted successfully", new Command("OK"));
                    new ListFeedbackForm(res).show();
                     
                    });
                 upd.addActionListener((evt) -> {
                      //  new ModifAbonnnementForm((int) a.getIdFeedback()).show();
                    });
                 
                  
                     
                   
        addAll(spl,spl2,sp3,sp4,sp5,sup,upd)
                ;}
        
        
        
        
        
    }
    
        
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    
    
    
}
