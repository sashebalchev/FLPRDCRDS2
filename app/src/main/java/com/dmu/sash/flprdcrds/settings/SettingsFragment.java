package com.dmu.sash.flprdcrds.settings;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmu.sash.flprdcrds.R;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    private SharedPreferencesFactory sharedPreferencesFactory;
    private SharedPreferences sharedPreferences;
    private View view;
    private static SettingsFragment instance;

    public static SettingsFragment getInstance(){
        if (instance == null){
            instance = new SettingsFragment();
        }
        return instance;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
        sharedPreferencesFactory = new SharedPreferencesFactory(getContext());
        sharedPreferences = sharedPreferencesFactory.getSharedPreferences();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Preference resetPreferencesButton = findPreference(getString(R.string.PREF_RESET));


        resetPreferencesButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                int session = sharedPreferencesFactory.getSessionPreference();
                sharedPreferences.edit().clear().apply();
                sharedPreferences.edit().putInt("SESSION", session).apply();
                return false;
            }
        });
    }

//TODO make preference selection reflect the real selection
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    }
}
