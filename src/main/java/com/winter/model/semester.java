package com.winter.model;

public class semester {
    private String semesterid;

    private String semestername;

    public String getSemesterid() {
        return semesterid;
    }

    public void setSemesterid(String semesterid) {
        this.semesterid = semesterid == null ? null : semesterid.trim();
    }

    public String getSemestername() {
        return semestername;
    }

    public void setSemestername(String semestername) {
        this.semestername = semestername == null ? null : semestername.trim();
    }
}