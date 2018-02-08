import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
public class Entries {
	public static void main(String[] args) {
		Runtime runtime = Runtime.getRuntime();

		NumberFormat format = NumberFormat.getInstance();

		StringBuilder sb = new StringBuilder();
		long maxMemory = runtime.maxMemory();
		long allocatedMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();
		String haString = "12345678John   Smiths 111222999999999 1455     Maisonneuve West, Montreal, QC, H3G 1M8";
		String heString = "88888888Roselyn Shiri 222444888888888 1515 Saint-Catherine West, Montreal, QC, H3G 1M7";
		sb.append("free memory: " + format.format(freeMemory / 1024) + "\n");
		sb.append("allocated memory: " + format.format(allocatedMemory / 1024) + "\n");
		sb.append("max memory: " + format.format(maxMemory / 1024) + "\n");
		sb.append("total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024) + "\n");
		sb.append("read # entries: "+ format.format(maxMemory /200) +"\n");
		sb.append("read # blocks: "+ format.format(maxMemory /200/40) +"\n");
		
		BufferedWriter bw1 = null;
		try {
			bw1 = new BufferedWriter(new FileWriter("input1.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int j=0;j<500000;j++) {
			try {
				bw1.write(haString);
				bw1.newLine();
				bw1.write(heString);
				bw1.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		System.out.println(sb);
	}
}
