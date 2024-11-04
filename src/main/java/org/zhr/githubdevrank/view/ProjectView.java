package org.zhr.githubdevrank.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectView {
    @JsonProperty("html_url")
    private String html_url;
    @JsonProperty("description")
    private String description;
    // 开发语言
    @JsonProperty("language")
    private String language;
    // fork数（被复制的次数）
    @JsonProperty("forks_count")
    private int forks_count;
    // 点赞数
    @JsonProperty("stargazers_count")
    private int stargazers_count;
    // 项目大小
    @JsonProperty("size")
    private int size;
    //关注者数量
    @JsonProperty("watchers")
    private int watchers;
    //查看人数
    @JsonProperty("watchers_count")
    private int watchers_count;
    //公开issues数
    @JsonProperty("open_issues_count")
    private int open_issues_count;
}
