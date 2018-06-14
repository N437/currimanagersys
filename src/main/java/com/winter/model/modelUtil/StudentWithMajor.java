package com.winter.model.modelUtil;

import com.winter.model.student;

public class StudentWithMajor extends student {

    private String classname;

    private String majorname;

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getMajorname() {
        return majorname;
    }

    public void setMajorname(String majorname) {
        this.majorname = majorname;
    }
}
