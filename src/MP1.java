import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter; 
import java.util.ArrayList;
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
		case "F":
//			System.out.println("F");
			return 0;

		default:
			return -1;
		}
	}
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
        BufferedReader br1 = null;
        BufferedWriter bw1 = null;
        String content = null;
        int number = 0;
        long numberOfIO = 0;
        int sublists = 0;
        ArrayList<String> t1 = new ArrayList<>();
        
        try {
//        		
        	 	br1 = new BufferedReader(new FileReader(inFile1));
        	 	System.out.println(" Free memory left: "+Runtime.getRuntime().freeMemory());
        	 	for (int i = 0;i<20000;i++) {
        	 		content=br1.readLine();
					t1.add(content);
        	 	}
        	 	br1.close();
        	 	System.out.println(" Free memory left: "+Runtime.getRuntime().freeMemory());
//			while((content=br1.readLine())!=null){
//        	 	for (int i = 0;i<50000;i++) {
////				System.out.println(" Free memory left: "+Runtime.getRuntime().freeMemory());
////				System.out.println(content);
//				while (Runtime.getRuntime().freeMemory()>500000) {
//					content=br1.readLine();
//					t1.add(content);
//					number +=1;
//					numberOfIO +=100;
//				}
//				sublists +=1;
//				t1.clear();
//        	 		
//			}
//			br1.close();
//			double[] credits = new double[t1.size()];
//			double[] points = new double[t1.size()];;
//			for (int i = 0; i < t1.size(); i++) {
//				credits[i] = 0;
//				points[i] = 0;
//			}
//			br1 = new BufferedReader(new FileReader(inFile2));
//			bw1 = new BufferedWriter(new FileWriter(result));
////			while((content=br1.readLine())!=null){
//			for (int j = 0; j < 5000; j++) {
////				System.out.println(j);
//				content = br1.readLine();
//				for (int i = 0; i< t1.size(); i++) {
//					if (content.substring(0, 8).equals(t1.get(i).substring(0, 8))) {
//						credits[i] += Double.parseDouble(content.substring(21, 23));
//						points[i] += convertGrade(content.substring(23)) * Double.parseDouble(content.substring(21, 23));
//						bw1.write(t1.get(i)+content.substring(8));
//						bw1.newLine();
//					}
//				}
//				number +=1;
//				numberOfIO +=27;
//			}
//			br1.close();
//			bw1.close();
//			bw1 = new BufferedWriter(new FileWriter(gpaResult));
//			for (int i = 0; i< t1.size(); i++) {
//				double gpa = (double)Math.round((points[i]/credits[i])*10.0)/10.0;
//				bw1.write(t1.get(i).substring(0, 8)+" "+gpa);
//				bw1.newLine();
//			}
//			bw1.close();
			long time = System.currentTimeMillis()-startTime;
            System.out.println("time:"+ time +" number:"+number+" IO:"+numberOfIO+ " subs:"+sublists );
		} catch (IOException e) {
			e.printStackTrace();
		}
        finally {
			t1.clear();
		}
	}
}
