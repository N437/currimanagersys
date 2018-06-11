package com.winter.model;

import java.util.Date;

public class course_category {
    private String coursecategoryid;

    private String coursecategoryname;

    private Date createtime;

    public String getCoursecategoryid() {
        return coursecategoryid;
    }

    public void setCoursecategoryid(String coursecategoryid) {
        this.coursecategoryid = coursecategoryid == null ? null : coursecategoryid.trim();
    }

    public String getCoursecategoryname() {
        return coursecategoryname;
    }

    public void setCoursecategoryname(String coursecategoryname) {
        this.coursecategoryname = coursecategoryname == null ? null : coursecategoryname.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}