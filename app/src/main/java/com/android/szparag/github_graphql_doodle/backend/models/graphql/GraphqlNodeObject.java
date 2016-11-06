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

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getDescription() {
        return description;
    }

    public Forks getForks() {
        return forks;
    }

    public Stargazers getStargazers() {
        return stargazers;
    }

    public Watchers getWatchers() {
        return watchers;
    }
}
