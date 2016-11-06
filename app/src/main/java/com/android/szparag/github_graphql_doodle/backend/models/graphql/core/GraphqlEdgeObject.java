package com.android.szparag.github_graphql_doodle.backend.models.graphql.core;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.annotations.GraphqlType;

/**
 * Created by ciemek on 06/11/2016.
 */
@GraphqlType
public class GraphqlEdgeObject<T extends GraphqlBaseObject> {

    private String cursor;
    private T node;

}
