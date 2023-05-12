/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

/**
 *
 * @author rayen
 */

import com.codename1.components.MultiButton;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
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
import com.mycompany.entities.ReservationSnack;
import com.mycompany.entites.snack;
import com.mycompany.services.FilmService;
import com.mycompany.services.serviceReservationSnack;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
/**
 *
 * @author rayen
 */
public class editpanier extends BaseForm{
static ArrayList<ReservationSnack> snackreservation1iste = new ArrayList<ReservationSnack>();
    public editpanier(Resources res) {
        
        super("edit panier", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("edit panier");
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

    // Créer la forme

    // Créer la liste des produits
    
    
 Button removeButton = new Button("remove");
            add( removeButton);

    

    // Créer le modèle pour la liste des produits
    DefaultListModel<snack> produitsModel = new DefaultListModel<>(panier.panierlist);
    List produitsList = new List(produitsModel);

    // Ajouter un ListListener à la liste des produits
produitsList.addActionListener((evt) -> {
    final int index = produitsList.getSelectedIndex(); // variable finale
    if (index >= 0) {
        snack selectedProduct = produitsModel.getItemAt(index);
        removeButton.addActionListener((e) -> {
            panier.panierlist.remove(selectedProduct);
            System.out.println("Produit ajouté : " + selectedProduct);
            final int innerIndex = index; // variable finale interne
            System.out.println("Inner index : " + innerIndex);
            Vector listeners = removeButton.getActionListeners();
            for (Object listener : listeners) {
                removeButton.removeActionListener((ActionListener) listener);
            }
                               new editpanier(res).show();

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
                float s=0;

        for (snack produit : panier.panierlist) {
        s=s+produit.getPrix();
}
        Dialog optionsDialog = new Dialog("la somme est: "+s+"DT" );




        System.out.println("somme est   :"+s+"DT");
        Button clearButton = new Button("Vider le panier");
        Button validateButton = new Button("Payer");
        optionsDialog.add(clearButton);
        optionsDialog.add(validateButton);

        // Ajouter un ActionListener au bouton "Vider le panier"
        clearButton.addActionListener((e) -> {
            panier.panierlist.clear();       
            System.out.println("Le panier a été vidé "+ panier.panierlist);
                   new panier(res).show();

            optionsDialog.dispose(); // Fermer le dialogue
        });
        
        
        
       


        // Ajouter un ActionListener au bouton "Valider"
        validateButton.addActionListener((e) -> {
      System.out.println("Contenu du panier : " + panier.panierlist);
      for (snack b : panier.panierlist) {
                    ReservationSnack snackreservation1 = new ReservationSnack(1,b.getPrix(),150,(int)b.getId_snack());

                    boolean reservationExiste = false;

                    for (ReservationSnack existingReservation : snackreservation1iste) {
                        if (existingReservation.getIdSnack() == b.getId_snack()) {
                            existingReservation.setQuantite(existingReservation.getQuantite() + 1);
                            existingReservation.setPrix((existingReservation.getQuantite() + 1)*existingReservation.getPrix());
                            reservationExiste = true;
                            break;
                        }
                    }
                    
                    if (!reservationExiste) {
                        snackreservation1iste.add(snackreservation1);
                    }
                }
       for(ReservationSnack rr:editpanier.snackreservation1iste){
                    serviceReservationSnack.getInstance().ajoutReservation(rr);
                }                  
                          System.out.println("snackreservation1isteeee " + snackreservation1iste);

      
            optionsDialog.dispose(); // Fermer le dialogue
            
            
                                                          //  new modifpanier().show1();

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
    

    
    


