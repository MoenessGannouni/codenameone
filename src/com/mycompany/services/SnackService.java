/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entites.cinema;
import com.mycompany.entites.salle;
import com.mycompany.entites.snack;
import com.mycompany.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rayen
 */
public class SnackService {
    private static SnackService instance = null;
    private ConnectionRequest req;
    public static boolean resultOk = true;
    public ArrayList<snack> snacklist;

    private SnackService() {
        req = new ConnectionRequest();
    }
    
    public static SnackService getInstance() {
        if (instance == null) {
            instance = new SnackService();
        }
        return instance;
    }

    
  /* public void ajoutsalle(salle salle) {
       // new?largeur=11&longeur=12&acces=true&idCinema=43&nom=ddddd
        String url =Statics.BASE_URL+"/salle/addsalleJSON/new?longeur="+salle.getLongueur()+"&largeur="+salle.getLongueur()+"&nom="+salle.getNom()+"&idCinema="+salle.getId_cinema(); 
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }*/
    
    /*
     public boolean modifierReservation(int Id_reservation,int Id_film,int Id_user,int Id_projection) {
            String url = Statics.BASE_URL + "reservation/updateJson/" + Id_reservation
            + "?filmName=" + Id_film
            + "&userName=" + Id_user
            + "&projId=" + Id_projection;
            //127.0.0.1:8000/reservation/updateJson/141?filmName=66&userName=26&projId=8
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
    */
    
    /*
    
    public boolean deleteSalle(float id) {
        String url = Statics.BASE_URL +"/salle/deletemobilesalle/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    */
    
   public ArrayList<snack> parseSnack(String jsonText){
        try {
                        snacklist= new ArrayList<>();

            JSONParser j = new JSONParser();
            Map<String,Object> CategorieListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           List< Map<String,Object>> list =(List< Map<String,Object>>) CategorieListJson.get("root");
           for ( Map<String,Object> obj: list){
             snack re = new snack();
          float id_snack = Float.parseFloat(obj.get("idSnack").toString());
re.setId_snack(19);

               re.setId_snack(id_snack);
                re.setPrix(Float.parseFloat(obj.get("prix").toString()));
              re.setQuantite (Float.parseFloat(obj.get("quantite").toString()));
              re.setNom(obj.get("nom").toString());

               
                //re.setId_projection(Float.parseFloat(((Double) ((Map<String, Object>) obj.get("idProjection")).get("idProjection")).toString()));

             snacklist.add(re);
         
        } }
           catch (IOException ex) {
             
        }
          return snacklist;
 }
    
     public ArrayList<snack> getAllsnack(float id){
        String url = Statics.BASE_URL + "/mobile/mobilesnackidcine/"+5;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                snacklist = parseSnack(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return snacklist;
    }
 /*
     public Reservation getReservation(float idres) {
    final Reservation[] reservation = new Reservation[1]; // Create a final array to hold the reservation object

    String url = Statics.BASE_URL + "reservation/OneReservationJson/" + idres;
    req.setUrl(url);
    req.setPost(false);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            reservations = parseReservations(new String(req.getResponseData()));
            if (!reservations.isEmpty()) {
                // Update the reservation object with the received data
                reservation[0] = reservations.get(0);
                System.out.println(reservation[0]); // Debugging purposes
            }
            req.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return reservation[0];
}
  */
    
    
    /*
    public ArrayList<salle> getsalle(float id){
        String url = Statics.BASE_URL + "/salle/mobilesalleidcine/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                sallelist = parseSalle(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return sallelist;
    }
 */

}

