import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.io.BufferedReader;
import java.io.BufferedWriter; 
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;

public class MP2 {
	private static File inFile1 = new File("T1.txt");
	private static File inFile2 = new File("T2.txt");
	private static File result = new File("result.txt");
	private static File gpaResult = new File("gpa.txt");
	private static TreeMap<String,BufferedReader> readers = new TreeMap<String,BufferedReader>();
	private static TreeSet<String> set = new TreeSet<String>();
	private static long numberOfIO = 0;

	public static int convertGrade(String grade) {
        switch (grade.trim()) {
        case "A+":
//          System.out.println("A+");
            return 43;
        case "A":
//          System.out.println("A");
            return 40;
        case "A-":
//          System.out.println("A-");
            return 37;
        case "B+":
//          System.out.println("B+");
            return 33;
        case "B":
//          System.out.println("B");
            return 30;
        case "B-":
//          System.out.println("B-");
            return 27;
        case "C+":
//          System.out.println("C+");
            return 23;
        case "C":
//          System.out.println("C");
            return 20;
        case "C-":
//          System.out.println("C-");
            return 17;
        case "D+":
//          System.out.println("D+");
            return 13;
        case "D":
//          System.out.println("D");
            return 10;
        case "D-":
//          System.out.println("D-");
            return 7;
        case "Fail":
//          System.out.println("F");
            return 0;

        default:
            return 0;
        }
    }
    
	

	public static void joinFiles(String file1, String file2) {
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(new File(file1)));
			BufferedReader br2 = new BufferedReader(new FileReader(new File(file2)));
			BufferedWriter bw = new BufferedWriter(new FileWriter(result));
			BufferedWriter bwGPA = new BufferedWriter(new FileWriter(gpaResult));
			String content1 = br1.readLine();
			String content2 = br2.readLine();
			int tuppleSize1 = 100;
			int tuppleSize2 = 27;
			numberOfIO+=tuppleSize1;
			numberOfIO+=tuppleSize2;

			int c = 0;
			int points = 0;

