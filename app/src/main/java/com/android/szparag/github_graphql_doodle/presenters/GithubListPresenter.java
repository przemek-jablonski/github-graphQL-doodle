package com.android.szparag.github_graphql_doodle.presenters;

import com.android.szparag.github_graphql_doodle.backend.models.Repository;
import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphQLResponseObject;
import com.android.szparag.github_graphql_doodle.backend.services.GraphqlService;
import com.android.szparag.github_graphql_doodle.dagger.MainComponent;
import com.android.szparag.github_graphql_doodle.presenters.contracts.GithubListBasePresenter;
import com.android.szparag.github_graphql_doodle.utils.RepoStatsComparator;
import com.android.szparag.github_graphql_doodle.views.contracts.GithubListView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.szparag.github_graphql_doodle.utils.RepoStatsComparator.SortItem.STARS;


/**
 * Created by ciemek on 05/11/2016.
 */

public class GithubListPresenter implements GithubListBasePresenter {

    private GithubListView view;
    private RepositoryOwner repositoryOwner;
    private List<Repository> repositores;

    private RepoStatsComparator comparator;
    @Inject
    GraphqlService graphqlService;


    @Override
    public void setView(GithubListView view, MainComponent dagger) {
        this.view = view;
        dagger.inject(this);
        comparator = new RepoStatsComparator();
    }

    @Override
    public void checkInternetConnectivity() {

    }

    @Override
    public void checkGrantedPermissions() {
        //I don't check for permissions at runtime, because right now I only need
        // .INTERNET and .ACCESS_NETWORK_STATE:
        // http://stackoverflow.com/a/34435733/6942800
    }

    private void sortData() {
        Collections.sort(repositores, comparator.sortBy(STARS));
    }

    @Override
    public void fetchData() {
        if (repositoryOwner != null) {
            fetchRepositoryOwnerLocal();
        } else {
            fetchRepositoryOwnerGraph();
            fetchRepositoryOwnerExtras();
        }
    }


    //todo: method: sorting

    private void fetchRepositoryOwnerGraph() {
        graphqlService.getRepositoryOwner(new Callback<GraphQLResponseObject<RepositoryOwner>>() {
            @Override
            public void onResponse(Call<GraphQLResponseObject<RepositoryOwner>> call, Response<GraphQLResponseObject<RepositoryOwner>> response) {
                repositoryOwner = response.body().getObject();
                repositores = repositoryOwner.getRepositoriesList();
                fetchRepositoryOwnerLocal();
                view.showGithubFetchSuccess();
            }

            @Override
            public void onFailure(Call<GraphQLResponseObject<RepositoryOwner>> call, Throwable t) {
                if (view.getInternetConnectivity()) {
                    view.showGithubFetchFailure();
                } else {
                    view.showNetworkConnectionFailure();
                }
            }
        });
    }

    private void fetchRepositoryOwnerLocal() {
        sortData();
        view.updateRepositoriesListView(repositores);
        view.updateRepositoryOwnerView(repositoryOwner);
    }

    private void fetchRepositoryOwnerExtras() {
        //from REST API
    }

}
