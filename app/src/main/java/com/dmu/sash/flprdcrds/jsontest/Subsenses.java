package com.dmu.sash.flprdcrds.jsontest;

import java.util.List;

class Subsenses {
    /**
     * definitions : ["a pilot who has shot down many enemy aircraft"]
     * examples : [{"text":"a Battle of Britain ace"}]
     * id : m_en_gbus0005680.011
     */

    private String id;
    private List<String> definitions;
    private List<ExamplesX> examples;

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

    public List<ExamplesX> getExamples() {
        return examples;
    }

    public void setExamples(List<ExamplesX> examples) {
        this.examples = examples;
    }
}
