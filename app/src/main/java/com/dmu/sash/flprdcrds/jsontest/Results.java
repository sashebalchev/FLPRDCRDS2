package com.dmu.sash.flprdcrds.jsontest;

import java.util.List;

class Results {
    /**
     * id : ace
     * language : en
     * lexicalEntries : [{"entries":[{"homographNumber":"100","senses":[{"definitions":["a playing card with a single spot on it, ranked as the highest card in its suit in most card games"],"examples":[{"text":"life had started dealing him aces again"},{"text":"the ace of diamonds"}],"id":"m_en_gbus0005680.006"},{"definitions":["a person who excels at a particular sport or other activity"],"examples":[{"text":"a motorcycle ace"}],"id":"m_en_gbus0005680.010","subsenses":[{"definitions":["a pilot who has shot down many enemy aircraft"],"examples":[{"text":"a Battle of Britain ace"}],"id":"m_en_gbus0005680.011"}]},{"definitions":["(in tennis and similar games) a service that an opponent is unable to return and thus wins a point"],"examples":[{"text":"Nadal banged down eight aces in the set"}],"id":"m_en_gbus0005680.013","subsenses":[{"definitions":["a hole in one"],"examples":[{"text":"his hole in one at the 15th was Senior's second ace as a professional"}],"id":"m_en_gbus0005680.014"}]}]},{"homographNumber":"200","senses":[{"definitions":["a person who has no sexual feelings or desires"],"examples":[{"text":"both asexual, they have managed to connect with other aces offline"}],"id":"m_en_gbus1190638.004"}]}],"language":"en","lexicalCategory":"Noun","pronunciations":[{"audioFile":"http://audio.oxforddictionaries.com/en/mp3/ace_1_gb_1_abbr.mp3","dialects":["British English"],"phoneticNotation":"IPA","phoneticSpelling":"eɪs"}],"text":"ace"},{"entries":[{"homographNumber":"101","senses":[{"definitions":["very good"],"examples":[{"text":"Ace! You've done it!"},{"text":"an ace swimmer"}],"id":"m_en_gbus0005680.016"}]},{"homographNumber":"201","senses":[{"definitions":["(of a person) having no sexual feelings or desires; asexual"],"examples":[{"text":"I didn't realize that I was ace for a long time"}],"id":"m_en_gbus1190638.006"}]}],"language":"en","lexicalCategory":"Adjective","pronunciations":[{"audioFile":"http://audio.oxforddictionaries.com/en/mp3/ace_1_gb_1_abbr.mp3","dialects":["British English"],"phoneticNotation":"IPA","phoneticSpelling":"eɪs"}],"text":"ace"},{"entries":[{"homographNumber":"102","senses":[{"definitions":["(in tennis and similar games) serve an ace against (an opponent)"],"examples":[{"text":"he can ace opponents with serves of no more than 62 mph"}],"id":"m_en_gbus0005680.020","subsenses":[{"definitions":["score an ace on (a hole) or with (a shot)"],"examples":[{"text":"there was a prize for the first player to ace the hole"}],"id":"m_en_gbus0005680.026"}]},{"definitions":["achieve high marks in (a test or exam)"],"examples":[{"text":"I aced my grammar test"}],"id":"m_en_gbus0005680.028","subsenses":[{"definitions":["outdo someone in a competitive situation"],"examples":[{"text":"the magazine won an award, acing out its rivals"}],"id":"m_en_gbus0005680.029"}]}]}],"language":"en","lexicalCategory":"Verb","pronunciations":[{"audioFile":"http://audio.oxforddictionaries.com/en/mp3/ace_1_gb_1_abbr.mp3","dialects":["British English"],"phoneticNotation":"IPA","phoneticSpelling":"eɪs"}],"text":"ace"}]
     * type : headword
     * word : ace
     */

    private String id;
    private String language;
    private String type;
    private String word;
    private List<LexicalEntries> lexicalEntries;

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

    public List<LexicalEntries> getLexicalEntries() {
        return lexicalEntries;
    }

    public void setLexicalEntries(List<LexicalEntries> lexicalEntries) {
        this.lexicalEntries = lexicalEntries;
    }
}
