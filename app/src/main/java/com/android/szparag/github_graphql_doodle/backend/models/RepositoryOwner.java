package com.android.szparag.github_graphql_doodle.backend.models;

import android.support.annotation.Nullable;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.GraphqlBaseObject;

import java.util.LinkedHashMap;

import static com.android.szparag.github_graphql_doodle.utils.Constants.GraphqlConstants.ARGUMENT_LOGIN;

/**
 * Created by ciemek on 06/11/2016.
 */

public class RepositoryOwner extends GraphqlBaseObject {

    public RepositoryOwner(@Nullable LinkedHashMap<String, String> args) {
        super("repositoryOwner");
        super.availableArgs = new String[] {
                ARGUMENT_LOGIN
        };
        checkArguments(args);
    }


    private String avatarURL;
    private String login;
    private String path;
//    private GraphqlConnectionObject<GraphqlEdgeObject<Repository>> repositories; //todo: we can query for repository OR/AND repositories!
    private RepositoryConnection repositories;
    private Repository repository;  //todo: figure out some solution for this


    public void set(String avatarURL, String login, String path,
                    RepositoryConnection repositories, Repository repository) {
        this.avatarURL = avatarURL;
        this.login = login;
        this.path = path;
        this.repositories = repositories;
        this.repository = repository;
    }


    public String getAvatarURL() {
        return avatarURL;
    }

    public String getLogin() {
        return login;
    }

    public String getPath() {
        return path;
    }

    public RepositoryConnection getRepositories() {
        return repositories;
    }

    public Repository getRepository() {
        return repository;
    }
}
