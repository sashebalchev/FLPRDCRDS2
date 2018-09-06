package com.dmu.sash.flprdcrds.learning;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;

import com.dmu.sash.flprdcrds.R;
import com.dmu.sash.flprdcrds.database.entities.Word;

import java.util.List;

public class LearningFragment extends Fragment {

    public LearningFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_learning, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LearningDataProvider learningDataProvider = new LearningDataProvider(getContext());
        List<Word> data = learningDataProvider.getData();
        AdapterViewFlipper viewFlipper = view.findViewById(R.id.flipper_all);
        WordCardAdapter wordCardAdapter = new WordCardAdapter(getContext(), data);
        viewFlipper.setAdapter(wordCardAdapter);
    }
}
