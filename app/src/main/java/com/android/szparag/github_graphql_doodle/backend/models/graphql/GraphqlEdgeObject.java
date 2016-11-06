package com.android.szparag.github_graphql_doodle.backend.models.graphql;

/**
 * Created by ciemek on 06/11/2016.
 */
public class GraphqlEdgeObject<T extends GraphqlBaseObject> {

    private String cursor;
    private T node;

}
