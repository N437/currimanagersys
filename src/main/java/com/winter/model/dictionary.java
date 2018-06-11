package com.winter.model;

public class dictionary {
    private String dictionaryid;

    private String dictypeid;

    private String dictionaryname;

    public String getDictionaryid() {
        return dictionaryid;
    }

    public void setDictionaryid(String dictionaryid) {
        this.dictionaryid = dictionaryid == null ? null : dictionaryid.trim();
    }

    public String getDictypeid() {
        return dictypeid;
    }

    public void setDictypeid(String dictypeid) {
        this.dictypeid = dictypeid == null ? null : dictypeid.trim();
    }

    public String getDictionaryname() {
        return dictionaryname;
    }

    public void setDictionaryname(String dictionaryname) {
        this.dictionaryname = dictionaryname == null ? null : dictionaryname.trim();
    }
}