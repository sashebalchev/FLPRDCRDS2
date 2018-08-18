package com.dmu.sash.flprdcrds.service.object;

import java.util.List;

public class Derivative {
    /**
     * domains : ["string"]
     * id : string
     * language : string
     * regions : ["string"]
     * registers : ["string"]
     * text : string
     */

    private String id;
    private String language;
    private String text;
    private List<String> domains;
    private List<String> regions;
    private List<String> registers;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    public List<String> getRegions() {
        return regions;
    }

    public void setRegions(List<String> regions) {
        this.regions = regions;
    }

    public List<String> getRegisters() {
        return registers;
    }

    public void setRegisters(List<String> registers) {
        this.registers = registers;
    }
}
