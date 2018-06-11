package com.winter.model;

public class teacher {
    private String teacherid;

    private String teachertcode;

    private String teachername;

    private String teachergender;

    private String teacherdegree;

    private String teachertitle;

    private String teacherintro;

    private String teacherphoto;

    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid == null ? null : teacherid.trim();
    }

    public String getTeachertcode() {
        return teachertcode;
    }

    public void setTeachertcode(String teachertcode) {
        this.teachertcode = teachertcode == null ? null : teachertcode.trim();
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername == null ? null : teachername.trim();
    }

    public String getTeachergender() {
        return teachergender;
    }

    public void setTeachergender(String teachergender) {
        this.teachergender = teachergender == null ? null : teachergender.trim();
    }

    public String getTeacherdegree() {
        return teacherdegree;
    }

    public void setTeacherdegree(String teacherdegree) {
        this.teacherdegree = teacherdegree == null ? null : teacherdegree.trim();
    }

    public String getTeachertitle() {
        return teachertitle;
    }

    public void setTeachertitle(String teachertitle) {
        this.teachertitle = teachertitle == null ? null : teachertitle.trim();
    }

    public String getTeacherintro() {
        return teacherintro;
    }

    public void setTeacherintro(String teacherintro) {
        this.teacherintro = teacherintro == null ? null : teacherintro.trim();
    }

    public String getTeacherphoto() {
        return teacherphoto;
    }

    public void setTeacherphoto(String teacherphoto) {
        this.teacherphoto = teacherphoto == null ? null : teacherphoto.trim();
    }
}