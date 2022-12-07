
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Student {

    int id;
    int grade;
    ArrayList<CourseHistory> pastData;

    public Student(File f) {
        pastData = new ArrayList<CourseHistory>();
        try {
            Scanner scan = new Scanner(f);
            id = scan.nextInt(); //grade=scan.nextInt();
                scan.nextLine();
                System.out.println(id);
                scan.useDelimiter(",");
            while (scan.hasNext()) {
                scan.nextLine();
                String gradeString=scan.nextLine().replaceAll("th", "");
                grade=Integer.parseInt(gradeString);
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

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void printDetails(){
        System.out.println("************");
        System.out.println(id);
        for (CourseHistory courseHistory : pastData) {
            System.out.println(courseHistory.name);
            System.out.println(courseHistory.getAverages());
        }
    }
}