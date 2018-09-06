package com.dmu.sash.flprdcrds.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;

import com.dmu.sash.flprdcrds.R;


public final class PreferencesManager {
    private SharedPreferences sharedPreferences;

    public PreferencesManager(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        android.support.v7.preference.PreferenceManager.setDefaultValues(context, R.xml.preferences, false);
    }

    public int getBackgroundColorPreference() {
        return Color.parseColor(sharedPreferences.getString("PREF_COLOR_BG", "#FFFBEB"));
    }

    public int getFontColorPreference() {
        return Color.parseColor(sharedPreferences.getString("PREF_COLOR_FONT", "#000000"));
    }

    public String getFontStylePreference() {
        return sharedPreferences.getString("PREF_STYLE_FONT", "1");
    }

    public int getSessionPreference() {
        return sharedPreferences.getInt("SESSION", 1);
    }

    public void setSessionPreference(int sessionNumber) {
        sharedPreferences.edit().putInt("SESSION", sessionNumber).apply();
    }

    public boolean getNotificationFlag() {
        return sharedPreferences.getBoolean("FIRST_TIME", false);
    }


    public int getTotalSessions() {
        return sharedPreferences.getInt("TOTAL_SESSIONS", 0);
    }

    public void setTotalSessions(int totalSessions) {
        sharedPreferences.edit().putInt("TOTAL_SESSIONS", totalSessions).apply();
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void setNotificationFlag(boolean notification) {
        sharedPreferences.edit().putBoolean("FIRST_TIME", notification).apply();
    }

    public void clearPreferences() {
        sharedPreferences.edit().clear().apply();
    }
}
