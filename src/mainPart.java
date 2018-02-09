import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	
	
	public static void main(String[] args) {
		Comparator<String> sAscComp = (s1, s2) -> s1.substring(0, 8).compareTo(s2.substring(0, 8));
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
				for (i = 0;i<2000;i++) {
				content=br1.readLine();
					if(content!=null) {
						buffer.add(content);
						number++;
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
					bw1.newLine();
					bw1.write(buffer.get(i));
				
				}
			}
			long stopTime = System.currentTimeMillis();
		      long elapsedTime = stopTime - startTime;
		      System.out.println(elapsedTime);
		      System.out.println(number);
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

class threadSorter implements Callable<ArrayList<String>>{
	ArrayList<String> sorted;
	public threadSorter(ArrayList<String> ar) {
		sorted = ar;
	}

	@Override
	public ArrayList<String> call() throws Exception {
		Collections.sort(sorted, new AscComparator());
		return sorted;
	}
	

}


class AscComparator implements Comparator <String>{

	@Override
	public int compare(String o1, String o2) {		
		//return Integer.parseInt(o1.substring(0,8)) - Integer.parseInt(o2.substring(0,8));
		return o1.substring(0,8).compareTo(o2.substring(0,8));
				
	}
}