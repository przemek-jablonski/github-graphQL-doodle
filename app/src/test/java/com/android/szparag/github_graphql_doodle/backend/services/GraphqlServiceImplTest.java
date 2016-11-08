package com.android.szparag.github_graphql_doodle.backend.services;

import com.android.szparag.github_graphql_doodle.backend.apis.GraphqlApi;
import com.android.szparag.github_graphql_doodle.backend.models.Repository;
import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphQLResponseObject;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.queries.GraphqlBaseQuery;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.queries.RepositoryOwnerQueryFull;
import com.android.szparag.github_graphql_doodle.presenters.GithubListPresenter;
import com.android.szparag.github_graphql_doodle.presenters.contracts.GithubListBasePresenter;
import com.android.szparag.github_graphql_doodle.views.contracts.GithubListView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by ciemek on 07/11/2016.
 */
public class GraphqlServiceImplTest {

    private RepositoryOwner repositoryOwner;


    GraphqlServiceImpl service;

    @Mock
    GraphqlApi api;

    @Mock
    Call<GraphQLResponseObject<RepositoryOwner>> mockCall;

    @Mock
    ResponseBody mockResponseBody;

    @Mock
    ArgumentCaptor<Callback<GraphQLResponseObject<RepositoryOwner>>> mockArgumentCaptor;


    //todo: mockito testing

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        repositoryOwner = new RepositoryOwner();
    }

    @Test
    public void costams() {
        when(api.getRepositoryOwner(new RepositoryOwnerQueryFull().getQuery())).thenReturn(mockCall);
        Response<GraphQLResponseObject<RepositoryOwner>> response = Response.success(new GraphQLResponseObject<RepositoryOwner>());



        mockArgumentCaptor.getValue().onResponse(null, response);

    }


}