package com.android.szparag.github_graphql_doodle.backend.services;

import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.GraphqlBaseObject;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.GraphqlDataObject;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.GraphqlResponseObject;

import retrofit2.Callback;

/**
 * Created by ciemek on 05/11/2016.
 */

public interface GraphqlService {

    void getGraphData(GraphqlBaseObject graphqlObject, Callback<GraphqlResponseObject> callback);

}
