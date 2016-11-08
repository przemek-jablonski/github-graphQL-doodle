package com.android.szparag.github_graphql_doodle.backend.models.graphql.core;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.annotations.GraphQLType;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ciemek on 06/11/2016.
 */

@GraphQLType
public class GraphQLGraphObject<T extends GraphQLBaseObject> {

    private List<GraphQLEdgeObject<T>> edges;
    private int totalCount;

    public List<GraphQLEdgeObject<T>> getEdges() {
        return edges;
    }

    public int getTotalCount() {
        return totalCount;
    }

}
