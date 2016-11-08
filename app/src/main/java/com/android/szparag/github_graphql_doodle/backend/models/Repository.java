package com.android.szparag.github_graphql_doodle.backend.models;

import android.support.annotation.Nullable;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphQLBaseObject;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphQLGraphObject;

import java.util.LinkedHashMap;

import static com.android.szparag.github_graphql_doodle.utils.Constants.GraphqlConstants.*;

/**
 * Created by ciemek on 06/11/2016.
 */
public class Repository extends GraphQLBaseObject {

    public Repository(@Nullable LinkedHashMap<String, String> args) {
        super(NAME_REPOSITORY);
        super.availableArgs = new String[]{
                ARGUMENT_FIRST,
                ARGUMENT_AFTER,
                ARGUMENT_LAST,
                ARGUMENT_BEFORE,
                ARGUMENT_PRIVACY,
                ARGUMENT_ISFORK,
                ARGUMENT_ORDERBY,
        };
        checkArguments(args);
    }

    public Repository() {
        this(null);
    }


    private String description;
    private String homepageURL;
    private String name;
    private String path;


    private GraphQLGraphObject<Repository>  forks;
    private GraphQLGraphObject<PullRequest> pullRequests;
    private GraphQLGraphObject<User>        stargazers;
    private GraphQLGraphObject<User>        watchers;


    public String[] getAvailableArgs() {
        return availableArgs;
    }

    public String getDescription() {
        return description;
    }

    public String getHomepageURL() {
        return homepageURL;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public GraphQLGraphObject<Repository> getForks() {
        return forks;
    }

    public GraphQLGraphObject<PullRequest> getPullRequests() {
        return pullRequests;
    }

    public GraphQLGraphObject<User> getStargazers() {
        return stargazers;
    }

    public GraphQLGraphObject<User> getWatchers() {
        return watchers;
    }
}
