package com.android.szparag.github_graphql_doodle;

import android.app.Application;

import com.android.szparag.github_graphql_doodle.dagger.DaggerMainComponent;
import com.android.szparag.github_graphql_doodle.dagger.MainComponent;
import com.android.szparag.github_graphql_doodle.dagger.modules.GraphqlDoodleModule;
import com.android.szparag.github_graphql_doodle.dagger.modules.NetworkingModule;
import com.facebook.stetho.Stetho;

/**
 * Created by ciemek on 04/11/2016.
 */

public class GraphqlDoodleApplication extends Application {

    private MainComponent daggerComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

        daggerComponent = DaggerMainComponent.builder()
                .graphqlDoodleModule(new GraphqlDoodleModule(this))
                .networkingModule(new NetworkingModule())
                .build();
    }

    public MainComponent getDaggerComponent() {
        return daggerComponent;
    }
}
