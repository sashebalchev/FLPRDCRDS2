package com.dmu.sash.flprdcrds.json;

import java.util.List;

public class Pronunciation {
    /**
     * audioFile : string
     * dialects : ["string"]
     * phoneticNotation : string
     * phoneticSpelling : string
     * regions : ["string"]
     */

    private String audioFile;
    private String phoneticNotation;
    private String phoneticSpelling;
    private List<String> dialects;
    private List<String> regions;

    /**
     * audioFile : string
     * dialects : ["string"]
     * phoneticNotation : string
     * phoneticSpelling : string
     * regions : ["string"]
     */

    public String getAudioFile() {
        return audioFile;
    }

    public void setAudioFile(String audioFile) {
        this.audioFile = audioFile;
    }

    public String getPhoneticNotation() {
        return phoneticNotation;
    }

    public void setPhoneticNotation(String phoneticNotation) {
        this.phoneticNotation = phoneticNotation;
    }

    public String getPhoneticSpelling() {
        return phoneticSpelling;
    }

    public void setPhoneticSpelling(String phoneticSpelling) {
        this.phoneticSpelling = phoneticSpelling;
    }

    public List<String> getDialects() {
        return dialects;
    }

    public void setDialects(List<String> dialects) {
        this.dialects = dialects;
    }

    public List<String> getRegions() {
        return regions;
    }

    public void setRegions(List<String> regions) {
        this.regions = regions;
    }
}