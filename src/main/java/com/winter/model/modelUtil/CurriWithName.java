package com.winter.model.modelUtil;

import com.winter.model.curriculum;

public class CurriWithName extends curriculum {
    private String educationprogramname;

    private String coursename;

    private String coursecategoryname;

    private String degreeis;

    public String getDegreeis() {
        return degreeis;
    }

    public void setDegreeis(String degreeis) {
        this.degreeis = degreeis;
    }

    public String getEducationprogramname() {
        return educationprogramname;
    }

    public void setEducationprogramname(String educationprogramname) {
        this.educationprogramname = educationprogramname;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCoursecategoryname() {
        return coursecategoryname;
    }

    public void setCoursecategoryname(String coursecategoryname) {
        this.coursecategoryname = coursecategoryname;
    }
}
