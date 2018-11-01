package com.mycompany.app;

import com.fasterxml.jackson.annotation.JacksonInject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.HttpCookie;
import java.security.NoSuchAlgorithmException;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @JacksonInject
    RestTemplate restTemplate = new RestTemplate();
    SendData sendData = new SendData();
    MyCookieStore myStore = new MyCookieStore();
    HttpCookie myCookie = new HttpCookie("name", null);



    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public RedirectView login(@RequestBody String user , Model model, HttpSession httpSession) throws ParseException, NoSuchAlgorithmException {


        System.out.println(user);

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(user);
        String username = (String) json.get("username");
        String password = (String) json.get("password");
        System.out.println(username);

        String generatedPassord = sendData.hashPassword(password);
        json.replace("password", password, generatedPassord);


        if(sendData.auth(username, generatedPassord) == false){
            System.out.println(sendData.auth(username, generatedPassord));
            System.out.println("User do not exist");
        }else{
            myCookie = myStore.setCookie("name", username);
            System.out.println(myCookie.getValue());
            httpSession.setAttribute("username", username);
            System.out.println("welcome back    " + username);

        }

        model.addAttribute("username", username);
        return new RedirectView("/coo");
    }


}
