package com.mycompany.app.Teams;


import com.fasterxml.jackson.annotation.JacksonInject;
import com.mycompany.app.Persons.PersonService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@RestController
@CrossOrigin(origins = "*")
public class TeamService {

    @JacksonInject
    RestTemplate restTemplate = new RestTemplate();


    @CrossOrigin("*")
    public String getInfo(String url){
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://ballc-backend-api.herokuapp.com"));
        return restTemplate.getForObject(url, String.class);
    }

    @CrossOrigin("*")
    public String getMatchInfo(){
        return restTemplate.getForObject("http://ballc-backend-api.herokuapp.com/matches", String.class);
    }

    @CrossOrigin(origins = "*")
    public String RegTeam(String url, String user){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(user, headers);
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://ballc-backend-api.herokuapp.com"));
        return restTemplate.exchange(url, HttpMethod.POST , entity, String.class).getBody();
    }




}
