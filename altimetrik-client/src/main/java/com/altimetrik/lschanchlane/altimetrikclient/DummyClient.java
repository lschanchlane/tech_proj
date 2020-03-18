package com.altimetrik.lschanchlane.altimetrikclient;


import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DummyClient {

    @GetMapping(produces = "application/json")
    @RequestMapping("/dummyclient")
    public String composeDummy(){
        RestTemplate restTemplate = new RestTemplate();
        UserPassword userPassword = new UserPassword("altimetrik","altimetrik");
        String loginResponse = restTemplate.postForObject("http://localhost:8080/login/",userPassword,String.class ); //TODO: Add URL to PROPS
        System.out.println("Token = " + loginResponse);
        if(loginResponse!= null){

            Map<String,String> headerMap  = new HashMap<>();
            headerMap.put(HttpHeaders.AUTHORIZATION, loginResponse);
            headerMap.put(HttpHeaders.)
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

            headers.add("Authorization", loginResponse);
            String url = "http://localhost:8080/dummyMethod";
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            HttpEntity<String> httpEntity = new HttpEntity<String>("data : {Hello}", headers);
            String response = restTemplate.postForObject(url,httpEntity,String.class);

            return response;
        }
        return "Failure";
    }

}
