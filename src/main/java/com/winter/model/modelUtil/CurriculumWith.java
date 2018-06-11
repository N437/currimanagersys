package com.winter.model.modelUtil;

import com.winter.model.curriculum;

public class CurriculumWith extends curriculum {
    private String programname;

    private String educationprogramname;

    private String coursecategoryname;

    private String coursename;

    public String getProgramname() {
        return programname;
    }

    public void setProgramname(String programname) {
        this.programname = programname;
    }

    public String getEducationprogramname() {
        return educationprogramname;
    }

    public void setEducationprogramname(String educationprogramname) {
        this.educationprogramname = educationprogramname;
    }

    public String getCoursecategoryname() {
        return coursecategoryname;
    }

    public void setCoursecategoryname(String coursecategoryname) {
        this.coursecategoryname = coursecategoryname;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }
}
