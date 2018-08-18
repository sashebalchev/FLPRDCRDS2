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
    private String pronunciation;
    @Required
    private String audioPronunciation;
    private int score;
    private int proficiency;
    private int consecutiveKnownSessions;
    private int consecutiveNotKnownSessions;
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
        setConsecutiveKnownSessions(0);
        setScore(1);
        setProficiency(1);
        increaseConsecutiveNotKnownSessions();
    }

    public void increaseScore() {
        setConsecutiveNotKnownSessions(0);
        increaseConsecutiveKnownSessions();
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

    public void setConsecutiveKnownSessions(int consecutiveKnownSessions) {
        this.consecutiveKnownSessions = consecutiveKnownSessions;
    }

    private void increaseConsecutiveKnownSessions() {
        this.consecutiveKnownSessions++;
    }

    public void setConsecutiveNotKnownSessions(int consecutiveNotKnownSessions) {
        this.consecutiveNotKnownSessions = consecutiveNotKnownSessions;
    }

    private void increaseConsecutiveNotKnownSessions() {
        this.consecutiveNotKnownSessions++;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


}
