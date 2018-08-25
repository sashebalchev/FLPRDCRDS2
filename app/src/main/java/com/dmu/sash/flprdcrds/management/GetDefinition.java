package com.dmu.sash.flprdcrds.management;


import com.dmu.sash.flprdcrds.database.RealmFactory;
import com.dmu.sash.flprdcrds.database.entities.Word;
import com.dmu.sash.flprdcrds.service.objects.Entry;
import com.dmu.sash.flprdcrds.service.objects.LexicalEntry;
import com.dmu.sash.flprdcrds.service.objects.Pronunciation;
import com.dmu.sash.flprdcrds.service.objects.Response;
import com.dmu.sash.flprdcrds.service.objects.Result;
import com.dmu.sash.flprdcrds.service.objects.Sense;
import com.google.gson.Gson;

import java.util.List;
import java.util.UUID;

import io.realm.Realm;

public class GetDefinition implements AsyncResponse {

    @Override
    public void processFinish(boolean hasErrors, String output) {
        Response response = extractResponse(output);
        String word = extractWord(response);
        LexicalEntry lexicalEntry = extractLexicalEntry(response);
        String definition = extractDefinition(lexicalEntry);
        String audioPronunciation = extractAudioPronunciation(lexicalEntry);
        saveWord(word, definition, audioPronunciation);
    }

    private Response extractResponse(String output) {
        Gson gson = new Gson();
        return gson.fromJson(output, Response.class);
    }

    private String extractWord(Response response) {
        String word = null;
        if (response != null) {
            Result result = response.getResults().get(0);
            if (result != null) {
                word = result.getWord();
            }
        }
        return word;
    }

    private LexicalEntry extractLexicalEntry(Response response) {
        LexicalEntry lexicalEntry = null;
        if (response != null) {
            Result result = response.getResults().get(0);
            if (result != null) {
                lexicalEntry = result.getLexicalEntries().get(0);
            }
        }
        return lexicalEntry;
    }

    private String extractDefinition(LexicalEntry lexicalEntry) {
        String definition = null;
        if (lexicalEntry != null) {
            Entry entry = lexicalEntry.getEntries().get(0);
            if (entry != null) {
                Sense sense = entry.getSenses().get(0);
                if (sense != null) {
                    if (sense.getShort_definitions() != null) {
                        definition = sense.getShort_definitions().get(0);
                    }
                }
            }
        }
        return definition;
    }

    private String extractAudioPronunciation(LexicalEntry lexicalEntry) {
        String audioPronunciation = null;
        List<Pronunciation> pronunciationList = lexicalEntry.getPronunciations();
        if (pronunciationList != null) {
            for (Pronunciation pronunciationEntry : pronunciationList) {
                audioPronunciation = pronunciationEntry.getAudioFile();
                if (audioPronunciation != null) break;
            }
        }
        if (audioPronunciation == null) {
            for (Entry entry : lexicalEntry.getEntries()) {
                pronunciationList = entry.getPronunciations();
                if (pronunciationList != null) {
                    for (Pronunciation pronunciationEntry : pronunciationList) {
                        audioPronunciation = pronunciationEntry.getAudioFile();
                        if (audioPronunciation != null) break;
                    }
                }
                if (audioPronunciation != null) break;
            }
        }
        return audioPronunciation;
    }

    private void saveWord(String word, String definition, String audioPronunciation) {
        if (word != null && definition != null && audioPronunciation != null) {
            final Word wordDef = new Word();
            wordDef.setId(UUID.randomUUID().toString());
            wordDef.setWord(word);
            wordDef.setDefinition(definition);
            wordDef.setAudioPronunciation(audioPronunciation);
            wordDef.setScore(1);
            wordDef.setProficiency(1);
            wordDef.setTimestamp(System.currentTimeMillis());
            Realm realm = RealmFactory.getRealm();
            realm.executeTransactionAsync(r -> r.copyToRealm(wordDef));
        }
    }
}
