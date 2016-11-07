package com.android.szparag.github_graphql_doodle.backend.models.graphql.core;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.annotations.GraphQLType;

import java.util.LinkedList;
import java.util.List;

import static com.android.szparag.github_graphql_doodle.utils.Constants.GraphqlConstants.NAME_BLANK;

/**
 * Created by ciemek on 06/11/2016.
 */

@GraphQLType
public class GraphQLConnectionObject<E extends GraphQLEdgeObject> extends GraphQLBaseObject {

    public GraphQLConnectionObject() {
        super(NAME_BLANK);
        edges = new LinkedList<>();
    }

    public GraphQLConnectionObject(String serializableName) {
        super(serializableName);
    }

    private int totalCount;

    private List<E> edges;

    public int getTotalCount() {
        return totalCount;
    }

    public List<E> getEdges() {
        return edges;
    }
}
