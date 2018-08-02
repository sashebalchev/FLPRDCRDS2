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

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    private int bgColor;
    private int fontColor;
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
        switch (key) {
            case "PREF_COLOR_BG":
                String bgColorPref = sharedPreferences.getString("PREF_COLOR_BG", "#FFFFFF");
                bgColor = Color.parseColor(bgColorPref);
                view.setBackgroundColor(bgColor);
                break;
            default:
                System.out.println("Nothing happened");;
        }
    }
    //    public SettingsFragment() {
//// Required empty public constructor
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        RealmConfiguration settingsConfig = new RealmConfiguration.Builder()
//                .name("settings.realm")
//                .schemaVersion(0)
//
//                .build();
//        realm = Realm.getInstance(settingsConfig);
//
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_settings, null);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        EditText editText = getView().findViewById(R.id.setting_test);
//        Button button = getView().findViewById(R.id.button_test);
//
//
//
//
////        System.out.println(realm.where(Settings.class).equalTo("id", "6").findFirst().getFont());
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println(realm.where(Settings.class).equalTo("id", "5").findFirst().getFont());
//            }
//        });
//
//        FloatingActionButton fab = getView().findViewById(R.id.fab_test);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String text = editText.getText().toString();
//                test(text);
//            }
//        });
//    }
//
//    private void test(String text) {
//        realm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.createObject(Settings.class, "5")
//                        .setFont(text);
//            }
//        });
//    }
}
