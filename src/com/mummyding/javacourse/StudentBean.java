package com.mummyding.javacourse;

import java.util.Scanner;

/**
 * Created by mummyding on 15-11-25.
 */
public class StudentBean {
    private String studentID;
    private String studentName;
    private String birthDay;
    private String major;

    public String getStudentID() {
        return studentID;
    }

    public boolean setStudentID(String studentID) {
        this.studentID = studentID;
        return true;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public boolean isDataComplete(){
        if(studentID == null || studentName == null || birthDay == null || major == null){
            return false;
        }
        return true;
    }
    public void readData(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Input Student Info ,please! ");
        System.out.println("Student ID: ");
        setStudentID(sc.nextLine().trim());
        System.out.println("Student Name: ");
        setStudentName(sc.nextLine());
        System.out.println("Student BirthDay: ");
        setBirthDay(sc.nextLine());
        System.out.println("Student Major: ");
        setMajor(sc.nextLine());
    }
    @Override
    public String toString() {
        return getStudentID()+","+getStudentName()+","+getBirthDay()+","+getMajor();
    }

}
