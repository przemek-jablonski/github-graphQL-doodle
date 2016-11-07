package com.android.szparag.github_graphql_doodle.backend.services;

import com.android.szparag.github_graphql_doodle.backend.apis.GraphqlApi;
import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphQLBaseObject;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphQLResponseObject;

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
    public void getGraphData(GraphQLBaseObject graphQLBaseObject, Callback<GraphQLResponseObject<GraphQLBaseObject>> callback) {
        api.getGraphData(graphQLBaseObject).enqueue(callback);
    }

    @Override
    public void getRepositoryOwner(RepositoryOwner repositoryOwner, Callback<GraphQLResponseObject<RepositoryOwner>> callback) {
        api.getGraphData(repositoryOwner).enqueue(callback);
    }


//    @Override
//    public void getRepositoryOwner(RepositoryOwner repositoryOwner, Callback<GraphQLResponseObject<RepositoryOwner>> callback) {
//        api.getGraphData(repositoryOwner).enqueue(callback);
//    }
//
//    @Override
//    public void getGraphData(GraphQLBaseObject graphqlBaseObject, Callback<GraphQLResponseObject<GraphQLBaseObject>> callback) {
//        api.getGraphData(graphqlBaseObject).enqueue(callback);
//    }

    //    @Override
//    public void getRepositoryOwner(RepositoryOwner repositoryOwner, Callback<GraphQLResponseObject> callback) {
//        api.getRepositoryOwner(repositoryOwner).enqueue(callback);
//    }

//    @Override
//    public void getGraphData(GraphQLBaseObject graphqlObject, Callback<GraphQLResponseObject> callback) {
//        api.getGraphData(graphqlObject).enqueue(callback);
//    }


}
