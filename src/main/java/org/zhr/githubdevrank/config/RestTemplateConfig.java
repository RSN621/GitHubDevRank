package org.zhr.githubdevrank.config;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public JSONObject  jsonObject(){return new JSONObject();}
}
