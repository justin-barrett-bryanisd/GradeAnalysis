
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Student implements Comparable {

    int id;
    int grade;
    ArrayList<CourseHistory> pastData;
    ArrayList<ArrayList<CourseHistory>> peerData;
    HashMap<String, Double> expectedGrades;
    HashMap<String, Double> concernList;
    static final int CONCERN_THRESHOLD=0;
    static final DecimalFormat DF=new DecimalFormat("0.00");

    public Student(File f) {
        pastData = new ArrayList<CourseHistory>();
        try {  //reading in data
            Scanner scan = new Scanner(f);
            scan.useDelimiter(",");
            id = scan.nextInt(); grade=scan.nextInt();
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
                    boolean done = false;
                    for (int i = 0; i < pastData.size(); i++) {
                        if (pastData.get(i).getName().equals(name)) {
                            done = true;
                            pastData.get(i).addLine(scan.nextLine());
                        }
                    }
                    if (!done) {
                        pastData.add(new CourseHistory(name, scan.nextLine()));
                    }
                }

            }
            //calcuates what grades the student should have
            expectedGrades=new HashMap<String, Double>();
            for (CourseHistory courseHistory : pastData) {
                String courseName=courseHistory.getName();
                ArrayList<Double> averages=courseHistory.getAverages();
                int count=0;
                double sum=0;
                for (int i = 0; i < averages.size(); i++) {
                    count+=(i+1);
                    sum+=(i+1)*averages.get(i);
                }
                expectedGrades.put(courseName, (sum/count));                
            }
            //System.out.println(id+" EXPECTED GRADES: "+expectedGrades);
            concernList=new HashMap<String, Double>();
            for (String courseName : expectedGrades.keySet()) {
                concernList.put(courseName, 0.0);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void printDetails(){
        System.out.println("************");
        System.out.println(id);
        for (CourseHistory courseHistory : pastData) {
            if(concernList.get(courseHistory.name)>CONCERN_THRESHOLD){
                System.out.println(courseHistory.name);
                System.out.println("Expected:\t"+DF.format(expectedGrades.get(courseHistory.name)));
                System.out.println("Concern Level:\t"+DF.format(concernList.get(courseHistory.name)));
            }
        }
        if(getTotalConcern()>CONCERN_THRESHOLD){
            System.out.println("**Overall Concern:\t"+getTotalConcern());
        }
    }
    public void processSixWeeksGrade(String courseName, double average){
        double expected=expectedGrades.get(courseName);
        double currentConcern=-1;
        if(average > expected){
            currentConcern=0;
        }
        else{
            currentConcern=expected-average;
        }
        if(concernList.containsKey(courseName)){
            concernList.put(courseName, currentConcern+concernList.get(courseName));
        }
        else{
            System.out.println("No expected grade for "+courseName);
        }
        
    }
    
    public double getTotalConcern(){
        double total=0;
        for (Double value : concernList.values()) {
            total+=value;
        }
        return total;
    }

    @Override
    public int compareTo(Object o) {
        return (int)((((Student)o).getTotalConcern()- getTotalConcern())*100);
    }
    
    
}