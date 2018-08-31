package com.dmu.sash.flprdcrds.settings;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.View;

import com.dmu.sash.flprdcrds.R;

public class SettingsFragment extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener {
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        sharedPreferences = new PreferencesProvider(getContext()).getSharedPreferences();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
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
            int totalSessions = preferencesProvider.getTotalSessions();
            preferencesProvider.clearPreferences();
            preferencesProvider.setSessionPreference(session);
            preferencesProvider.setNotification(notification);
            preferencesProvider.setTotalSessions(totalSessions);
            return false;
        });
    }

    //TODO make preference selection reflect the real selection
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        ListPreference preference = (ListPreference) findPreference(key);
//        preference.setSummary(preference.getValue());
    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }
}
