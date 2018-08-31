package com.dmu.sash.flprdcrds.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;


public final class PreferencesProvider {
    private SharedPreferences sharedPreferences;

    public PreferencesProvider(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public int getBackgroundColorPreference() {
        return Color.parseColor(sharedPreferences.getString("PREF_COLOR_BG", "#FCFAD1"));
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

    public void setNotification(boolean notification) {
        sharedPreferences.edit().putBoolean("FIRST_TIME", notification).apply();
    }

    public void clearPreferences() {
        sharedPreferences.edit().clear().apply();
    }
}
