package com.android.szparag.github_graphql_doodle.presenters;

import android.test.suitebuilder.annotation.SmallTest;

import com.android.szparag.github_graphql_doodle.backend.models.Repository;
import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;
import com.android.szparag.github_graphql_doodle.backend.services.GraphqlService;
import com.android.szparag.github_graphql_doodle.dagger.MainComponent;
import com.android.szparag.github_graphql_doodle.presenters.contracts.GithubListBasePresenter;
import com.android.szparag.github_graphql_doodle.repositories.RepositoryOwnerRepository;
import com.android.szparag.github_graphql_doodle.views.contracts.GithubListView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by ciemek on 07/11/2016.
 */
public class GithubListPresenterTest {


    GithubListBasePresenter presenter;

    GithubListView          mockView;
    RepositoryOwner         mockData;
    List<Repository>        mockDataList;
    RepositoryOwnerRepository mockDataRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockDataRepository = mock(RepositoryOwnerRepository.class);
        mockData = mock(RepositoryOwner.class);
        mockView = mock(GithubListView.class);
        mockDataList = Collections.singletonList(new Repository());

        when(mockData.getPath()).thenReturn("blabla");
        when(mockDataRepository.getRepositoryOwner()).thenReturn(mockData);
        when(mockDataRepository.getRepositories()).thenReturn(mockDataList);
        presenter = new GithubListPresenter();
        presenter.setView(mockView, mock(GraphqlService.class), mockDataRepository);
    }

//


    @Test
    public void fetchData_updateViewIfDataExists() {
        presenter.fetchData();

        verify(mockView).updateRepositoriesListView(mockDataList);
        verify(mockView).updateRepositoryOwnerView(mockData);
    }

    @Test
    public void fetchData_NoUpdateViewIfDataIsNull() {
        when(mockDataRepository.getRepositoryOwner()).thenReturn(null);
        when(mockDataRepository.getRepositories()).thenReturn(null);

        presenter.fetchData();

        verify(mockView, never()).updateRepositoriesListView(mockDataList);
        verify(mockView, never()).updateRepositoryOwnerView(mockData);
    }

    @Test
    public void saveData_passedToDataRepositoryIfDataExists() {
        presenter.saveData(mockData);
        verify(mockDataRepository, times(1)).saveRepositoryOwner(mockData);
    }

    @Test
    public void saveData_passedToDataRepositoryIfDataIsNull() {
        presenter.saveData(null);
        verify(mockDataRepository, times(1)).saveRepositoryOwner(null);
    }


    @Test
    public void setView_InjectDependenciesIfDaggerExists() {
        presenter = new GithubListPresenter();
        MainComponent mockComponent = mock(MainComponent.class);
        presenter.setView(mockView, mockComponent);

        verify(mockComponent, times(1)).inject((GithubListPresenter) presenter);
    }



}