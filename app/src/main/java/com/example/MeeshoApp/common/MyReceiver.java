package com.example.MeeshoApp.common;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.MeeshoApp.Activity.MainActivity;

public class MyReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String status = Networkutil.getConnectivityStatusString(context);
        if (status.isEmpty()){
            status ="No Internet Connection";

        }
        Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
    }


    }





