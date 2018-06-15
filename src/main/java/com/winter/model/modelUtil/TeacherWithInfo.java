package com.winter.model.modelUtil;

import com.winter.model.teacher;

public class TeacherWithInfo extends teacher {
    private String degreename;

    private String titlename;

    public String getDegreename() {
        return degreename;
    }

    public void setDegreename(String degreename) {
        this.degreename = degreename;
    }

    public String getTitlename() {
        return titlename;
    }

    public void setTitlename(String titlename) {
        this.titlename = titlename;
    }

}
