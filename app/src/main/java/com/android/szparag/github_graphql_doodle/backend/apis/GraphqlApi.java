package com.android.szparag.github_graphql_doodle.backend.apis;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ciemek on 05/11/2016.
 */

public interface GraphqlApi {

    @POST("graphql")
    Call<String> getData(@Body String string);

}
