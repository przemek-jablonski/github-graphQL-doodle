package com.android.szparag.github_graphql_doodle.presenters.contracts;

import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;
import com.android.szparag.github_graphql_doodle.dagger.MainComponent;
import com.android.szparag.github_graphql_doodle.views.contracts.GithubListView;

/**
 * Created by ciemek on 05/11/2016.
 */

public interface GithubListBasePresenter {

    void setView(GithubListView view, MainComponent dagger);

    void checkGrantedPermissions();

    void fetchData();

    void saveData(RepositoryOwner repositoryOwner);

}
