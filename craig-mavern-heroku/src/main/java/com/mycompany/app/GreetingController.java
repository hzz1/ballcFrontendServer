package com.mycompany.app;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.mycompany.app.Persons.Persons;
import com.mycompany.app.Users.Users;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.net.*;
import java.util.ArrayList;


@RestController
@CrossOrigin(origins = "*")
public class GreetingController {

    ArrayList<Users> users = new ArrayList<>();
    ArrayList<Persons> persons = new ArrayList<>();

    @JacksonInject
    RestTemplate restTemplate = new RestTemplate();
    SendData sendData = new SendData();
    MyCookieStore myStore = new MyCookieStore();
    HttpCookie myCookie = new HttpCookie("name", null);

}