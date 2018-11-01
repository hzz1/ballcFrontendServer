package com.mycompany.app.Users;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.mycompany.app.SendData;
import com.mycompany.app.Users.Users;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;



@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @JacksonInject
    RestTemplate restTemplate = new RestTemplate();
    UserService sendData = new UserService();
    ArrayList<Users> users = new ArrayList<>();


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String createUser(HttpServletResponse response, @RequestBody String user) throws IOException, ParseException, NoSuchAlgorithmException {

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(user);

        Users userInfo = new Users();
        String username = (String) json.get("username");
        String email = (String) json.get("email");
        String password = (String) json.get("password");
        String patternpass = "^(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";
        String patternemail = "^(?:(?:[\\w`~!#$%^&*\\-=+;:{}'|,?\\/]+(?:(?:\\.(?:\"(?:\\\\?[\\w`~!#$%^&*\\-=+;:{}'|,?\\/\\.()<>\\[\\] @]|\\\\\"|\\\\\\\\)*\"|[\\w`~!#$%^&*\\-=+;:{}'|,?\\/]+))*\\.[\\w`~!#$%^&*\\-=+;:{}'|,?\\/]+)?)|(?:\"(?:\\\\?[\\w`~!#$%^&*\\-=+;:{}'|,?\\/\\.()<>\\[\\] @]|\\\\\"|\\\\\\\\)+\"))@(?:[a-zA-Z\\d\\-]+(?:\\.[a-zA-Z\\d\\-]+)*|\\[\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\])$";


        if (password.matches(patternpass) && email.matches(patternemail)) {
            System.out.println("Strong one");
            String generatedPassord = sendData.hashPassword(password);
            System.out.println(generatedPassord);

            json.replace("password", generatedPassord);

            System.out.println(json.get("password").toString());

            if (sendData.authReg(username)) {
                System.out.println("User do not exist");
                sendData.SignUp(json.toString());

                userInfo.setEmail(email);
                userInfo.setUsername(username);
                userInfo.setPassword(generatedPassord);
                users.add(userInfo);

                return "Registration Completed";
            } else {
                System.out.println("you have a username");

            }
        }else{
            System.out.println("your info is not secure");
        }

        return "hahaha";
    }


    @CrossOrigin(value = "*")
    @GetMapping("/userslist")
    public String getPersons() throws ParseException {


        String result = sendData.usersList();
        JSONParser parser = new JSONParser();
        JSONArray json = (JSONArray) parser.parse(result);

        for (Object obj : json){
            JSONObject jsonObj = (JSONObject) parser.parse(obj.toString());
            System.out.println(jsonObj.toString());
        }

        return result;
    }




    @CrossOrigin(value = "*")
    @RequestMapping(value = "/deletuser", method = RequestMethod.GET)
    public void deleteuser(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "hzz1");
        String username = jsonObject.get("username").toString();
        sendData.deletUser(username);
    }

    @CrossOrigin(value = "*")
    @PutMapping("/updateuser")
    public void updateUser(){
        JSONObject json = new JSONObject();
        json.put("username", "hzz1");
        json.put("password", "c4416d8249c0902c993ae08a2254050d");
        json.put("email", "hamza.hamzawi@outlook.com");

        String userinformation = json.toString();
        String username = json.get("username").toString();
        sendData.updateUser(userinformation);
    }
}
