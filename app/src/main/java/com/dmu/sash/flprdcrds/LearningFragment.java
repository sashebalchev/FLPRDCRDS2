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

    private AdapterViewFlipper viewFlipper;
    private LearningDataProvider learningDataProvider;
    List<Word> data;

    public LearningFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        learningDataProvider = new LearningDataProvider(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_learning, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Word> data = learningDataProvider.getData();
        viewFlipper = getView().findViewById(R.id.flipper_all);
        WordCardAdapter wordCardAdapter = new WordCardAdapter(getContext(), data);
        viewFlipper.setAdapter(wordCardAdapter);
    }
}
