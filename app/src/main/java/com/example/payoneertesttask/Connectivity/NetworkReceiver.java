package com.example.payoneertesttask.Connectivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

public class NetworkReceiver extends BroadcastReceiver {

    private static final  String TAG = NetworkReceiver.class.getSimpleName();
    public MainViewModel mainViewModel;


    public NetworkReceiver(MainViewModel mainViewModel){
        this.mainViewModel = mainViewModel;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
            mainViewModel.checkConnections();
        }
    }
}
