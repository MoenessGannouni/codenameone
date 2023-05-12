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
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Reservation;
import com.mycompany.entities.ReservationPlace;
import com.mycompany.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kortb
 */
public class serviceReservationPlace {
     private static serviceReservationPlace instance = null;
    private ConnectionRequest req;
    public static boolean resultOk = true;
    public ArrayList<ReservationPlace> reservationsPlace;

    private serviceReservationPlace() {
        req = new ConnectionRequest();
    }
    
    public static serviceReservationPlace getInstance() {
        if (instance == null) {
            instance = new serviceReservationPlace();
        }
        return instance;
    }

    
    public void ajoutReservation(ReservationPlace reservtion) {
        
        String url =Statics.BASE_URL+"/reservation/place/newJson?coordonnee="+reservtion.getCoordonnee()+"&prix="+reservtion.getPrix()+"&idReservation="+reservtion.getIdReservation(); 
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
    
     public boolean modifierReservation(int Id_reservation,int Id_film,int Id_user,int Id_projection) {
            String url = Statics.BASE_URL + "/reservation/updateJson/" + Id_reservation
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
    
    
    
    
    public boolean deleteReservation(float id) {
        String url = Statics.BASE_URL +"/reservation/deleteJson/"+id;
        
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
    
    
    public ArrayList<ReservationPlace> parseReservations(String jsonText){
        try {
            reservationsPlace = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> CategorieListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           List< Map<String,Object>> list =(List< Map<String,Object>>) CategorieListJson.get("root");
           for ( Map<String,Object> obj: list){
               ReservationPlace re = new ReservationPlace();
            float idResPlace = Float.parseFloat(obj.get("idResPlace").toString());

                re.setIdResPlace(idResPlace);
                re.setIdReservation(Float.parseFloat(((Double) ((Map<String, Object>) obj.get("idReservation")).get("idReservation")).toString()));
                re.setCoordonnee(obj.get("coordonnee").toString());
                re.setPrix(Float.parseFloat(obj.get("prix").toString()));

             reservationsPlace.add(re);
         
        } }
           catch (IOException ex) {
             
        }
          return reservationsPlace;
 }
    
     public ArrayList<ReservationPlace> getAllReservationPlace(){
        String url = Statics.BASE_URL+"/reservation/place/AllReservationPlaceJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reservationsPlace = parseReservations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservationsPlace;
    }
     
     
     
     
     public ArrayList<ReservationPlace> dejaresrve(float id){
        String url = Statics.BASE_URL+"/reservation/place/reservationidsalle/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reservationsPlace = parseReservations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservationsPlace;
    }
     
     
     
     
     
 
     public ReservationPlace getReservation(float idres) {
    final ReservationPlace[] reservationPlace = new ReservationPlace[1]; // Create a final array to hold the reservation object

    String url = Statics.BASE_URL + "/reservation/place/" + idres + "/showJosn";
    req.setUrl(url);
    req.setPost(false);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            reservationsPlace = parseReservations(new String(req.getResponseData()));
            if (!reservationsPlace.isEmpty()) {
                // Update the reservation object with the received data
                reservationPlace[0] = reservationsPlace.get(0);
                System.out.println(reservationPlace[0]); // Debugging purposes
            }
            req.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return reservationPlace[0];
}
    
 


}
