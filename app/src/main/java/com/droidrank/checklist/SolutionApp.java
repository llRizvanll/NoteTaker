package com.droidrank.checklist;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class SolutionApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
