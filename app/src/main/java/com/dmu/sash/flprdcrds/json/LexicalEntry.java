package com.dmu.sash.flprdcrds.json;

import java.util.List;

public class LexicalEntry {
    /**
     * entries : [{"etymologies":["Middle English (denoting the \u2018one\u2019 on dice): via Old French from Latin as \u2018unity, a unit\u2019"],"grammaticalFeatures":[{"text":"Singular","type":"Number"}],"homographNumber":"100","senses":[{"definitions":["a playing card with a single spot on it, ranked as the highest card in its suit in most card games"],"domains":["Cards"],"examples":[{"registers":["figurative"],"text":"life had started dealing him aces again"},{"text":"the ace of diamonds"}],"id":"m_en_gbus0005680.006","short_definitions":["playing card with single spot on it, ranked as highest card in its suit in most card games"]},{"definitions":["a person who excels at a particular sport or other activity"],"domains":["Sport"],"examples":[{"text":"a motorcycle ace"}],"id":"m_en_gbus0005680.010","registers":["informal"],"short_definitions":["person who excels at particular sport or other activity"],"subsenses":[{"definitions":["a pilot who has shot down many enemy aircraft"],"domains":["Air Force"],"examples":[{"text":"a Battle of Britain ace"}],"id":"m_en_gbus0005680.011","short_definitions":["pilot who has shot down many enemy aircraft"]}],"thesaurusLinks":[{"entry_id":"ace","sense_id":"t_en_gb0000173.001"}]},{"definitions":["(in tennis and similar games) a service that an opponent is unable to return and thus wins a point"],"domains":["Tennis"],"examples":[{"text":"Nadal banged down eight aces in the set"}],"id":"m_en_gbus0005680.013","short_definitions":["(in tennis and similar games) service that opponent is unable to return and thus wins point"],"subsenses":[{"definitions":["a hole in one"],"domains":["Golf"],"examples":[{"text":"his hole in one at the 15th was Senior's second ace as a professional"}],"id":"m_en_gbus0005680.014","registers":["informal"],"short_definitions":["hole in one"]}]}]},{"etymologies":["early 21st century: abbreviation of asexual, with alteration of spelling on the model of ace"],"grammaticalFeatures":[{"text":"Singular","type":"Number"}],"homographNumber":"200","senses":[{"definitions":["a person who has no sexual feelings or desires"],"domains":["Sex"],"examples":[{"text":"both asexual, they have managed to connect with other aces offline"}],"id":"m_en_gbus1190638.004","short_definitions":["asexual person"]}]}]
     * language : en
     * lexicalCategory : Noun
     * pronunciations : [{"audioFile":"http://audio.oxforddictionaries.com/en/mp3/ace_1_gb_1_abbr.mp3","dialects":["British English"],"phoneticNotation":"IPA","phoneticSpelling":"eɪs"}]
     * text : ace
     */

    private String language;
    private String lexicalCategory;
    private String text;
    private List<Entry> entries;
    private List<Pronunciation> pronunciations;

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

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public List<Pronunciation> getPronunciations() {
        return pronunciations;
    }

    public void setPronunciations(List<Pronunciation> pronunciations) {
        this.pronunciations = pronunciations;
    }
}
