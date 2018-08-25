package com.dmu.sash.flprdcrds.management;

import android.support.annotation.NonNull;

import com.dmu.sash.flprdcrds.service.searchobjects.Result;
import com.dmu.sash.flprdcrds.service.searchobjects.SearchResponse;
import com.google.gson.Gson;

import java.util.List;

public class WordFinder implements AsyncResponse {
    private static final String SEARCH_URL = "https://od-api.oxforddictionaries.com/api/v1/search/en?q=";
    private WordResultHandler wordResultHandler;

    public void findWord(@NonNull WordResultHandler handler, @NonNull String word) {
        this.wordResultHandler = handler;
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
                wordResultHandler.handleWordResult("Error finding word", null);
            }
        }
    }
}
