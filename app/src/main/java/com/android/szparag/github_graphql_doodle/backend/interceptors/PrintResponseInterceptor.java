package com.android.szparag.github_graphql_doodle.backend.interceptors;

import com.android.szparag.github_graphql_doodle.utils.Utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by ciemek on 04/11/2016.
 */

public class PrintResponseInterceptor implements Interceptor {

    private final   String REQUEST = "Request:" + "\n";
    private final   String RESPONSE = "Response:" + "\n";

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        Request request = chain.request();
        long t1 = System.nanoTime();

        String logMessage = REQUEST +
                "URL: " + request.url() + "\n" +
                "BODY: " + request.body();
        Utils.logRetrofit(logMessage);

        Response response = chain.proceed(request);
        long t2 = System.nanoTime();

        String bodyString = response.body().string();
        if (response.body() == null) {
            logMessage = RESPONSE +
                    "TIME: " + ((t2 - t1) / 1e6d) + "ms" + "\n" +
                    "URL: " + request.url() + "\n" +
                    "REQUEST BODY FOR GIVEN URL IS NULL!";
        } else {
            logMessage = RESPONSE +
                    "URL: " + response.request().url() + "\n" +
                    "CODE: " + response.code() + " / " + response.message() + "\n" +
                    "TIME: " + ((t2 - t1) / 1e6d) + "ms" + "\n" +
                    "DATE: " + response.header("Date") + "\n" +
                    "TYPE: " + response.header("Content-Type");
            Utils.logRetrofit(logMessage);
        }

        return response.newBuilder()
                .body(ResponseBody.create(response.body().contentType(), bodyString))
                .build();
    }

}
