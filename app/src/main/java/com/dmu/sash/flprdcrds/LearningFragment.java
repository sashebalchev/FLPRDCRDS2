package com.dmu.sash.flprdcrds;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.ListView;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class LearningFragment extends Fragment {

    private Realm realm;
    private int bgColor;
    private ListView listView;
    private URLAsyncTask urlAsyncTask;
    private AdapterViewFlipper viewFlipper;
    private RealmResults<Word> words;
    private static LearningFragment instance;
    private int session;
    private SharedPreferences sharedPreferences;

    public LearningFragment() {
    }

    public static LearningFragment getInstance() {
        if (instance == null) {
            instance = new LearningFragment();
        }
        return instance;
    }

    //sd
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("flprcrds.realm")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(realmConfig);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        session = Integer.parseInt(sharedPreferences.getString("SESSION", "1"));
        switch (session) {
            case 1:
                words = realm.where(Word.class).equalTo("score", 1).findAllAsync();
                break;
            case 2:
                words = realm.where(Word.class).lessThanOrEqualTo("score", 2).findAllAsync();
                break;
            case 3:
                words = realm.where(Word.class).equalTo("score", 1).findAllAsync();
                break;
            case 4:
                words = realm.where(Word.class).lessThanOrEqualTo("score", 2).findAllAsync();
                break;
            case 5:
                words = realm.where(Word.class).lessThanOrEqualTo("score", 3).findAllAsync();
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learning, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewFlipper = getView().findViewById(R.id.flipper_all);
        WordCardAdapter wordCardAdapter = new WordCardAdapter(this, words, getContext(), viewFlipper);
        viewFlipper.setAdapter(wordCardAdapter);
        Button sessionButton = getView().findViewById(R.id.session_button);
        sessionButton.setOnClickListener(v -> {
            if (session < 5) {
                String sessionToChangeTo = String.valueOf(session + 1);
                sharedPreferences.edit().putString("SESSION", sessionToChangeTo).apply();
            } else {
                sharedPreferences.edit().putString("SESSION", "1").apply();
            }
        });
    }
}
