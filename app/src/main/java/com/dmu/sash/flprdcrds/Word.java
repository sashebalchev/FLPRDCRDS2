package com.dmu.sash.flprdcrds;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Word extends RealmObject {

    @Required
    @PrimaryKey
    private String id;
    @Required
    private String word;
    @Required
    private String definition;
    @Required
    private String pronounciation;
    @Required
    private String audioPronounciation;
    private int score;

    public Word() {
        score = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getPronounciation() {
        return pronounciation;
    }

    public void setPronounciation(String pronounciation) {
        this.pronounciation = pronounciation;
    }

    public String getAudioPronounciation() {
        return audioPronounciation;
    }

    public void setAudioPronounciation(String audioPronounciation) {
        this.audioPronounciation = audioPronounciation;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void increaseScore() {
        score = score + 1;
    }

    public void decreaseScore() {
        score = score - 1;
    }
}
