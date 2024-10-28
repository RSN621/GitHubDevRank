package org.zhr.githubdevrank.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserView {
    //用户登录名
    @JsonProperty("login")
    private String login;
    //id
    @JsonProperty("id")
    private int id;
    //名字
    @JsonProperty("name")
    private String name;
    //地址
    @JsonProperty("location")
    private String location;
    // 关注数
    @JsonProperty("followers")
    private int followers;
    // 公有仓库数量
    @JsonProperty("public_repos")
    private int public_repos;
    //头像地址avatar_url: https://avatars.githubusercontent.com/u/106749034?v=4
    @JsonProperty("avatar_url")
    private String avatar_url;
    // github主页
    @JsonProperty("html_url")
    private String html_url;
    //博客
    @JsonProperty("blog")
    private String blog;
    //邮箱
    @JsonProperty("email")
    private String email;
}
