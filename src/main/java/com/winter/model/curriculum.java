package com.winter.model;

public class curriculum {
    private String curriculumid;

    private String educationprogramid;

    private String courseid;

    private String coursecategoryid;

    private Byte semester;

    private Boolean isdegree;

    public String getCurriculumid() {
        return curriculumid;
    }

    public void setCurriculumid(String curriculumid) {
        this.curriculumid = curriculumid == null ? null : curriculumid.trim();
    }

    public String getEducationprogramid() {
        return educationprogramid;
    }

    public void setEducationprogramid(String educationprogramid) {
        this.educationprogramid = educationprogramid == null ? null : educationprogramid.trim();
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid == null ? null : courseid.trim();
    }

    public String getCoursecategoryid() {
        return coursecategoryid;
    }

    public void setCoursecategoryid(String coursecategoryid) {
        this.coursecategoryid = coursecategoryid == null ? null : coursecategoryid.trim();
    }

    public Byte getSemester() {
        return semester;
    }

    public void setSemester(Byte semester) {
        this.semester = semester;
    }

    public Boolean getIsdegree() {
        return isdegree;
    }

    public void setIsdegree(Boolean isdegree) {
        this.isdegree = isdegree;
    }
}