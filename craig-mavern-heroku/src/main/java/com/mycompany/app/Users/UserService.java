package com.mycompany.app.Users;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class UserService {

    RestTemplate restTemplate = new RestTemplate();



    @CrossOrigin(origins = "*")
    public String SignUp(String user){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(user, headers);
        return restTemplate.exchange("http://ballc-frontend-usersapi.herokuapp.com/addusers", HttpMethod.POST , entity, String.class).getBody();
    }


    public Boolean authReg(String username) throws ParseException {

        final String uri = "http://ballc-frontend-usersapi.herokuapp.com/users";
        String result = restTemplate.getForObject(uri, String.class);

        JSONParser parser = new JSONParser();
        JSONArray json = (JSONArray) parser.parse(result);

        for(Object myjson : json){

            JSONObject myJson = (JSONObject) parser.parse(myjson.toString());
            String name = myJson.get("name").toString();
            if (username.equals(name)){
                return false;
            }
        }
        return true;
    }

    @CrossOrigin(origins = "*")
    public String updateUser(String user){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(user, headers);
        return restTemplate.exchange("http://ballc-backend-api.herokuapp.com/2", HttpMethod.PUT , entity, String.class).getBody();
    }

    @CrossOrigin(origins = "*")
    public String updateuser(String url){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://ballc-backend-api.herokuapp.com/updateusers/"));
        return restTemplate.exchange(url, HttpMethod.GET , entity, String.class).getBody();
    }

    @CrossOrigin(value = "*")
    public void deletUser(String username){
        final String uri = "http://ballc-frontend-usersapi.herokuapp.com/deleteusers/"+ username;
        String result = restTemplate.getForObject(uri, String.class);
    }

    @CrossOrigin(value = "*")
    public String usersList(){
        final String uri = "http://ballc-frontend-usersapi.herokuapp.com/users";
        return restTemplate.getForObject(uri, String.class);
    }

    @CrossOrigin(origins = "*")
    public String sendUser(String username){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(username, headers);
        return restTemplate.exchange("http://localhost:3000/userCookie", HttpMethod.POST , entity, String.class).getBody();
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
