package com.winter.model;

public class class_student {
    private String classstudentid;

    private String courseclassid;

    private String studentid;

    private Byte gpascore;

    private Byte paperscore;

    private Byte practicescore;

    private Byte score;

    public String getClassstudentid() {
        return classstudentid;
    }

    public void setClassstudentid(String classstudentid) {
        this.classstudentid = classstudentid == null ? null : classstudentid.trim();
    }

    public String getCourseclassid() {
        return courseclassid;
    }

    public void setCourseclassid(String courseclassid) {
        this.courseclassid = courseclassid == null ? null : courseclassid.trim();
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid == null ? null : studentid.trim();
    }

    public Byte getGpascore() {
        return gpascore;
    }

    public void setGpascore(Byte gpascore) {
        this.gpascore = gpascore;
    }

    public Byte getPaperscore() {
        return paperscore;
    }

    public void setPaperscore(Byte paperscore) {
        this.paperscore = paperscore;
    }

    public Byte getPracticescore() {
        return practicescore;
    }

    public void setPracticescore(Byte practicescore) {
        this.practicescore = practicescore;
    }

    public Byte getScore() {
        return score;
    }

    public void setScore(Byte score) {
        this.score = score;
    }
}