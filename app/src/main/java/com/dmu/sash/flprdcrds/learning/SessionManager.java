package com.dmu.sash.flprdcrds.learning;

import android.content.Context;
import android.content.SharedPreferences;

import com.dmu.sash.flprdcrds.settings.PreferencesProvider;

public class SessionManager {

    private SharedPreferences sharedPreferences;
    private int session;
    private static SessionManager instance;

    private SessionManager(Context context) {
        PreferencesProvider preferencesProvider = new PreferencesProvider(context);
        sharedPreferences = preferencesProvider.getSharedPreferences();
        session = preferencesProvider.getSessionPreference();
    }

    public static SessionManager getInstance(Context context) {
        if (instance == null) {
            instance = new SessionManager(context);
        }
        return instance;
    }

    public int getSession() {
        return session;
    }

    public int nextSession() {
        if (session < 5) {
            session++;
            sharedPreferences.edit().putInt("SESSION", session).apply();
        } else {
            session = initialSession();
        }
        System.out.println(session);
        return session;
    }

    public int initialSession() {
        session = 1;
        sharedPreferences.edit().putInt("SESSION", session).apply();
        return session;
    }
}
