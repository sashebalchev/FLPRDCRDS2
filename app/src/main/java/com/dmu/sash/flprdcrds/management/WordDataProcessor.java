package com.dmu.sash.flprdcrds.management;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;

public class WordDataProcessor implements WordResultHandler, WordDataResultHandler {
    private Context context;

    public WordDataProcessor(Context context) {
        this.context = context;
    }

    public void processWord(@NonNull String word) {
        WordFinder wordFinder = new WordFinder();
        wordFinder.findWord(this, word);
    }

    @Override
    public void handleWordDataResult(String error, String word, String definition, String audioPronunciation) {
        if (error == null) {
            WordSaver wordSaver = new WordSaver();
            wordSaver.saveWord(word, definition, audioPronunciation);
        } else {
            TextView textView = new TextView(context);
            textView.setText(error);
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
            AlertDialog dialog = new AlertDialog.Builder(context)
                    .setTitle("Error finding word.")
                    .setView(textView)
                    .setPositiveButton("OK", null)
                    .create();
            dialog.show();
        }
    }
}
