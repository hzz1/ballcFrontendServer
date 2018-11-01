package com.mycompany.app.Teams;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "*")
public class PostTeam {

    RestTemplate restTemplate = new RestTemplate();
    TeamService sendData = new TeamService();

    @CrossOrigin(value = "*")
    @RequestMapping(value = "/teams", method = RequestMethod.POST)
    public String createTeam(@RequestBody String user){

        sendData.RegTeam("/teams", user);

        return user;
    }

}
