package com.android.szparag.github_graphql_doodle.backend.apis;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.GraphqlBaseObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ciemek on 05/11/2016.
 */

public interface GraphqlApi {

    @POST("graphql")
    Call<GraphqlBaseObject> getGraphData(@Body GraphqlBaseObject graphqlObject);

}
