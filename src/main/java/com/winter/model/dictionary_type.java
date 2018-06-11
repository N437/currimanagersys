package com.winter.model;

public class dictionary_type {
    private String dictypeid;

    private String dictypename;

    public String getDictypeid() {
        return dictypeid;
    }

    public void setDictypeid(String dictypeid) {
        this.dictypeid = dictypeid == null ? null : dictypeid.trim();
    }

    public String getDictypename() {
        return dictypename;
    }

    public void setDictypename(String dictypename) {
        this.dictypename = dictypename == null ? null : dictypename.trim();
    }
}