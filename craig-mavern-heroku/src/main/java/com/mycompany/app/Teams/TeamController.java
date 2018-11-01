package com.mycompany.app.Teams;

import com.fasterxml.jackson.annotation.JacksonInject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@CrossOrigin(origins = "*")
public class TeamController {

    @JacksonInject
    RestTemplate restTemplate = new RestTemplate();
    TeamService sendData = new TeamService();

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    public String getTeams() throws ParseException {

        String result = sendData.getInfo("/teams");
        JSONParser parser = new JSONParser();
        JSONArray json = (JSONArray) parser.parse(result);

        for (Object obj : json){
            JSONObject jsonObj = (JSONObject) parser.parse(obj.toString());
            System.out.println(jsonObj.toString());

        }

        return result;
    }

    @CrossOrigin(value = "*")
    @GetMapping("/team/{id}")
    public String getTeam(@PathVariable("id") String id) throws ParseException {

        //System.out.println(id);


        String url = "/teams/" + id;
        String result = sendData.getInfo(url);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(result);
        //System.out.println(json.toString());

        return json.toString();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/coaches", method = RequestMethod.GET)
    public String getCoaches() throws ParseException {

        String result = sendData.getInfo("/coaches");
        JSONParser parser = new JSONParser();
        JSONArray json = (JSONArray) parser.parse(result);

        for (Object obj : json){
            JSONObject jsonObj = (JSONObject) parser.parse(obj.toString());
            System.out.println(jsonObj.toString());

        }

        return result;
    }

    @CrossOrigin(value = "*")
    @GetMapping("/coach/{id}")
    public String getcouch(@PathVariable("id") String id) throws ParseException {

        //System.out.println(id);


        String url = "/coaches/" + id;
        String result = sendData.getInfo(url);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(result);
        //System.out.println(json.toString());

        return json.toString();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/matcheso", method = RequestMethod.GET)
    public String getMatches() throws ParseException {

        String result = sendData.getMatchInfo();
        JSONParser parser = new JSONParser();
        JSONArray json = (JSONArray) parser.parse(result);

        for (Object obj : json){
            JSONObject jsonObj = (JSONObject) parser.parse(obj.toString());
            System.out.println(jsonObj.toString());

        }

        return result;
    }

    @CrossOrigin(value = "*")
    @GetMapping("/match/{id}")
    public String getMatche(@PathVariable("id") String id) throws ParseException {

        //System.out.println(id);


        String url = "/matches/" + id;
        String result = sendData.getInfo(url);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(result);
        //System.out.println(json.toString());

        return json.toString();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/owners", method = RequestMethod.GET)
    public String getOwners() throws ParseException {

        String result = sendData.getInfo("/owners");
        JSONParser parser = new JSONParser();
        JSONArray json = (JSONArray) parser.parse(result);

        for (Object obj : json){
            JSONObject jsonObj = (JSONObject) parser.parse(obj.toString());
            System.out.println(jsonObj.toString());

        }

        return result;
    }

    @CrossOrigin(value = "*")
    @GetMapping("/owner/{id}")
    public String getOwner(@PathVariable("id") String id) throws ParseException {

        //System.out.println(id);


        String url = "/owners/" + id;
        String result = sendData.getInfo(url);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(result);
        //System.out.println(json.toString());

        return json.toString();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public String getLocations() throws ParseException {

        String result = sendData.getInfo("/locations");
        JSONParser parser = new JSONParser();
        JSONArray json = (JSONArray) parser.parse(result);

        for (Object obj : json){
            JSONObject jsonObj = (JSONObject) parser.parse(obj.toString());
            System.out.println(jsonObj.toString());

        }

        return result;
    }

    @CrossOrigin(value = "*")
    @GetMapping("/location/{id}")
    public String getlocation(@PathVariable("id") String id) throws ParseException {

        //System.out.println(id);


        String url = "/locations/" + id;
        String result = sendData.getInfo(url);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(result);
        //System.out.println(json.toString());

        return json.toString();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/associations", method = RequestMethod.GET)
    public String getassociations() throws ParseException {

        String result = sendData.getInfo("/associations");
        JSONParser parser = new JSONParser();
        JSONArray json = (JSONArray) parser.parse(result);

        for (Object obj : json){
            JSONObject jsonObj = (JSONObject) parser.parse(obj.toString());
            System.out.println(jsonObj.toString());

        }

        return result;
    }

    @CrossOrigin(value = "*")
    @GetMapping("/association/{id}")
    public String getassociation(@PathVariable("id") String id) throws ParseException {

        //System.out.println(id);


        String url = "/associations/" + id;
        String result = sendData.getInfo(url);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(result);
        //System.out.println(json.toString());

        return json.toString();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/matchgoals", method = RequestMethod.GET)
    public String getmatchgoals() throws ParseException {

        String result = sendData.getInfo("/matchgoals");
        JSONParser parser = new JSONParser();
        JSONArray json = (JSONArray) parser.parse(result);

        for (Object obj : json){
            JSONObject jsonObj = (JSONObject) parser.parse(obj.toString());
            System.out.println(jsonObj.toString());

        }

        return result;
    }

    @CrossOrigin(value = "*")
    @GetMapping("/matchgoal/{id}")
    public String getmatchgoal(@PathVariable("id") String id) throws ParseException {

        //System.out.println(id);


        String url = "/matchgoals/" + id;
        String result = sendData.getInfo(url);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(result);
        //System.out.println(json.toString());

        return json.toString();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/seasons", method = RequestMethod.GET)
    public String getseasons() throws ParseException {

        String result = sendData.getInfo("/seasons");
        JSONParser parser = new JSONParser();
        JSONArray json = (JSONArray) parser.parse(result);

        for (Object obj : json){
            JSONObject jsonObj = (JSONObject) parser.parse(obj.toString());
            System.out.println(jsonObj.toString());

        }

        return result;
    }

    @CrossOrigin(value = "*")
    @GetMapping("/season/{id}")
    public String getseason(@PathVariable("id") String id) throws ParseException {

        //System.out.println(id);


        String url = "/seasons/" + id;
        String result = sendData.getInfo(url);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(result);
        //System.out.println(json.toString());

        return json.toString();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/goaltypes", method = RequestMethod.GET)
    public String getgoaltypes() throws ParseException {

        String result = sendData.getInfo("/goaltypes");
        JSONParser parser = new JSONParser();
        JSONArray json = (JSONArray) parser.parse(result);

        for (Object obj : json){
            JSONObject jsonObj = (JSONObject) parser.parse(obj.toString());
            System.out.println(jsonObj.toString());

        }

        return result;
    }

    @CrossOrigin(value = "*")
    @GetMapping("/goaltype/{id}")
    public String getgoaltype(@PathVariable("id") String id) throws ParseException {

        //System.out.println(id);


        String url = "/goaltypes/" + id;
        String result = sendData.getInfo(url);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(result);
        //System.out.println(json.toString());

        return json.toString();
    }

}
