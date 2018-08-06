package com.dmu.sash.flprdcrds.json;

import java.util.List;

public class Example {
    /**
     * definitions : ["string"]
     * domains : ["string"]
     * notes : [{"id":"string","text":"string","type":"string"}]
     * regions : ["string"]
     * registers : ["string"]
     * senseIds : ["string"]
     * text : string
     * translations : [{"domains":["string"],"grammaticalFeatures":[{"text":"string","type":"string"}],"language":"string","notes":[{"id":"string","text":"string","type":"string"}],"regions":["string"],"registers":["string"],"text":"string"}]
     */

    private String text;
    private List<String> definitions;
    private List<String> domains;
    private List<Note> notes;
    private List<String> regions;
    private List<String> registers;
    private List<String> senseIds;
    private List<Translation> translations;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<String> definitions) {
        this.definitions = definitions;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
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

    public List<String> getSenseIds() {
        return senseIds;
    }

    public void setSenseIds(List<String> senseIds) {
        this.senseIds = senseIds;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }
}
