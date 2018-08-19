package com.dmu.sash.flprdcrds.management;

import com.dmu.sash.flprdcrds.service.searchobjects.SearchResponse;
import com.google.gson.Gson;

public class GetWord implements AsyncResponse {
    private static final String SEARCH_URL = "https://od-api.oxforddictionaries.com/api/v1/search/en?q=";
    private static final String DEFINITION_URL = "https://od-api.oxforddictionaries.com/api/v1/entries/en/";

    public String getSearchURL(String text) {
        final String wordLowerCase = text.toLowerCase().trim();
        if (wordLowerCase.length() > 0) {
            return SEARCH_URL + wordLowerCase;
        }
        return null;
    }

    @Override
    public void processFinish(String output) {
        Gson gson = new Gson();
        SearchResponse searchResponse = gson.fromJson(output, SearchResponse.class);
        String searchedWord = searchResponse.getResults().get(0).getWord();
        GetDefinition getDefinition = new GetDefinition();
        URLAsyncTask urlAsyncTask = new URLAsyncTask(getDefinition);
        urlAsyncTask.execute(DEFINITION_URL + searchedWord);
    }
}
