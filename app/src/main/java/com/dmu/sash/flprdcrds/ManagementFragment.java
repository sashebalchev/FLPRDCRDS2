package com.dmu.sash.flprdcrds;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class ManagementFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    private Realm realm;
    private RealmResults<Word> words;
    private URLAsyncTask urlAsyncTask;
    private String textColor;
    private int bgColor;
    private int fontColor;
    private static ManagementFragment instance;

    public ManagementFragment() {
        // Required empty public constructor
    }

    public static ManagementFragment getInstance() {
        if (instance == null) {
            instance = new ManagementFragment();
        }
        return instance;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO remove the delete realm clause before release. After release implement migration methods.
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("flprcrds.realm")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(realmConfig);
        words = realm.where(Word.class).findAllAsync().sort("timestamp");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_management, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = getView().findViewById(R.id.word_list);
        final WordAdapter adapter = new WordAdapter(this, words, getActivity());
//        listView.setBackgroundColor(bgColor);
        Button deleteButton = getView().findViewById(R.id.delete_words_button);
        deleteButton.setOnClickListener(v -> {
            AlertDialog deleteWordsDialog = new AlertDialog.Builder(getContext())
                    .setTitle("Delete ALL words?")
                    .setPositiveButton("Delete", (dialog1, which) -> deleteAllWords())
                    .setNegativeButton("Cancel", null)
                    .create();
            deleteWordsDialog.show();
        });

        FloatingActionButton fab = getView().findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            final EditText taskEditText = new EditText(getActivity());
            taskEditText.setGravity(Gravity.CENTER);
            taskEditText.setOnFocusChangeListener((v1, hasFocus) -> taskEditText.post(() -> {
                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(taskEditText, InputMethodManager.SHOW_IMPLICIT);
            }));
            AlertDialog dialog = new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.add_word)
                    .setView(taskEditText)
                    .setPositiveButton("Add", (dialogInterface, i) -> {
                        GetWord getWord = new GetWord();
                        String text = taskEditText.getText().toString();
                        urlAsyncTask = new URLAsyncTask(getWord);
                        urlAsyncTask.execute(getWord.getSearchURL(text));
//                        listView.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                listView.setSelection(adapter.getCount());
//                            }
//                        });
                    })
                    .setNegativeButton("Cancel", null)
                    .create();
            dialog.show();
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

            taskEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before,
                                          int count) {
                    if (s.length()>0 && s.subSequence(s.length()-1, s.length())
                            .toString().equalsIgnoreCase("\n")) {
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).performClick();
                    }
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    // Check if EditText is empty
                    if (TextUtils.isEmpty(s)) {
                        // Disable ADD button if no there's no text.
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    } else {
                        // Enable ADD button if there's some text.
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    }

                }
            });
            taskEditText.setFocusableInTouchMode(true);
            taskEditText.requestFocus();

        });


        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            final Word word = (Word) parent.getAdapter().getItem(position);
            final TextView wordEdit = new TextView(getActivity());
            wordEdit.setText(word.getDefinition());
            wordEdit.setTextSize(24);
            wordEdit.setPadding(14, 14, 14, 14);
            wordEdit.setTypeface(null, Typeface.ITALIC);
            wordEdit.setGravity(Gravity.CENTER);
            AlertDialog dialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
                    .setTitle(word.getWord().toUpperCase())
                    .setView(wordEdit)
                    .setPositiveButton("Pronunciation", (dialog1, which) -> audioPronunciation(word.getAudioPronunciation()))
                    .setNegativeButton("Delete", (dialog12, which) -> deleteWordEntry(word.getId()))
                    .create();
            dialog.show();
        });

    }

    private void deleteAllWords() {
        realm.executeTransactionAsync(realm -> realm.deleteAll());
    }

    private void audioPronunciation(String url) {
        AudioPronunciation.getInstance().play(url, getContext());
    }

    private void deleteWordEntry(String id) {
        realm.executeTransactionAsync((realm) -> Objects.requireNonNull(realm.where(Word.class)
                .equalTo("id", id)
                .findFirst())
                .deleteFromRealm());
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
