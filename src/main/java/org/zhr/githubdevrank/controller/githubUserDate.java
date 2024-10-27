package org.zhr.githubdevrank.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


@Controller("/")
public class githubUserDate {
    @Resource
    private RestTemplate restTemplate;

    @ResponseBody
    @GetMapping("/user")
    public ResponseEntity<String> fetchGitHubUserData(String username)  {
        //127.0.0.1:8080/user?username=rsn621
        System.out.println("请求user  "+username);
        String url = "https://api.github.com/users/"+username;
        return restTemplate.getForEntity(url, String.class);
    }
    @ResponseBody
    @GetMapping("/user/repos")
    public JSONArray GitHubUserRepos(String username)  {
        //http://127.0.0.1:8080/user/repos?username=rsn621
        System.out.println("请求repos  "+username);
        String url = "https://api.github.com/users/"+username+"/repos";
        ResponseEntity<String> data=restTemplate.getForEntity(url, String.class);
        JSONArray dataJson = JSON.parseArray(data.getBody());
        JSONArray resultJson = new JSONArray();
        if (dataJson != null) {
            for (int i = 0; i < dataJson.size(); i++) {
                JSONObject obj = dataJson.getJSONObject(i);
                resultJson.add(obj.getString("name"));
            }
        }
        return resultJson;
    }
    @ResponseBody
    @GetMapping("/user/followers")
    public JSONArray GitHubUserFollowers(String username)  {
        //http://127.0.0.1:8080/user/followers?username=rsn621
        System.out.println("请求repos  "+username);
        String url = "https://api.github.com/users/"+username+"/followers";
        ResponseEntity<String> data=restTemplate.getForEntity(url, String.class);
        JSONArray dataJson = JSON.parseArray(data.getBody());
        JSONArray resultJson = new JSONArray();
        int count=0;
        if (dataJson != null) {
            for (int i = 0; i < dataJson.size(); i++) {
                JSONObject obj = dataJson.getJSONObject(i);
                resultJson.add(obj.getString("login"));
                count++;
            }
        }
        System.out.println(count);
        return resultJson;
    }
    @ResponseBody
    @GetMapping("/user/contributors")
    public JSONArray GitHubUserContributors(String username,String repo)  {
        //http://127.0.0.1:8080/user/contributors?username=rsn621&repo=carefree
        System.out.println(username+" "+repo);
        String url = "https://api.github.com/repos/"+username+"/"+repo+"/contributors";
        ResponseEntity<String> data=restTemplate.getForEntity(url, String.class);
        JSONArray dataJson = JSON.parseArray(data.getBody());
        JSONArray resultJson = new JSONArray();
        int count=0;
        if (dataJson != null) {
            for (int i = 0; i < dataJson.size(); i++) {
                JSONObject obj = dataJson.getJSONObject(i);
                resultJson.add(obj.getString("login"));
                resultJson.add(obj.getString("contributions"));
                count++;
            }
        }
        System.out.println(count);
        return resultJson;
    }
}


