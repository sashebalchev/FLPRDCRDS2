package com.dmu.sash.flprdcrds.management;

import com.dmu.sash.flprdcrds.database.RealmFactory;
import com.dmu.sash.flprdcrds.database.entities.Word;

import java.util.UUID;

import io.realm.Realm;

public class WordSaver {
    public void saveWord(Word word) {
        String wordStr = word.getWord();
        String definition = word.getDefinition();
        String audioPronunciation = word.getAudioPronunciation();
        if (wordStr != null && definition != null) {
            final Word wordDef = new Word();
            wordDef.setId(UUID.randomUUID().toString());
            wordDef.setWord(wordStr);
            wordDef.setDefinition(definition);
            wordDef.setAudioPronunciation(audioPronunciation);
            wordDef.setScore(1);
            wordDef.setProficiency(1);
            wordDef.setTimestamp(System.currentTimeMillis());
            Realm realm = RealmFactory.getRealm();
            Word existingWord = realm.where(Word.class).equalTo("word", wordStr)
                    .and().equalTo("definition", definition).findFirst();
            if (existingWord == null) {
                realm.executeTransactionAsync(r -> r.copyToRealm(wordDef));
            }
        }
    }
}
