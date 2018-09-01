package com.dmu.sash.flprdcrds.management;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.dmu.sash.flprdcrds.R;
import com.dmu.sash.flprdcrds.database.RealmFactory;
import com.dmu.sash.flprdcrds.database.entities.Word;
import com.dmu.sash.flprdcrds.helpers.AudioPronunciation;

import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmResults;

public class ManagementFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static ManagementFragment instance;
    private Realm realm;
    private RealmResults<Word> words;

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
        realm = RealmFactory.getRealm();
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
        ListView listView = view.findViewById(R.id.word_list);
        final Context context = getContext();
        final WordAdapter adapter = new WordAdapter(words, context);
        Button deleteButton = view.findViewById(R.id.delete_words_button);
        deleteButton.setOnClickListener(v -> {
            AlertDialog deleteWordsDialog = new AlertDialog.Builder(context)
                    .setTitle(R.string.delete_all_words_prompt)
                    .setPositiveButton(R.string.delete, (dialog1, which) -> deleteAllWords())
                    .setNegativeButton(R.string.cancel, null)
                    .create();
            deleteWordsDialog.show();
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            final EditText taskEditText = new EditText(getActivity());
            taskEditText.setGravity(Gravity.CENTER);
            taskEditText.setOnFocusChangeListener((v1, hasFocus) -> taskEditText.post(() -> {
                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(taskEditText, InputMethodManager.SHOW_IMPLICIT);
            }));
            AlertDialog dialog = new AlertDialog.Builder(context)
                    .setTitle(R.string.add_word)
                    .setView(taskEditText)
                    .setPositiveButton(R.string.add, (dialogInterface, i) -> {
                        WordDataProcessor wordDataProcessor = new WordDataProcessor(context);
                        String text = taskEditText.getText().toString().trim().toLowerCase();
                        wordDataProcessor.processWord(text);
                    })
                    .setNegativeButton(R.string.cancel, null)
                    .create();
            dialog.show();
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

            taskEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() > 0 && s.subSequence(s.length() - 1, s.length())
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
                        // Disable ADD button if there's no text.
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
            final TextView wordEdit = new TextView(getContext());
            wordEdit.setText(word.getDefinition());
            wordEdit.setTextSize(24);
            wordEdit.setPadding(14, 14, 14, 14);
            wordEdit.setTypeface(null, Typeface.ITALIC);
            wordEdit.setGravity(Gravity.CENTER);
            AlertDialog dialog = new AlertDialog.Builder(Objects.requireNonNull(getContext()))
                    .setTitle(word.getWord().toUpperCase())
                    .setView(wordEdit)
                    .setNegativeButton(R.string.delete, (dialog12, which) -> deleteWordEntry(word.getId()))
                    .create();
            dialog.setOnShowListener(arg1 -> dialog.getButton(DialogInterface.BUTTON_NEGATIVE)
                    .setTextColor(Color.parseColor("#f44336")));

            if (word.getAudioPronunciation() != null) {
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.pronunciation),
                        (dialog1, which) -> audioPronunciation(word.getAudioPronunciation()));
            }
            dialog.show();
        });
    }

    private void deleteAllWords() {
        realm.executeTransactionAsync(realm -> realm.deleteAll());
    }

    private void audioPronunciation(String url) {
        AudioPronunciation.getInstance().play(url);
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
