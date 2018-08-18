package com.dmu.sash.flprdcrds.service.objects;

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
    private List<Note> grammaticalFeatures;
    private List<Note> notes;
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

    public List<Note> getGrammaticalFeatures() {
        return grammaticalFeatures;
    }

    public void setGrammaticalFeatures(List<Note> grammaticalFeatures) {
        this.grammaticalFeatures = grammaticalFeatures;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
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