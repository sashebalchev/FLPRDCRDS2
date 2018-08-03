package com.dmu.sash.flprdcrds.jsonSearch;

public class Result {
    /**
     * inflection_id : ace
     * matchType : inflection
     * matchString : ace
     * word : ace
     * score : 12.682389
     * region : gb
     * id : ace
     */

    private String inflection_id;
    private String matchType;
    private String matchString;
    private String word;
    private double score;
    private String region;
    private String id;

    public String getInflection_id() {
        return inflection_id;
    }

    public void setInflection_id(String inflection_id) {
        this.inflection_id = inflection_id;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getMatchString() {
        return matchString;
    }

    public void setMatchString(String matchString) {
        this.matchString = matchString;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
