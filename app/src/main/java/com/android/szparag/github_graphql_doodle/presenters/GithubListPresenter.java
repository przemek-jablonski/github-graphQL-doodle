package com.android.szparag.github_graphql_doodle.presenters;

import android.support.annotation.NonNull;

import com.android.szparag.github_graphql_doodle.backend.models.Repository;
import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphQLResponseObject;
import com.android.szparag.github_graphql_doodle.backend.services.GraphqlService;
import com.android.szparag.github_graphql_doodle.dagger.MainComponent;
import com.android.szparag.github_graphql_doodle.presenters.contracts.GithubListBasePresenter;
import com.android.szparag.github_graphql_doodle.repositories.InMemoryRepositoryOwnerRepository;
import com.android.szparag.github_graphql_doodle.repositories.RepositoryOwnerRepository;
import com.android.szparag.github_graphql_doodle.utils.RepoStatsComparator;
import com.android.szparag.github_graphql_doodle.views.contracts.GithubListView;

import java.security.InvalidKeyException;
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

    @Inject
    GraphqlService graphqlService;

    @Inject
    RepositoryOwnerRepository dataRepository;

    private GithubListView view;


    @Override
    public void setView(GithubListView view, MainComponent dagger) {
        this.view = view;
        dagger.inject(this);
    }

    @Override
    public void setView(GithubListView view, GraphqlService service, RepositoryOwnerRepository dataRepository) {
        this.view = view;
        this.graphqlService = service;
        this.dataRepository = dataRepository;

    }

    @Override
    public void checkGrantedPermissions() {
        //I don't check for permissions at runtime, because right now I only need .INTERNET
        // http://stackoverflow.com/a/34435733/6942800
    }

    @Override
    public void fetchData() {
        if (dataRepository.getRepositoryOwner() != null) {
            fetchDataLocal();
        } else {
            fetchDataGraphql();
            fetchDataExtrasRest();
        }
    }

    @Override
    public void saveData(RepositoryOwner repositoryOwner) {
        dataRepository.saveRepositoryOwner(repositoryOwner);
    }


    void fetchDataGraphql() {
        graphqlService.getRepositoryOwner(new Callback<GraphQLResponseObject<RepositoryOwner>>() {
            @Override
            public void onResponse(Call<GraphQLResponseObject<RepositoryOwner>> call, Response<GraphQLResponseObject<RepositoryOwner>> response) {
                if (response.body() == null || response.body().getObject() == null) {
                    onFailure(call, new InvalidKeyException());
                    return;
                }
                saveData(response.body().getObject());
                fetchDataLocal();
                view.showGithubFetchSuccess();
            }

            @Override
            public void onFailure(Call<GraphQLResponseObject<RepositoryOwner>> call, Throwable t) {
                if(t instanceof InvalidKeyException) {
                    view.showGithubApiKeyFailure();
                }
                if (view.getInternetConnectivity()) {
                    view.showGithubFetchFailure();
                } else {
                    view.showNetworkConnectionFailure();
                }
            }
        });
    }

    void fetchDataLocal() {
        view.updateRepositoriesListView(dataRepository.getRepositories());
        view.updateRepositoryOwnerView(dataRepository.getRepositoryOwner());
        view.showRepositoriesListView();
        view.showRepositoryOwnerView();
    }


    void fetchDataExtrasRest() {
        //extra data from REST API
    }

}
