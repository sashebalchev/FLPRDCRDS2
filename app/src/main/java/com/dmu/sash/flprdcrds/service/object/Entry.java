package com.dmu.sash.flprdcrds.service.object;

import java.util.List;

public class Entry {
    /**
     * etymologies : ["string"]
     * grammaticalFeatures : [{"text":"string","type":"string"}]
     * homographNumber : string
     * notes : [{"id":"string","text":"string","type":"string"}]
     * pronunciations : [{"audioFile":"string","dialects":["string"],"phoneticNotation":"string","phoneticSpelling":"string","regions":["string"]}]
     * senses : [{"crossReferenceMarkers":["string"],"crossReferences":[{"id":"string","text":"string","type":"string"}],"definitions":["string"],"domains":["string"],"examples":[{"definitions":["string"],"domains":["string"],"notes":[{"id":"string","text":"string","type":"string"}],"regions":["string"],"registers":["string"],"senseIds":["string"],"text":"string","translations":[{"domains":["string"],"grammaticalFeatures":[{"text":"string","type":"string"}],"language":"string","notes":[{"id":"string","text":"string","type":"string"}],"regions":["string"],"registers":["string"],"text":"string"}]}],"id":"string","notes":[{"id":"string","text":"string","type":"string"}],"pronunciations":[{"audioFile":"string","dialects":["string"],"phoneticNotation":"string","phoneticSpelling":"string","regions":["string"]}],"regions":["string"],"registers":["string"],"short_definitions":["string"],"subsenses":[{}],"thesaurusLinks":[{"entry_id":"string","sense_id":"string"}],"translations":[{"domains":["string"],"grammaticalFeatures":[{"text":"string","type":"string"}],"language":"string","notes":[{"id":"string","text":"string","type":"string"}],"regions":["string"],"registers":["string"],"text":"string"}],"variantForms":[{"regions":["string"],"text":"string"}]}]
     * variantForms : [{"regions":["string"],"text":"string"}]
     */

    private String homographNumber;
    private List<String> etymologies;
    private List<GrammaticalFeature> grammaticalFeatures;
    private List<Note> notes;
    private List<Pronunciation> pronunciations;
    private List<Sense> senses;
    private List<VariantForm> variantForms;

    public String getHomographNumber() {
        return homographNumber;
    }

    public void setHomographNumber(String homographNumber) {
        this.homographNumber = homographNumber;
    }

    public List<String> getEtymologies() {
        return etymologies;
    }

    public void setEtymologies(List<String> etymologies) {
        this.etymologies = etymologies;
    }

    public List<GrammaticalFeature> getGrammaticalFeatures() {
        return grammaticalFeatures;
    }

    public void setGrammaticalFeatures(List<GrammaticalFeature> grammaticalFeatures) {
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

    public List<Sense> getSenses() {
        return senses;
    }

    public void setSenses(List<Sense> senses) {
        this.senses = senses;
    }

    public List<VariantForm> getVariantForms() {
        return variantForms;
    }

    public void setVariantForms(List<VariantForm> variantForms) {
        this.variantForms = variantForms;
    }
}
