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

		int n = 100000 + random.nextInt( 900000);
		
		
		String haString = "John   Smiths 111222999999999 1455             Maisonneuve West, Montreal,       QC, H3G 1M8";
//		sb.append("free memory: " + format.format(freeMemory / 1024) + "\n");
//		sb.append("allocated memory: " + format.format(allocatedMemory / 1024) + "\n");
//		sb.append("max memory: " + format.format(maxMemory / 1024) + "\n");
//		sb.append("total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024) + "\n");
//		sb.append("read # entries: "+ format.format(maxMemory /200) +"\n");
//		sb.append("read # blocks: "+ format.format(maxMemory /200/40) +"\n");
		
		System.out.println(haString.length());
		BufferedWriter bw1 = null;
		BufferedWriter bw2 = null;
		try {
			bw1 = new BufferedWriter(new FileWriter("T1.txt"));
			bw2 = new BufferedWriter(new FileWriter("T2.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (long j=0;j<100000;j++) {
			try {
				StringBuilder sb = new StringBuilder();
				sb.append(10000000 + random.nextInt( 90000000));
				sb.append(haString);
				sb.append("\n");
				bw1.write(sb.toString());
				if (random.nextInt(50)>25){
					sb = new StringBuilder();
					sb.append(10000000 + random.nextInt( 90000000));
					sb.append(haString);
					sb.append("\n");
				}
				bw2.write(sb.toString());
				
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