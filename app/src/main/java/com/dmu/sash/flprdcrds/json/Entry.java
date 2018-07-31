package com.dmu.sash.flprdcrds.json;

import java.util.List;

public class Entry {
    /**
     * etymologies : ["Middle English (denoting the \u2018one\u2019 on dice): via Old French from Latin as \u2018unity, a unit\u2019"]
     * grammaticalFeatures : [{"text":"Singular","type":"Number"}]
     * homographNumber : 100
     * senses : [{"definitions":["a playing card with a single spot on it, ranked as the highest card in its suit in most card games"],"domains":["Cards"],"examples":[{"registers":["figurative"],"text":"life had started dealing him aces again"},{"text":"the ace of diamonds"}],"id":"m_en_gbus0005680.006","short_definitions":["playing card with single spot on it, ranked as highest card in its suit in most card games"]},{"definitions":["a person who excels at a particular sport or other activity"],"domains":["Sport"],"examples":[{"text":"a motorcycle ace"}],"id":"m_en_gbus0005680.010","registers":["informal"],"short_definitions":["person who excels at particular sport or other activity"],"subsenses":[{"definitions":["a pilot who has shot down many enemy aircraft"],"domains":["Air Force"],"examples":[{"text":"a Battle of Britain ace"}],"id":"m_en_gbus0005680.011","short_definitions":["pilot who has shot down many enemy aircraft"]}],"thesaurusLinks":[{"entry_id":"ace","sense_id":"t_en_gb0000173.001"}]},{"definitions":["(in tennis and similar games) a service that an opponent is unable to return and thus wins a point"],"domains":["Tennis"],"examples":[{"text":"Nadal banged down eight aces in the set"}],"id":"m_en_gbus0005680.013","short_definitions":["(in tennis and similar games) service that opponent is unable to return and thus wins point"],"subsenses":[{"definitions":["a hole in one"],"domains":["Golf"],"examples":[{"text":"his hole in one at the 15th was Senior's second ace as a professional"}],"id":"m_en_gbus0005680.014","registers":["informal"],"short_definitions":["hole in one"]}]}]
     */

    private String homographNumber;
    private List<String> etymologies;
    private List<GrammaticalFeature> grammaticalFeatures;
    private List<Sense> senses;

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

    public List<Sense> getSenses() {
        return senses;
    }

    public void setSenses(List<Sense> senses) {
        this.senses = senses;
    }
}
