package com.winter.model.modelUtil;

import com.winter.model.resource;

public class ResWithCourse extends resource {
    private String coursename;

    private String majorname;

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getMajorname() {
        return majorname;
    }

    public void setMajorname(String majorname) {
        this.majorname = majorname;
    }
}
