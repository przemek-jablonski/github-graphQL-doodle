package com.android.szparag.github_graphql_doodle.backend.models.graphql.core;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.annotations.GraphQLType;
import com.android.szparag.github_graphql_doodle.utils.Constants;

/**
 * Created by ciemek on 06/11/2016.
 */
@GraphQLType
public class GraphQLEdgeObject<T extends GraphQLBaseObject> extends GraphQLBaseObject{

    public GraphQLEdgeObject() {
        super(Constants.GraphqlConstants.NAME_BLANK);
    }

//    private String cursor;
    @GraphQLType
    private T node;

}
