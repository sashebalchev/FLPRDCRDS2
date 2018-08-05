package com.dmu.sash.flprdcrds.json;

import java.util.List;

public class Sense {
    /**
     * crossReferenceMarkers : ["string"]
     * crossReferences : [{"id":"string","text":"string","type":"string"}]
     * definitions : ["string"]
     * domains : ["string"]
     * examples : [{"definitions":["string"],"domains":["string"],"notes":[{"id":"string","text":"string","type":"string"}],"regions":["string"],"registers":["string"],"senseIds":["string"],"text":"string","translations":[{"domains":["string"],"grammaticalFeatures":[{"text":"string","type":"string"}],"language":"string","notes":[{"id":"string","text":"string","type":"string"}],"regions":["string"],"registers":["string"],"text":"string"}]}]
     * id : string
     * notes : [{"id":"string","text":"string","type":"string"}]
     * pronunciations : [{"audioFile":"string","dialects":["string"],"phoneticNotation":"string","phoneticSpelling":"string","regions":["string"]}]
     * regions : ["string"]
     * registers : ["string"]
     * short_definitions : ["string"]
     * subsenses : [{}]
     * thesaurusLinks : [{"entry_id":"string","sense_id":"string"}]
     * translations : [{"domains":["string"],"grammaticalFeatures":[{"text":"string","type":"string"}],"language":"string","notes":[{"id":"string","text":"string","type":"string"}],"regions":["string"],"registers":["string"],"text":"string"}]
     * variantForms : [{"regions":["string"],"text":"string"}]
     */

    private String id;
    private List<String> crossReferenceMarkers;
    private List<Notes> crossReferences;
    private List<String> definitions;
    private List<String> domains;
    private List<Example> examples;
    private List<Notes> notes;
    private List<Pronunciation> pronunciations;
    private List<String> regions;
    private List<String> registers;
    private List<String> short_definitions;
    private List<Subsense> subsenses;
    private List<ThesaurusLink> thesaurusLinks;
    private List<Translation> translations;
    private List<VariantForm> variantForms;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getCrossReferenceMarkers() {
        return crossReferenceMarkers;
    }

    public void setCrossReferenceMarkers(List<String> crossReferenceMarkers) {
        this.crossReferenceMarkers = crossReferenceMarkers;
    }

    public List<Notes> getCrossReferences() {
        return crossReferences;
    }

    public void setCrossReferences(List<Notes> crossReferences) {
        this.crossReferences = crossReferences;
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

    public List<Example> getExamples() {
        return examples;
    }

    public void setExamples(List<Example> examples) {
        this.examples = examples;
    }

    public List<Notes> getNotes() {
        return notes;
    }

    public void setNotes(List<Notes> notes) {
        this.notes = notes;
    }

    public List<Pronunciation> getPronunciations() {
        return pronunciations;
    }

    public void setPronunciations(List<Pronunciation> pronunciations) {
        this.pronunciations = pronunciations;
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

    public List<String> getShort_definitions() {
        return short_definitions;
    }

    public void setShort_definitions(List<String> short_definitions) {
        this.short_definitions = short_definitions;
    }

    public List<Subsense> getSubsenses() {
        return subsenses;
    }

    public void setSubsenses(List<Subsense> subsenses) {
        this.subsenses = subsenses;
    }

    public List<ThesaurusLink> getThesaurusLinks() {
        return thesaurusLinks;
    }

    public void setThesaurusLinks(List<ThesaurusLink> thesaurusLinks) {
        this.thesaurusLinks = thesaurusLinks;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }

    public List<VariantForm> getVariantForms() {
        return variantForms;
    }

    public void setVariantForms(List<VariantForm> variantForms) {
        this.variantForms = variantForms;
    }
}
