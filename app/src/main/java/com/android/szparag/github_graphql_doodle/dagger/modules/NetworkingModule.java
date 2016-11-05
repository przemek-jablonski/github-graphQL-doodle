package com.android.szparag.github_graphql_doodle.dagger.modules;

import android.content.Context;

import com.android.szparag.github_graphql_doodle.R;
import com.android.szparag.github_graphql_doodle.backend.apis.GraphqlApi;
import com.android.szparag.github_graphql_doodle.backend.interceptors.OAuthTokenInterceptor;
import com.android.szparag.github_graphql_doodle.backend.interceptors.PrintResponseInterceptor;
import com.android.szparag.github_graphql_doodle.backend.services.GraphqlService;
import com.android.szparag.github_graphql_doodle.backend.services.GraphqlServiceImpl;
import com.android.szparag.github_graphql_doodle.utils.Constants;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ciemek on 04/11/2016.
 */

@Module
public class NetworkingModule {

    @Provides
    @Singleton
    @Named(Constants.GITHUB_GRAPHQL_ENDPOINT)
    public String provideGraphqlEndpoint(Context context) {
        return context.getString(R.string.github_endpoint);
    }

    @Provides
    @Singleton
    @Named(Constants.GITHUB_GRAPHQL_APIKEY)
    public String provideGraphqlApiKey(Context context) {
        return context.getString(R.string.github_apikey);
    }

    @Provides
    @Singleton
    @Named(Constants.RETROFIT_ADAPTER_GRAPHQL)
    public Retrofit provideRetrofitGraphqlAdapter(
            @Named(Constants.GITHUB_GRAPHQL_ENDPOINT) String graphqlEndpoint,
            @Named(Constants.GITHUB_GRAPHQL_APIKEY) String graphqlApiKey) {
        return new Retrofit.Builder()
                .baseUrl(graphqlEndpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                        new OkHttpClient.Builder()
                                .addInterceptor(new OAuthTokenInterceptor(graphqlApiKey))
                                .addInterceptor(new PrintResponseInterceptor()) //debug
                                .build())
                .build();
    }

    @Provides
    @Singleton
    public GraphqlApi provideGraphqlApi(@Named(Constants.RETROFIT_ADAPTER_GRAPHQL) Retrofit retrofit) {
        return retrofit.create(GraphqlApi.class);
    }

    @Provides
    @Singleton
    public GraphqlService provideGraphqlService(GraphqlApi api) {
        return new GraphqlServiceImpl(api);
    }

//    @Provides
//    @Singleton
//    @Named(Constants.RETROFIT_ADAPTER_REST)
//    public Retrofit provideRetrofitRestAdapter(@Named(Constants.GITHUB_REST_ENDPOINT) String restEndpoint) {

}
