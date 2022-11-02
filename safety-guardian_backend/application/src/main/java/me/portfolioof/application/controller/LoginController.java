package me.portfolioof.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "redirect:https://accounts.google.com/o/oauth2/v2/auth?" +
                "scope=https%3A//www.googleapis.com/auth/userinfo.profile&" +
                "access_type=offline&" +
                "include_granted_scopes=true&" +
                "response_type=code&" +
                " state=/handler&" +
                "redirect_uri=http%3A//localhost:8080/handler&" +
                "client_id=362548341839-85drmmkp873jq4j0dbteqaf9c5fidlfh.apps.googleusercontent.com";
    }

    @GetMapping("/handler")
    public String handle(@RequestParam String code) throws JsonProcessingException {
        String url = "https://oauth2.googleapis.com/token";
        MultiValueMap<String, String> args = new LinkedMultiValueMap<>();
        args.add("grant_type", "authorization_code");
        args.add("code", code);
        args.add("client_id", "362548341839-85drmmkp873jq4j0dbteqaf9c5fidlfh.apps.googleusercontent.com");
        args.add("client_secret", "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");
        args.add("redirect_uri", "http://localhost:8080/handler");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(args, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.postForObject(url, req, String.class);
        System.out.println(res);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(res);
        String token = jsonNode.get("access_token").toString();

        String userInfoURL = "https://www.googleapis.com/oauth2/v2/userinfo";
        RestTemplate getTemplate = new RestTemplate();
        HttpHeaders authHeaders = new HttpHeaders();
        authHeaders.setBearerAuth(token);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(authHeaders);
        ResponseEntity<String> userinfo = getTemplate.exchange(userInfoURL, HttpMethod.GET, request, String.class);
        System.out.println(userinfo.getBody());
        return "/";
    }
}
