package com.android.szparag.github_graphql_doodle.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.szparag.github_graphql_doodle.R;
import com.android.szparag.github_graphql_doodle.adapters.RepositoriesViewAdapter;
import com.android.szparag.github_graphql_doodle.adapters.RepositoryOwnerAdapter;
import com.android.szparag.github_graphql_doodle.backend.models.Repository;
import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;
import com.android.szparag.github_graphql_doodle.dagger.MainComponent;
import com.android.szparag.github_graphql_doodle.decorators.HorizontalSeparator;
import com.android.szparag.github_graphql_doodle.presenters.contracts.GithubListBasePresenter;
import com.android.szparag.github_graphql_doodle.utils.Utils;
import com.android.szparag.github_graphql_doodle.views.contracts.GithubListView;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class GithubListFragment extends Fragment implements GithubListView {


    //todo: remove graphql-java library (not used)

    @BindView(R.id.recycler_repositories)
    RecyclerView            repositoriesView;
    RepositoriesViewAdapter repositoriesAdapter;

    @BindView(R.id.repositoryowner_front)
    View                    repositoryOwnerView;
    RepositoryOwnerAdapter  repositoryOwnerAdapter;

    @Inject
    GithubListBasePresenter presenter;

    private Unbinder        unbinder;


    //todo: newInstance here!
    public GithubListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainComponent dagger = Utils.getDagger2(this);
        dagger.inject(this);
        presenter.setView(this, dagger);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, layout);
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buildRepositoriesListView();
        buildRepositoryOwnerView();
        hideRepositoryOwnerView();
        hideRepositoriesListView();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.fetchData();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void showGithubFetchSuccess() {
        Snackbar.make(getView(), getString(R.string.data_fetch_success), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showGithubFetchFailure() {
        Snackbar.make(getView(), getString(R.string.data_fetch_failure), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showNetworkConnectionFailure() {
        Snackbar.make(getView(), getString(R.string.network_connection_failure), Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void buildRepositoryOwnerView() {
        repositoryOwnerAdapter = new RepositoryOwnerAdapter(repositoryOwnerView);
        hideRepositoryOwnerView();
    }

    @Override
    public void hideRepositoryOwnerView() {
        repositoryOwnerView.setVisibility(View.GONE); //todo: animations on show / hide view
    }

    @Override
    public void showRepositoryOwnerView() {
        repositoryOwnerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateRepositoryOwnerView(RepositoryOwner repositoryOwner) {
        showRepositoryOwnerView();
        repositoryOwnerAdapter.updateItem(repositoryOwner);
    }

    @Override
    public void buildRepositoriesListView() {
        //todo: snapping!
//        repositoriesView.setLayoutManager(
//                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        repositoriesView.setLayoutManager(new LinearLayoutManager(getContext()));
        repositoriesView.addItemDecoration(new HorizontalSeparator(getContext()));
        SnapHelper snapHelper = new GravitySnapHelper(Gravity.TOP);
        snapHelper.attachToRecyclerView(repositoriesView);
        repositoriesAdapter = new RepositoriesViewAdapter(null);
        repositoriesView.setAdapter(repositoriesAdapter);
        repositoriesView.setNestedScrollingEnabled(false); //todo: ...false?
    }

    @Override
    public void hideRepositoriesListView() {
        repositoriesView.setVisibility(View.GONE);
    }

    @Override
    public void showRepositoriesListView() {
        repositoriesView.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateRepositoriesListView(List<Repository> repositories) {
        showRepositoriesListView();
        repositoriesAdapter.updateItems(repositories);

    }
}
