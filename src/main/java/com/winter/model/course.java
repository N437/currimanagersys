package com.winter.model;

import java.util.Date;

public class course {
    private String courseid;

    private String coursenum;

    private String coursename;

    private String enname;

    private String coursecategoryid;

    private Byte score;

    private Byte chour;

    private Byte lhour;

    private Byte tchour;

    private Short tlhour;

    private Date createtime;

    private String test1;

    private String test2;

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid == null ? null : courseid.trim();
    }

    public String getCoursenum() {
        return coursenum;
    }

    public void setCoursenum(String coursenum) {
        this.coursenum = coursenum == null ? null : coursenum.trim();
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename == null ? null : coursename.trim();
    }

    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname == null ? null : enname.trim();
    }

    public String getCoursecategoryid() {
        return coursecategoryid;
    }

    public void setCoursecategoryid(String coursecategoryid) {
        this.coursecategoryid = coursecategoryid == null ? null : coursecategoryid.trim();
    }

    public Byte getScore() {
        return score;
    }

    public void setScore(Byte score) {
        this.score = score;
    }

    public Byte getChour() {
        return chour;
    }

    public void setChour(Byte chour) {
        this.chour = chour;
    }

    public Byte getLhour() {
        return lhour;
    }

    public void setLhour(Byte lhour) {
        this.lhour = lhour;
    }

    public Byte getTchour() {
        return tchour;
    }

    public void setTchour(Byte tchour) {
        this.tchour = tchour;
    }

    public Short getTlhour() {
        return tlhour;
    }

    public void setTlhour(Short tlhour) {
        this.tlhour = tlhour;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getTest1() {
        return test1;
    }

    public void setTest1(String test1) {
        this.test1 = test1 == null ? null : test1.trim();
    }

    public String getTest2() {
        return test2;
    }

    public void setTest2(String test2) {
        this.test2 = test2 == null ? null : test2.trim();
    }
}