			while(content2!=null && content1 !=null) {
				if (content1.substring(0, 8).equals(content2.substring(0, 8))) {
					c += Integer.parseInt( content2.substring(21, 23).trim() );
					points += Integer.parseInt( content2.substring(21, 23).trim() ) * convertGrade(content2.substring(23));
					// write file content1 + content2
					bw.write(content1+content2.substring(8));
					numberOfIO+=tuppleSize1+tuppleSize2-8;
					bw.newLine();
					
					content2 = br2.readLine();
					numberOfIO+=tuppleSize2;
					
					if (content2 == null) {
						bwGPA.write(content1.substring(0,8) + " "+Math.round(((double)points/(double)c))/10.0);
						numberOfIO+=12;
						bwGPA.newLine();
					}
				} else {
					if (content2.substring(0,8).compareTo(content1.substring(0,8))<0) { // if content2 is smaller than content1
						if (c!= 0) {
							bwGPA.write(content2.substring(0,8) + " "+Math.round(((double)points/(double)c))/10.0);
							numberOfIO+=12;
							bwGPA.newLine();
						}
						content2 = br2.readLine();
						numberOfIO+=tuppleSize2;

					} else { // content1 is smaller than content2
						if (c != 0) {
							bwGPA.write(content1.substring(0,8) + " "+Math.round(((double)points/(double)c))/10.0);
							numberOfIO+=12;
							bwGPA.newLine();
						}
						content1 = br1.readLine();
						numberOfIO+=tuppleSize1;
					}
					c = 0;
					points = 0;
				}
			}
			bw.close();
			bwGPA.close();
			br1.close();
			br2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void putter(String toput, BufferedReader br, int tuppleSize) {
		String newput = null;
		try {
			if (readers.get(toput)==null) {
				readers.put(toput, br);
			} else if ( (newput=br.readLine()) != null){
				numberOfIO+=tuppleSize;
				putter(newput, br, tuppleSize);

			}else {
				br.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String multimergesorter(ArrayList<String> files, int tuppleSize) {
		Random random  = new Random();
		int id = random.nextInt(100);
		String result = files.get(0).substring(0,8)+"_"+id;
		int subSort = (int) Runtime.getRuntime().maxMemory()/50000;
		ArrayList<String> subFiles = new ArrayList<>();
		int iteration = 1;
		if (files.size()>subSort) {
			iteration = (int) Math.ceil((double) files.size()/(double) subSort);
		}
		
		//System.out.println(files.size()+"_"+subSort+"_"+iteration);
		try {
			
			for (int j=0;j<iteration;j++) {
			int subSize = subSort;
			if (j==iteration-1) {
				subSize = files.size()%subSort;
			}
		//	System.out.println(subSize);
			
			String fileName = result+"_"+j+".txt";
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(new File(fileName)));
			for (int i=j*subSort;i<(j*subSort+subSize);i++) {
			//for(String file: files) {
				String file = files.get(i);
				BufferedReader br = new BufferedReader(new FileReader(file));
				String toput = br.readLine();
				numberOfIO+= tuppleSize;
				putter(toput,br, tuppleSize);

			}	
			while(readers.size()>0) {
				String in = readers.firstKey();
				bw1.write(in+"\n");
				numberOfIO+=tuppleSize;
				String toput = null;
				if ((toput=readers.get(in).readLine())!=null) {
					numberOfIO+=tuppleSize;
					putter(toput,readers.get(in), tuppleSize);
				} else {
					readers.get(in).close();
				}
				readers.remove(in);
			}
			bw1.close();
			subFiles.add(fileName);
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		//System.out.println(subFiles.size());
		if(subFiles.size()==1) {
			return subFiles.get(0);
		} 
		return multimergesorter(subFiles, tuppleSize);
	}

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		BufferedReader br1 = null;
		BufferedReader br2 = null;
		BufferedWriter bw1 = null;
		BufferedWriter bw2 = null;
		ArrayList<String> files1 = new ArrayList<String>();
		ArrayList<String> files2 = new ArrayList<String>();

		long heapMaxSize = Runtime.getRuntime().maxMemory();
		//System.out.println(heapMaxSize);
		int bs= (int) ((heapMaxSize/10000));
		int bbs1 = bs*5;
		int bbs2 = bs*5;

		try {
			br1 = new BufferedReader(new FileReader(inFile1));
			br2 = new BufferedReader(new FileReader(inFile2));
			int filenumT1 = 0;
			int filenumT2 = 0;
			String content = null;
			String content1 = null;

			while((content=br1.readLine())!=null){
				if (set.size() == bbs1) {
					bw1 = new BufferedWriter(new FileWriter(new File("premapT1_"+filenumT1+".txt")));
					for(String p: set) {
						bw1.write(p+"\n");
						numberOfIO+=100;                        
					}
					set.clear();
					bw1.close();
					files1.add("premapT1_"+filenumT1+".txt");
					filenumT1++;
				}
				if (content!=null) {
					set.add(content);
					numberOfIO+=100;
				}
			}
			bw1 = new BufferedWriter(new FileWriter(new File("premapT1_"+filenumT1+".txt")));
			for(String p: set) {
				bw1.write(p+"\n");
				numberOfIO+=100;
			}
			bw1.close();
			files1.add("premapT1_"+filenumT1+".txt");
			set.clear();
			while((content1=br2.readLine())!=null){
				if (set.size() == bbs2) {
					bw1 = new BufferedWriter(new FileWriter(new File("premapT2_"+filenumT2+".txt")));
					for(String p: set) {
						bw1.write(p+"\n");
						numberOfIO+=27;
					}
					set.clear();
					bw1.close();

					files2.add("premapT2_"+filenumT2+".txt");
					filenumT2++;
				}
				if (content1!=null) {
					set.add(content1);
					numberOfIO+=27;
				}
			}
			bw1 = new BufferedWriter(new FileWriter(new File("premapT2_"+filenumT2+".txt")));
			for(String p: set) {
				bw1.write(p+"\n");
				numberOfIO+=27;
			}
			bw1.close();
			files2.add("premapT2_"+filenumT2+".txt");
			set.clear();

			//some cleanup
			br1.close();
			br2.close();

			long presortTime = System.currentTimeMillis()-startTime;

			System.out.println("Sublists creation phase: time:"+ presortTime +" IO:"+numberOfIO/4096 );
			// multi-merge

			String sortedT1 = multimergesorter(files1, 100);
			String sortedT2 = multimergesorter(files2, 27);

			long mergeTime = System.currentTimeMillis()-startTime;
			System.out.println("Multimerge phase: time:"+ mergeTime +" IO:"+numberOfIO/4096 );
			// join

			joinFiles(sortedT1, sortedT2);

			long joinTime = System.currentTimeMillis()-startTime;
			System.out.println("Join phase: time:"+ joinTime +" IO:"+numberOfIO/4096 );
			

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