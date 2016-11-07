package com.android.szparag.github_graphql_doodle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by ciemek on 07/11/2016.
 */

public class ConnectivityBroadcastReceiver extends BroadcastReceiver {

    protected ConnectivityStateListener stateListener;

    public ConnectivityBroadcastReceiver(ConnectivityStateListener stateListener) {
        this.stateListener = stateListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getExtras() == null) {
            return;
        }

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo currentConnection =  connectivityManager.getActiveNetworkInfo();

        if (currentConnection == null){
            return;
        }

        if(currentConnection.isAvailable() && currentConnection.isConnected()) {
            stateListener.connectionAvailable();
        } else {
            stateListener.connectionUnvailable();
        }
    }

    public void removeListener() {
        stateListener = null;
    }

    public interface ConnectivityStateListener {
        void connectionAvailable();
        void connectionUnvailable();
    }
}
