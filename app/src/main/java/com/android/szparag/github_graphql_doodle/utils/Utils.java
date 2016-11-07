package com.android.szparag.github_graphql_doodle.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.net.ConnectivityManagerCompat;
import android.util.Log;

import com.android.szparag.github_graphql_doodle.GraphqlDoodleApplication;
import com.android.szparag.github_graphql_doodle.dagger.MainComponent;

/**
 * Created by ciemek on 04/11/2016.
 */

public class Utils {

    public static MainComponent getDagger2(Activity activity) {
        return ((GraphqlDoodleApplication) activity.getApplication()).getDaggerComponent();
    }

    public static MainComponent getDagger2(Fragment fragment) {
        return getDagger2(fragment.getActivity());
    }


    public static void logMisc(String... logMessages) {
        for (int i=0; i < logMessages.length; ++i) {
            logDebug(Constants.LOG_TAG_MISC, logMessages[i]);
        }
    }

    public static void logRetrofit(String... logMessages) {
        for (int i=0; i < logMessages.length; ++i) {
            logError(Constants.LOG_TAG_RETROFIT, logMessages[i]);
        }
    }

    public static void logException(Throwable exception) {
        logError(Constants.LOG_TAG_EXCEPTION, exception.getMessage());
    }

    private static void logDebug(String tag, String message) {
        Log.println(Log.DEBUG, tag, message);
    }

    private static void logError(String tag, String message) {
        Log.println(Log.ERROR, tag, message);
    }

}
