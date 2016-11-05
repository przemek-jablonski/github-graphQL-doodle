package com.android.szparag.github_graphql_doodle.backend.models;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.GraphqlBaseObject;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.GraphqlEdgeObject;

import java.util.ArrayList;
import java.util.List;

import graphql.relay.Edge;

/**
 * Created by ciemek on 05/11/2016.
 */

public class Repository extends GraphqlBaseObject {

    public Repository(String serializableName, boolean hasArguments, String argValue) {
        super(serializableName, hasArguments, argValue);
    }

    public List<GraphqlEdgeObject> edges = new ArrayList<GraphqlEdgeObject>();



}
