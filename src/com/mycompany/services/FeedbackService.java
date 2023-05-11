/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entites.Feedback;
import com.mycompany.utils.Statics;
import static com.mycompany.utils.Statics.BASE_URL;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author acer
 */
public class FeedbackService {

    private static FeedbackService instance = null;
    public ConnectionRequest req;
    public ArrayList<Feedback> feedbacks;

    public boolean resultOK;

    public static FeedbackService getInstance() {
        if (instance == null) {
            instance = new FeedbackService();
        }
        return instance;
    }

    private FeedbackService() {
        req = new ConnectionRequest();
    }

    public boolean AddFeedback(Feedback feedback) {
        String url = Statics.BASE_URL + "/feedback/addFeedbackJSON/new?feedback="
                + feedback.getFeedback() + "&idUser=" + feedback.getIdUser()
                + "&idFilm=" + feedback.getIdfilm() + "&date=" + feedback.getDate();
        //  String url = Statics.BASE_URL + "create";
        req.setUrl(url);
        req.addResponseListener((e) -> {
            resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
            String str = new String(req.getResponseData());
            System.out.println("data" + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean deleteFeedback(int idFeedback) {

        String url = Statics.BASE_URL + "/feedback/deleteFeedbackJSON/" + idFeedback + "";
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

    public ArrayList<Feedback> parseFeedbacks(String jsonText) {

        try {
            feedbacks = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> CategorieListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List< Map<String, Object>> list = (List< Map<String, Object>>) CategorieListJson.get("root");
            for (Map<String, Object> obj : list) {
                Feedback a = new Feedback();
                float idFeedback = Float.parseFloat(obj.get("idFeedback").toString());
                //float id_user = Float.parseFloat(obj.get("id_user").toString());

                a.setIdFeedback((int) idFeedback);
                a.setFeedback(obj.get("feedback").toString());
                a.setIdUser((int) 1);
                a.setIdfilm((int) 2);
                a.setDate(obj.get("date").toString());

                feedbacks.add(a);

            }
        } catch (IOException ex) {
            //            Logger.getLogger(ServiceOeuvre.class.getName()).log(Level.SEVERE, null, ex);

        }
        return feedbacks;
    }

    public ArrayList<Feedback> getAllFeedbacks() {
        String url = BASE_URL + "/feedback/Allfeedbacks";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                feedbacks = parseFeedbacks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return feedbacks;
    }

    public boolean updateFeedback(float idFeedback, String feedback,float idUser,float idfilm,String date) {

       
          String url = Statics.BASE_URL +"/feedback/updateFeedbackJSON/"
                            +idFeedback+"?feedback="+feedback+"&idUser="+idUser+"&idfilm="+idfilm;



        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        System.out.println("" + resultOK);
        NetworkManager.getInstance().addToQueue(req);
        return resultOK;
    }

    public boolean updateFeedback(int idFeedback) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}


