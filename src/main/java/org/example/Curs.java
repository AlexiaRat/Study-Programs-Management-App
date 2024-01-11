package org.example;

import java.util.ArrayList;
import java.util.List;
public class Curs<E extends Student> {
    private String cName;
    private int maxCapacity;
    private String studyProgram;
    private List<E> registeredStudents;
    int actualCapacity;
    public Curs( String cName, int maxCapacity, String studyProgram) {
        this.cName = cName;
        this.maxCapacity = maxCapacity;
        this.studyProgram = studyProgram;
        this.registeredStudents = new ArrayList<>();
        actualCapacity = maxCapacity;
    }
    public String getcName() {

        return cName;
    }
    public void setcName(String cName) {
        this.cName = cName;
    }
    public int getMaxCapacity() {

        return maxCapacity;
    }
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
    public String getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(String studyProgram) {
        this.studyProgram = studyProgram;
    }
    public List<E> getRegisteredStudents() {

        return registeredStudents;
    }
    public void studentsregistration (E student) {
        if(registeredStudents.size() < maxCapacity) {
            registeredStudents.add(student);
        } else {
            System.out.println("full course");
        }
    }

    public E getFinalRegisteredStudent() {
        if (!registeredStudents.isEmpty()) {
            return registeredStudents.get(registeredStudents.size() - 1);
        } else {
            return null;
        }
    }
}
