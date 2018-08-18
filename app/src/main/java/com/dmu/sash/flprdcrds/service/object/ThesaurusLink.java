package com.dmu.sash.flprdcrds.service.object;

public class ThesaurusLink {
    /**
     * entry_id : string
     * sense_id : string
     */

    private String entry_id;
    private String sense_id;

    public String getEntry_id() {
        return entry_id;
    }

    public void setEntry_id(String entry_id) {
        this.entry_id = entry_id;
    }

    public String getSense_id() {
        return sense_id;
    }

    public void setSense_id(String sense_id) {
        this.sense_id = sense_id;
    }
}
