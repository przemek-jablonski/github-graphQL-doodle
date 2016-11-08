package com.android.szparag.github_graphql_doodle.views;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.szparag.github_graphql_doodle.ConnectivityBroadcastReceiver;
import com.android.szparag.github_graphql_doodle.ConnectivityBroadcastReceiver.ConnectivityStateListener;
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

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;


public class GithubListFragment extends Fragment implements GithubListView, ConnectivityStateListener {

    @BindView(R.id.repositoryowner_front)
    View            repositoryOwnerView;

    @BindView(R.id.recycler_repositories)
    RecyclerView    repositoriesView;

    @Inject
    GithubListBasePresenter presenter;

    private boolean     connectivityAvailable;
    private Unbinder    unbinder;

    private RepositoryOwnerAdapter  repositoryOwnerAdapter;
    private RepositoriesViewAdapter repositoriesAdapter;
    private ConnectivityBroadcastReceiver connectivityReceiver;


    //fragment's lifecycle methods:
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainComponent dagger = Utils.getDagger2(this);
        dagger.inject(this);
        presenter.setView(this, dagger);
        registerInternetConnectivityListener();
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
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.fetchData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterInternetConnectivityListener();
    }


    //handling actual UI views (build/update/show/hide):
    //repositoryOwnerView - fragments 'Header', showing User (repositoryOwner) data
    //repositoriesListView - RecyclerView for list of Repositories
    @Override
    public void buildRepositoryOwnerView() {
        repositoryOwnerAdapter = new RepositoryOwnerAdapter(repositoryOwnerView);
        hideRepositoryOwnerView();
    }

    @Override
    public void buildRepositoriesListView() {
        repositoriesView.setLayoutManager(new LinearLayoutManager(getContext()));
        repositoriesView.addItemDecoration(new HorizontalSeparator(getContext()));
        repositoriesAdapter = new RepositoriesViewAdapter(null);
        repositoriesView.setAdapter(repositoriesAdapter);
        hideRepositoriesListView();
    }

    @Override
    public void updateRepositoryOwnerView(RepositoryOwner repositoryOwner) {
        repositoryOwnerAdapter.updateItem(repositoryOwner);
    }

    @Override
    public void updateRepositoriesListView(List<Repository> repositories) {
        repositoriesAdapter.updateItems(repositories);
    }

    @Override
    public void hideRepositoryOwnerView() {
        repositoryOwnerView.setVisibility(View.GONE);
    }

    @Override
    public void hideRepositoriesListView() {
        repositoriesView.setVisibility(View.GONE);
    }

    @Override
    public void showRepositoryOwnerView() {
        repositoryOwnerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRepositoriesListView() {
        repositoriesView.setVisibility(View.VISIBLE);
    }


    //gentle messages for user on specific events (Snackbars here):
    @Override
    public void showGithubFetchSuccess() {
        Snackbar.make(getView(), getString(R.string.data_fetch_success), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showGithubFetchFailure() {
        Snackbar.make(getView(), getString(R.string.data_fetch_failure), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showGithubApiKeyFailure() {
        Snackbar.make(getView(), getString(R.string.api_key_failure), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showNetworkConnectionFailure() {
        Snackbar.make(getView(), getString(R.string.network_connection_failure), Snackbar.LENGTH_INDEFINITE).show();
    }


    //Internet connection listening stuff:
    @Override
    public void connectionAvailable() {
        connectivityAvailable = true;
        presenter.fetchData();
    }

    @Override
    public void connectionUnvailable() {
        connectivityAvailable = false;
        showNetworkConnectionFailure();
    }

    @Override
    public boolean getInternetConnectivity() {
        return connectivityAvailable;
    }

    @Override
    public void registerInternetConnectivityListener() {
        connectivityReceiver = new ConnectivityBroadcastReceiver(this);
        getActivity().registerReceiver(connectivityReceiver, new IntentFilter(CONNECTIVITY_ACTION));
    }

    @Override
    public void unregisterInternetConnectivityListener() {
        connectivityReceiver.removeListener();
        getActivity().unregisterReceiver(connectivityReceiver);
        connectivityReceiver = null;
    }
}
