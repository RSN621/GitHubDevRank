package org.zhr.githubdevrank.controller;

import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;

@Controller("/")
public class githubUserDate {
    @Resource
    private RestTemplate restTemplate;

    @ResponseBody
    @GetMapping("/user")
    public ResponseEntity<String> fetchGitHubUserData(String username)  {
        System.out.println("请求user  "+username);
        String url = "https://api.github.com/users/"+username;
        ResponseEntity<String> result =restTemplate.getForEntity(url, String.class);
        return result;
    }
}


