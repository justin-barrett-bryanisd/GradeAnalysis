
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Student implements Comparable {

    static final int CONCERN_THRESHOLD = 0;
    static final DecimalFormat DF = new DecimalFormat("0.00");

    int id;
    int grade;
    HashMap<String, CourseHistory> pastData;
    ArrayList<Student> peers;

    HashMap<String, Double> expectedGrades;
    HashMap<String, String> providedGrades;
    HashMap<String, Double> concernList;
    int concernCount = 0;
    boolean dataEntered;

    public Student(File f) {
        pastData = new HashMap<String, CourseHistory>();
        try {  //reading in data
            Scanner scan = new Scanner(f);
            scan.useDelimiter(",");
            id = scan.nextInt();
            grade = scan.nextInt();
            scan.nextLine();
            while (scan.hasNext()) {
                scan.useDelimiter(",");
                scan.nextLine();
                scan.nextLine();
//                String gradeString=scan.nextLine().replaceAll("th", "");
//                grade=Integer.parseInt(gradeString);
                //System.out.println(gradeString+"|||||||||");
                for (int j = 0; j < 6; j++) {
                    String name = scan.next();

                    if (pastData.containsKey(name)) {
                        pastData.get(name).addLine(scan.nextLine());
                    } else {
                        pastData.put(name, new CourseHistory(name, scan.nextLine()));
                    }
                }

            }
            //calcuates what grades the student should have
            expectedGrades = new HashMap<String, Double>();
            dataEntered=false;
            for (CourseHistory courseHistory : pastData.values()) {
                String courseName = courseHistory.getName();
                ArrayList<Double> averages = courseHistory.getAverages();
                int count = 0;
                double sum = 0;
                for (int i = 0; i < averages.size(); i++) {
                    count += (i + 1);
                    sum += (i + 1) * averages.get(i);
                }
                expectedGrades.put(courseName, (sum / count));
            }
            //System.out.println(id+" EXPECTED GRADES: "+expectedGrades);
            concernList = new HashMap<String, Double>();
            providedGrades = new HashMap<String, String>();
            for (String courseName : expectedGrades.keySet()) {
                concernList.put(courseName, 0.0);
                providedGrades.put(courseName, "");

            }
            concernCount = 0;

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<Student> getPeers() {
        return peers;
    }

    public void setPeerData(ArrayList<Student> peers) {
        this.peers = peers;
    }

    public void printDetails() {
        System.out.println("************");
        System.out.println(id);
        System.out.println("Grade: " + grade);
        System.out.println("Peer Count: " + peers.size());
        for (CourseHistory courseHistory : pastData.values()) {
            if (concernList.get(courseHistory.name) > CONCERN_THRESHOLD) {
                System.out.println(courseHistory.name);
                System.out.println("Expected:\t\t" + DF.format(expectedGrades.get(courseHistory.name)));
                System.out.println("Provided Grades:\t" + providedGrades.get(courseHistory.name));
                System.out.println("Concern Level:\t\t" + DF.format(concernList.get(courseHistory.name)));
            }
        }
        if (getTotalConcern() > CONCERN_THRESHOLD) {
            System.out.println("**Overall Concern:\t" + DF.format(getTotalConcern()));
        }
    }

    public void processSixWeeksGrade(String courseName, double average) {
        double expected = expectedGrades.get(courseName);
        //
        dataEntered=true;
        //
        double currentConcern = -1;
        concernCount++;
        if (average > expected) {
            currentConcern = 0;
        } else {
            currentConcern = expected - average;
        }
        if (concernList.containsKey(courseName)) {
            concernList.put(courseName, currentConcern + concernList.get(courseName));
            providedGrades.put(courseName, (providedGrades.get(courseName) + "  " + DF.format(average)).trim());
        } else {
            System.out.println("No expected grade for " + courseName);
        }

    }
    
    public boolean isDataEntered(){
        return dataEntered;
    }

    public double getTotalConcern() {
        if (concernCount < 1) {
            return 0;
        }
        double total = 0;
        for (Double value : concernList.values()) {
            total += value;
        }
        return total / concernCount;
    }

    @Override
    public int compareTo(Object o) {
        return (int) ((((Student) o).getTotalConcern() - getTotalConcern()) * 10000);
    }

}
