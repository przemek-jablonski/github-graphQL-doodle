package com.android.szparag.github_graphql_doodle.presenters;

import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphQLResponseObject;
import com.android.szparag.github_graphql_doodle.backend.services.GraphqlService;
import com.android.szparag.github_graphql_doodle.dagger.MainComponent;
import com.android.szparag.github_graphql_doodle.presenters.contracts.GithubListBasePresenter;
import com.android.szparag.github_graphql_doodle.utils.Constants;
import com.android.szparag.github_graphql_doodle.views.contracts.GithubListView;

import java.util.LinkedHashMap;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ciemek on 05/11/2016.
 */

public class GithubListPresenter implements GithubListBasePresenter {

    private GithubListView view;
    private RepositoryOwner repositoryOwner;

    @Inject
    GraphqlService graphqlService;


    @Override
    public void setView(GithubListView view, MainComponent dagger) {
        this.view = view;
        dagger.inject(this);
    }

    @Override
    public void checkInternetConnectivity() {
        //...
    }

    @Override
    public void checkGrantedPermissions() {
        //...
    }

    @Override
    public void fetchData() {
//        if (repositoryOwner.repositories.edges.size() != 0) {
//            fetchRepositoryOwnerLocal();
//        } else {
            fetchRepositoryOwnerGraph();
//            fetchRepositoryOwnerExtras();
//        }
    }

    private void fetchRepositoryOwnerGraph() {


        String argumentsLoginString = "ReactiveX";
        LinkedHashMap<String, String> args = new LinkedHashMap<>();
        args.put(Constants.GraphqlConstants.ARGUMENT_LOGIN, argumentsLoginString);



        graphqlService.getRepositoryOwner(new Callback<GraphQLResponseObject<RepositoryOwner>>() {
            @Override
            public void onResponse(Call<GraphQLResponseObject<RepositoryOwner>> call, Response<GraphQLResponseObject<RepositoryOwner>> response) {
                view.showGithubFetchSuccess();
                view.updateRepositoriesListView(response.body().getObject().getRepositoriesList());
                view.showRepositoryOwnerView();
            }

            @Override
            public void onFailure(Call<GraphQLResponseObject<RepositoryOwner>> call, Throwable t) {
                view.showGithubFetchFailure();
            }
        });


    }

    private void fetchRepositoryOwnerLocal() {

    }

    private void fetchRepositoryOwnerExtras() {

    }

}
