package com.dmu.sash.flprdcrds.service.searchobjects;

public class Metadata {
    /**
     * provider : Oxford University Press
     * sourceLanguage : en
     * limit : 5000
     * offset : 0
     * total : 14
     */

    private String provider;
    private String sourceLanguage;
    private int limit;
    private int offset;
    private int total;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
