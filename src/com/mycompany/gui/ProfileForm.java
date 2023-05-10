/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServiceUser;
import java.util.Date;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileForm extends BaseForm {

    public ProfileForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        
        Button modiff = new Button("Modifier");
        
       
        
        
        

        String ps = SessionManager.getPseudo();
        String n = SessionManager.getNom();
        String p = SessionManager.getPrenom();
        String d = SessionManager.getDate_naissance();
        String t = SessionManager.getTel();
        
        System.out.println(ps);
        
        TextField pseudo = new TextField(ps);
        pseudo.setUIID("TextFieldBlack");
        addStringValue("Pseudo", pseudo);
        
        TextField nom = new TextField(n);
        nom.setUIID("TextFieldBlack");
        addStringValue("Nom", nom);
        
        TextField prenom = new TextField(p);
        prenom.setUIID("TextFieldBlack");
        addStringValue("Prenom", prenom);
        
        TextField tel = new TextField(t);
        tel.setUIID("TextFieldBlack");
        addStringValue("Tel", tel);
        
        
        TextField da = new TextField(d);
        da.setUIID("TextFieldBlack");
        addStringValue("Date", da);
        
        Picker dateTimePicker = new Picker();
        dateTimePicker.setType(Display.PICKER_TYPE_DATE);
        dateTimePicker.getAllStyles().setFgColor(0xff0000);
        
        

        TextField email = new TextField(SessionManager.getEmail(), "E-Mail", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);
        
        TextField password = new TextField(SessionManager.getPassowrd(), "Password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("Password", password);

        CheckBox cb1 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb1.setUIID("Label");
        cb1.setPressedIcon(res.getImage("on-off-on.png"));
        CheckBox cb2 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb2.setUIID("Label");
        cb2.setPressedIcon(res.getImage("on-off-on.png"));
        
        
        
        modiff.setUIID("Edit");
    addStringValue("",modiff);
    
    modiff.addActionListener((edit) -> {
       InfiniteProgress ip = new InfiniteProgress();
       final Dialog ipDlg = ip.showInifiniteBlocking();
       
       
       Date date = dateTimePicker.getDate();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = dateFormat.format(date);
                
                
       
       ServiceUser.EditUser(SessionManager.getId(),nom.getText(),prenom.getText(),email.getText(),tel.getText(),pseudo.getText(),password.getText(),formattedDate);
       SessionManager.setPseudo(pseudo.getText());
       SessionManager.setNom(nom.getText());
       SessionManager.setPrenom(prenom.getText());
       SessionManager.setEmail(email.getText());
       
       
       Dialog.show("succes","Modifications avec succes","OK",null);
       ipDlg.dispose();
       refreshTheme();
       
    });
    }
    
    
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
