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

import java.util.ArrayList;
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
    private List<Word> wordsForSession;
    private LearningDataProvider learningDataProvider;
    public LearningFragment() {
    }

    //TODO remove the delete realm clause before release. After release implement migration methods.
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        learningDataProvider = new LearningDataProvider(getContext());
//        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
//                .name("flprcrds.realm")
//                .schemaVersion(0)
//                .deleteRealmIfMigrationNeeded()
//                .build();
//        realm = Realm.getInstance(realmConfig);
//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
//        session = sharedPreferences.getInt("SESSION", 1);
//        if (!realm.isEmpty()){
//            getRealmData();
//            while (wordsForSession.size() <= 0){
//                changeSession();
//                getRealmData();
//            }
//        } else {
//            setFirstSession();
//            wordsForSession = new ArrayList<>(0);
//        }
//    }
//
//    private void getRealmData() {
//        RealmResults<Word> wordRealmResults = null;
//        switch (session) {
//            case 1:
//                wordRealmResults = realm.where(Word.class).equalTo("score", 1).findAll();
//                break;
//            case 2:
//                wordRealmResults = realm.where(Word.class).lessThanOrEqualTo("score", 2).findAll();
//                break;
//            case 3:
//                wordRealmResults = realm.where(Word.class).equalTo("score", 1).findAll();
//                break;
//            case 4:
//                wordRealmResults = realm.where(Word.class).lessThanOrEqualTo("score", 2).findAll();
//                break;
//            case 5:
//                wordRealmResults = realm.where(Word.class).findAll();
//                break;
//            default:
//                wordRealmResults = realm.where(Word.class).findAll();
//                break;
//        }
//        wordsForSession = realm.copyFromRealm(wordRealmResults);
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
        List<Word> data = learningDataProvider.getData();
        viewFlipper = getView().findViewById(R.id.flipper_all);
        WordCardAdapter wordCardAdapter = new WordCardAdapter(getContext(), data);
        viewFlipper.setAdapter(wordCardAdapter);
//        Button sessionButton = getView().findViewById(R.id.session_button);
//        sessionButton.setOnClickListener(v -> changeSession());
    }




}
