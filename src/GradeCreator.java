
/**
 *
 * @author NAME
 */

import java.util.*;
import java.text.*;
import java.io.*;
import java.lang.*;
import javax.swing.*;

public class GradeCreator {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 200; i++) {
           createStudent(); 
        }
        
    }

    private static void createStudent() throws FileNotFoundException {
        Random randy = new Random();
        int math = randy.nextInt(30)+70, english = randy.nextInt(30)+70, sci = randy.nextInt(30)+70, soc = randy.nextInt(30)+70, pe = randy.nextInt(5)+95, art = randy.nextInt(20)+80;
        int mathC = randy.nextInt(10)+1, englishC = randy.nextInt(10)+1, sciC = randy.nextInt(10)+1, socC = randy.nextInt(10)+1, peC = randy.nextInt(5)+1, artC = randy.nextInt(8)+1;
        int id = randy.nextInt(999999);
        DecimalFormat df = new DecimalFormat("000000");
        PrintWriter out = new PrintWriter("HistoricalData/stu" + df.format(id) + ".csv");
        int grade=randy.nextInt(4)+9;
        out.println(df.format(id)+","+grade+",");
        for (int i = 7; i < grade; i++) {
            out.println("");
            out.println(i + "th");
            printLine("MATH", math, out);
            printLine("ENG", english, out);
            printLine("SCI", sci, out);
            printLine("SOC", soc, out);
            printLine("PE", pe, out);
            printLine("ART", art, out);

            math += randy.nextInt(mathC) - mathC/2;
            english += randy.nextInt(englishC) - englishC/2;
            sci += randy.nextInt(sciC) - sciC/2;
            soc += randy.nextInt(socC) - socC/2;
            pe += randy.nextInt(peC) - peC/2;
            art += randy.nextInt(artC) - artC/2;
        }
        out.close();
    }

    public static void printLine(String label, int num, PrintWriter out) {
        Random randy = new Random();
        out.print(label + ",");
        for (int j = 0; j < 6; j++) {
            int g = randy.nextInt(num - 3, num + 3);
            g = Math.max(0, g);
            g = Math.min(g, 100);
            out.print(g + ",");
        }
        out.println("");
    }

}
