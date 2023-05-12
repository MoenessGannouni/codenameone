/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ProjectionService;
import java.util.StringTokenizer;
import com.mycompany.entites.Film;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.mycompany.services.FilmService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

/**
 *
 * @author rayen
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class place extends BaseForm {

    static HashSet<String> List_New_Reservation = new HashSet<>();
    static HashSet<String> List_Reservation_Affi = new HashSet<>();

    public place(Resources res) {

        super("Add place", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Add Place");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });

        Image cinemaImage = res.getImage("cinema.jpg").scaled(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight() / 3);
        Label cinemaLabel = new Label(cinemaImage);
        cinemaLabel.setUIID("Container");
        cinemaLabel.getAllStyles().setMarginTop(2);
        add(cinemaLabel);
        //////////////////////////////////  

        Label emptyLabel = new Label(""); // Texte vide
        emptyLabel.setPreferredH(10);
        int rows = 5;
        int cols = 8;
        HashSet<String> reservedSquares = new HashSet<>();
        reservedSquares.add("2,2");
        reservedSquares.add("2,4");
        reservedSquares.add("2,5");
        reservedSquares.add("2,3");

        Style squareStyle = new Style();
        squareStyle.setBorder(Border.createLineBorder(1));
        squareStyle.setFgColor(0x000000); // Couleur du texte
        squareStyle.setBgTransparency(0); // Transparence du fond
        squareStyle.setBgColor(0xFFFFFF); // Couleur du fond
        Container gridContainer = new Container(new GridLayout(rows, cols));
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Label square = new Label();
                int finalRow = row;
                int finalCol = col;
                String coordstest = finalRow + "," + finalCol; // concaténer les coordonnées en une chaîne

                if (reservedSquares.contains(coordstest)) {
                    square.getUnselectedStyle().setBgColor(0x808080);
                    square.getUnselectedStyle().setBgTransparency(255);
                    square.setPreferredW(100);
                    square.setPreferredH(100);
                } else {
                    square.getUnselectedStyle().setBgColor(0x0000ff);
                    square.getUnselectedStyle().setBgTransparency(255);
                    square.setPreferredW(100);
                    square.setPreferredH(100);

                }

                square.addPointerPressedListener(e -> {
                    String coords = finalRow + "," + finalCol; // concaténer les coordonnées en une chaîne

                    if (reservedSquares.contains(coords)) {
                        System.out.println("impossible deja reservé ");

                    } else if (List_New_Reservation.contains(coords)) {
                        List_New_Reservation.remove(coords);
                        square.getUnselectedStyle().setBgColor(0x0000ff);
                    } else {
                        String s = "";
                        List_New_Reservation.add(coords);

                        s = "Ch(rang:" + finalRow + "," + " n°:" + finalCol + ")";
                        List_Reservation_Affi.add(s);
                        square.getUnselectedStyle().setBgColor(0xff0000);
                    }
                    // Mettre à jour le label avec les coordonnées
                    System.out.println("Coordonnées : " + List_New_Reservation);
                    // Actualiser la vue
                    square.getParent().getParent().repaint();
                });

                /*  square.addPointerPressedListener(e -> {
 String coords = finalRow + "," + finalCol; // concaténer les coordonnées en une chaîne
                clickedSquares.add(coords);
                square.getUnselectedStyle().setBgColor(0xff0000);
                // Mettre à jour le label avec les coordonnées
                System.out.println("Coordonnées: " + clickedSquares);
                // Actualiser la vue
                square.getParent().getParent().repaint();
            });*/
                gridContainer.add(square);
            }
        }
        add(gridContainer);

        Button alertButton = new Button("Valider");
        Style alertButtonStyle = alertButton.getAllStyles();
//alertButtonStyle.setBgColor(0x00FF00); // couleur de fond vert
//alertButtonStyle.setFgColor(0x000000); // couleur de police noire

        alertButtonStyle.setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM)); // police en gras, taille moyenne
        alertButton.addActionListener(e -> {
            String message = "";
            if (List_New_Reservation.isEmpty()) {
                message = "Vous n'avez pas réservé de chaises.";
                Dialog.show("Information", message, "OK", null);

            } else {

                message = "Vous avez réservé les chaises suivantes :\n"
                        + List_Reservation_Affi.toString() + "\n\nVoulez-vous acheter des snacks ?";
                if (Dialog.show("Confirmation", message, "Acheter", "Non")) {
                    System.out.println("Achat effectué !");
                     new RecomandedSnack(res,this).show();
                    // new panier().show();

                } else {
                    System.out.println("Achat annulé.");
                }
            }
        });
        add(alertButton);
    }

}
