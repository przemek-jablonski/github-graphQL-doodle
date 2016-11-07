package com.android.szparag.github_graphql_doodle.presenters;

import com.android.szparag.github_graphql_doodle.backend.models.Repository;
import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphQLResponseObject;
import com.android.szparag.github_graphql_doodle.backend.services.GraphqlService;
import com.android.szparag.github_graphql_doodle.dagger.MainComponent;
import com.android.szparag.github_graphql_doodle.presenters.contracts.GithubListBasePresenter;
import com.android.szparag.github_graphql_doodle.utils.Computation;
import com.android.szparag.github_graphql_doodle.utils.Computation.RepoStatsComparator;
import com.android.szparag.github_graphql_doodle.utils.Constants;
import com.android.szparag.github_graphql_doodle.views.contracts.GithubListView;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.szparag.github_graphql_doodle.utils.Computation.RepoStatsComparator.SortItem.STARS;

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

    //todo: method: sorting

    private void fetchRepositoryOwnerGraph() {

        String argumentsLoginString = "ReactiveX";
        LinkedHashMap<String, String> args = new LinkedHashMap<>();
        args.put(Constants.GraphqlConstants.ARGUMENT_LOGIN, argumentsLoginString);



        graphqlService.getRepositoryOwner(new Callback<GraphQLResponseObject<RepositoryOwner>>() {
            @Override
            public void onResponse(Call<GraphQLResponseObject<RepositoryOwner>> call, Response<GraphQLResponseObject<RepositoryOwner>> response) {
                view.showGithubFetchSuccess();
                view.showRepositoryOwnerView();
                repositoryOwner = response.body().getObject();
                repositores = repositoryOwner.getRepositoriesList();
                Collections.sort(repositores, comparator.sortBy(STARS));
                view.updateRepositoriesListView(repositores);
                view.updateRepositoryOwnerView(repositoryOwner);

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
