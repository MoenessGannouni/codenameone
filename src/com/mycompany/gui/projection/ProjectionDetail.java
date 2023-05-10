/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.projection;

import com.codename1.components.MultiButton;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
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
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Film;
import com.mycompany.entites.Projection;
import com.mycompany.gui.BaseForm;
import static com.mycompany.gui.film.FilmDetail.idFilm;
import com.mycompany.services.FilmService;
import com.mycompany.services.ProjectionService;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author selim
 */
public class ProjectionDetail extends BaseForm {

    public static float idProjection;

    public ProjectionDetail(Resources res) {

        super("Add Projection", BoxLayout.y());
        try {
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

            Projection projection = ProjectionService.getInstance().getProjection(idProjection);

            Picker dateTimePicker = new Picker();
            dateTimePicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
            dateTimePicker.getAllStyles().setFgColor(0xff0000);
            add(dateTimePicker);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = dateFormat.parse(projection.getDate_debut().substring(0, 10) + " " + projection.getDate_debut().substring(11, 16));
            dateTimePicker.setDate(date);

            ArrayList<Film> films = FilmService.getInstance().getAllFilms();
            Film desiredFilm = null;
            for (Film film : films) {
                if (film.getId_film() == projection.getId_film()) {
                    desiredFilm = film;
                    break;
                }
            }
            TextField title = new TextField(desiredFilm.getNom());
            title.setUIID("TextFieldBlack");
            addStringValue("Film ", title);

            String places = (int) projection.getNbr_places() + "";
            TextField nbrplaces = new TextField(places);
            nbrplaces.setUIID("TextFieldBlack");
            addStringValue("Nbr Places ", nbrplaces);
            System.out.println(projection.getNbr_places());

            ComboBox<Film> comboBox = new ComboBox<>(films.toArray());
            comboBox.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
            add(comboBox);

            Button btnValider = new Button("Update Projection");
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
                            if (ProjectionService.getInstance().updateProjection(projection.getId_projection(), comboBox.getSelectedItem(), formattedDate)) {
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

            Button btnDelete = new Button("Delete Projection");
            btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ProjectionService.getInstance().delete(projection.getId_projection());
                    Dialog.show("Success", "Connection accepted", new Command("OK"));
                    new ListProjection(res).show();
                }
            });
            addAll(btnDelete);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }

}
