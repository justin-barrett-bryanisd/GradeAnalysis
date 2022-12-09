
import java.util.ArrayList;
import java.util.Scanner;


public class CourseHistory {
    String name;
    ArrayList<ArrayList<Integer>> grades;
    
    public CourseHistory(String name, String line){
        this.name=name;
        grades=new ArrayList<ArrayList<Integer>>();
        addLine(line);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ArrayList<Integer>> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<ArrayList<Integer>> grades) {
        this.grades = grades;
    }
    
    public void addLine(String str){
        Scanner scan=new Scanner(str);
        scan.useDelimiter(",");
        ArrayList<Integer> data=new ArrayList<Integer>();
        while(scan.hasNextInt()){
            data.add(scan.nextInt());
        }
        //System.out.println(data);
        grades.add(data);
        //System.out.println(name+"************************");
        //System.out.println(grades);
        //System.out.println("------------");
    }
    public ArrayList<Double> getAverages(){
        ArrayList<Double> averages=new ArrayList<Double>();
        for (int i = 0; i < grades.size(); i++) {
            double sum=0;
            for (int j = 0; j < grades.get(i).size(); j++) {
                sum+=grades.get(i).get(j);
            }
            averages.add(sum/grades.get(i).size());
        }
        return averages;
    }

    public ArrayList<Double> findDiscrepancyAverage(ArrayList<Student> peers){
        
        ArrayList<Double> averages=getAverages();
        ArrayList<Double> differences=new ArrayList<Double>();
        for (int i = 0; i < averages.size(); i++) {
            double difference=0;
            int diffCount=0;
            for (Student peer : peers) {
                try {
                    double peerDiff=averages.get(i)-peer.pastData.get(name).getAverages().get(i);
                    difference+=peerDiff;
                    diffCount++;
                } catch (Exception e) {
                    System.out.println(e);
                }
                
            }
            differences.add(difference/diffCount);
            
        }
        
        
        
        return differences;
    }

}
