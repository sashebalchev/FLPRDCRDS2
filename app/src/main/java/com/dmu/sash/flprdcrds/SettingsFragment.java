package com.dmu.sash.flprdcrds;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    private int bgColor;
    private int fontColor;
    private TextView exampleText;
    private SharedPreferences sharedPreferences;
    private View view;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        bgColor = Color.parseColor(sharedPreferences.getString("PREF_COLOR_BG", "#FFFFFF"));
//        fontColor = Color.parseColor(sharedPreferences.getString("PREF_COLOR_FONT", "#000000"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        view.setBackgroundColor(bgColor);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int test = findPreference("EXAMPLE_TEXT").getLayoutResource();
        exampleText = getView().findViewById(findPreference("EXAMPLE_TEXT").getLayoutResource());
        Preference button = findPreference(getString(R.string.PREF_RESET));

        //TODO make preference selection reflect the real selection
        button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                sharedPreferences.edit().clear().commit();
                onSharedPreferenceChanged(sharedPreferences, "PREF_COLOR_BG");
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//        switch (key) {
//            case "PREF_COLOR_BG":
//                String bgColorPref = sharedPreferences.getString("PREF_COLOR_BG", "#FFFFFF");
//                bgColor = Color.parseColor(bgColorPref);
//                exampleText.setBackgroundColor(bgColor);
//                break;
//            case "PREF_COLOR_FONT":
//                String fontColorPref = sharedPreferences.getString("PREF_COLOR_FONT", "#000000");
//                fontColor = Color.parseColor(fontColorPref);
//                exampleText.setTextColor(fontColor);
//                break;
//            default:
//                System.out.println("Nothing happened");;
//        }
    }
}
