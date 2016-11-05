package com.android.szparag.github_graphql_doodle.backend.services;

import retrofit2.Callback;

/**
 * Created by ciemek on 05/11/2016.
 */

public interface GraphqlService {

    void getData(String string, Callback<String> callback);

}
