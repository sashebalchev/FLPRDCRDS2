package com.dmu.sash.flprdcrds.database;

import android.app.Application;

import io.realm.Realm;

public class FlprcrdsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}