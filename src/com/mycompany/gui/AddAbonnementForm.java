/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Abonnement;
import com.mycompany.services.AbonnementService;

/**
 *
 * @author Home
 */
public class AddAbonnementForm extends BaseForm{
     public AddAbonnementForm(Resources res) {
        

    setTitle("Add Abonnement");
    ComboBox<String> type = new ComboBox<>("1", "3", "6","12");
    setLayout(BoxLayout.y());
    
    TextField idUser = new TextField("", "idUser");
    idUser.getStyle().setFgColor(154245);
    Button btnValider = new Button("Valider");
    btnValider.getStyle().setFgColor(154245);
    btnValider.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
           // if ((Type.getText().length()==0))
             //   Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            //else {
             String selectedOption = type.getSelectedItem();
             int type2=Integer.parseInt(selectedOption);
                Abonnement abonnement = new Abonnement(selectedOption,Integer.parseInt(idUser.getText()));
                if (AbonnementService.getInstance().AddAbonnement(abonnement)) {
                    Dialog.show("Success","Connection accepted",new Command("OK"));
                } else {
                    Dialog.show("ERROR", "erreur", new Command("OK"));
                }
                new ListAbonnementForm(res).show();
            }
        
    });
    Container content = BoxLayout.encloseY(idUser, btnValider,type);
    add(content);
}
}
