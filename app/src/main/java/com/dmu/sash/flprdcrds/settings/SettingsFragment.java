package com.dmu.sash.flprdcrds.settings;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.View;

import com.dmu.sash.flprdcrds.R;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Preference resetPreferencesButton = findPreference("PREF_RESET");

        resetPreferencesButton.setOnPreferenceClickListener(preference -> {
            PreferencesProvider preferencesProvider = new PreferencesProvider(getContext());
            int session = preferencesProvider.getSessionPreference();
            boolean notification = preferencesProvider.getNotificationFlag();
            SharedPreferences preferences = preferencesProvider.getSharedPreferences();
            //TODO extract implementation to PreferencesProvider. Setters.
            preferences.edit().clear().apply();
            preferences.edit().putInt("SESSION", session).apply();
//            preferences.edit().putBoolean("FIRST_TIME", notification).apply();
            return false;
        });
    }
    //TODO make preference selection reflect the real selection
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    }
}
