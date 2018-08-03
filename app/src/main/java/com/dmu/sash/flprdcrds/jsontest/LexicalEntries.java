package com.dmu.sash.flprdcrds.jsontest;

import java.util.List;

class LexicalEntries {
    /**
     * entries : [{"homographNumber":"100","senses":[{"definitions":["a playing card with a single spot on it, ranked as the highest card in its suit in most card games"],"examples":[{"text":"life had started dealing him aces again"},{"text":"the ace of diamonds"}],"id":"m_en_gbus0005680.006"},{"definitions":["a person who excels at a particular sport or other activity"],"examples":[{"text":"a motorcycle ace"}],"id":"m_en_gbus0005680.010","subsenses":[{"definitions":["a pilot who has shot down many enemy aircraft"],"examples":[{"text":"a Battle of Britain ace"}],"id":"m_en_gbus0005680.011"}]},{"definitions":["(in tennis and similar games) a service that an opponent is unable to return and thus wins a point"],"examples":[{"text":"Nadal banged down eight aces in the set"}],"id":"m_en_gbus0005680.013","subsenses":[{"definitions":["a hole in one"],"examples":[{"text":"his hole in one at the 15th was Senior's second ace as a professional"}],"id":"m_en_gbus0005680.014"}]}]},{"homographNumber":"200","senses":[{"definitions":["a person who has no sexual feelings or desires"],"examples":[{"text":"both asexual, they have managed to connect with other aces offline"}],"id":"m_en_gbus1190638.004"}]}]
     * language : en
     * lexicalCategory : Noun
     * pronunciations : [{"audioFile":"http://audio.oxforddictionaries.com/en/mp3/ace_1_gb_1_abbr.mp3","dialects":["British English"],"phoneticNotation":"IPA","phoneticSpelling":"eɪs"}]
     * text : ace
     */

    private String language;
    private String lexicalCategory;
    private String text;
    private List<Entries> entries;
    private List<Pronunciations> pronunciations;

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

    public List<Entries> getEntries() {
        return entries;
    }

    public void setEntries(List<Entries> entries) {
        this.entries = entries;
    }

    public List<Pronunciations> getPronunciations() {
        return pronunciations;
    }

    public void setPronunciations(List<Pronunciations> pronunciations) {
        this.pronunciations = pronunciations;
    }
}
