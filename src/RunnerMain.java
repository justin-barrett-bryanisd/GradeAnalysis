/**
 *
 * @author NAME
 */

import java.util.*;
import java.text.*;
import java.io.*;
import java.lang.*;
import javax.swing.*;

public class RunnerMain {
    public static void main(String[] args) throws Exception {
        File folder = new File("HistoricalData");
        File[] listOfFiles = folder.listFiles();
//        //make an ArrayList of Students so you can add them all
        HashMap<Integer, Student> students = new HashMap<Integer, Student>();
//        //new Student(new File("HistoricalData"));
        for (File file : listOfFiles) {
            if (file.isFile()) {
                Student student=new Student(file);
                students.put(student.id, student);
            }
        }
        students.get(988429).processSixWeeksGrade("MATH", 73);
        students.get(988429).processSixWeeksGrade("ENG", 73);
        students.get(988429).processSixWeeksGrade("SCI", 73);
        students.get(988429).processSixWeeksGrade("SOC", 73);
        students.get(988429).processSixWeeksGrade("PE", 73);
        
        students.get(100623).processSixWeeksGrade("MATH", 73);
        students.get(100623).processSixWeeksGrade("ENG", 73);
        students.get(100623).processSixWeeksGrade("SCI", 73);
        students.get(100623).processSixWeeksGrade("SOC", 73);
        students.get(100623).processSixWeeksGrade("PE", 73);
        
        
        students.get(122349).processSixWeeksGrade("MATH", 73);
        students.get(122349).processSixWeeksGrade("ENG", 73);
        students.get(122349).processSixWeeksGrade("SCI", 73);
        students.get(122349).processSixWeeksGrade("SOC", 73);
        students.get(122349).processSixWeeksGrade("PE", 73);
        
        
        students.get(5207).processSixWeeksGrade("MATH", 85);
        students.get(5207).processSixWeeksGrade("ENG", 84);
        students.get(5207).processSixWeeksGrade("SCI", 70);
        students.get(5207).processSixWeeksGrade("SOC", 79);
        students.get(5207).processSixWeeksGrade("PE", 98);
        
        students.get(5207).processSixWeeksGrade("MATH", 85);
        students.get(5207).processSixWeeksGrade("ENG", 83);
        students.get(5207).processSixWeeksGrade("SCI", 71);
        students.get(5207).processSixWeeksGrade("SOC", 77);
        students.get(5207).processSixWeeksGrade("PE", 100);
        
        //students.get(988429).printDetails();
        
        ArrayList<Student> sortedStudent=new ArrayList<Student>();
        sortedStudent.addAll(students.values());
        Collections.sort(sortedStudent);
        for (Student student : sortedStudent) {
            student.printDetails();
        }

    }
    

}
