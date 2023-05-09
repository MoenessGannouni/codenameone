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
import com.mycompany.entites.Film;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author selim
 */
public class FilmService {
    
    public static FilmService instance = null;
    private ConnectionRequest req; 
    public boolean resultOK;
    
    private FilmService() {
        req = new ConnectionRequest();
    }
    
    public static FilmService getInstance() {
        if (instance == null) {
            instance = new FilmService();
        }
        return instance;
    }

    
    public ArrayList<Film> getAllFilms(){
        ArrayList<Film> films = new ArrayList<>();
        String url = Statics.BASE_URL + "/film_mobile/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String jsonText = new String(req.getResponseData());
                try {
                    
                    JSONParser j = new JSONParser();
                    Map<String,Object> filmsJSON = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                    List< Map<String,Object>> list =(List< Map<String,Object>>) filmsJSON.get("root");
                    for ( Map<String,Object> obj: list){
                        Film f = new Film();
                        f.setId_film(Float.parseFloat(obj.get("idFilm").toString())); 
                        f.setNom(obj.get("nom").toString());
                        f.setCategorie(obj.get("categorie").toString());
                        f.setDescription(obj.get("description").toString());
                        f.setDuree(Float.parseFloat(obj.get("duree").toString()));
                        f.setPoster(obj.get("poster").toString());
                        f.setTrailer(obj.get("trailer").toString());
                        f.setReleaseDate(obj.get("releasedate").toString());
                        f.setId_imdb(obj.get("idImdb").toString());

                        films.add(f);
                    } 
                }
                catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return films;
    }
    
    public Film getFilm(float idFilm) {
        Film film = new Film();
        String url = Statics.BASE_URL + "/film_mobile/" + (int) Math.round(idFilm);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String jsonText = new String(req.getResponseData());
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> filmJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                    film.setId_film(Float.parseFloat(filmJson.get("idFilm").toString())); 
                    film.setNom(filmJson.get("nom").toString());
                    film.setCategorie(filmJson.get("categorie").toString());
                    film.setDescription(filmJson.get("description").toString());
                    film.setDuree(Float.parseFloat(filmJson.get("duree").toString()));
                    film.setPoster(filmJson.get("poster").toString());
                    film.setTrailer(filmJson.get("trailer").toString());
                    film.setReleaseDate(filmJson.get("releasedate").toString());
                    film.setId_imdb(filmJson.get("idImdb").toString());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return film;
    }

    public boolean delete(float idFilm) {
        String url = Statics.BASE_URL + "/film_mobile/delete?idFilm=" + (int) idFilm;
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

    /**
    public boolean addFilm (JSONObject json) {
        //String url = BASE_URL+"/addReclamationApi?type_reclamation="+type.getText()+"&description="+description.getText();

        String url = Statics.BASE_URL + "/film_mobile/add";

        req.setUrl(url);
        req.setPost(false);
        
        String requestBody = json.toString();
        req.setRequestBody(requestBody);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    } **/
    

    
}
