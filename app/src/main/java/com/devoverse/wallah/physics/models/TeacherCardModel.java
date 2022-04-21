package com.devoverse.wallah.physics.models;

public class TeacherCardModel {
    private int teacherID;
    private String teacherName;
    private String teacherSubject;
    private String teacherQualification;
    private String teacherImageURL;

    public TeacherCardModel(int teacherID, String teacherName, String teacherSubject, String teacherQualification, String teacherImageURL) {
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.teacherSubject = teacherSubject;
        this.teacherQualification = teacherQualification;
        this.teacherImageURL = teacherImageURL;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherSubject() {
        return teacherSubject;
    }

    public void setTeacherSubject(String teacherSubject) {
        this.teacherSubject = teacherSubject;
    }

    public String getTeacherQualification() {
        return teacherQualification;
    }

    public void setTeacherQualification(String teacherQualification) {
        this.teacherQualification = teacherQualification;
    }

    public String getTeacherImageURL() {
        return teacherImageURL;
    }

    public void setTeacherImageURL(String teacherImageURL) {
        this.teacherImageURL = teacherImageURL;
    }
}
