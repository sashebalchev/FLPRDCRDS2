package com.dmu.sash.flprdcrds.learning;

import android.content.Context;

import com.dmu.sash.flprdcrds.helpers.PreferencesManager;

public class SessionManager {

    private PreferencesManager preferencesManager;
    private int session;
    private static SessionManager instance;

    private SessionManager(Context context) {
        preferencesManager = new PreferencesManager(context);
        session = preferencesManager.getSessionPreference();
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
            preferencesManager.setSessionPreference(session);
        } else {
            session = initialSession();
        }
        return session;
    }

    public int initialSession() {
        session = 1;
        preferencesManager.setSessionPreference(session);
        return session;
    }

    public void finishedSession() {
        int totalSessions = preferencesManager.getTotalSessions() + 1;
        preferencesManager.setTotalSessions(totalSessions);
    }
}
