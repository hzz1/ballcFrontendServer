package com.mycompany.app.Persons;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.mycompany.app.MyCookieStore;
import com.mycompany.app.SendData;
import com.mycompany.app.Users.Users;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpCookie;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


@RestController
@CrossOrigin(origins = "*")
public class PersonController {

    ArrayList<Persons> persons = new ArrayList<>();

    @JacksonInject
    RestTemplate restTemplate = new RestTemplate();
    PersonService sendData = new PersonService();
    MyCookieStore myStore = new MyCookieStore();


/*

    public PersonController() throws ParseException {


    }
    */

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/coo")
    public String showMyCookie(HttpSession httpSession) throws ParseException {

        Object value = httpSession.getValue("username");
        System.out.println(value.toString());
        //String myCookie = myStore.sendingCookies();
        return value.toString();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/regperson", method = RequestMethod.POST)
    public String createPerson(@RequestBody String user) throws IOException, ParseException {

        int addressID = 0;
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(user);


        String address1 = (String) json.get("address_1");
        String address2 = (String) json.get("address_2");
        String address3 = (String) json.get("address_3");
        String psCode = (String) json.get("postal_code");
        String city = (String) json.get("city");
        String country = (String) json.get("country");

        JSONObject addresses = new JSONObject();

        addresses.put("address_line_1", address1);
        addresses.put("address_line_2", address2);
        addresses.put("address_line_3", address3);
        addresses.put("city", city);
        addresses.put("country", country);
        addresses.put("postal_code", psCode);

        if (sendData.checkAddress(address1)){

            addressID = sendData.findID("/addresses", "address_line_1", "address_id" ,  address1);
            System.out.println("its exist fuck off");
        }else{
            addressID = sendData.RegAddress("/addresses", addresses.toString());
            System.out.println(addressID);
        }


        String firstname = (String) json.get("firstname");
        String lastname = (String) json.get("lastname");
        String dob = (String) json.get("dob");



        JSONObject personalInfo = new JSONObject();
        personalInfo.put("first_name", firstname);
        personalInfo.put("last_name", lastname);
        personalInfo.put("date_of_birth", dob);

        personalInfo.put("address", addressID);

        String newPerson = sendData.RegPerson("/persons", personalInfo.toString());
        System.out.println(personalInfo.toString());
        System.out.println(newPerson);

        JSONObject json2 = (JSONObject) parser.parse(newPerson);
        String personID = json2.get("person_id").toString();

        String phonenumber = (String) json.get("phonenumber");
        String email = (String) json.get("email");

        JSONObject emails = new JSONObject();
        emails.put("contact_type", "Email");
        emails.put("person", personID);
        emails.put("contact_detail", email);
        System.out.println(emails.toString());
        String newEmail = sendData.RegPerson("/contacts", emails.toString());
        System.out.println(newEmail);
        JSONObject phone = new JSONObject();
        phone.put("contact_type", "Phonenumber");
        phone.put("person", personID);
        phone.put("contact_detail", phonenumber);
        String newphonenumber = sendData.RegPerson("/contacts", phone.toString());
        System.out.println(newphonenumber);


        System.out.println(user);

        return "user";
    }


    @CrossOrigin(value = "*")
    @GetMapping("/persons")
    public String getPersons() throws ParseException {

        String result = sendData.getInfo("/persons");
        JSONParser parser = new JSONParser();
        JSONArray json = (JSONArray) parser.parse(result);

        for (Object obj : json){
            JSONObject jsonObj = (JSONObject) parser.parse(obj.toString());
            //System.out.println(jsonObj.toString());

        }

        return result;
    }

    @CrossOrigin(value = "*")
    @GetMapping("/person")
    public String getPerson() throws ParseException {

        int id = 1;
        int address_id = sendData.findPerson("/persons", "person_id", "address_id", id);
        //System.out.println(address_id);

        /*
        String url = "/persons/" + id;
        String result = sendData.getInfo(url);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(result);
        System.out.println(json.toString());
*/
        return "yes";
    }

    @CrossOrigin(value = "*")
    @GetMapping("/addresses")
    public String getAddresses() throws ParseException {

        String result = sendData.getInfo("/addresses");
        JSONParser parser = new JSONParser();
        JSONArray json = (JSONArray) parser.parse(result);

        for (Object obj : json){
            JSONObject jsonObj = (JSONObject) parser.parse(obj.toString());
            //System.out.println(jsonObj.toString());
        }

        return result;
    }

    @CrossOrigin(value = "*")
    @GetMapping("/address/{id}")
    public String getAddress(@PathVariable("id") String id) throws ParseException {

        //System.out.println(id);


        String url = "/addresses/" + id;
        String result = sendData.getInfo(url);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(result);
        //System.out.println(json.toString());

        return json.toString();
    }

    @CrossOrigin(value = "*")
    @GetMapping("/contacts")
    public String getContacts() throws ParseException {

        String result = sendData.getInfo("/contacts");
        JSONParser parser = new JSONParser();
        JSONArray json = (JSONArray) parser.parse(result);

        for (Object obj : json){
            JSONObject jsonObj = (JSONObject) parser.parse(obj.toString());
            //System.out.println(jsonObj.toString());
        }

        return result;
    }

    @CrossOrigin(value = "*")
    @GetMapping("/contact/{id}")
    public String getContact(@PathVariable("id") String id) throws ParseException {

        String url = "/contacts/" + id;
        String result = sendData.getInfo(url);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(result);
        //System.out.println(json.toString());

        return result;
    }

    @CrossOrigin(value = "*")
    @GetMapping("/players")
    public String getPlayers() throws ParseException {

        String result = sendData.getInfo("/players");
        JSONParser parser = new JSONParser();
        JSONArray json = (JSONArray) parser.parse(result);

        for (Object obj : json){
            JSONObject jsonObj = (JSONObject) parser.parse(obj.toString());
            //System.out.println(jsonObj.toString());
        }

        return result
                ;
    }

    @CrossOrigin(value = "*")
    @GetMapping("/player/{id}")
    public String getPlayer(@PathVariable("id") String id) throws ParseException {

        String url = "/players/" + id;
        String result = sendData.getInfo(url);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(result);
        System.out.println(json.toString());

        return result;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/updateperson", method = RequestMethod.POST)
    public String updatePerson(@RequestBody String user) throws ParseException {

        System.out.println(user);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(user);
        String id = json.get("person_id").toString();
        String result = sendData.updateInfo("/persons", user);
        return user;

    }

}
