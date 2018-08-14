package edu.phoenix.mbl402.week5appwr1030.Model;

public class Student{
    public static final long ID_INVALID = -1;
    private long id;
    private String firstName;
    private String lastName;
    private long classID;
    private String className;
    private Integer pointGrade;
    private String letterGrade;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getClassID() {
        return classID;
    }

    public void setClassID(long classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getPointGrade() {
        return pointGrade;
    }

    public void setPointGrade(Integer pointGrade) {
        this.pointGrade = pointGrade;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

    public Student() {id = ID_INVALID;

    }

    public String getFullName(){
        return firstName + " " + lastName;

    }
}
