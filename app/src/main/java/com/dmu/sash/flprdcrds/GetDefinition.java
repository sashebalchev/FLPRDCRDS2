package com.dmu.sash.flprdcrds;


import com.dmu.sash.flprdcrds.json.Entry;
import com.dmu.sash.flprdcrds.json.LexicalEntry;
import com.dmu.sash.flprdcrds.json.Response;
import com.dmu.sash.flprdcrds.json.Result;
import com.dmu.sash.flprdcrds.json.Sense;
import com.google.gson.Gson;

import java.util.UUID;

import io.realm.Realm;

public class GetDefinition implements AsyncResponse {

    private final String language;
    private static Realm realm;

    public GetDefinition() {
        language = "en";
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void processFinish(String output) {
        String word = null;
        String definition = null;
        String pronunciation = null;
        String audioPronunciation = null;
        Result result;
        Gson gson = new Gson();
        Response response = gson.fromJson(output, Response.class);
        if (response != null) {
            result = response.getResults().get(0);
            if (result != null) {
                word = result.getWord();
                LexicalEntry lexicalEntry = result.getLexicalEntries().get(0);
                if (lexicalEntry != null){
                    Entry entry = lexicalEntry.getEntries().get(0);
                    if (entry != null){
                        Sense sense = entry.getSenses().get(0);
                        if (sense != null) {
                            if (sense.getShort_definitions() != null){
                                definition = sense.getShort_definitions().get(0);
                            } else {
                                System.out.println("Problem getting definition");
                            }
                        } else {
                            System.out.println("No definitions");
                        }
                    } else {
                        System.out.println("No entries");
                    }
                } else {
                    System.out.println("No entries");
                }
            } else {
                System.out.println("No word found.");
            }
        } else {
            System.out.println("No results found.");
        }
        //Else error

        // TODO Loop senses over and find the short definitions there.
        // TODO handle exception if word doesn't exist
        pronunciation = "";
        audioPronunciation = response.getResults().get(0).getLexicalEntries().get(0).getPronunciations().get(0).getAudioFile();
//        String definition = response.getResults().get(0).getLexicalEntries().get(0).getEntries().get(0).getSenses().get(0).getShort_definitions().get(0);
        if (word!=null && definition != null && pronunciation != null && audioPronunciation != null){
            final Word wordDef = new Word();
            wordDef.setId(UUID.randomUUID().toString());
            wordDef.setWord(word);
            wordDef.setDefinition(definition);
            wordDef.setPronounciation("");
            wordDef.setAudioPronounciation(audioPronunciation);
            realm.executeTransactionAsync(realm -> realm.copyToRealm(wordDef));
        }

    }
}
