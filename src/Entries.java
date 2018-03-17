import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Random;
import java.io.BufferedWriter;
public class Entries {
	public static void main(String[] args) {
		Runtime runtime = Runtime.getRuntime();

		NumberFormat format = NumberFormat.getInstance();

		
		long maxMemory = runtime.maxMemory();
		long allocatedMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();
		
		Random random  = new Random();

		int sid = 100000 + random.nextInt( 900000);
		String[] GPA = new String[9];
		GPA[0] = "A+  ";
		GPA[1] = "A   ";
		GPA[2] = "A-  ";
		GPA[3] = "B+  ";
		GPA[4] = "B   ";
		GPA[5] = "B-  ";
		GPA[6] = "C   ";
		GPA[7] = "F   ";
		GPA[8] = "D   ";
		
		String t1String = "Cody      Rivera    115004072002780Livfu Way,Gewubzi,ON,V3V 5R6                             ";
		String t2String = "comp62512201804";

		BufferedWriter bw1 = null;
		BufferedWriter bw2 = null;
		try {
			bw1 = new BufferedWriter(new FileWriter("T1.txt"));
			bw2 = new BufferedWriter(new FileWriter("T2.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (long j=0;j<50000;j++) {
			try {
				if (random.nextInt(60)>10) {
					sid = 100000 + random.nextInt( 900000);
				}
				StringBuilder sb = new StringBuilder();
				sb.append(sid);
				sb.append(t1String);
				sb.append("\n");
				bw1.write(sb.toString());
				for (int k=0;k<100;k++) {
					StringBuilder sb2 = new StringBuilder();
					sb2.append(sid);
					sb2.append(t2String);
					sb2.append(GPA[random.nextInt(8)]);
					sb2.append("\n");
					bw2.write(sb2.toString());
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		try {
			bw1.close();
			bw2.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}