package com.dmu.sash.flprdcrds;

import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetWord implements AsyncResponse{
    private final String language;
    public GetWord(){
        language = "en";
    }
    public String searchWord (EditText editText){
        final String word = editText.getText().toString();
        final String word_id = word.toLowerCase();
        if (word_id.length() >= 1){
            return "https://od-api.oxforddictionaries.com/api/v1/search/" + language + "?q=" + word_id;
        }
        return null;
    }
    @Override
    public void processFinish(String output) {
        try {
            JSONObject jsonObject = new JSONObject(output);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            String word = jsonArray.getJSONObject(0).getString("word");
            GetDefinition getDefinition = new GetDefinition();
            URLAsyncTask urlAsyncTask= new URLAsyncTask(getDefinition);
            urlAsyncTask.execute("https://od-api.oxforddictionaries.com/api/v1/entries/" + language + "/" + word);
        } catch (JSONException e) {
            System.out.println("ERROR");
        }
    }
}
