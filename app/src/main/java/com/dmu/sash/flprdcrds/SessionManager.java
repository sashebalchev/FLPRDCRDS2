package com.dmu.sash.flprdcrds;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SessionManager {

    private SharedPreferences sharedPreferences;
    private int session;
    private static SessionManager instance;

    private SessionManager(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        session = sharedPreferences.getInt("SESSION", 1);
    }

    public static SessionManager getInstance(Context context) {
        if (instance == null){
            instance = new SessionManager(context);
        }
        return instance;
    }

    public int getSession() {
        return session;
    }

    public int nextSession(){
        if (session < 5) {
            session++;
            sharedPreferences.edit().putInt("SESSION", session).commit();
        } else {
            session = initialSession();
        }
        return session;
    }

    public int initialSession(){
        session = 1;
        sharedPreferences.edit().putInt("SESSION", session).commit();
        return session;
    }
}
