package com.example.ashley.assignment1;

public class StudentListClass {
    private int studentNumber = 0;

    private  String firstName;

    private  String lastName;

    public StudentListClass(Integer number, String fname, String lname){
        studentNumber = number;
        firstName = fname;
        lastName = lname;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
