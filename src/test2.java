import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;


public class test2 {
	public static String convertGrade(String grade) {
		switch (grade.trim()) {
		case "A+":
//			System.out.println("A+");
			return "4.3";
		case "A":
//			System.out.println("A");
			return "4.0";
		case "A-":
//			System.out.println("A-");
			return "3.7";
		case "B+":
//			System.out.println("B+");
			return "3.3";
		case "B":
//			System.out.println("B");
			return "3.0";
		case "B-":
//			System.out.println("B-");
			return "2.7";
		case "C+":
//			System.out.println("C+");
			return "2.3";
		case "C":
//			System.out.println("C");
			return "2.0";
		case "C-":
//			System.out.println("C-");
			return "1.7";
		case "D+":
//			System.out.println("D+");
			return "1.3";
		case "D":
//			System.out.println("D");
			return "1.0";
		case "D-":
//			System.out.println("D-");
			return "0.7";
		case "Fail":
//			System.out.println("F");
			return "0.0";

		default:
			return "0.0";
		}
	}
	
	static public void main(String[] args) {
	try {
		BufferedReader t2 = new BufferedReader(new FileReader(new File("testGpa.txt")));
		BigDecimal credit = new BigDecimal(0);
		BigDecimal grade = new BigDecimal(0);
		
		String content1 = null;
		String content2 = null;
		while ((content1=t2.readLine())!=null) {
			credit = credit.add(new BigDecimal(content1.substring(21, 23).trim()));
			grade = grade.add(new BigDecimal(content1.substring(21, 23).trim()).multiply(new BigDecimal(convertGrade(content1.substring(23)))));
		}

		System.out.println(grade);
		System.out.println(credit);
		System.out.println(grade.divide(credit,	RoundingMode.HALF_UP));
//		double gpa = Math.round((grade/credit)*10.0)/10.0;
//		System.out.println(gpa);
		
		
	} catch (Exception e){
		e.printStackTrace();
	}
	}
	}
