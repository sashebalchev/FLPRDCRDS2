package com.dmu.sash.flprdcrds.service.objects;

import java.util.List;

public class VariantForm {
    /**
     * regions : ["string"]
     * text : string
     */

    private String text;
    private List<String> regions;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getRegions() {
        return regions;
    }

    public void setRegions(List<String> regions) {
        this.regions = regions;
    }
}