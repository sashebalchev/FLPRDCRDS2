package com.dmu.sash.flprdcrds.jsontest;

import java.util.List;

class Senses {
    /**
     * definitions : ["a playing card with a single spot on it, ranked as the highest card in its suit in most card games"]
     * examples : [{"text":"life had started dealing him aces again"},{"text":"the ace of diamonds"}]
     * id : m_en_gbus0005680.006
     * subsenses : [{"definitions":["a pilot who has shot down many enemy aircraft"],"examples":[{"text":"a Battle of Britain ace"}],"id":"m_en_gbus0005680.011"}]
     */

    private String id;
    private List<String> definitions;
    private List<Examples> examples;
    private List<Subsenses> subsenses;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<String> definitions) {
        this.definitions = definitions;
    }

    public List<Examples> getExamples() {
        return examples;
    }

    public void setExamples(List<Examples> examples) {
        this.examples = examples;
    }

    public List<Subsenses> getSubsenses() {
        return subsenses;
    }

    public void setSubsenses(List<Subsenses> subsenses) {
        this.subsenses = subsenses;
    }
}
