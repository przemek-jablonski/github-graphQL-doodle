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


        String argumentsLoginString = "ReactiveX";
        LinkedHashMap<String, String> args = new LinkedHashMap<>();
        args.put(Constants.GraphqlConstants.ARGUMENT_LOGIN, argumentsLoginString);

        //todo: this shit looks bad - instantiating before doing internet fetching?
        repositoryOwner = new RepositoryOwner(args);

        //todo: maybe this 3 parameters above should go below, into .getGraphData(...)?
//        service.getGraphData(repositoryOwner, new Callback<GraphQLResponseObject>() {
//                    @Override
//                    public void onResponse(Call<GraphQLResponseObject> call, Response<GraphQLResponseObject> response) {
//                        GraphQLResponseObject obj = response.body();
////                        view.updateRepositoryOwnerView(obj.getData().getRepositoryOwner());
////
////                        List<GraphQLEdgeObject> edges = obj.getData().getRepositoryOwner().repositories.edges;
////                        List<GraphqlNodeObject> nodes = new ArrayList<GraphqlNodeObject>(edges.size());
////
////                        for (int i = 0; i < edges.size(); ++i) {
////                            nodes.add(edges.get(i).node);
////                        }
////                        view.updateRepositoriesListView(nodes);
//                        view.showGithubFetchSuccess();
//                    }
//
//                    @Override
//                    public void onFailure(Call<GraphQLResponseObject> call, Throwable t) {
//                        view.showGithubFetchFailure();
//                    }
//                }
//        );


//        service.getRepositoryOwner(repositoryOwner, new Callback<GraphQLResponseObject<RepositoryOwner>>() {
//            @Override
//            public void onResponse(Call<GraphQLResponseObject<RepositoryOwner>> call, Response<GraphQLResponseObject<RepositoryOwner>> response) {
//                view.showGithubFetchSuccess();
//            }
//
//            @Override
//            public void onFailure(Call<GraphQLResponseObject<RepositoryOwner>> call, Throwable t) {
//                view.showGithubFetchFailure();
//            }
//        });


        //todo: is repositoryOwner object even needed here?
        service.getRepositoryOwner(repositoryOwner, new Callback<GraphQLResponseObject<RepositoryOwner>>() {
            @Override
            public void onResponse(Call<GraphQLResponseObject<RepositoryOwner>> call, Response<GraphQLResponseObject<RepositoryOwner>> response) {
                view.showGithubFetchSuccess();
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
