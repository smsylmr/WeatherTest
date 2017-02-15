package com.example.lmr.weathertest.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by LMR on 2017/2/15.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}

