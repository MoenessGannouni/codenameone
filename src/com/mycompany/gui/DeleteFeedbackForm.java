/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entites.Feedback;
import com.mycompany.services.FeedbackService;

/**
 *
 * @author acer
 */
public class DeleteFeedbackForm extends Form{
    public DeleteFeedbackForm() {
            setTitle("Delete Feedback");
            setLayout(BoxLayout.y());
            TextField idFeedback = new TextField("", "idFeedback");
                                    idFeedback.getStyle().setFgColor(154245);
            Button btnValider = new Button("Valider");
            btnValider.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if ((idFeedback.getText().length()==0))
                        Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                    else
                    {
                        try {
                           Feedback feedback = new Feedback(Integer.parseInt(idFeedback.getText()));
                            if( FeedbackService.getInstance().deleteFeedback(Integer.parseInt(idFeedback.getText())))
                            {
                               Dialog.show("Success","Connection accepted",new Command("OK"));
                            }else
                                Dialog.show("ERROR", "Server error", new Command("OK"));
                        } catch (NumberFormatException e) {
                            Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                        }

                    }            }
            });
            
              addAll(idFeedback,btnValider);
        }  

  
    
}
