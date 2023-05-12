/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Projection;
import com.mycompany.entities.user;
import com.mycompany.gui.AdminForm;
import com.mycompany.gui.ProfileForm;
import com.mycompany.gui.SessionManager;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*import com.mycompany.gui.AjoutReclamationForm;
import com.mycompany.gui.ListReclamationForm;
import com.mycompany.gui.SessionManager; */
/**
 *
 * @author MOEµNESS
 */
public class ServiceUser {

    //singleton
    public static ServiceUser instance = null;

    public static boolean resultOK = true;
    String json;

    //init connection request
    private ConnectionRequest req;

    public static ServiceUser getInstance() {

        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    public ServiceUser() {
        req = new ConnectionRequest();
    }

    public void signup(TextField nom, TextField prenom, TextField email, TextField tel, TextField pseudo, TextField password, String date, Resources res) {

        String url = Statics.BASE_URL + "/userJSON/newJSON?nom=" + nom.getText().toString() + "&prenom=" + prenom.getText().toString() + "&email=" + email.getText().toString()
                + "&tel=" + tel.getText().toString() + "&pseudo=" + pseudo.getText().toString() + "&password=" + password.getText().toString() + "&dateNaissance=" + date + "&id_badge=1";
        System.out.println(url);
        req.setUrl(url);

        //Control saisi
        if (nom.getText().equals(" ") && prenom.getText().equals(" ") && email.getText().equals(" ") && tel.getText().equals(" ") && pseudo.getText().equals(" ") && password.getText().equals(" ") && date.equals(" ")) {

            Dialog.show("Erreur", "Veuillez remplir les champs", "OK", null);

        }

        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e) -> {

            //njib data ly7atithom fi form 
            byte[] data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
            String responseData = new String(data);//ba3dika na5o content 

            System.out.println("data ===>" + responseData);
        }
        );

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public void signin(TextField pseudo, TextField password, Resources rs) {

        String url = Statics.BASE_URL + "/userJSON/loginJSON?pseudo=" + pseudo.getText().toString() + "&password=" + password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);

        req.addResponseListener((e) -> {

            JSONParser j = new JSONParser();

            String json = new String(req.getResponseData()) + "";

            try {

                if (json.equals("failed")) {
                    Dialog.show("Echec d'authentification", "Pseudo ou mot de passe éronné", "OK", null);
                } else {
                    System.out.println("data ==" + json);

                    Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));

                    //Session 
                    float id = Float.parseFloat(user.get("id").toString());
                    SessionManager.setId((int) id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                    SessionManager.setPassowrd(user.get("password").toString());
                    SessionManager.setPseudo(user.get("pseudo").toString());
                    SessionManager.setEmail(user.get("email").toString());
                    SessionManager.setNom(user.get("nom").toString());
                    SessionManager.setPrenom(user.get("prenom").toString());
                    SessionManager.setTel(user.get("tel").toString());
                    SessionManager.setDate_naissance(user.get("dateNaissance").toString());
                    SessionManager.setRoles(user.get("roles").toString());

                    System.out.println("current user ==> " + SessionManager.getPseudo() + ", " + SessionManager.getPassowrd());
                    System.out.println(SessionManager.getRoles());

                    new ProfileForm(rs).show();

                }

            } catch (Exception ex) {
                Dialog.show("Echec d'authentification", "Pseudo ou mot de passe éronné", "OK", null);
            }

        });

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public ArrayList<user> getAllUsers() {
        ArrayList<user> projections = new ArrayList<>();
        String url = Statics.BASE_URL + "/userJSON/allJSON";
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
                        user p = new user();
                        p.setId_user((int) Float.parseFloat(obj.get("id").toString()));
                        Map<String, Object> idSalleObj = (Map<String, Object>) obj.get("badge");
                        p.setId_badge((int) Float.parseFloat(idSalleObj.get("idBadge").toString()));
                        p.setMontant(Float.parseFloat(obj.get("montant").toString()));
                        p.setTel((int) Float.parseFloat(obj.get("tel").toString()));
                        p.setPseudo(obj.get("pseudo").toString());
                        p.setRoles(obj.get("roles").toString());
                        p.setDate_naissance(obj.get("dateNaissance").toString());
                        p.setPrenom(obj.get("prenom").toString());
                        p.setNom(obj.get("nom").toString());
                        p.setPassword(obj.get("password").toString());
                        p.setEmail(obj.get("email").toString());

                        projections.add(p);
                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

                req.removeResponseListener(
                        this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return projections;
    }

    //heki 5dmtha taw nhabtha ala description
    public String getPasswordByPseudo(String pseudo, Resources rs) {

        String url = Statics.BASE_URL + "/userJSON/getPasswordByPseudo?pseudo=" + pseudo;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);

        req.addResponseListener((e) -> {

            JSONParser j = new JSONParser();

            json = new String(req.getResponseData()) + "";

            try {

                System.out.println("data ==" + json);

                Map<String, Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        return json;
    }

    public static void EditUser(int id, String nom, String prenom, String email, String tel, String pseudo, String password, String date) {

        String url = Statics.BASE_URL + "/userJSON/editJSON/" + id + "?nom=" + nom + "&prenom=" + prenom
                + "&email=" + email + "&tel=" + tel + "&pseudo=" + pseudo + "&password=" + password + "&dateNaissance=" + date + "&id_badge=1&montant=0";
        MultipartRequest req = new MultipartRequest();

        req.setUrl(url);
        req.setPost(true);
        req.addArgument("id", String.valueOf(SessionManager.getId()));
        req.addArgument("pseudo", pseudo);
        req.addArgument("email", email);
        req.addArgument("password", password);
        req.addArgument("nom", nom);
        req.addArgument("prenom", prenom);
        req.addArgument("dateNaissance", date);

        System.out.println(email);

        req.addResponseListener((response) -> {

            byte[] data = (byte[]) response.getMetaData();
            String s = new String(data);
            System.out.println(s);
            if (s.equals("succes")) {
            } else {

                Dialog.show("Erreur", "Echec de modifications", "OK", null);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

}
