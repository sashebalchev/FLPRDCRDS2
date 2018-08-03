package com.dmu.sash.flprdcrds;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class LearningFragment extends Fragment {

    private Realm realm;
    private SwipeFlingAdapterView swipeFlingAdapterView;
    private int bgColor;
    private ListView listView;
    private URLAsyncTask urlAsyncTask;

    public LearningFragment() {
//        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.fragment_learning, R.id.fetched_text, al)
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

        swipeFlingAdapterView = getView().findViewById(R.id.swipe_frame);

        RealmResults<Word> words = realm.where(Word.class).findAll();
        WordCardAdapter wordCardAdapter = new WordCardAdapter(this, words, getContext());
        swipeFlingAdapterView.setAdapter(wordCardAdapter);
        swipeFlingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

            }

            @Override
            public void onLeftCardExit(Object o) {

            }

            @Override
            public void onRightCardExit(Object o) {

            }

            @Override
            public void onAdapterAboutToEmpty(int i) {

            }

            @Override
            public void onScroll(float v) {

            }
        });


    }

    //    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        listView = getView().findViewById(R.id.word_list);
//        listView.setBackgroundColor(bgColor);
//        FloatingActionButton fab = getView().findViewById(R.id.fab);
//        fab.setOnClickListener(v -> {
//            final EditText taskEditText = new EditText(getActivity());
//            AlertDialog dialog = new AlertDialog.Builder(getActivity())
//                    .setTitle("Add Word")
//                    .setView(taskEditText)
//                    .setPositiveButton("Add", (dialogInterface, i) -> {
//                        GetWord getWord = new GetWord();
//                        urlAsyncTask = new URLAsyncTask(getWord);
//                        urlAsyncTask.execute(getWord.searchWord(taskEditText));
//                    })
//                    .setNegativeButton("Cancel", null)
//                    .create();
//            dialog.show();
//        });
//        RealmResults<Word> words = realm.where(Word.class).findAll();
//        final WordAdapter adapter = new WordAdapter(this, words, getActivity());
//        ListView listView = getView().findViewById(R.id.word_list);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener((parent, view1, position, id) -> {
//            final Word word = (Word) parent.getAdapter().getItem(position);
//            final TextView wordEdit = new TextView(getActivity());
//            wordEdit.setText(word.getDefinition());
//            wordEdit.setTextSize(24);
//            wordEdit.setTypeface(null, Typeface.ITALIC);
//            wordEdit.setGravity(Gravity.CENTER);
//            AlertDialog dialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
//                    .setTitle("LEARNING")
//                    .setView(wordEdit)
//                    .setPositiveButton("Pronunciation", (dialog1, which) -> audioPronunciation(word.getId()))
//                    .setNegativeButton("Delete", (dialog12, which) -> deleteWordEntry(word.getId()))
//                    .create();
//            dialog.show();
//        });
//    }
//    private void audioPronunciation(String id) {
//        AudioPronunciation audio = new AudioPronunciation();
//        audio.playPronunciation(id);
//    }
//
//    private void deleteWordEntry(String id) {
//        realm.executeTransactionAsync((realm) -> {
//            Objects.requireNonNull(realm.where(Word.class).equalTo("id", id)
//                    .findFirst())
//                    .deleteFromRealm();
//        });
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        realm.close();
//    }
}
