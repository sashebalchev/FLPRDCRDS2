package com.dmu.sash.flprdcrds.database;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public final class RealmFactory {
    private static final String REALM_FILENAME = "flprcrds.realm";

    private RealmFactory() {
    }

    public static Realm getRealm() {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name(REALM_FILENAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        return Realm.getInstance(realmConfig);
    }
}
