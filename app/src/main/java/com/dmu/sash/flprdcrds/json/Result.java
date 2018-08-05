package com.dmu.sash.flprdcrds.json;

import java.util.List;

public class Result {
    /**
     * id : string
     * language : string
     * lexicalEntries : [{"derivativeOf":[{"domains":["string"],"id":"string","language":"string","regions":["string"],"registers":["string"],"text":"string"}],"derivatives":[{"domains":["string"],"id":"string","language":"string","regions":["string"],"registers":["string"],"text":"string"}],"entries":[{"etymologies":["string"],"grammaticalFeatures":[{"text":"string","type":"string"}],"homographNumber":"string","notes":[{"id":"string","text":"string","type":"string"}],"pronunciations":[{"audioFile":"string","dialects":["string"],"phoneticNotation":"string","phoneticSpelling":"string","regions":["string"]}],"senses":[{"crossReferenceMarkers":["string"],"crossReferences":[{"id":"string","text":"string","type":"string"}],"definitions":["string"],"domains":["string"],"examples":[{"definitions":["string"],"domains":["string"],"notes":[{"id":"string","text":"string","type":"string"}],"regions":["string"],"registers":["string"],"senseIds":["string"],"text":"string","translations":[{"domains":["string"],"grammaticalFeatures":[{"text":"string","type":"string"}],"language":"string","notes":[{"id":"string","text":"string","type":"string"}],"regions":["string"],"registers":["string"],"text":"string"}]}],"id":"string","notes":[{"id":"string","text":"string","type":"string"}],"pronunciations":[{"audioFile":"string","dialects":["string"],"phoneticNotation":"string","phoneticSpelling":"string","regions":["string"]}],"regions":["string"],"registers":["string"],"short_definitions":["string"],"subsenses":[{}],"thesaurusLinks":[{"entry_id":"string","sense_id":"string"}],"translations":[{"domains":["string"],"grammaticalFeatures":[{"text":"string","type":"string"}],"language":"string","notes":[{"id":"string","text":"string","type":"string"}],"regions":["string"],"registers":["string"],"text":"string"}],"variantForms":[{"regions":["string"],"text":"string"}]}],"variantForms":[{"regions":["string"],"text":"string"}]}],"grammaticalFeatures":[{"text":"string","type":"string"}],"language":"string","lexicalCategory":"string","notes":[{"id":"string","text":"string","type":"string"}],"pronunciations":[{"audioFile":"string","dialects":["string"],"phoneticNotation":"string","phoneticSpelling":"string","regions":["string"]}],"text":"string","variantForms":[{"regions":["string"],"text":"string"}]}]
     * pronunciations : [{"audioFile":"string","dialects":["string"],"phoneticNotation":"string","phoneticSpelling":"string","regions":["string"]}]
     * type : string
     * word : string
     */

    private String id;
    private String language;
    private String type;
    private String word;
    private List<LexicalEntry> lexicalEntries;
    private List<Pronunciation> pronunciations;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<LexicalEntry> getLexicalEntries() {
        return lexicalEntries;
    }

    public void setLexicalEntries(List<LexicalEntry> lexicalEntries) {
        this.lexicalEntries = lexicalEntries;
    }

    public List<Pronunciation> getPronunciations() {
        return pronunciations;
    }

    public void setPronunciations(List<Pronunciation> pronunciations) {
        this.pronunciations = pronunciations;
    }
}
