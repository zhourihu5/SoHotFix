package com.example.myapplication;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        getResources();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // PerformanceMonitoring.monitoringTakeUpTime("main");
    }
}
