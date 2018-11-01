package com.mycompany.app;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.*;
import java.util.List;

public class MyCookieStore {

    SendData sendData = new SendData();
    HttpCookie myCookie = new HttpCookie("name", null);


    public static HttpCookie setCookie(String key, String value) {
        try{
            CookieManager manager = new CookieManager();
            CookieHandler.setDefault(manager);
            java.net.CookieStore cookieJar = (java.net.CookieStore) manager.getCookieStore();
            HttpCookie cookie = new HttpCookie(key, value);
            URL url = new URL("http://localhost:8080");
            cookieJar.add(url.toURI(), cookie);
            return cookie;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }

    }


    public String sendingCookies() throws ParseException {
        String username = myCookie.getValue();
        System.out.println(username);
        JSONObject myjson = sendData.userData(username);
        System.out.println(myjson.toString());
        return myjson.toString();
    }
}
