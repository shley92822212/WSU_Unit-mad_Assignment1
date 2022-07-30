package com.example.ashley.assignment1;

public class ExamListClass {
    private  String title;


    private Integer studentId;
    private String date;
    private String time;
    private Integer examId;
    private String location;
    private boolean deleteMe;

    public ExamListClass(String examTitle, String Date, String Time, Integer StudentId, Integer sentId, String Location){
        title = examTitle;
        date = Date;
        time = Time;
        location = Location;
        examId = sentId;
        studentId = StudentId;
    }

    public void setDeleteMe(boolean deleteMe) {
        this.deleteMe = deleteMe;
    }

    public String getLocation() {
        return location;
    }

    public boolean getDeleteMe() {
        return deleteMe;
    }

    public Integer getId() {
        return examId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public Integer getExamId() {
        return examId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }
}
