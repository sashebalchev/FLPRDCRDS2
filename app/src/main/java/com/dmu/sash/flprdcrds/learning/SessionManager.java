package com.dmu.sash.flprdcrds.learning;

import android.content.Context;
import android.content.SharedPreferences;

import com.dmu.sash.flprdcrds.settings.PreferencesProvider;

public class SessionManager {

    private PreferencesProvider preferencesProvider;
    private int session;
    private static SessionManager instance;

    private SessionManager(Context context) {
        preferencesProvider = new PreferencesProvider(context);
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
            preferencesProvider.setSessionPreference(session);
        } else {
            session = initialSession();
        }
        return session;
    }

    public int initialSession() {
        session = 1;
        preferencesProvider.setSessionPreference(session);
        return session;
    }

    public void finishedSession() {
        int totalSessions = preferencesProvider.getTotalSessions() + 1;
        preferencesProvider.setTotalSessions(totalSessions);
    }
}
