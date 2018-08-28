package com.dmu.sash.flprdcrds.management;

import android.content.Context;
import android.content.ContextWrapper;
import android.support.annotation.NonNull;

import com.dmu.sash.flprdcrds.R;
import com.dmu.sash.flprdcrds.service.searchobjects.Result;
import com.dmu.sash.flprdcrds.service.searchobjects.SearchResponse;
import com.google.gson.Gson;

import java.util.List;

public class WordFinder extends ContextWrapper implements AsyncResponse {
    private static final String SEARCH_URL = "https://od-api.oxforddictionaries.com/api/v1/search/en?q=";
    private WordResultHandler wordResultHandler;
    private String word;

    public WordFinder (Context context) {
        super(context);
    }

    public void findWord(@NonNull WordResultHandler handler, @NonNull String word) {
        this.wordResultHandler = handler;
        this.word = word;
        URLAsyncTask urlAsyncTask = new URLAsyncTask(this);
        urlAsyncTask.execute(SEARCH_URL + word);
    }

    @Override
    public void processFinish(boolean hasErrors, String output) {
        if (hasErrors) {
            wordResultHandler.handleWordResult(output, null);
        } else {
            Gson gson = new Gson();
            SearchResponse searchResponse = gson.fromJson(output, SearchResponse.class);
            List<Result> results = searchResponse.getResults();
            if ((results != null) && (results.size() > 0)) {
                String searchedWord = results.get(0).getWord();
                wordResultHandler.handleWordResult(null, searchedWord);
            } else {
                String error = getString(R.string.error_could_not_find_word, word);
                wordResultHandler.handleWordResult(error, null);
            }
        }
    }
}
