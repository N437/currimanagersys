package com.winter.model;

public class classes {
    private String classid;

    private String classname;

    private Short enrollyear;

    private String majorid;

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid == null ? null : classid.trim();
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }

    public Short getEnrollyear() {
        return enrollyear;
    }

    public void setEnrollyear(Short enrollyear) {
        this.enrollyear = enrollyear;
    }

    public String getMajorid() {
        return majorid;
    }

    public void setMajorid(String majorid) {
        this.majorid = majorid == null ? null : majorid.trim();
    }
}