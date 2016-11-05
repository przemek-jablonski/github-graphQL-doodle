package com.android.szparag.github_graphql_doodle.backend.models.graphql;

/**
 * Created by ciemek on 05/11/2016.
 */

public class GraphqlEdgeObject extends GraphqlBaseObject{

    GraphqlNodeObject node;

    public GraphqlEdgeObject(String serializableName, boolean hasArguments, String argValue) {
        super(serializableName, hasArguments, argValue);
    }
}
