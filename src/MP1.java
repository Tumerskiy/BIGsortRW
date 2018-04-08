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
	private static File result = new File("LoopResult.txt");
	private static File gpaResult = new File("Loopgpa.txt");
	
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
        BufferedWriter bw2 = null;
        int bbs1 = 8400;
//        int bbs2 = 10000;
        String content = null;
        long numberOfIO = 0;
        HashMap<String, String> t1;
        HashMap<String, Double> credits = new HashMap<>();
        HashMap<String, Double> grades = new HashMap<>();
        ArrayList<String> t2 = new ArrayList<>();
        
        try {
//        		System.out.println("Free memory left: "+Runtime.getRuntime().freeMemory()/1024+"KB");
        		br1 = new BufferedReader(new FileReader(inFile1));
        		bw2 = new BufferedWriter(new FileWriter(gpaResult));
        		bw1 = new BufferedWriter(new FileWriter(result));
        		
        		while((content=br1.readLine())!=null){
//        			t1.clear();
        			t1 = new HashMap<>();
//        			credits.clear();
//        			grades.clear();
        			if (content!=null) {
    					t1.put(content.substring(0, 8), content.substring(8));
    					numberOfIO++;
        			}
        			for (int i = 1;i<bbs1;i++) {
        				if ((content=br1.readLine())!=null) {
        					t1.put(content.substring(0, 8), content.substring(8));
        					numberOfIO++;
        					credits.put(content.substring(0, 8), (double) 0);
        					grades.put(content.substring(0, 8), (double) 0);
        				}
					
        			}
//        			System.out.println(t1.size());
        			br2 = new BufferedReader(new FileReader(inFile2));
        			while((content=br2.readLine())!=null){
        				if (t1.get(content.substring(0, 8))!=null) {
    						bw1.write(t1.get(content.substring(0, 8))+content);
    						bw1.newLine();
    						if (credits.get(content.substring(0, 8))!=null) {
								credits.put(content.substring(0, 8), credits.get(content.substring(0, 8)) + Double.parseDouble(content.substring(21, 23)));
							} else{
								credits.put(content.substring(0, 8),Double.parseDouble(content.substring(21, 23)));
							}
							if (grades.get(content.substring(0, 8))!=null) {
								//grades.put(content.substring(0, 8), grades.get(content.substring(0, 8)) + Double.parseDouble(content.substring(21, 23)));}
								grades.put(content.substring(0, 8), grades.get(content.substring(0, 8)) + convertGrade(content.substring(23)) * Double.parseDouble(content.substring(21, 23)));
							}
    						else{
								grades.put(content.substring(0, 8),Double.parseDouble(content.substring(21, 23)));
							}
        				}
	        			
        			}
        			br2.close();
        			// read from credits grades and t1
//        		gpa formula	(double)Math.round((points/credits)*10.0)/10.0

					for(String key: t1.keySet()){
						if(grades.get(key)!=null && credits.get(key)!=null) {
							bw2.write(key + " " + (double) Math.round((grades.get(key) / credits.get(key))*10)/10+"\n");
						}
					}
        		}

        	 	t2.clear();
        	 	br2.close();
        	 	br1.close();
        	 	bw1.close();
        	 	bw2.close();
        	 	System.out.println("Free memory left: "+Runtime.getRuntime().freeMemory()/1024+"KB");
        	 	System.out.println("time:"+ (System.currentTimeMillis() -  startTime) +" times:"+numberOfIO );
        	 	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
