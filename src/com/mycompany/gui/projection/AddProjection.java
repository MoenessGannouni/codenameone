/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.projection;

import com.codename1.components.MultiButton;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListCellRenderer;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ProjectionService;
import java.util.StringTokenizer;
import com.mycompany.entites.Film;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.list.ListCellRenderer;
import com.codename1.ui.spinner.Picker;
import com.mycompany.gui.BaseForm;
import com.mycompany.services.FilmService;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author selim
 */
public class AddProjection extends BaseForm {

    public AddProjection(Resources res) {

        super("Add Projection", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Add Projection");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });

        Image cinemaImage = res.getImage("cinema.jpg").scaled(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight() / 3);
        Label cinemaLabel = new Label(cinemaImage);
        cinemaLabel.setUIID("Container");
        cinemaLabel.getAllStyles().setMarginTop(2);
        add(cinemaLabel);

        ArrayList<Film> films = FilmService.getInstance().getAllFilms();
        ComboBox<Film> comboBox = new ComboBox<>(films.toArray());
        comboBox.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
        add(comboBox);
        Picker dateTimePicker = new Picker();
        dateTimePicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        dateTimePicker.getAllStyles().setFgColor(0xff0000);
        add(dateTimePicker);

        Button btnValider = new Button("Add Projection");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((dateTimePicker.getText().length() == 0) || (comboBox.getSelectedIndex() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {

                        Date date = dateTimePicker.getDate();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd/HH:mm");
                        String formattedDate = dateFormat.format(date);
                        if (ProjectionService.getInstance().addProjection(comboBox.getSelectedItem(), formattedDate)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        }

                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "must be a number", new Command("OK"));

                    }
                    Dialog.show("Success", "Connection accepted", new Command("OK"));
                }
            }
        });
        addAll(btnValider);

    }

}
