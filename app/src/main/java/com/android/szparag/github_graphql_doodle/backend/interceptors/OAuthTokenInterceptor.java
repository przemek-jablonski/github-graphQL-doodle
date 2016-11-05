package com.android.szparag.github_graphql_doodle.backend.interceptors;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by ciemek on 05/11/2016.
 */

public class OAuthTokenInterceptor implements Interceptor {

    private final String    AUTHORIZATION = "Authorization";
    private final String    BEARER = "Bearer ";
    private String          oauthToken;


    public OAuthTokenInterceptor(@NonNull String oauthToken) {
        this.oauthToken = oauthToken;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
            return chain.proceed(
                    chain.request()
                            .newBuilder()
                            .addHeader(AUTHORIZATION, BEARER + oauthToken)
                            .build()
            );
    }

}
