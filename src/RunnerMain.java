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
        ArrayList<Student> students = new ArrayList<Student>();
//        //new Student(new File("HistoricalData"));
        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
                students.add(new Student(file));

            }
        }
        for (Student student : students) {
            student.printDetails();
        }

    }
    

}
