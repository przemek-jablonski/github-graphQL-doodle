package com.android.szparag.github_graphql_doodle.backend.models.graphql.core;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.annotations.GraphqlType;

import java.util.List;

/**
 * Created by ciemek on 06/11/2016.
 */

@GraphqlType
public class GraphqlConnectionObject<E extends GraphqlEdgeObject> {

    private List<E> edges;
//    private PageInfo pageInfo;
    private int totalCount;

}
