import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class mainPart {
	
	
	public static void writeToFile (String s, FileWriter fw) {
		try {
			fw.write(String.format("%s%n", s));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	private static File inFile1 = new File("input1.txt");
	private static File inFile2 = new File("");
	private static File outFile1 = new File("output1.txt");
	private static File outFile2 = new File("");
	
	
	private static ArrayList<String> buffer;
	static Comparator<String> sAscComp = (s1, s2) -> s1.substring(0, 8).compareTo(s2.substring(0, 8));
	
	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		BufferedReader br1 = null;
		BufferedReader br2 = null;
		BufferedWriter bw1 = null;
		BufferedWriter bw2 = null;
		buffer = new ArrayList<>();
		try {
			br1 = new BufferedReader(new FileReader(inFile1));
			bw1 = new BufferedWriter(new FileWriter(outFile1));
			final FileWriter fw = new FileWriter(outFile1);
			String content = null;
			int i=0;
			int number = 0;
			while(((content=br1.readLine())!=null)){
				buffer.clear();
				buffer.add(content);
				number ++;
				for (i = 1;i<5000;i++) {
				content=br1.readLine();
					if(content!=null) {
						buffer.add(content);
						//number++;
					}
				}
				//Collections.sort(buffer, new AscComparator());
				
				
				buffer =(ArrayList<String>) buffer										
					.stream()					
					.sorted(sAscComp)
					.collect(Collectors.toList());
				 
//				buffer
//					.stream()
//					.sorted(sAscComp)
//					.forEach(s -> writeToFile(s,fw));
				
					
				
				for (i = 0;i<buffer.size();i++) {
					bw1.write(buffer.get(i));
					bw1.newLine();
				
				}
			}
			buffer.clear();
			
			bw1.close();
			br1.close();
			/*
			ArrayList<BufferedReader> bufferedReaders = new ArrayList<>();
			for (int j=0;j<number;j++) {
				BufferedReader nbReader = new BufferedReader(new FileReader(outFile1));
				for (int k = 0;k<j*5000;k++) {
					nbReader.readLine();
				}
				bufferedReaders.add(nbReader);
			}
			
			for (int j=0;j<number;j++) {
				System.out.println(5000*j+1+")"+bufferedReaders.get(j).readLine());
			}
			

			
			
			
			
			for (int j=0;j<number;j++) {
				bufferedReaders.get(j).close();
			}
			*/
			long stopTime = System.currentTimeMillis();
		      long elapsedTime = stopTime - startTime;
		      System.out.println(elapsedTime);
		      System.out.println(number);
		      
/*_____________*/
		      Runtime runtime = Runtime.getRuntime();

		      NumberFormat format = NumberFormat.getInstance();

				
				long maxMemory = runtime.maxMemory();
				long allocatedMemory = runtime.totalMemory();
				long freeMemory = runtime.freeMemory();
				
				StringBuilder sb = new StringBuilder();
		      
				
				sb.append("free memory: " + format.format(freeMemory / 1024) + "\n");
				sb.append("allocated memory: " + format.format(allocatedMemory / 1024) + "\n");
				sb.append("max memory: " + format.format(maxMemory / 1024) + "\n");
				sb.append("total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024) + "\n");
				
				System.out.println(sb);
		      
		     
/*_____________*/		      
		      
			/*
			if (!outFile1.exists()) {
				outFile1.createNewFile();
			}
			
			bw1 = new BufferedWriter(new FileWriter(outFile1));
			for (int j=0;j<buffer.size();j++) {
				bw1.write(buffer.get(j));
			}
			
			*/
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				 if(bw1!=null)
					 bw1.close();
				 if(bw2!=null)
					 bw2.close();
				 if(br1!=null)
					 br1.close();
				 if(br2!=null)
					 br2.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
/*
class threadSorter implements Callable<ArrayList<String>>{
	Comparator<String> sAscComp = (s1, s2) -> s1.substring(0, 8).compareTo(s2.substring(0, 8));
	ArrayList<String> sorted;
	public threadSorter(ArrayList<String> ar) {
		sorted = ar;
	}

	@Override
	public ArrayList<String> call() throws Exception {
		sorted =(ArrayList<String>) sorted										
				.stream()					
				.sorted(sAscComp)
				.collect(Collectors.toList());
		return sorted;
	}
}
*/