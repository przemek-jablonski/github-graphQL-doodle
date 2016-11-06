package com.android.szparag.github_graphql_doodle.backend.models.graphql.core;

import java.util.List;

/**
 * Created by ciemek on 06/11/2016.
 */

public class GraphqlConnectionObject<E extends GraphqlEdgeObject> {


    private List<E> edges;
//    private PageInfo pageInfo;
    private int totalCount;

}
