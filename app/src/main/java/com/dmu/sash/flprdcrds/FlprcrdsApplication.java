package com.dmu.sash.flprdcrds;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.annotations.RealmModule;

public class FlprcrdsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

//        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
//                .name("flprcrds.realm")
//                .schemaVersion(0)
//                .modules(new Word())
//                .build();

//        RealmConfiguration settingsConfig = new RealmConfiguration.Builder()
//                .name("settings.realm")
//                .schemaVersion(0)
//                .modules(new Settings())
//                .build();
////        Realm.setDefaultConfiguration(realmConfig);
//
//        Realm wordRealm = Realm.getInstance(realmConfig);
//        Realm settingRealm = Realm.getInstance(settingsConfig);

    }
}