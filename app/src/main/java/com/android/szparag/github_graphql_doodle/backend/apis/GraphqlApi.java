package com.android.szparag.github_graphql_doodle.backend.apis;

import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.GraphqlBaseObject;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.GraphqlDataObject;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.GraphqlResponseObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ciemek on 05/11/2016.
 */

public interface GraphqlApi {

    @POST("graphql")
    Call<GraphqlResponseObject> getGraphData(@Body GraphqlBaseObject graphqlObject);

}
