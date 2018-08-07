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

import java.util.Collections;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class LearningFragment extends Fragment {

    private Realm realm;
    private int bgColor;
    private ListView listView;
    private URLAsyncTask urlAsyncTask;
    private AdapterViewFlipper viewFlipper;
    private RealmResults<Word> wordRealmResults;
    private List<Word> wordsForSession;

    public static int session;
    private static SharedPreferences sharedPreferences;

    public LearningFragment() {
    }

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
        session = sharedPreferences.getInt("SESSION", 1);
        getRealmData();
    }

    private void getRealmData() {
        switch (session) {
            case 1:
                wordRealmResults = realm.where(Word.class).equalTo("score", 1).findAll();
                break;
            case 2:
                wordRealmResults = realm.where(Word.class).lessThanOrEqualTo("score", 2).findAll();
                break;
            case 3:
                wordRealmResults = realm.where(Word.class).equalTo("score", 1).findAll();
                break;
            case 4:
                wordRealmResults = realm.where(Word.class).lessThanOrEqualTo("score", 2).findAll();
                break;
            case 5:
                wordRealmResults = realm.where(Word.class).lessThanOrEqualTo("score", 3).findAll();
                break;
        }

        wordsForSession = realm.copyFromRealm(wordRealmResults);
        Collections.shuffle(wordsForSession);
        if (wordsForSession.size() == 0){
            changeSession();
            getRealmData();
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
        WordCardAdapter wordCardAdapter = new WordCardAdapter(this, wordsForSession, getContext(), viewFlipper, getFragmentManager());
        viewFlipper.setAdapter(wordCardAdapter);
        Button sessionButton = getView().findViewById(R.id.session_button);
        sessionButton.setOnClickListener(v -> changeSession());
    }

    public static void changeSession() {
        if (session < 5) {
            sharedPreferences.edit().putInt("SESSION", session + 1).commit();
        } else {
            sharedPreferences.edit().putInt("SESSION", 1).commit();
        }
    }
}
