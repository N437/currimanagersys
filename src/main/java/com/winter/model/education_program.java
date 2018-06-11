package com.winter.model;

import java.util.Date;

public class education_program {
    private String educationprogramid;

    private String name;

    private String objective;

    private String specification;

    private Byte duration;

    private String degree;

    private int mincredit;

    private Short publishyear;

    private String majorid;

    private Date createtime;

    public String getEducationprogramid() {
        return educationprogramid;
    }

    public void setEducationprogramid(String educationprogramid) {
        this.educationprogramid = educationprogramid == null ? null : educationprogramid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective == null ? null : objective.trim();
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }

    public Byte getDuration() {
        return duration;
    }

    public void setDuration(Byte duration) {
        this.duration = duration;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    public int getMincredit() {
        return mincredit;
    }

    public void setMincredit(int mincredit) {
        this.mincredit = mincredit;
    }

    public Short getPublishyear() {
        return publishyear;
    }

    public void setPublishyear(Short publishyear) {
        this.publishyear = publishyear;
    }

    public String getMajorid() {
        return majorid;
    }

    public void setMajorid(String majorid) {
        this.majorid = majorid == null ? null : majorid.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}