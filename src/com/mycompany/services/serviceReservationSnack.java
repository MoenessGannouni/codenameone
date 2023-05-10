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
import com.mycompany.entities.ReservationPlace;
import com.mycompany.entities.ReservationSnack;
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
public class serviceReservationSnack {
    private static serviceReservationSnack instance = null;
    private ConnectionRequest req;
    public static boolean resultOk = true;
    public ArrayList<ReservationSnack> reservationsSnack;

    public serviceReservationSnack() {
        req = new ConnectionRequest();
    }
    
    public static serviceReservationSnack getInstance() {
        if (instance == null) {
            instance = new serviceReservationSnack();
        }
        return instance;
    }

    
    public void ajoutReservation(ReservationSnack reservtion) {
        
        String url =Statics.BASE_URL+"reservation/snack/newJson?quantite="+reservtion.getQuantite()+
                "&prix="+reservtion.getPrix()+
                "&idReservation="+reservtion.getIdReservation()+
                "&idSnack="+reservtion.getIdSnack(); 
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
    
     public boolean modifierReservation(float idResSnack,float prix,float idReservation,float idSnack,float quantite) {
            String url = Statics.BASE_URL + idResSnack + "reservation/snack/editJson" + 
                "&quantite"+quantite+
                "&prix="+prix +
                "&idReservation="+idReservation+
                "&idSnack"+idSnack ;
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
        String url = Statics.BASE_URL +id+"reservation/snack/deleteJson"+id;
        
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
    
    
    public ArrayList<ReservationSnack> parseReservations(String jsonText){
        try {
            reservationsSnack = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> CategorieListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           List< Map<String,Object>> list =(List< Map<String,Object>>) CategorieListJson.get("root");
           for ( Map<String,Object> obj: list){
               ReservationSnack re = new ReservationSnack();
            float idResSnack = Float.parseFloat(obj.get("idResSnack").toString());

                re.setIdResSnack(idResSnack);
                re.setIdSnack(Float.parseFloat(((Double) ((Map<String, Object>) obj.get("idSnack")).get("idSnack")).toString()));
                re.setIdReservation(Float.parseFloat(((Double) ((Map<String, Object>) obj.get("idReservation")).get("idReservation")).toString()));
                re.setQuantite(Float.parseFloat(obj.get("quantite").toString()));
                re.setPrix(Float.parseFloat(obj.get("prix").toString()));

                
             reservationsSnack.add(re);
         
        } }
           catch (IOException ex) {
             
        }
          return reservationsSnack;
 }
    
     public ArrayList<ReservationSnack> getAllReservationSnack(){
        String url = Statics.BASE_URL+"reservation/snack/viewJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reservationsSnack = parseReservations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservationsSnack;
    }
 
     public ReservationSnack getReservation(float idres) {
    final ReservationSnack[] ReservationSnack = new ReservationSnack[1]; // Create a final array to hold the reservation object

    String url = Statics.BASE_URL + "reservation/snack/" + idres + "/showOneJson";
    req.setUrl(url);
    req.setPost(false);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            reservationsSnack = parseReservations(new String(req.getResponseData()));
            if (!reservationsSnack.isEmpty()) {
                // Update the reservation object with the received data
                ReservationSnack[0] = reservationsSnack.get(0);
                System.out.println(ReservationSnack[0]); // Debugging purposes
            }
            req.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return ReservationSnack[0];
}
    
 public Map<String, Object> getData() {
    String url = Statics.BASE_URL + "reservation/snack/snackrecommandJson";

    req.setUrl(url);
    req.setPost(false);

    final Map<String, Object>[] jsonData = new Map[]{null}; // Create a mutable array to store the parsed JSON data

    req.addResponseListener((e) -> {
        String responseData = new String(req.getResponseData());
        // Parse the JSON response
        JSONParser parser = new JSONParser();
        try {
            jsonData[0] = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(responseData.getBytes())));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
      System.out.println(jsonData[0]);
    return jsonData[0];
}

}
