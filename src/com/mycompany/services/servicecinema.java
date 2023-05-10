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
public class servicecinema {
    private static servicecinema instance = null;
    private ConnectionRequest req;
    public static boolean resultOk = true;
    public ArrayList<cinema> ci;

    private servicecinema() {
        req = new ConnectionRequest();
    }
    
    public static servicecinema getInstance() {
        if (instance == null) {
            instance = new servicecinema();
        }
        return instance;
    }

    
    public void ajoutcinema(cinema c) {
        
        String url =Statics.BASE_URL+"/cinema/addcinemaJSON/new?nom="+c.getNom()+"&idUser="+c.getId_user()+"&localisation="+c.getLocalisation()+"&description="+c.getDescription()+"&photo="+c.getPhoto(); 
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
    
     public boolean modifierCinema(float id,String nom,String description,String localisation) {
                    String url = Statics.BASE_URL +"/cinema/updateCinemaJSON/"
                            +id+"?nom="+nom+"&description="+description+"&localisation="+localisation;

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
    
      public boolean deleteCinema(float id) {
        String url = Statics.BASE_URL +"/cinema/deleteidcinemaJSON/"+id;
        
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
   
    
    
    
    public ArrayList<cinema> parsecinemas(String jsonText){
        try {
            ci= new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> CategorieListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           List< Map<String,Object>> list =(List< Map<String,Object>>) CategorieListJson.get("root");
           for ( Map<String,Object> obj: list){
             cinema re = new cinema();
            float id_cinema = Float.parseFloat(obj.get("idCinema").toString());
             //   re.setId_user(Float.parseFloat(((Double) ((Map<String, Object>) obj.get("idUser")).get("idUser")).toString()));

                re.setId_cinema((int)id_cinema);
                re.setNom(obj.get("nom").toString());
                re.setDescription(obj.get("description").toString());
                re.setPhoto(obj.get("photo").toString());
               re.setLocalisation(obj.get("localisation").toString());

                //re.setId_user(Float.parseFloat(((Double) ((Map<String, Object>) obj.get("idUser")).get("idUser")).toString()));
                                           //   re.setId_user(Float.parseFloat(obj.get("prixFinal").toString()));
re.setId_user(1);
                ci.add(re);
         
        } }
           catch (IOException ex) {
             
        }
          return ci;
 }
    
     public ArrayList<cinema> getAllCinema(){
        String url = Statics.BASE_URL + "/cinema/mobile/Allcinemaaa";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ci = parsecinemas(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ci;
    }
 
    /* public cinema getcinema(float idci) {
    final cinema[] cinemas = new cinema[1]; // Create a final array to hold the reservation object

    String url = Statics.BASE_URL + "reservation/OneReservationJson/" + idci;
    req.setUrl(url);
    req.setPost(false);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            ci = parsecinemas(new String(req.getResponseData()));
            if (!ci.isEmpty()) {
                // Update the reservation object with the received data
                cinemas[0] = cinemas.get(0);
                System.out.println(cinemas[0]); // Debugging purposes
            }
            req.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return reservation[0];
}*/
    
 
}

