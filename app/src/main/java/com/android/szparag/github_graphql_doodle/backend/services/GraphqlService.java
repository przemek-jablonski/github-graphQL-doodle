package com.android.szparag.github_graphql_doodle.backend.services;

import com.android.szparag.github_graphql_doodle.backend.models.Repository;
import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphQLBaseObject;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphQLResponseObject;

import graphql.schema.GraphQLObjectType;
import retrofit2.Callback;

/**
 * Created by ciemek on 05/11/2016.
 */

public interface GraphqlService {

    void getRepositoryOwner(Callback<GraphQLResponseObject<RepositoryOwner>> callback);
    void getRepositoryOwner(GraphQLObjectType query, Callback<GraphQLResponseObject<RepositoryOwner>> callback);

    void getRepository(Callback<GraphQLResponseObject<Repository>> callback);
    void getRepository(GraphQLObjectType query, Callback<GraphQLResponseObject<Repository>> callback);

    void getGraphData(GraphQLObjectType query, Callback<GraphQLResponseObject<GraphQLBaseObject>> callback);
}
