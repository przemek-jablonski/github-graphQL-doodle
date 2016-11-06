package com.android.szparag.github_graphql_doodle.backend.models.graphql.core;

import com.google.gson.annotations.SerializedName;

import static com.android.szparag.github_graphql_doodle.utils.Constants.GraphqlConstants.NAME_REPOSITORIES;
import static com.android.szparag.github_graphql_doodle.utils.Constants.GraphqlConstants.NAME_REPOSITORY;
import static com.android.szparag.github_graphql_doodle.utils.Constants.GraphqlConstants.NAME_REPOSITORY_OWNER;
import static com.android.szparag.github_graphql_doodle.utils.Constants.GraphqlConstants.NAME_VIEWER;

/**
 * Created by ciemek on 05/11/2016.
 */

public class GraphqlResponseObject<T extends GraphqlBaseObject> extends GraphqlBaseObject {

    private GraphqlDataObject<T> data;

    public GraphqlResponseObject() {
        super("");
    }

    public GraphqlDataObject<T> getData() {
        return data;
    }

    public T getObject() {
        return data.object;
    }



    public class GraphqlDataObject<T extends GraphqlBaseObject> extends GraphqlBaseObject {
        public GraphqlDataObject() {
            super("");
        }
        @SerializedName(
                value = NAME_REPOSITORY_OWNER,
                alternate = {NAME_REPOSITORIES, NAME_REPOSITORY, NAME_VIEWER})
        private T object;

    }

}
