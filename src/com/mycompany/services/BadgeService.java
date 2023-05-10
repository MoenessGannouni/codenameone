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
import com.mycompany.entites.Abonnement;
import com.mycompany.entites.Badge;
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
public class BadgeService {
     private static BadgeService instance = null;
    public ConnectionRequest req;
    public ArrayList<Badge> badges;

    public boolean resultOK;

    public static BadgeService getInstance() {
        if(instance== null)
            instance = new BadgeService();
        return instance;
    }
    
    private BadgeService() {
        req = new ConnectionRequest();
    }
    
    
    
    
    
    
     public ArrayList<Badge> parseBadges(String jsonText){
    
    try {
    badges = new ArrayList<>();
   JSONParser j = new JSONParser();
            Map<String,Object> CategorieListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           List< Map<String,Object>> list =(List< Map<String,Object>>) CategorieListJson.get("root");
           for ( Map<String,Object> obj: list){
    Badge b = new Badge();
    float id = Float.parseFloat(obj.get("idBadge").toString());
    //float id_user = Float.parseFloat(obj.get("id_user").toString());
    
    b.setId_badge((int)id);
    b.setType(obj.get("type").toString());
   
   
    
    
    badges.add(b);
    
    } }
    catch (IOException ex) {
    //            Logger.getLogger(ServiceOeuvre.class.getName()).log(Level.SEVERE, null, ex);
    
    }
    return badges;
    }
     
     
     
     
   
     public ArrayList<Badge> getAllBadges(){
          String url = BASE_URL+"/badge/AllBadges";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                badges = parseBadges(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return badges;
    }
      public boolean deleteBadge(int id) {

        String url = Statics.BASE_URL + "/badge/deleteBadgeJSON/" + id + "";
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
      
      
      public boolean AddBadge(Badge badge)
    {
        String url = Statics.BASE_URL+"/badge/addBadgeJSON/new?type="+badge.getType()+"&nbres="+badge.getNbr_reservation();
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
    
}
