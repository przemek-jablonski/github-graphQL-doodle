package com.android.szparag.github_graphql_doodle.backend.models.graphql;

import com.android.szparag.github_graphql_doodle.backend.models.Forks;
import com.android.szparag.github_graphql_doodle.backend.models.Stargazers;
import com.android.szparag.github_graphql_doodle.backend.models.Watchers;

/**
 * Created by ciemek on 05/11/2016.
 */

public class GraphqlNodeObject extends GraphqlBaseObject {
    public GraphqlNodeObject(String serializableName, boolean hasArguments, String argValue) {
        super(serializableName, hasArguments, argValue);
    }

    String name;
    String path;
    String description;

    Forks forks;
    Stargazers stargazers;
    Watchers watchers;
}
