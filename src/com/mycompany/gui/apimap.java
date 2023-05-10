/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.WebBrowser;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author rayen
 */
public class apimap extends BaseForm{
    public apimap(Resources res , String location) {
     
            
            
            
            
            
            
            WebBrowser browser = new WebBrowser();
    browser.setURL("https://maps.google.com/maps?q="+location);
    browser.setPreferredSize(new Dimension(2000, 2000)); // Définir la taille de la carte
   add(browser);
   show();
   Button back = new Button("back");
   back.addActionListener(e -> {
                new ListCinema(res).show();
            });
   
   add(back);
}
}