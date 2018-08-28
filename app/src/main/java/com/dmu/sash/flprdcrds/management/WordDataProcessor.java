package com.dmu.sash.flprdcrds.management;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.view.Gravity;
import android.widget.TextView;

import com.dmu.sash.flprdcrds.R;
import com.dmu.sash.flprdcrds.database.entities.Word;

import java.util.List;

public class WordDataProcessor extends ContextWrapper implements WordResultHandler, WordDataResultHandler {

    WordDataProcessor(Context context) {
        super(context);
    }

    public void processWord(@NonNull String word) {
        WordFinder wordFinder = new WordFinder(this);
        wordFinder.findWord(this, word);
    }

    @Override
    public void handleWordDataResult(String error, List<Word> words) {
        if (error == null) {
            WordSaver wordSaver = new WordSaver();
            if (words.size() > 1) {

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
                for (Word word : words) {
                    arrayAdapter.add(word.getDefinition());
                }
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setTitle(getString(R.string.select_definition_prompt, words.get(0).getWord()));
                alertBuilder.setNegativeButton(R.string.cancel, null);
                alertBuilder.setAdapter(arrayAdapter, (dialog, which) -> {
                    AlertDialog.Builder innerDialog = new AlertDialog.Builder(this);
                    innerDialog.setTitle(words.get(which).getWord());
                    innerDialog.setMessage(words.get(which).getDefinition());
                    innerDialog.setPositiveButton(R.string.btn_ok, (dialog1, which1) ->
                            wordSaver.saveWord(words.get(which)));
                    innerDialog.setNegativeButton(R.string.cancel, null);
                    innerDialog.show();
                });
                alertBuilder.show();
            } else if (words.size() == 1) {
                wordSaver.saveWord(words.get(0));
            }
        } else {
            TextView textView = new TextView(this);
            textView.setText(error);
            textView.setGravity(Gravity.CENTER);
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(R.string.error_finding_word_data)
                    .setView(textView)
                    .setPositiveButton(R.string.btn_ok, null)
                    .create();
            dialog.show();
        }
    }

    @Override
    public void handleWordResult(String error, String word) {
        if (error == null) {
            WordDataExtractor wordDataExtractor = new WordDataExtractor(this);
            wordDataExtractor.extractWordData(this, word);
        } else {
            TextView textView = new TextView(this);
            textView.setText(error);
            textView.setGravity(Gravity.CENTER);
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(R.string.error_finding_word)
                    .setView(textView)
                    .setPositiveButton(R.string.btn_ok, null)
                    .create();
            dialog.show();
        }
    }
}
