/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Feedback;
import com.mycompany.services.FeedbackService;
import java.util.Date;

/**
 *
 * @author acer
 */
public class AddFeedback extends BaseForm {
     public AddFeedback(Resources res) {
        
        super("Add Feedback", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Add Feedback");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        TextField feedback = new TextField("", "Entrez un feedback!");
        feedback.setUIID("TextFieldBlack");
        addStringValue("feedback", feedback);

        // TextField pour l'ID utilisateur
        TextField idUser = new TextField("", "Entrez l'ID utilisateur");
        idUser.setUIID("TextFieldBlack");
        addStringValue("idUser", idUser);

// TextField pour l'ID du film
        TextField idFilm = new TextField("", "Entrez l'ID du film");
        idFilm.setUIID("TextFieldBlack");
        addStringValue("idFilm", idFilm);

        //TextField idUser = new TextField("", "Entrez un feedback!");
        //feedback.setUIID("TextFieldBlack");
        //addStringValue("feedback", feedback);
        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);
        //click event
        btnAjouter.addActionListener((e) -> {

            try {
                if (feedback.getText() == "") {
                    Dialog.show("veuillez verifier les données ", "", "Annuler", "OK");
                } else {
                    InfiniteProgress ip = new InfiniteProgress();
                    //loading after insert date
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    // Feedback f = new Feedback(String.valueOf(feedback.getText()).toString(),
                    //format.format(new Date()));

                    Feedback f = new Feedback(
                            String.valueOf(feedback.getText()).toString(),
                            Integer.parseInt(idUser.getText()),
                            Integer.parseInt(idFilm.getText()),
                            format.format(new Date())
                    );

                    System.out.println("data feedback == " + f);

                    //appel methode ajoutfeedback mt3 service dans la bse de donnee
                    FeedbackService.getInstance().AddFeedback(f);
                    iDialog.dispose(); //tnahi loading baaed lajout
                    new ListFeedbackForm(res).show();

                    
                    
                    
               //baaed lajout nemchiw l liste
            //  new ListeFeedbackForm(res).show();
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    refreshTheme(); //actualisation 
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PADDEDLABEL")).add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));

    }
}

