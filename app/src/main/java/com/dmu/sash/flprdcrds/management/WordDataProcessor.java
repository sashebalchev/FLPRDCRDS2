package com.dmu.sash.flprdcrds.management;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.view.Gravity;
import android.widget.TextView;

import com.dmu.sash.flprdcrds.database.entities.Word;

import java.util.List;

public class WordDataProcessor implements WordResultHandler, WordDataResultHandler {
    private Context context;

    WordDataProcessor(Context context) {
        this.context = context;
    }

    public void processWord(@NonNull String word) {
        WordFinder wordFinder = new WordFinder();
        wordFinder.findWord(this, word);
    }

    @Override
    public void handleWordDataResult(String error, List<Word> words) {
        if (error == null) {
            WordSaver wordSaver = new WordSaver();
            if (words.size() > 1) {

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.select_dialog_singlechoice);
                for (Word word : words) {
                    arrayAdapter.add(word.getDefinition());
                }
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                alertBuilder.setTitle("Select definition for " + words.get(0).getWord());
                alertBuilder.setNegativeButton("Cancel", null);
                alertBuilder.setAdapter(arrayAdapter, (dialog, which) -> {
                    AlertDialog.Builder innerDialog = new AlertDialog.Builder(context);
                    innerDialog.setTitle(words.get(which).getWord());
                    innerDialog.setMessage(words.get(which).getDefinition());
                    innerDialog.setPositiveButton("OK", (dialog1, which1) ->
                            wordSaver.saveWord(words.get(which)));
                    innerDialog.setNegativeButton("Cancel", null);
                    innerDialog.show();
                });
                alertBuilder.show();
            } else if (words.size() == 1) {
                wordSaver.saveWord(words.get(0));
            }
        } else {
            TextView textView = new TextView(context);
            textView.setText(error);
            textView.setGravity(Gravity.CENTER);
            AlertDialog dialog = new AlertDialog.Builder(context)
                    .setTitle("Error finding word data.")
                    .setView(textView)
                    .setPositiveButton("OK", null)
                    .create();
            dialog.show();
        }
    }

    @Override
    public void handleWordResult(String error, String word) {
        if (error == null) {
            WordDataExtractor wordDataExtractor = new WordDataExtractor();
            wordDataExtractor.extractWordData(this, word);
        } else {
            TextView textView = new TextView(context);
            textView.setText(error);
            textView.setGravity(Gravity.CENTER);
            AlertDialog dialog = new AlertDialog.Builder(context)
                    .setTitle("Error finding word.")
                    .setView(textView)
                    .setPositiveButton("OK", null)
                    .create();
            dialog.show();
        }
    }
}
