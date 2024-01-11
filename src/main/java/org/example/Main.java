package org.example;

import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        String testName = args[0];
        String testFolderPath = "src/main/resources/" + testName;
        String inputFile = testFolderPath + "/" + testName + ".in";
        String outputFile = testFolderPath + "/" + testName + ".out";
       // String referenceFile = testFolderPath + "/" + testName + ".ref";

        Secretariat secretariat = new Secretariat();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             FileReader reader1 = new FileReader(inputFile)) {

            String command;
            while ((command = reader.readLine()) != null) {
                processCommand(secretariat, command, reader1, outputFile, testFolderPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Secretariat.sDataBase.clear();
    }

    private static void processCommand(Secretariat secretariat, String command, FileReader reader1, String outputfile, String testFolderPath) throws IOException {

        String[] parts = command.split(" ");

        if (parts.length > 0) {
            try {
                switch (parts[0]) {
                    case "adauga_student":
                        if (parts.length == 5) {
                            String studyProgram = parts[2];
                            String sName = parts[4];
                            if ("master".equalsIgnoreCase(studyProgram)) {
                                secretariat.addStudent(sName,studyProgram, outputfile);
                            } else if ("licenta".equalsIgnoreCase(studyProgram)) {
                                secretariat.addStudent(sName, studyProgram, outputfile);
                            } else {
                                throw new InvalidCommandException("Program de studiu invalid: " + studyProgram);
                            }
                        } else {
                            throw new InvalidCommandException("Comandă invalidă: " + command);
                        }
                        break;
                    case "adauga_curs":
                        if (parts.length == 7) {
                            String studyProgram = parts[2];
                            String cName = parts[4];
                            int maxCapacity = Integer.parseInt(parts[6]);
                            if ("master".equalsIgnoreCase(studyProgram)) {
                                secretariat.addCourse(studyProgram,cName, maxCapacity);
                            } else if ("licenta".equalsIgnoreCase(studyProgram)) {
                                secretariat.addCourse(studyProgram, cName, maxCapacity);
                            } else {
                                throw new InvalidCommandException("Program de studiu invalid pentru curs: " + studyProgram);
                            }
                        } else {
                            throw new InvalidCommandException("Comandă invalidă: " + command);
                        }
                        break;
                    case "citeste_mediile":

                        secretariat.citesteMediile(testFolderPath);
                        break;
                    case "posteaza_mediile":
                        secretariat.posteazaMediile(outputfile);
                        break;
                    case "contestatie":
                        if (parts.length == 5) {
                            String sNameContest = parts[2];
                            double nouaMedie = Double.parseDouble(parts[4]);
                            secretariat.contestatie(sNameContest, nouaMedie);
                        } else {
                            throw new InvalidCommandException("Comandă invalidă: " + command);
                        }
                        break;
                    case "adauga_preferinte":
                        if (parts.length > 2) {
                            String sNamePreferences = parts[2];

                            for(int i = 4;i< parts.length;i+=2) {
                                secretariat.adaugaPreferinte(sNamePreferences, parts[i]);
                            }

                        } else {
                            throw new InvalidCommandException("Comandă invalidă: " + command);
                        }
                        break;
                    case "repartizeaza":
                        secretariat.repartizeaza();
                        break;
                    case "posteaza_curs":
                        if (parts.length == 3) {
                            String cNamePost = parts[2];
                            secretariat.posteazaCurs(cNamePost, outputfile);
                        } else {
                            throw new InvalidCommandException("Comandă invalidă: " + command);
                        }
                        break;
                    case "posteaza_student":
                        if (parts.length == 3) {
                            String sNamePost = parts[2];
                            secretariat.posteazaStudent(sNamePost, outputfile);
                        } else {
                            throw new InvalidCommandException("Comandă invalidă: " + command);
                        }
                        break;
                    default:
                        throw new UnknownCommandException("Comandă necunoscută: " + command);
                }
            } catch (InvalidCommandException | NumberFormatException e) {
               System.out.println("Eroare la procesarea comenzii: " + e.getMessage() + "\n");
            } catch (UnknownCommandException e) {
                System.out.println("Comandă necunoscută: " + e.getMessage() + "\n");
            } catch (ExistentStudentException e) {
                throw new RuntimeException(e);
            }
        }

    }

}






