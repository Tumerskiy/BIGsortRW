import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;


public class test2 {
	public static int convertGrade(String grade) {
        switch (grade.trim()) {
        case "A+":
//          System.out.println("A+");
            return 43;
        case "A":
//          System.out.println("A");
            return 40;
        case "A-":
//          System.out.println("A-");
            return 37;
        case "B+":
//          System.out.println("B+");
            return 33;
        case "B":
//          System.out.println("B");
            return 30;
        case "B-":
//          System.out.println("B-");
            return 27;
        case "C+":
//          System.out.println("C+");
            return 23;
        case "C":
//          System.out.println("C");
            return 20;
        case "C-":
//          System.out.println("C-");
            return 17;
        case "D+":
//          System.out.println("D+");
            return 13;
        case "D":
//          System.out.println("D");
            return 10;
        case "D-":
//          System.out.println("D-");
            return 7;
        case "Fail":
//          System.out.println("F");
            return 0;

        default:
            return 0;
        }
    }
    
	
	static public void main(String[] args) {
	try {
		BufferedReader t2 = new BufferedReader(new FileReader(new File("testGpa.txt")));
		int credit =0;
		int grade = 0;
		
		String content1 = null;
		String content2 = null;
		while ((content1=t2.readLine())!=null) {
			credit += Integer.parseInt(content1.substring(21,23).trim());
			grade += Integer.parseInt(content1.substring(21,23).trim()) * convertGrade(content1.substring(23));
		}

		System.out.println(grade);
		System.out.println(credit);
		
		double gpa = grade/credit;
		System.out.println(gpa);
		
		
	} catch (Exception e){
		e.printStackTrace();
	}
	}
	}
