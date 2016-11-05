package com.android.szparag.github_graphql_doodle.dagger.modules;

import android.app.Application;
import android.content.Context;

import com.android.szparag.github_graphql_doodle.GraphqlDoodleApplication;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by ciemek on 04/11/2016.
 */

@Module
public class GraphqlDoodleModule {

    private GraphqlDoodleApplication application;

    public GraphqlDoodleModule(GraphqlDoodleApplication application) {
        this.application = application;
    }

    @Provides
    public Context provideContext() {
        return application;
    }

}
