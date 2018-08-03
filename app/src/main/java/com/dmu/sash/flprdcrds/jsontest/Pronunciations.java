package com.dmu.sash.flprdcrds.jsontest;

import java.util.List;

class Pronunciations {
    /**
     * audioFile : http://audio.oxforddictionaries.com/en/mp3/ace_1_gb_1_abbr.mp3
     * dialects : ["British English"]
     * phoneticNotation : IPA
     * phoneticSpelling : eɪs
     */

    private String audioFile;
    private String phoneticNotation;
    private String phoneticSpelling;
    private List<String> dialects;

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
}
