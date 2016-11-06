package com.android.szparag.github_graphql_doodle.backend.models;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.GraphqlBaseObject;

/**
 * Created by ciemek on 05/11/2016.
 */

public class Stargazers extends GraphqlBaseObject {
    public Stargazers(boolean hasArguments, String argValue) {
        super("stargazers", hasArguments, argValue);
    }

    public Integer totalCount;
}
