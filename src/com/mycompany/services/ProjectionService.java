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
import com.mycompany.entites.Film;
import com.mycompany.entites.Projection;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.codename1.ui.util.Resources;

/**
 *
 * @author selim
 */
public class ProjectionService {

    public static ProjectionService instance = null;
    private ConnectionRequest req;
    public boolean resultOK = false;

    private ProjectionService() {
        req = new ConnectionRequest();
    }

    public static ProjectionService getInstance() {
        if (instance == null) {
            instance = new ProjectionService();
        }
        return instance;
    }

    public ArrayList<Projection> getAllProjections() {
        ArrayList<Projection> projections = new ArrayList<>();
        String url = Statics.BASE_URL + "/projection_mobile/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String jsonText = new String(req.getResponseData());
                try {

                    JSONParser j = new JSONParser();
                    Map<String, Object> filmsJSON = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                    List< Map<String, Object>> list = (List< Map<String, Object>>) filmsJSON.get("root");
                    for (Map<String, Object> obj : list) {
                        Projection p = new Projection();
                        p.setId_projection(Float.parseFloat(obj.get("idProjection").toString()));
                        Map<String, Object> idSalleObj = (Map<String, Object>) obj.get("idSalle");
                        p.setId_salle(Float.parseFloat(idSalleObj.get("idSalle").toString()));
                        Map<String, Object> idFilmObj = (Map<String, Object>) obj.get("idFilm");
                        p.setId_film(Float.parseFloat(idFilmObj.get("idFilm").toString()));
                        p.setDate_debut(obj.get("dateDebut").toString());
                        p.setDate_fin(obj.get("dateDebut").toString());
                        p.setDiffuse(Boolean.parseBoolean(obj.get("idProjection").toString()));
                        p.setNbr_places(Float.parseFloat(obj.get("nbrPlaces").toString()));

                        projections.add(p);
                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return projections;
    }

    public Projection getProjection(float idProjection) {
        Projection projection = new Projection();
        String url = Statics.BASE_URL + "/projection_mobile/" + (int) Math.round(idProjection);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String jsonText = new String(req.getResponseData());
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> filmJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                    projection.setId_projection(Float.parseFloat(filmJson.get("idProjection").toString()));
                    Map<String, Object> idSalleObj = (Map<String, Object>) filmJson.get("idSalle");
                    projection.setId_salle(Float.parseFloat(idSalleObj.get("idSalle").toString()));
                    Map<String, Object> idFilmObj = (Map<String, Object>) filmJson.get("idFilm");
                    projection.setId_film(Float.parseFloat(idFilmObj.get("idFilm").toString()));
                    projection.setDate_debut(filmJson.get("dateDebut").toString());
                    projection.setDate_fin(filmJson.get("dateDebut").toString());
                    projection.setDiffuse(Boolean.parseBoolean(filmJson.get("idProjection").toString()));
                    projection.setNbr_places(Float.parseFloat(filmJson.get("nbrPlaces").toString()));
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return projection;
    }

    public boolean addProjection(Film selectedItem, String text) {
        String url = Statics.BASE_URL + "/projection_mobile/new?idFilm=" + (int) Math.round(selectedItem.getId_film()) + "&date=" + text;
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

    public boolean updateProjection(float idProjection, Film selectedItem, String text) {
        String url = Statics.BASE_URL + "/projection_mobile/update?idProjection=" + (int) idProjection + "&idFilm=" + (int) Math.round(selectedItem.getId_film()) + "&date=" + text;
        System.out.println(url);
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

    public boolean delete(float idProjection) {
        String url = Statics.BASE_URL + "/projection_mobile/delete?idProjection=" + (int) idProjection;
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

}
