package com.dmu.sash.flprdcrds.json;

import java.util.List;

public class Translation {
    /**
     * domains : ["string"]
     * grammaticalFeatures : [{"text":"string","type":"string"}]
     * language : string
     * notes : [{"id":"string","text":"string","type":"string"}]
     * regions : ["string"]
     * registers : ["string"]
     * text : string
     */

    private String language;
    private String text;
    private List<String> domains;
    private List<Notes> grammaticalFeatures;
    private List<Notes> notes;
    private List<String> regions;
    private List<String> registers;


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    public List<Notes> getGrammaticalFeatures() {
        return grammaticalFeatures;
    }

    public void setGrammaticalFeatures(List<Notes> grammaticalFeatures) {
        this.grammaticalFeatures = grammaticalFeatures;
    }

    public List<Notes> getNotes() {
        return notes;
    }

    public void setNotes(List<Notes> notes) {
        this.notes = notes;
    }

    public List<String> getRegions() {
        return regions;
    }

    public void setRegions(List<String> regions) {
        this.regions = regions;
    }

    public List<String> getRegisters() {
        return registers;
    }

    public void setRegisters(List<String> registers) {
        this.registers = registers;
    }
}