package com.android.szparag.github_graphql_doodle.presenters;

import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.GraphqlEdgeObject;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.GraphqlNodeObject;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.GraphqlResponseObject;
import com.android.szparag.github_graphql_doodle.backend.services.GraphqlService;
import com.android.szparag.github_graphql_doodle.dagger.MainComponent;
import com.android.szparag.github_graphql_doodle.presenters.contracts.GithubListBasePresenter;
import com.android.szparag.github_graphql_doodle.utils.Utils;
import com.android.szparag.github_graphql_doodle.views.contracts.GithubListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
    GraphqlService service;


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
        //todo: this shit looks bad - instantiating before doing internet fetching?
        repositoryOwner = new RepositoryOwner("repositoryOwner", true, "ReactiveX");

        //todo: maybe this 3 parameters above should go below, into .getGraphData(...)?
        service.getGraphData(repositoryOwner, new Callback<GraphqlResponseObject>() {
                    @Override
                    public void onResponse(Call<GraphqlResponseObject> call, Response<GraphqlResponseObject> response) {
                        GraphqlResponseObject obj = response.body();
                        view.updateRepositoryOwnerView(obj.getData().getRepositoryOwner());

                        List<GraphqlEdgeObject> edges = obj.getData().getRepositoryOwner().repositories.edges;
                        List<GraphqlNodeObject> nodes = new ArrayList<GraphqlNodeObject>(edges.size());

                        for (int i = 0; i < edges.size(); ++i) {
                            nodes.add(edges.get(i).node);
                        }
                        view.updateRepositoriesListView(nodes);
                        view.showGithubFetchSuccess();
                    }

                    @Override
                    public void onFailure(Call<GraphqlResponseObject> call, Throwable t) {
                        view.showGithubFetchFailure();
                    }
                }
        );
    }

    private void fetchRepositoryOwnerLocal() {

    }

    private void fetchRepositoryOwnerExtras() {

    }

}
