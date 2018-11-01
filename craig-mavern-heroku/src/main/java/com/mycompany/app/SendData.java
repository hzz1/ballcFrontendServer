package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SendData {

    RestTemplate restTemplate = new RestTemplate();

    public Boolean auth(String username, String password) throws ParseException {

        final String uri = "http://ballc-frontend-usersapi.herokuapp.com/users";
        String result = restTemplate.getForObject(uri, String.class);

        JSONParser parser = new JSONParser();
        JSONArray json = (JSONArray) parser.parse(result);

        for(Object myjson : json){

            JSONObject myJson = (JSONObject) parser.parse(myjson.toString());
            String name = myJson.get("name").toString();
            String pass = myJson.get("password").toString();
            if (username.equals(name) && password.equals(pass)){
            return true;
            }
        }
        return false;
    }



    public JSONObject userData(String username) throws ParseException {

        final String uri = "http://ballc-frontend-usersapi.herokuapp.com/users";
        String result = restTemplate.getForObject(uri, String.class);

        JSONParser parser = new JSONParser();
        JSONArray json = (JSONArray) parser.parse(result);

        for(Object myjson : json){

            JSONObject myJson = (JSONObject) parser.parse(myjson.toString());
            System.out.println(myJson.toString());

            if (username.equals(myJson.get("name").toString())){
                return myJson;
            }else{
                return null;
            }

        }
        return null;
    }

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] bytes = md.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        String generatedPassord = sb.toString();
        return generatedPassord;
    }


}
