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
    private String pronunciation;
    @Required
    private String audioPronunciation;
    private int score;
    private int proficiency;
    private long timestamp;

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

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getAudioPronunciation() {
        return audioPronunciation;
    }

    public void setAudioPronunciation(String audioPronunciation) {
        this.audioPronunciation = audioPronunciation;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void decreaseScore() {
        setScore(1);
        setProficiency(1);
    }

    public void increaseScore() {
        if (score < 3) {
            score = score + 1;
        } else {
            proficiency++;
        }
    }

    public int getProficiency() {
        return proficiency;
    }

    public void setProficiency(int proficiency) {
        this.proficiency = proficiency;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
