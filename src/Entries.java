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
		
		
		String haString = "John   Smiths 111222999999999 1455             Maisonneuve West, Montreal,       QC, H3G 1M8";
//		sb.append("free memory: " + format.format(freeMemory / 1024) + "\n");
//		sb.append("allocated memory: " + format.format(allocatedMemory / 1024) + "\n");
//		sb.append("max memory: " + format.format(maxMemory / 1024) + "\n");
//		sb.append("total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024) + "\n");
//		sb.append("read # entries: "+ format.format(maxMemory /200) +"\n");
//		sb.append("read # blocks: "+ format.format(maxMemory /200/40) +"\n");
		
		System.out.println(haString.length());
		BufferedWriter bw1 = null;
		try {
			bw1 = new BufferedWriter(new FileWriter("input1.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (long j=0;j<100000;j++) {
			try {
				StringBuilder sb = new StringBuilder();
				sb.append(random.nextInt(10));
				sb.append(random.nextInt(10));
				sb.append(random.nextInt(10));
				sb.append(random.nextInt(10));
				sb.append(random.nextInt(10));
				sb.append(random.nextInt(10));
				sb.append(random.nextInt(10));
				sb.append(random.nextInt(10));
				sb.append(haString);
				bw1.newLine();
				bw1.write(sb.toString());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		try {
			bw1.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
