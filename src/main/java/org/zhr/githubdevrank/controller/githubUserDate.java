package org.zhr.githubdevrank.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.zhr.githubdevrank.view.ProjectView;
import org.zhr.githubdevrank.view.UserView;


@Controller("/")
public class githubUserDate {
    @Resource
    private RestTemplate restTemplate;

    @ResponseBody
    @GetMapping("/user")
    public UserView fetchGitHubUserData(String user) throws JsonProcessingException {
        //127.0.0.1:8080/user?username=rsn621  查询用户数据
        System.out.println("请求user  " + user);
        String url = "https://api.github.com/users/" + user;
        JSONObject jsonObject = JSON.parseObject(restTemplate.getForEntity(url, String.class).getBody());
        return jsonObject.toJavaObject(UserView.class);
    }

    @ResponseBody
    @GetMapping("/user/repos")
    public JSONArray GitHubUserRepos(String user) {
        //http://127.0.0.1:8080/user/repos?user=rsn621 查询仓库数据
        System.out.println("请求repos  " + user);
        String url = "https://api.github.com/users/" + user + "/repos";
        ResponseEntity<String> data = restTemplate.getForEntity(url, String.class);
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
    @GetMapping("/user/repo")
    public ProjectView UserRepo(String user, String repo) {
        //127.0.0.1:8080/user/repo?user=rsn621&repo=carefree 查询项目
        System.out.println("请求user  " + user);
        String url = "https://api.github.com/repos/" + user + "/" + repo;
        JSONObject jsonObject = JSON.parseObject(restTemplate.getForEntity(url, String.class).getBody());
        return jsonObject.toJavaObject(ProjectView.class);
    }

    @ResponseBody
    @GetMapping("/user/followers")
    public JSONArray GitHubUserFollowers(String user) {
        //http://127.0.0.1:8080/user/followers?user=rsn621
        System.out.println("请求repos  " + user);
        String url = "https://api.github.com/users/" + user + "/followers";
        ResponseEntity<String> data = restTemplate.getForEntity(url, String.class);
        JSONArray dataJson = JSON.parseArray(data.getBody());
        JSONArray resultJson = new JSONArray();
        int count = 0;
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
    public JSONArray GitHubUserContributors(String user, String repo) {
        //http://127.0.0.1:8080/user/contributors?user=rsn621&repo=carefree
        System.out.println(user + " " + repo);
        String url = "https://api.github.com/repos/" + user + "/" + repo + "/contributors";
        ResponseEntity<String> data = restTemplate.getForEntity(url, String.class);
        JSONArray dataJson = JSON.parseArray(data.getBody());
        JSONArray resultJson = new JSONArray();
        int count = 0;
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


