package com.android.szparag.github_graphql_doodle.backend.models;

import android.support.annotation.Nullable;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphqlBaseObject;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphqlConnectionObject;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphqlEdgeObject;

import java.util.LinkedHashMap;

import static com.android.szparag.github_graphql_doodle.utils.Constants.GraphqlConstants.*;

/**
 * Created by ciemek on 06/11/2016.
 */
public class Repository extends GraphqlBaseObject {

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


    private String description;
    private String homepageURL;
    private String name;
    private String url;

//    private GraphqlConnectionObject<GraphqlEdgeObject<Repository>>  forks;
//    private GraphqlConnectionObject<GraphqlEdgeObject<PullRequest>> pullRequests;
//    private GraphqlConnectionObject<GraphqlEdgeObject<User>>        stargazers;
//    private GraphqlConnectionObject<GraphqlEdgeObject<User>>        watchers;


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

    public String getUrl() {
        return url;
    }

}
