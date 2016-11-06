package com.android.szparag.github_graphql_doodle.backend.models;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.GraphqlBaseObject;

/**
 * Created by ciemek on 05/11/2016.
 */

public class RepositoryOwner extends GraphqlBaseObject {

    private String avatarURL;
    private String login;
    private String path;
    private String url;
    public Repository repositories;


    public RepositoryOwner(String serializableName, boolean hasArguments, String argValue) {
        super(serializableName, hasArguments, argValue);
        int repos = 3;

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

    public String getUrl() {
        return url;
    }

//    public Repository[] getRepositories() {
//        return repositories;
//    }
}
