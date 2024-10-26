package org.zhr.githubdevrank.lgoin;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GitHubUserDataFetcher {
    public static void main(String[] args) {
        String username = "rsn621"; // 替换为实际的 GitHub 用户名
        String token = "your_personal_access_token"; // 替换为您的个人访问令牌
        fetchGitHubUserData(username, token);
    }
    public static void fetchGitHubUserData(String username, String token) {
        HttpClient client = HttpClient.newHttpClient();
        String url = String.format("https://api.github.com/users/%s", username);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + token) // 添加个人访问令牌
                .header("Accept", "application/vnd.github.v3+json") // 指定接受的响应格式
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join(); // 等待请求完成并打印结果
    }
}
