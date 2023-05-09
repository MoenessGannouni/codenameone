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
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ProjectionService;
import java.util.StringTokenizer;
import com.mycompany.entites.Film;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entites.reservation_snack;
import com.mycompany.entites.snack;
import com.mycompany.services.FilmService;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
/**
 *
 * @author rayen
 */
public class panier extends BaseForm{
          static  ArrayList<snack> panierlist = new ArrayList<>();

    public panier(Resources res) {
        
        super("Add panier", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Add panier");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Image cinemaImage = res.getImage("cinema.jpg").scaled(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight() / 3);
            Label cinemaLabel = new Label(cinemaImage);
            cinemaLabel.setUIID("Container");
            cinemaLabel.getAllStyles().setMarginTop(2);
            add(cinemaLabel);
         //////////////////////////////////   
        //     System.out.println("placereservé from panier affichage pour borry"+Place.List_New_Reservation);
                        ArrayList<reservation_snack> reservation_snacks = new ArrayList<>();

    // Créer la forme

    // Créer la liste des produits
    ArrayList<snack> produits = new ArrayList<>();
    
    
 Button addButton = new Button("ajouter");
            add( addButton);

    produits.add(new snack("snack", (float) 1.00, 5, "ff"));
    produits.add(new snack("coca", (float) 5.00, 5, "ff"));
    produits.add(new snack("fanta", (float) 5.00, 5, "ff"));

    // Créer le modèle pour la liste des produits
    DefaultListModel<snack> produitsModel = new DefaultListModel<>(produits);
    List produitsList = new List(produitsModel);

    // Ajouter un ListListener à la liste des produits
produitsList.addActionListener((evt) -> {
    final int index = produitsList.getSelectedIndex(); // variable finale
    if (index >= 0) {
        snack selectedProduct = produitsModel.getItemAt(index);
        addButton.addActionListener((e) -> {
            panierlist.add(selectedProduct);
            System.out.println("Produit ajouté : " + selectedProduct);
            final int innerIndex = index; // variable finale interne
            System.out.println("Inner index : " + innerIndex);
            Vector listeners = addButton.getActionListeners();
            for (Object listener : listeners) {
                addButton.removeActionListener((ActionListener) listener);
            }
        });
    }
});



    //Vous avez réservé la chaise suivante: Ch(rang:1, n°:5)


    add( produitsList);
   
    

    
    // Créer un bouton pour afficher le dialogue
    Button optionsButton = new Button("Options");

    // Ajouter un ActionListener au bouton
    optionsButton.addActionListener((evt) -> {
        // Créer un nouveau dialogue
        Dialog optionsDialog = new Dialog("Options");
        
        

        Button clearButton = new Button("Vider le panier");
        Button validateButton = new Button("Payer");
        optionsDialog.add(clearButton);
        optionsDialog.add(validateButton);

        // Ajouter un ActionListener au bouton "Vider le panier"
        clearButton.addActionListener((e) -> {
            panierlist.clear();       
            System.out.println("Le panier a été vidé "+ panierlist);
                   

            optionsDialog.dispose(); // Fermer le dialogue
        });
        
        
        
       


        // Ajouter un ActionListener au bouton "Valider"
        validateButton.addActionListener((e) -> {
     

        

System.out.println("Contenu du reservation_snack : " + reservation_snacks);
            System.out.println("Contenu du panier : " + panierlist);
            optionsDialog.dispose(); // Fermer le dialogue
            
            
new editpanier(res).show();
        });
        
        

        // Afficher le dialogue
        optionsDialog.show();
    });

    // Ajouter le bouton à la forme
    add( optionsButton);

    // Affichejr la forme
    show();
}


        

    }
    

    
    

