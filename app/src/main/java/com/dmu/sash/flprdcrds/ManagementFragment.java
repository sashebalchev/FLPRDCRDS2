package com.dmu.sash.flprdcrds;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class ManagementFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    //    private Button button;
//    public static TextView data;
    private Realm realm;
    private URLAsyncTask urlAsyncTask;
    private String textColor;
    private int bgColor;
    private int fontColor;
    private ListView listView;

    public ManagementFragment() {
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
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        bgColor = Color.parseColor(sharedPreferences.getString("PREF_COLOR_BG", "#FFFFFF"));
//        fontColor = Color.parseColor(sharedPreferences.getString("PREF_COLOR_FONT", "#000000"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_management, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = getView().findViewById(R.id.word_list);
        listView.setBackgroundColor(bgColor);
        Button deleteButton = getView().findViewById(R.id.delete_words_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog deleteWordsDialog = new AlertDialog.Builder(getActivity())
                        .setTitle("Delete ALL words?")
                        .setPositiveButton("Delete", (dialog1, which) -> deleteAllWords())
                        .setNegativeButton("Cancel", null)
                        .create();
                deleteWordsDialog.show();
            }

        });
        FloatingActionButton fab = getView().findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            final EditText taskEditText = new EditText(getActivity());
            taskEditText.setGravity(Gravity.CENTER);
            taskEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    taskEditText.post(new Runnable() {
                        @Override
                        public void run() {
                            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            inputMethodManager.showSoftInput(taskEditText, InputMethodManager.SHOW_IMPLICIT);
                        }
                    });
                }
            });
            AlertDialog dialog = new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.add_word)
                    .setView(taskEditText)
                    .setPositiveButton("Add", (dialogInterface, i) -> {
                        GetWord getWord = new GetWord();
                        urlAsyncTask = new URLAsyncTask(getWord);
                        urlAsyncTask.execute(getWord.searchWord(taskEditText));
                    })
                    .setNegativeButton("Cancel", null)
                    .create();
            dialog.show();
            taskEditText.requestFocus();
        });
        RealmResults<Word> words = realm.where(Word.class).findAll();
        final WordAdapter adapter = new WordAdapter(this, words, getActivity());
        ListView listView = getView().findViewById(R.id.word_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            final Word word = (Word) parent.getAdapter().getItem(position);
            final TextView wordEdit = new TextView(getActivity());
            wordEdit.setText(word.getDefinition());
            wordEdit.setTextSize(24);
            wordEdit.setTypeface(null, Typeface.ITALIC);
            wordEdit.setGravity(Gravity.CENTER);
            AlertDialog dialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
                    .setTitle(word.getWord().toUpperCase())
                    .setView(wordEdit)
                    .setPositiveButton("Pronunciation", (dialog1, which) -> audioPronunciation(word.getId()))
                    .setNegativeButton("Delete", (dialog12, which) -> deleteWordEntry(word.getId()))
                    .create();
            dialog.show();
        });
    }

    private void deleteAllWords() {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });
    }

    private void audioPronunciation(String id) {
        AudioPronunciation audio = new AudioPronunciation(getContext());
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

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }
}
