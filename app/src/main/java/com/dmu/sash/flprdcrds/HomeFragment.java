package com.dmu.sash.flprdcrds;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    //    private Button button;
//    public static TextView data;
    private Realm realm;
    private URLAsyncTask urlAsyncTask;

    public HomeFragment() {
        // Required empty public constructor
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
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FloatingActionButton fab = getView().findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            final EditText taskEditText = new EditText(getActivity());
            AlertDialog dialog = new AlertDialog.Builder(getActivity())
                    .setTitle("Add Word")
                    .setView(taskEditText)
                    .setPositiveButton("Add", (dialogInterface, i) -> {
                        GetWord getWord = new GetWord();
                        urlAsyncTask = new URLAsyncTask(getWord);
                        urlAsyncTask.execute(getWord.searchWord(taskEditText));
                    })
                    .setNegativeButton("Cancel", null)
                    .create();
            dialog.show();
        });
        RealmResults<Word> words = realm.where(Word.class).findAll();
        final WordAdapter adapter = new WordAdapter(this, words);
        ListView listView = getView().findViewById(R.id.word_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            final Word word = (Word) parent.getAdapter().getItem(position);
            final EditText wordEdit = new EditText(getActivity());
            wordEdit.setText(word.getWord());
            AlertDialog dialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
                    .setTitle("Edit Entry")
                    .setView(wordEdit)
                    .setPositiveButton("Pronunciation", (dialog1, which) -> audioPronunciation(word.getId()))
                    .setNegativeButton("Delete", (dialog12, which) -> deleteWordEntry(word.getId()))
                    .create();
            dialog.show();
        });
    }
    private void audioPronunciation(String id) {
        AudioPronunciation audio = new AudioPronunciation();
        audio.playPronunciation(id);
    }

    private void deleteWordEntry(String id) {
        realm.executeTransactionAsync((realm) -> {
            Objects.requireNonNull(realm.where(Word.class).equalTo("id", id)
                    .findFirst())
                    .deleteFromRealm();
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
