
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
        ArrayList<ArrayList<Student>> peerData = new ArrayList<ArrayList<Student>>();
        for (int i = 0; i < 4; i++) {
            peerData.add(new ArrayList<Student>());
        }
//        //new Student(new File("HistoricalData"));
        for (File file : listOfFiles) {
            if (file.isFile()) {
                Student student = new Student(file);
                students.put(student.id, student);
                student.setPeerData(peerData.get(student.grade - 9));
                peerData.get(student.grade - 9).add(student);
            }
        }
        /*
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
         */
        //students.get(988429).printDetails();
        JFileChooser jfc = new JFileChooser();
        jfc.showOpenDialog(null);
        File myFile = jfc.getSelectedFile();

        Scanner scan = new Scanner(myFile);
        scan.nextLine();
        while (scan.hasNext()) {
            String[] input = scan.nextLine().toUpperCase().split(",");
            int id = Integer.parseInt(input[0].trim());

            String subj = input[1].trim();
            if (students.containsKey(id)) {
                for (int i = 2; i < input.length; i++) {
                    int grade = Integer.parseInt(input[i].trim());
                    students.get(id).processSixWeeksGrade(subj, grade);
                }
            } else {
                System.out.println("Student " + id + " not found in HistoricalData");
            }
        }
        scan.close();

        ArrayList<Student> sortedStudent = new ArrayList<Student>();
        sortedStudent.addAll(students.values());
        Collections.sort(sortedStudent);
        for (Student student : sortedStudent) {
            if (student.isDataEntered()) {
                student.printDetails();
            }
        }

        //System.out.println(students.get(5207).pastData.get("MATH").findDiscrepancyAverage(students.get(5207).getPeers()));
    }

}
