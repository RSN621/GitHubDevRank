package org.zhr.githubdevrank.controller;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.zhr.githubdevrank.view.ProjectView;
import org.zhr.githubdevrank.view.UserView;
import reactor.core.publisher.Mono;

@RestController("/")
public class githubUserDate {
    private final WebClient webClient;

    public githubUserDate(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/user")
    public Mono<UserView> fetchGitHubUserData(@RequestParam String user) {
        String url = "https://api.github.com/users/" + user;
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(UserView.class);
    }

    @GetMapping("/user/repos")
    public Mono<JSONArray> GitHubUserRepos(@RequestParam String user) {
        String url = "https://api.github.com/users/" + user + "/repos";
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JSONArray.class)
                .map(data -> {
                    JSONArray resultJson = new JSONArray();
                    for (int i = 0; i < data.size(); i++) {
                        resultJson.add(data.getJSONObject(i).getString("name"));
                    }
                    return resultJson;
                });
    }

    @GetMapping("/user/repo")
    public Mono<ResponseEntity<ProjectView>> UserRepo(@RequestParam String user, @RequestParam String repo) {
        String url = "https://api.github.com/repos/" + user + "/" + repo;
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(ProjectView.class)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/followers")
    public Mono<JSONArray> GitHubUserFollowers(@RequestParam String user) {
        String url = "https://api.github.com/users/" + user + "/followers";
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JSONArray.class)
                .map(data -> {
                    JSONArray resultJson = new JSONArray();
                    for (int i = 0; i < data.size(); i++) {
                        resultJson.add(data.getJSONObject(i).getString("login"));
                    }
                    return resultJson;
                });
    }

    @GetMapping("/user/contributors")
    public Mono<JSONArray> GitHubUserContributors(@RequestParam String user, @RequestParam String repo) {
        String url = "https://api.github.com/repos/" + user + "/" + repo + "/contributors";
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JSONArray.class)
                .map(data -> {
                    JSONArray resultJson = new JSONArray();
                    for (int i = 0; i < data.size(); i++) {
                        JSONObject obj = data.getJSONObject(i);
                        resultJson.add(obj.getString("login"));
                        resultJson.add(obj.getString("contributions"));
                    }
                    return resultJson;
                });
    }
}