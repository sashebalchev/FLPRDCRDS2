package com.dmu.sash.flprdcrds.jsontest;

import java.util.List;

class Entries {
    /**
     * homographNumber : 100
     * senses : [{"definitions":["a playing card with a single spot on it, ranked as the highest card in its suit in most card games"],"examples":[{"text":"life had started dealing him aces again"},{"text":"the ace of diamonds"}],"id":"m_en_gbus0005680.006"},{"definitions":["a person who excels at a particular sport or other activity"],"examples":[{"text":"a motorcycle ace"}],"id":"m_en_gbus0005680.010","subsenses":[{"definitions":["a pilot who has shot down many enemy aircraft"],"examples":[{"text":"a Battle of Britain ace"}],"id":"m_en_gbus0005680.011"}]},{"definitions":["(in tennis and similar games) a service that an opponent is unable to return and thus wins a point"],"examples":[{"text":"Nadal banged down eight aces in the set"}],"id":"m_en_gbus0005680.013","subsenses":[{"definitions":["a hole in one"],"examples":[{"text":"his hole in one at the 15th was Senior's second ace as a professional"}],"id":"m_en_gbus0005680.014"}]}]
     */

    private String homographNumber;
    private List<Senses> senses;

    public String getHomographNumber() {
        return homographNumber;
    }

    public void setHomographNumber(String homographNumber) {
        this.homographNumber = homographNumber;
    }

    public List<Senses> getSenses() {
        return senses;
    }

    public void setSenses(List<Senses> senses) {
        this.senses = senses;
    }
}
