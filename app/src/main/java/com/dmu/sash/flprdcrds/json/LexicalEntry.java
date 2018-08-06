package com.dmu.sash.flprdcrds.json;

import java.util.List;

public class LexicalEntry {
    /**
     * derivativeOf : [{"domains":["string"],"id":"string","language":"string","regions":["string"],"registers":["string"],"text":"string"}]
     * derivatives : [{"domains":["string"],"id":"string","language":"string","regions":["string"],"registers":["string"],"text":"string"}]
     * entries : [{"etymologies":["string"],"grammaticalFeatures":[{"text":"string","type":"string"}],"homographNumber":"string","notes":[{"id":"string","text":"string","type":"string"}],"pronunciations":[{"audioFile":"string","dialects":["string"],"phoneticNotation":"string","phoneticSpelling":"string","regions":["string"]}],"senses":[{"crossReferenceMarkers":["string"],"crossReferences":[{"id":"string","text":"string","type":"string"}],"definitions":["string"],"domains":["string"],"examples":[{"definitions":["string"],"domains":["string"],"notes":[{"id":"string","text":"string","type":"string"}],"regions":["string"],"registers":["string"],"senseIds":["string"],"text":"string","translations":[{"domains":["string"],"grammaticalFeatures":[{"text":"string","type":"string"}],"language":"string","notes":[{"id":"string","text":"string","type":"string"}],"regions":["string"],"registers":["string"],"text":"string"}]}],"id":"string","notes":[{"id":"string","text":"string","type":"string"}],"pronunciations":[{"audioFile":"string","dialects":["string"],"phoneticNotation":"string","phoneticSpelling":"string","regions":["string"]}],"regions":["string"],"registers":["string"],"short_definitions":["string"],"subsenses":[{}],"thesaurusLinks":[{"entry_id":"string","sense_id":"string"}],"translations":[{"domains":["string"],"grammaticalFeatures":[{"text":"string","type":"string"}],"language":"string","notes":[{"id":"string","text":"string","type":"string"}],"regions":["string"],"registers":["string"],"text":"string"}],"variantForms":[{"regions":["string"],"text":"string"}]}],"variantForms":[{"regions":["string"],"text":"string"}]}]
     * grammaticalFeatures : [{"text":"string","type":"string"}]
     * language : string
     * lexicalCategory : string
     * notes : [{"id":"string","text":"string","type":"string"}]
     * pronunciations : [{"audioFile":"string","dialects":["string"],"phoneticNotation":"string","phoneticSpelling":"string","regions":["string"]}]
     * text : string
     * variantForms : [{"regions":["string"],"text":"string"}]
     */

    private String language;
    private String lexicalCategory;
    private String text;
    private List<Derivative> derivativeOf;
    private List<Derivative> derivatives;
    private List<Entry> entries;
    private List<Note> grammaticalFeatures;
    private List<Note> notes;
    private List<Pronunciation> pronunciations;
    private List<VariantForm> variantForms;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLexicalCategory() {
        return lexicalCategory;
    }

    public void setLexicalCategory(String lexicalCategory) {
        this.lexicalCategory = lexicalCategory;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Derivative> getDerivativeOf() {
        return derivativeOf;
    }

    public void setDerivativeOf(List<Derivative> derivativeOf) {
        this.derivativeOf = derivativeOf;
    }

    public List<Derivative> getDerivatives() {
        return derivatives;
    }

    public void setDerivatives(List<Derivative> derivatives) {
        this.derivatives = derivatives;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
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

    public List<Pronunciation> getPronunciations() {
        return pronunciations;
    }

    public void setPronunciations(List<Pronunciation> pronunciations) {
        this.pronunciations = pronunciations;
    }

    public List<VariantForm> getVariantForms() {
        return variantForms;
    }

    public void setVariantForms(List<VariantForm> variantForms) {
        this.variantForms = variantForms;
    }
}
