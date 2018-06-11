package com.winter.model;

public class plan_study_course {
    private String planstudycourseid;

    private String courseid;

    private String semesterid;

    private String studentid;

    public String getPlanstudycourseid() {
        return planstudycourseid;
    }

    public void setPlanstudycourseid(String planstudycourseid) {
        this.planstudycourseid = planstudycourseid == null ? null : planstudycourseid.trim();
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid == null ? null : courseid.trim();
    }

    public String getSemesterid() {
        return semesterid;
    }

    public void setSemesterid(String semesterid) {
        this.semesterid = semesterid == null ? null : semesterid.trim();
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid == null ? null : studentid.trim();
    }
}