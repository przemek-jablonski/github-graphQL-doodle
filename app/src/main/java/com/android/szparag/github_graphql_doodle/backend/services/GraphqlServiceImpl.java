package com.android.szparag.github_graphql_doodle.backend.services;

import com.android.szparag.github_graphql_doodle.GraphqlDoodleApplication;
import com.android.szparag.github_graphql_doodle.backend.apis.GraphqlApi;

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
    public void getData(String string, Callback<String> callback) {
        api.getData(string).enqueue(callback);
    }
}
