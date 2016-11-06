package com.android.szparag.github_graphql_doodle.backend.services;

import com.android.szparag.github_graphql_doodle.backend.apis.GraphqlApi;
import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphqlBaseObject;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphqlResponseObject;

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
    public void getGraphData(GraphqlBaseObject graphqlBaseObject, Callback<GraphqlResponseObject<GraphqlBaseObject>> callback) {
        api.getGraphData(graphqlBaseObject).enqueue(callback);
    }

    @Override
    public void getRepositoryOwner(RepositoryOwner repositoryOwner, Callback<GraphqlResponseObject<RepositoryOwner>> callback) {
        api.getGraphData(repositoryOwner).enqueue(callback);
    }


//    @Override
//    public void getRepositoryOwner(RepositoryOwner repositoryOwner, Callback<GraphqlResponseObject<RepositoryOwner>> callback) {
//        api.getGraphData(repositoryOwner).enqueue(callback);
//    }
//
//    @Override
//    public void getGraphData(GraphqlBaseObject graphqlBaseObject, Callback<GraphqlResponseObject<GraphqlBaseObject>> callback) {
//        api.getGraphData(graphqlBaseObject).enqueue(callback);
//    }

    //    @Override
//    public void getRepositoryOwner(RepositoryOwner repositoryOwner, Callback<GraphqlResponseObject> callback) {
//        api.getRepositoryOwner(repositoryOwner).enqueue(callback);
//    }

//    @Override
//    public void getGraphData(GraphqlBaseObject graphqlObject, Callback<GraphqlResponseObject> callback) {
//        api.getGraphData(graphqlObject).enqueue(callback);
//    }


}
