package com.android.szparag.github_graphql_doodle.backend.models.graphql.core;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.annotations.GraphQLType;
import com.android.szparag.github_graphql_doodle.utils.Constants;
import com.google.gson.annotations.SerializedName;

import static com.android.szparag.github_graphql_doodle.utils.Constants.GraphqlConstants.NAME_REPOSITORIES;
import static com.android.szparag.github_graphql_doodle.utils.Constants.GraphqlConstants.NAME_REPOSITORY;
import static com.android.szparag.github_graphql_doodle.utils.Constants.GraphqlConstants.NAME_REPOSITORY_OWNER;
import static com.android.szparag.github_graphql_doodle.utils.Constants.GraphqlConstants.NAME_VIEWER;

/**
 * Created by ciemek on 05/11/2016.
 */
@GraphQLType
public class GraphQLResponseObject<T extends GraphQLBaseObject> extends GraphQLBaseObject {

    private GraphQLDataObject<T> data;

    public GraphQLResponseObject() {
        super(Constants.GraphqlConstants.NAME_BLANK);
    }

    public GraphQLDataObject<T> getData() {
        return data;
    }

    public T getObject() {
        return data.object;
    }


    public class GraphQLDataObject<T extends GraphQLBaseObject> extends GraphQLBaseObject {
        public GraphQLDataObject() {
            super(Constants.GraphqlConstants.NAME_BLANK);
        }
        @SerializedName(
                value = NAME_REPOSITORY_OWNER,
                alternate = {NAME_REPOSITORIES, NAME_REPOSITORY, NAME_VIEWER})
        private T object;

    }

}
