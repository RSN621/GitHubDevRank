package org.zhr.githubdevrank.githubapi;

import com.jcabi.github.Github;
import com.jcabi.github.RtGithub;
import com.jcabi.http.response.JsonResponse;

import javax.json.JsonObject;
import java.util.List;
import java.io.IOException;

public class search {
    public static void main(String[] args) throws IOException {
        final Github github = new RtGithub();
        final JsonResponse resp = github.entry()
                .uri().path("/search/repositories")
                .queryParam("q", "rsn621").back()
                .fetch()
                .as(JsonResponse.class);
        final List<JsonObject> items = resp.json().readObject()
                .getJsonArray("items")
                .getValuesAs(JsonObject.class);
        for (final JsonObject item : items) {
            System.out.printf(
                    "repository found: %s%n",
                    item.get("full_name").toString()
            );
        }
    }
}

