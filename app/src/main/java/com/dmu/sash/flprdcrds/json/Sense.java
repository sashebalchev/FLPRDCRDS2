package com.dmu.sash.flprdcrds.json;

import java.util.List;

public class Sense {
    /**
     * definitions : ["a playing card with a single spot on it, ranked as the highest card in its suit in most card games"]
     * domains : ["Cards"]
     * examples : [{"registers":["figurative"],"text":"life had started dealing him aces again"},{"text":"the ace of diamonds"}]
     * id : m_en_gbus0005680.006
     * short_definitions : ["playing card with single spot on it, ranked as highest card in its suit in most card games"]
     * registers : ["informal"]
     * subsenses : [{"definitions":["a pilot who has shot down many enemy aircraft"],"domains":["Air Force"],"examples":[{"text":"a Battle of Britain ace"}],"id":"m_en_gbus0005680.011","short_definitions":["pilot who has shot down many enemy aircraft"]}]
     * thesaurusLinks : [{"entry_id":"ace","sense_id":"t_en_gb0000173.001"}]
     */

    private String id;
    private List<String> definitions;
    private List<String> domains;
    private List<Example> examples;
    private List<String> short_definitions;
    private List<String> registers;
    private List<Subsense> subsenses;
    private List<ThesaurusLink> thesaurusLinks;

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

    public List<Example> getExamples() {
        return examples;
    }

    public void setExamples(List<Example> examples) {
        this.examples = examples;
    }

    public List<String> getShort_definitions() {
        return short_definitions;
    }

    public void setShort_definitions(List<String> short_definitions) {
        this.short_definitions = short_definitions;
    }

    public List<String> getRegisters() {
        return registers;
    }

    public void setRegisters(List<String> registers) {
        this.registers = registers;
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
}
