/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entites.Abonnement;
import com.mycompany.entites.Badge;
import com.mycompany.services.AbonnementService;
import com.mycompany.services.BadgeService;

/**
 *
 * @author Home
 */
public class AddBadgeForm extends BaseForm{
     public AddBadgeForm() {
        

    setTitle("Add Badge");

    setLayout(BoxLayout.y());
    
    TextField nbres = new TextField("", "Nombre reservation");
    Button btnValider = new Button("Valider");
    btnValider.getStyle().setFgColor(154245);
    btnValider.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            String typeb;
             
            if ((nbres.getText().length()==0))
               Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            else {
          
                if(Integer.parseInt(nbres.getText())<5){
                 typeb="Bronze";
                
            }else if(Integer.parseInt(nbres.getText())>5&&Integer.parseInt(nbres.getText())<10){
                typeb="Silver";
                
            }else{
                typeb="Gold";
            }
                Badge badge = new Badge(typeb,Integer.parseInt(nbres.getText()));
                
                if (BadgeService.getInstance().AddBadge(badge)) {
                    Dialog.show("Success","Connection accepted",new Command("OK"));
                } else {
                    Dialog.show("ERROR", "erreur", new Command("OK"));
                }
            }
        }
        
    });
    Container content = BoxLayout.encloseY(nbres, btnValider);
    add(content);
}
    
}
