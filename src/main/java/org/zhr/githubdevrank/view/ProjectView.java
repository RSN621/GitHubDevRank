package org.zhr.githubdevrank.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectView {
    @JsonProperty("html_url")
    private String html_url;
    @JsonProperty("fork")
    private boolean fork;
    @JsonProperty("description")
    private String description;
    // 开发语言
    @JsonProperty("language")
    private String language;
    // 点赞数
    @JsonProperty("stargazers_count")
    private int stargazers_count;
    // fork数
    @JsonProperty("forks_count")
    private int forks_count;
}
