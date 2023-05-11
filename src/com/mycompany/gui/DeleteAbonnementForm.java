/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import com.mycompany.entites.Abonnement;
import com.mycompany.services.AbonnementService;

/**
 *
 * @author Home
 */
public class DeleteAbonnementForm extends Form{
      public DeleteAbonnementForm() {
            setTitle("Delete Abonnement");
            setLayout(BoxLayout.y());
            TextField ID = new TextField("", "ID");
                                    ID.getStyle().setFgColor(154245);
            Button btnValider = new Button("Valider");
            btnValider.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if ((ID.getText().length()==0))
                        Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                    else
                    {
                        try {
                            Abonnement abonnement = new Abonnement(Integer.parseInt(ID.getText()));
                            if( AbonnementService.getInstance().deleteAbonnement(Integer.parseInt(ID.getText())))
                            {
                               Dialog.show("Success","Connection accepted",new Command("OK"));
                            }else
                                Dialog.show("ERROR", "Server error", new Command("OK"));
                        } catch (NumberFormatException e) {
                            Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                        }

                    }            }
            });
            
              addAll(ID,btnValider);
        }  

  
    
}
