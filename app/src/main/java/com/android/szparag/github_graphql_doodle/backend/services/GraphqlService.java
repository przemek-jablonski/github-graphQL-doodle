package com.android.szparag.github_graphql_doodle.backend.services;

import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphQLBaseObject;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphQLResponseObject;

import retrofit2.Callback;

/**
 * Created by ciemek on 05/11/2016.
 */

public interface GraphqlService {

    void getGraphData(GraphQLBaseObject graphQLBaseObject, Callback<GraphQLResponseObject<GraphQLBaseObject>> callback);

    void getRepositoryOwner(RepositoryOwner repositoryOwner, Callback<GraphQLResponseObject<RepositoryOwner>> callback);

}
