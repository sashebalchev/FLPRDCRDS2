package com.dmu.sash.flprdcrds;

import android.widget.EditText;

import com.dmu.sash.flprdcrds.json.Response;
import com.dmu.sash.flprdcrds.jsonSearch.SearchResponse;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetWord implements AsyncResponse {
    private static final String language = "en";

    public String searchWord(String text) {
        final String word = text;
        final String word_id = word.toLowerCase();
        if (word_id.length() >= 1) {
            return "https://od-api.oxforddictionaries.com/api/v1/search/" + language + "?q=" + word_id;
        }
        return null;
    }

    @Override
    public void processFinish(String output) {
        Gson gson = new Gson();
        SearchResponse searchResponse = gson.fromJson(output, SearchResponse.class);
        String searchedWord = searchResponse.getResults().get(0).getWord();
//        try {
//            JSONObject jsonObject = new JSONObject(output);
//            JSONArray jsonArray = jsonObject.getJSONArray("results");
//            String word = jsonArray.getJSONObject(0).getString("word");
        GetDefinition getDefinition = new GetDefinition();
        URLAsyncTask urlAsyncTask = new URLAsyncTask(getDefinition);
        urlAsyncTask.execute("https://od-api.oxforddictionaries.com/api/v1/entries/" + language + "/" + searchedWord);
//        } catch (JSONException e) {
//            System.out.println("ERROR");
//        }
    }
}
