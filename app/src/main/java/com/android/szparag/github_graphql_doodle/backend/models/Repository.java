package com.android.szparag.github_graphql_doodle.backend.models;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.GraphqlBaseObject;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.GraphqlEgdeObject;

/**
 * Created by ciemek on 05/11/2016.
 */

public class Repository extends GraphqlBaseObject {

    public Repository(String serializableName, boolean hasArguments, String argValue) {
        super(serializableName, hasArguments, argValue);
    }


    GraphqlEgdeObject<Repository>[] edges;

}
