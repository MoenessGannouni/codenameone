/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.serviceReservation;
import com.mycompany.services.serviceReservationSnack;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kortb
 */
public class RecomandedSnack extends Form{
     public RecomandedSnack(Resources res, Form previous) {
        setTitle("Recommended Snacks");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        
        Map<String, Object> data = serviceReservationSnack.getInstance().getData();
        ArrayList<Map<String, Object>> snacks = (ArrayList<Map<String, Object>>) data.get("root");
        for (Map<String, Object> snack : snacks) {
            Button title = new Button("Snack name: " + snack.get("nom"));
            Button quantity = new Button("Quantity: " + snack.get("quantity"));
            addAll(title, quantity);
        }
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}


