package com.android.szparag.github_graphql_doodle.dagger.modules;

import android.content.Context;

import com.android.szparag.github_graphql_doodle.GraphqlDoodleApplication;
import com.android.szparag.github_graphql_doodle.presenters.GithubListPresenter;
import com.android.szparag.github_graphql_doodle.presenters.contracts.GithubListBasePresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    GithubListBasePresenter provideGithubListBasePresenter() {
        return new GithubListPresenter();
    }

}
