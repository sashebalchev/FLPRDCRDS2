package com.dmu.sash.flprdcrds;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;

public final class PreferencesProvider {
    private SharedPreferences sharedPreferences;

    PreferencesProvider(Context context) {
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

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
