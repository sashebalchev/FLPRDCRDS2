package com.dmu.sash.flprdcrds.json;

import java.util.List;

public class Subsense {
    /**
     * definitions : ["a pilot who has shot down many enemy aircraft"]
     * domains : ["Air Force"]
     * examples : [{"text":"a Battle of Britain ace"}]
     * id : m_en_gbus0005680.011
     * short_definitions : ["pilot who has shot down many enemy aircraft"]
     */

    private String id;
    private List<String> definitions;
    private List<String> domains;
    private List<ExampleX> examples;
    private List<String> short_definitions;

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

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    public List<ExampleX> getExamples() {
        return examples;
    }

    public void setExamples(List<ExampleX> examples) {
        this.examples = examples;
    }

    public List<String> getShort_definitions() {
        return short_definitions;
    }

    public void setShort_definitions(List<String> short_definitions) {
        this.short_definitions = short_definitions;
    }
}
