package com.android.szparag.github_graphql_doodle.backend.services;

import com.android.szparag.github_graphql_doodle.backend.apis.GraphqlApi;
import com.android.szparag.github_graphql_doodle.backend.models.Repository;
import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphQLBaseObject;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphQLResponseObject;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.queries.RepositoryOwnerQueryFull;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.queries.RepositoryQueryShallow;

import graphql.schema.GraphQLObjectType;
import retrofit2.Callback;

/**
 * Created by ciemek on 05/11/2016.
 */

public class GraphqlServiceImpl implements GraphqlService {

    private GraphqlApi api;

    public GraphqlServiceImpl(GraphqlApi api) {
        this.api = api;
    }


    @Override
    public void getRepositoryOwner(Callback<GraphQLResponseObject<RepositoryOwner>> callback) {
        getRepositoryOwner(new RepositoryOwnerQueryFull().getQuery(), callback);
    }

    @Override
    public void getRepositoryOwner(GraphQLObjectType query, Callback<GraphQLResponseObject<RepositoryOwner>> callback) {
        api.getRepositoryOwner(query).enqueue(callback);
    }


    @Override
    public void getRepository(Callback<GraphQLResponseObject<Repository>> callback) {
        getRepository(new RepositoryQueryShallow().getQuery(), callback);
    }

    @Override
    public void getRepository(GraphQLObjectType query, Callback<GraphQLResponseObject<Repository>> callback) {
        api.getRepository(query).enqueue(callback);
    }


    @Override
    public void getGraphData(GraphQLObjectType query, Callback<GraphQLResponseObject<GraphQLBaseObject>> callback) {
        api.getGraphData(query).enqueue(callback);
    }
}
