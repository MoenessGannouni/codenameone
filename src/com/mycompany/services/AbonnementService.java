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
import com.mycompany.entites.Abonnement;
import com.mycompany.utils.Statics;
import static com.mycompany.utils.Statics.BASE_URL;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Home
 */
public class AbonnementService {
     private static AbonnementService instance = null;
    public ConnectionRequest req;
    public ArrayList<Abonnement> abonnements;

    public boolean resultOK;

    public static AbonnementService getInstance() {
        if(instance== null)
            instance = new AbonnementService();
        return instance;
    }
    
    private AbonnementService() {
        req = new ConnectionRequest();
    }
        
    public boolean AddAbonnement(Abonnement abonnement)
    {
        String url = Statics.BASE_URL+"/abonnement/addAbonnementJSON/new?type="+abonnement.getType()+"&idUser="+abonnement.getId_user();
             //  String url = Statics.BASE_URL + "create";
        req.setUrl(url);
    req.addResponseListener((e) -> {
                        resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
        String str = new String(req.getResponseData());
        System.out.println("data"+str);
    });
        NetworkManager.getInstance().addToQueueAndWait(req);
return resultOK;
    }
    
    public boolean deleteAbonnement(int id) {

        String url = Statics.BASE_URL + "/abonnement/deleteAbonnementJSON/" + id + "";
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
            NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
     public ArrayList<Abonnement> parseAbonnements(String jsonText){
    
    try {
    abonnements= new ArrayList<>();
   JSONParser j = new JSONParser();
            Map<String,Object> CategorieListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           List< Map<String,Object>> list =(List< Map<String,Object>>) CategorieListJson.get("root");
           for ( Map<String,Object> obj: list){
    Abonnement a = new Abonnement();
    float id = Float.parseFloat(obj.get("idAbonnement").toString());
    //float id_user = Float.parseFloat(obj.get("id_user").toString());
    
    a.setId_abonnement((int)id);
    
    a.setId_user((int)1);
    a.setType(obj.get("type").toString());
    
  
    
    
    abonnements.add(a);
    
    } }
    catch (IOException ex) {
    //            Logger.getLogger(ServiceOeuvre.class.getName()).log(Level.SEVERE, null, ex);
    
    }
    return abonnements;
    }
     
     
     
     
   
     public ArrayList<Abonnement> getAllAbonnements(){
          String url = BASE_URL+"/abonnement/Alltest";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                abonnements = parseAbonnements(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return abonnements;
    }
     
     
       public boolean updateAbonnement (int type,int id)
    { 

       String url = BASE_URL+"/abonnement/modifAbonnement/"+id+"?type="+type;
       req.setUrl(url);
       req.addResponseListener(new ActionListener<NetworkEvent>(){ 
           @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
             }
    });
        System.out.println(""+resultOK);
       NetworkManager.getInstance().addToQueue(req);
        return resultOK;
    }

       

       
    
}
