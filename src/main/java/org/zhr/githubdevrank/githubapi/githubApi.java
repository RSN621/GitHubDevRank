package org.zhr.githubdevrank.githubapi;

import com.jcabi.github.Coordinates;
import com.jcabi.github.Github;
import com.jcabi.github.Repo;
import com.jcabi.github.RtGithub;

import java.io.IOException;

public class githubApi {
    public static void main(String[] args) throws IOException {
        Github github=new RtGithub();
        Repo repo = github.repos().get(new Coordinates.Simple("rsn621", "carefree"));
        System.out.println(repo.stars());
        System.out.println(repo);
    }
}
