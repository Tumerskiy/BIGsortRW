import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class MP1 {
	private static File inFile1 = new File("T1.txt");
	private static File inFile2 = new File("T2.txt");
	private static File result = new File("result.txt");
	private static File gpaResult = new File("gpa.txt");
	
	public static double convertGrade(String grade) {
		switch (grade.trim()) {
		case "A+":
//			System.out.println("A+");
			return 4.3;
		case "A":
//			System.out.println("A");
			return 4.0;
		case "A-":
//			System.out.println("A-");
			return 3.7;
		case "B+":
//			System.out.println("B+");
			return 3.3;
		case "B":
//			System.out.println("B");
			return 3.0;
		case "B-":
//			System.out.println("B-");
			return 2.7;
		case "C+":
//			System.out.println("C+");
			return 2.3;
		case "C":
//			System.out.println("C");
			return 2.0;
		case "C-":
//			System.out.println("C-");
			return 1.7;
		case "D+":
//			System.out.println("D+");
			return 1.3;
		case "D":
//			System.out.println("D");
			return 1.0;
		case "D-":
//			System.out.println("D-");
			return 0.7;
		case "Fail":
//			System.out.println("F");
			return 0;

		default:
			return -1;
		}
	}
	
	
	
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
        BufferedReader br1 = null;
        BufferedReader br2 = null;
        BufferedWriter bw1 = null;
        int bbs1 = 5000;
//        int bbs2 = 10000;
        String content = null;
        long numberOfIO = 0;
//        ArrayList<String> t1 = new ArrayList<>();
        HashMap<String, String> t1 = new HashMap<>();
        HashMap<String, Double> credits = new HashMap<>();
        HashMap<String, Double> grades = new HashMap<>();
        ArrayList<String> t2 = new ArrayList<>();
        
        try {
//        		System.out.println("Free memory left: "+Runtime.getRuntime().freeMemory()/1024+"KB");
        		br1 = new BufferedReader(new FileReader(inFile1));
        		
        		bw1 = new BufferedWriter(new FileWriter(result));
        		while((content=br1.readLine())!=null){
        			t1.clear();
        			credits.clear();
        			grades.clear();
        			for (int i = 0;i<bbs1;i++) {
        				if (content!=null) {
        					t1.put(content.substring(0, 8), content.substring(8));
        					
//        					credits.put(content.substring(0, 8), (double) 0);
//        					grades.put(content.substring(0, 8), (double) 0);
        				}
					content=br1.readLine();
        			}
//        			System.out.println(t1.size());
        			br2 = new BufferedReader(new FileReader(inFile2));
        			while((content=br2.readLine())!=null){
        				if (t1.get(content.substring(0, 8))!=null) {
    						bw1.write(t1.get(content.substring(0, 8))+content);
    						bw1.newLine();
    						
//    						credits.put(content.substring(0, 8), credits.get(content.substring(0, 8)) + Double.parseDouble(content.substring(21, 23)));
//    						grades.put(content.substring(0, 8), grades.get(content.substring(0, 8)) + Double.parseDouble(content.substring(21, 23)));
    						
    						numberOfIO++;
    					}
//	        			for (int i = 0;i<bbs2;i++) {
//	        				if (content!=null)t2.add(content);
//	        				content=br2.readLine();
//	            	 	}
//	        			System.out.println(t2.size());
//	        			
//	        			for (int i = 0;i<t2.size();i++) {
//	        					if (t1.get(t2.get(i).substring(0, 8))!=null) {
//	        						bw1.write(t1.get(t2.get(i).substring(0, 8))+t2.get(i));
//	        						bw1.newLine();
//	        						numberOfIO++;
//	        					}
//	        				
//	        			}
////	        			System.out.print(numberOfIO);
////	        			System.out.println(" time:"+ (System.currentTimeMillis() -  startTime));
////	        			System.out.println(" Free memory left: "+Runtime.getRuntime().freeMemory()/1024+"KB");
//	        			t2.clear();
	        			
        			}
        			br2.close();
        			
        		}
        	 	t2.clear();
        	 	br2.close();
        	 	br1.close();
        	 	bw1.close();
        	 	System.out.println("Free memory left: "+Runtime.getRuntime().freeMemory()/1024+"KB");
        	 	System.out.println("time:"+ (System.currentTimeMillis() -  startTime) +" times:"+numberOfIO );
        	 	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
