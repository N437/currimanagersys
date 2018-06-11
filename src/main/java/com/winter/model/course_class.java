package com.winter.model;

public class course_class {
    private String courseclassid;

    private String semesterid;

    private String courseid;

    private Integer maxclasssize;

    private String teacherid;

    public String getCourseclassid() {
        return courseclassid;
    }

    public void setCourseclassid(String courseclassid) {
        this.courseclassid = courseclassid == null ? null : courseclassid.trim();
    }

    public String getSemesterid() {
        return semesterid;
    }

    public void setSemesterid(String semesterid) {
        this.semesterid = semesterid == null ? null : semesterid.trim();
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid == null ? null : courseid.trim();
    }

    public Integer getMaxclasssize() {
        return maxclasssize;
    }

    public void setMaxclasssize(Integer maxclasssize) {
        this.maxclasssize = maxclasssize;
    }

    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid == null ? null : teacherid.trim();
    }
}