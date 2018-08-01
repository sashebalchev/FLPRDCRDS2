package com.dmu.sash.flprdcrds;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
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
