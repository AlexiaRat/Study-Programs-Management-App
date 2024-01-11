package org.example;

import java.util.*;
import java.io.*;
public class Secretariat {
    static List<Student> sDataBase = new ArrayList<>();
    private List<Curs<Student>> cDataBase = new ArrayList<>();
    private List<Curs<Student>> masterCDatabase = new ArrayList<>();
    private List<Curs<Student>> bachelorCDatabase = new ArrayList<>();



    public Secretariat() {
    }

    public void addStudent(String sName, String studyProgam, String outputFile) throws ExistentStudentException {
        if (sDataBase.stream().anyMatch(student -> student.getsName().equals(sName))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true))) {
                writer.write("***\n");

                writer.write("Student duplicat: " + sName + "\n");

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Student student = new Student(sName, studyProgam);
            if (studyProgam.equals("master")) {
                student = new MasterStudent(sName, studyProgam);
            } else if (studyProgam.equals("licenta")) {
                student = new BachelorStudent(sName, studyProgam);
            }
            sDataBase.add(student);
        }
    }

    public void addCourse(String studyProgram, String cName, int maxCapacity) throws InvalidCommandException {
        Curs<Student> course = new Curs<>(cName, maxCapacity, studyProgram);

        if ("master".equalsIgnoreCase(studyProgram)) {
            masterCDatabase.add(course);
        } else if ("licenta".equalsIgnoreCase(studyProgram)) {
            bachelorCDatabase.add(course);
        } else {
            throw new InvalidCommandException("Program de studiu invalid pentru curs: " + studyProgram);
        }




        if ("master".equalsIgnoreCase(studyProgram) || "licenta".equalsIgnoreCase(studyProgram)) {
            course.setStudyProgram(studyProgram);
            cDataBase.add(course);
        }
    }

    private Student findStudentByName(String sName) {
        for (Student student : sDataBase) {
            if (student.getsName().equals(sName)) {
                return student;
            }
        }
        return null;
    }

    private Curs<Student> findCourseByName(String cName) {
        for (Curs<Student> course : cDataBase) {
            if (course.getcName().equals(cName)) {
                return course;
            }
        }
        return null;
    }

    public void citesteMediile(String testFolderPath) {
        File folder = new File(testFolderPath);
        File[] files = folder.listFiles((dir, name) -> name.startsWith("note_"));

        if (files != null) {
            for (File file : files) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(" ");
                        if (parts.length == 3) {
                            String sName = parts[0];
                            double average;
                            try {
                                average = Double.parseDouble(parts[2]);
                            } catch (NumberFormatException e) {
                                average = 0.0;
                            }

                            Student student = findStudentByName(sName);
                            if (student != null) {
                                student.setAverage(average);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void posteazaMediile(String outputfile) {
        sDataBase.sort(Comparator.comparingDouble(Student::getAverage).reversed().thenComparing(Student::getsName));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputfile,true))) {
            writer.write("***\n");
            for (Student student : sDataBase) {
                writer.write(student.getsName() + " - " + student.getAverage() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void contestatie(String sName, double nouaMedie) {
        Student student = findStudentByName(sName);
        if (student != null) {
            student.setAverage(nouaMedie);
        } else {
            System.out.println("Student not found in the database.");
        }
    }
    public void adaugaPreferinte(String sName, String cursuriPreferinte) {

        Student student = findStudentByName(sName);
        student.getPreferinteStudenti().add(cursuriPreferinte);
    }

    public void repartizeaza() {

        sDataBase.sort(Comparator.comparingDouble(Student::getAverage).reversed().thenComparing(Student::getsName));

        for (Student student : sDataBase) {
            List<String> preferences = student.getPreferinteStudenti();

            for (String cName : preferences) {
                Curs<Student> curs = findCourseByName(cName);

                if (curs != null && curs.getRegisteredStudents().size() < curs.getMaxCapacity()) {
                    curs.getRegisteredStudents().add(student);
                    break;
                } else if (curs != null && curs.getRegisteredStudents().size() == curs.actualCapacity) {
                   // List<Student> registeredStudents = curs.getRegisteredStudents();
                    Student finalStudent = curs.getFinalRegisteredStudent();

                    if (student.getAverage() == finalStudent.getAverage()) {
                        curs.getRegisteredStudents().add(student);
                        curs.actualCapacity+=1;
                        break;
                    }
                }
            }
        }
    }
    public void posteazaCurs(String cName, String outputFile) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile,true))) {
            Curs course = findCourseByName(cName);
            writer.write("***\n");
            writer.write(cName + " " + "(" + course.getMaxCapacity() + ")\n");
            course.getRegisteredStudents().sort(Comparator.comparing(Student::getsName));

            for (Object student : course.getRegisteredStudents()) {
                Student st = (Student)student;
                writer.write(st.getsName() + " - " + st.getAverage() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void posteazaStudent(String sName, String outputFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile,true))) {
            Student student = findStudentByName(sName);
            writer.write("***\n");
            String prog = null;
            String course = null;
            if(student.getStudyProgram().equals("licenta")) {
                prog = "Licenta";
                for(Curs curs : bachelorCDatabase) {
                    for(Object student1 : curs.getRegisteredStudents()) {
                        Student st = (Student)student1;
                        if(st.getsName().equals(sName)) {
                            course = curs.getcName();
                        }
                    }
                }
            } else {
                prog = "Master";
                for(Curs curs : masterCDatabase) {
                    for(Object student1 : curs.getRegisteredStudents()) {
                        Student st = (Student)student1;
                        if(st.getsName().equals(sName)) {
                            course = curs.getcName();
                        }
                    }
                }
            }
            writer.write("Student " + prog + ": " + student.getsName() + " - " +student.getAverage() + " - " + course + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

