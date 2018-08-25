package com.dmu.sash.flprdcrds.management;

import com.dmu.sash.flprdcrds.database.RealmFactory;
import com.dmu.sash.flprdcrds.database.entities.Word;

import java.util.UUID;

import io.realm.Realm;

public class WordSaver {
    public void saveWord(String word, String definition, String audioPronunciation) {
        if (word != null && definition != null) {
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
