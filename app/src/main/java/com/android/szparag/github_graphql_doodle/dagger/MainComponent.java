package com.android.szparag.github_graphql_doodle.dagger;

import com.android.szparag.github_graphql_doodle.dagger.modules.GraphqlDoodleModule;
import com.android.szparag.github_graphql_doodle.dagger.modules.NetworkingModule;
import com.android.szparag.github_graphql_doodle.views.MainActivityFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ciemek on 04/11/2016.
 */

@Singleton
@Component(modules =  {GraphqlDoodleModule.class, NetworkingModule.class})
public interface MainComponent {

    void inject(MainActivityFragment target);

}
