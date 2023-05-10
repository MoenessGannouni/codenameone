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
import com.mycompany.entities.Reservation;
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
public class serviceReservation {
    private static serviceReservation instance = null;
    private ConnectionRequest req;
    public static boolean resultOk = true;
    public ArrayList<Reservation> reservations;

    private serviceReservation() {
        req = new ConnectionRequest();
    }
    
    public static serviceReservation getInstance() {
        if (instance == null) {
            instance = new serviceReservation();
        }
        return instance;
    }

    
    public void ajoutReservation(Reservation reservtion) {
        
        String url =Statics.BASE_URL+"reservation/newJson?filmName="+reservtion.getId_film()+"&userName="+reservtion.getId_user()+"&projId="+reservtion.getId_projection(); 
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
    
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
    
    
    
    
    public boolean deleteReservation(float id) {
        String url = Statics.BASE_URL +"reservation/deleteJson/"+id;
        
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
    
    
    public ArrayList<Reservation> parseReservations(String jsonText){
        try {
            reservations= new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> CategorieListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           List< Map<String,Object>> list =(List< Map<String,Object>>) CategorieListJson.get("root");
           for ( Map<String,Object> obj: list){
             Reservation re = new Reservation();
            float id_reservation = Float.parseFloat(obj.get("idReservation").toString());

                re.setId_reservation((int)id_reservation);
                re.setPrix_final(Float.parseFloat(obj.get("prixFinal").toString()));
                re.setId_user(Float.parseFloat(((Double) ((Map<String, Object>) obj.get("idUser")).get("idUser")).toString()));
                re.setId_film(Float.parseFloat(obj.get("idFilm").toString()));
                re.setState(Boolean.parseBoolean(obj.get("state").toString()));
                re.setStart_time(obj.get("startTime").toString());
                re.setEnd_time(obj.get("endTime").toString());
                re.setId_projection(Float.parseFloat(((Double) ((Map<String, Object>) obj.get("idProjection")).get("idProjection")).toString()));

             reservations.add(re);
         
        } }
           catch (IOException ex) {
             
        }
          return reservations;
 }
    
     public ArrayList<Reservation> getAllReservation(){
        String url = Statics.BASE_URL +"reservation/viewJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reservations = parseReservations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservations;
    }
 
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
    
  public Map<String, Object> getChartData() {
    String url = Statics.BASE_URL + "reservations/chartJson";

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

