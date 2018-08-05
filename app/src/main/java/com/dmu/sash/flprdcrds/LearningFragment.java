package com.dmu.sash.flprdcrds;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
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

    public LearningFragment() {
    }

    public static LearningFragment getInstance(){
        if (instance == null){
            instance = new LearningFragment();
        }
        return instance;
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
        words = realm.where(Word.class).findAllAsync();
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
//        RealmResults<Word> wordsToLearn = realm.where(Word.class).equalTo("score", "1").findAll();
//        RealmResults<Word> words = realm.where(Word.class).findAllAsync();
        WordCardAdapter wordCardAdapter = new WordCardAdapter(this, words, getContext(), viewFlipper);
        viewFlipper.setAdapter(wordCardAdapter);
    }
}
