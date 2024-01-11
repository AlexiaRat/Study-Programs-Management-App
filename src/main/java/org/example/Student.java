package org.example;
import java.util.*;
public class Student {
    private String sName;
    private double average;
    private String studyProgram;
    private List<String> preferinteStudenti = new ArrayList<>();
    public Student (String sName, String studyProgram) {
        this.sName = sName;
        this.average = 0.0;
        this.studyProgram = studyProgram;
    }
    public String getStudyProgram() {
        return studyProgram;
    }
    public void setAverage ( double average){
        this.average = average;
    }
    public String getsName() {
        return sName;
    }
    public double getAverage() {
        return average;
    }
    public List<String> getPreferinteStudenti() {
    return preferinteStudenti;
    }


}
class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }
}

class UnknownCommandException extends Exception {
    public UnknownCommandException(String message) {
        super(message);
    }

}
class ExistentStudentException extends Exception{

    public ExistentStudentException(String message) {
        super(message);
    }

}



