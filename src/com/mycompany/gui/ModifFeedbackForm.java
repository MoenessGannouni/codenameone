/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Feedback;
import com.mycompany.services.FeedbackService;

/**
 *
 * @author acer
 */
public class ModifFeedbackForm extends BaseForm {
    /*
    
    public ModifFeedbackForm (int idFeedback,String feedback,int idUser,int idFilm) {
         super("Feedback", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Add Feedback");
        getContentPane().setScrollVisible(false);
        
        //tb.addSearchCommand(e -> {});
                Tabs swipe = new Tabs();
                        Label spacer1 = new Label();
        Label spacer2 = new Label();

       
        Button btnValider = new Button("Edit Feedback");
     
        btnValider.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                 String selectedOption = type.getSelectedItem();
                 int type2=Integer.parseInt(selectedOption);
                // if ((tfType.getText().length()==0))
                   // Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                 //else {
                    try {
                     
                        
                        if(FeedbackService.getInstance().updateFeedback(idFeedback))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                 
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", " must be a number", new Command("OK"));
                    
                   }
  Dialog.show("Success","Connection accepted",new Command("OK"));
                 }});
        
      addAll(tfType,btnValider,type);
     
    
    }
    private void addTab(Tabs swipe, Label spacer) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
     
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                           
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }*/
    
    
    
    /**  private Feedback feedback;

    public ModifFeedbackForm(Resources res, int idFeedback) {

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier Feedback");
        getContentPane().setScrollVisible(false);

        /*TextField id = new TextField(String.valueOf(reservation.getId_reservation()), "ID", 20, TextField.ANY);
        TextField film = new TextField(String.valueOf(reservation.getId_film()), "Film", 20, TextField.ANY);
        TextField user = new TextField(String.valueOf(reservation.getId_user()), "User", 20, TextField.ANY);
        TextField projection = new TextField(String.valueOf(reservation.getId_projection()), "Projection", 20, TextField.ANY);

        
        TextField feedback = new TextField("", "entrer feedback!!");
        feedback.setUIID("feedback");
         
       
        TextField date = new TextField("", "entrer date feedback!!");
        date.setUIID("date");

        
        
        Button btnModifier = new Button("Modifier");
        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    float idFeedback = Float.parseFloat(idFeedback.getText());
                    float idUser = Float.parseFloat(idUser.getText());
                    float idfilm = Float.parseFloat(idfilm.getText());
                    
                    
                    boolean success = FeedbackService.getInstance().ModifFeedbackForm((int)id_res,(int)idUser,(int)idFilm,(int)id_projection);
                    
                    if (success) {
                        Dialog.show("Success", "Reservation modified successfully", "OK", null);
                    } else {
                        Dialog.show("Error", "Failed to modify reservation", "OK", null);
                    }
                } catch (NumberFormatException ex) {
                    // Handle parsing errors (e.g., invalid integer or float values)
                    Dialog.show("Error", "Invalid input values", "OK", null);
                }
            }
        });

        addAll(id, film, user, projection, btnModifier);
    }
   
    **/     
    
}

