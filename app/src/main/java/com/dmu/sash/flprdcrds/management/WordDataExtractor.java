package com.dmu.sash.flprdcrds.management;

import android.content.Context;
import android.content.ContextWrapper;
import android.support.annotation.NonNull;

import com.dmu.sash.flprdcrds.R;
import com.dmu.sash.flprdcrds.database.entities.Word;
import com.dmu.sash.flprdcrds.service.objects.Entry;
import com.dmu.sash.flprdcrds.service.objects.LexicalEntry;
import com.dmu.sash.flprdcrds.service.objects.Pronunciation;
import com.dmu.sash.flprdcrds.service.objects.Response;
import com.dmu.sash.flprdcrds.service.objects.Result;
import com.dmu.sash.flprdcrds.service.objects.Sense;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class WordDataExtractor extends ContextWrapper implements AsyncResponse {
    private static final String DEFINITION_URL = "https://od-api.oxforddictionaries.com/api/v1/entries/en/";
    private WordDataResultHandler handler;

    public WordDataExtractor(Context context) {
        super(context);
    }

    public void extractWordData(@NonNull WordDataResultHandler handler, @NonNull String word) {
        this.handler = handler;
        URLAsyncTask urlAsyncTask = new URLAsyncTask(this);
        urlAsyncTask.execute(DEFINITION_URL + word);
    }

    @Override
    public void processFinish(boolean hasErrors, String output) {
        if (hasErrors) {
            handler.handleWordDataResult(output, null);
        } else {
            Response response = extractResponse(output);
            List<Word> words = extractWords(response);
            if (words.size() == 0) {
                handler.handleWordDataResult(getString(R.string.error_no_definitions_found), null);
            } else {
                handler.handleWordDataResult(null, words);
            }
        }
    }

    private Response extractResponse(String output) {
        Gson gson = new Gson();
        return gson.fromJson(output, Response.class);
    }

    public List<Word> extractWords(Response response) {
        List<Word> words = new ArrayList<>();
        if (response == null) {
            return words;
        }
        Result result = response.getResults().get(0);
        if (result == null) {
            return words;
        }
        String wordStr = result.getWord();
        List<LexicalEntry> lexicalEntries = result.getLexicalEntries();
        if (lexicalEntries == null) {
            return words;
        }
        for (LexicalEntry lexicalEntry : lexicalEntries) {
            List<Entry> entries = lexicalEntry.getEntries();
            if (entries != null) {
                for (Entry entry : entries) {
                    List<Sense> senses = entry.getSenses();
                    if (senses == null || senses.size() == 0){
                        continue;
                    }
                    Sense sense = senses.get(0);
                    if (sense.getShort_definitions() == null || sense.getShort_definitions().size() == 0) {
                        continue;
                    }
                    Word word = new Word();
                    word.setWord(wordStr);
                    word.setDefinition(sense.getShort_definitions().get(0));
                    List<Pronunciation> pronunciations = sense.getPronunciations();
                    word.setAudioPronunciation(getAudioPronunciation(pronunciations));
                    if (word.getAudioPronunciation() == null) {
                        word.setAudioPronunciation(getAudioPronunciation(entry.getPronunciations()));
                    }
                    words.add(word);
                }
                for (Word word : words) {
                    if (word.getAudioPronunciation() == null) {
                        word.setAudioPronunciation(getAudioPronunciation(lexicalEntry.getPronunciations()));
                    }
                }
            }
        }
        for (Word word : words) {
            if (word.getAudioPronunciation() == null) {
                word.setAudioPronunciation(getAudioPronunciation(result.getPronunciations()));
            }
        }
        return words;
    }

    private String getAudioPronunciation(List<Pronunciation> pronunciations) {
        String audioPronunciation = null;
        if (pronunciations == null) {
            return null;
        }
        for (Pronunciation pronunciationEntry : pronunciations) {
            audioPronunciation = pronunciationEntry.getAudioFile();
            if (audioPronunciation != null) break;
        }
        return audioPronunciation;
    }
}
