/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.WebBrowser;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.geom.Dimension;

/**
 *
 * @author selim
 */
public class Trailer extends BaseForm {

    public Trailer(Form previous, String trailer) {

        WebBrowser browser = new WebBrowser();
        browser.setURL(trailer);
        browser.setPreferredSize(new Dimension(2000, 2000)); // Définir la taille de la carte
        add(browser);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
