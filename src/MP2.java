import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter; 
import java.util.ArrayList;
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
			return 0;
		}
	}
	
	public static void loopLoopMerge(String sortedT1) {
		int bs;
		String content;
		BufferedReader br1 = null;
		BufferedWriter bw1 = null;
		BufferedWriter bw2 = null;
		bs = (int) (Runtime.getRuntime().freeMemory()/230);
		
		ArrayList<String> t1 = new ArrayList<>();
		try {
			bw1 = new BufferedWriter(new FileWriter(new File("loopLoopResult")));
			bw2 = new BufferedWriter(new FileWriter(new File("loopLoopGPA")));
			br1 = new BufferedReader(new FileReader(new File(sortedT1)));
			while((content=br1.readLine())!=null){
				System.out.println(bs);
				for (int i = 0;i<bs;i++) {
					if (content != null) t1.add(content);
					content = br1.readLine();
					
				}
				double[] credits = new double[t1.size()];
				double[] points = new double[t1.size()];
				for (int i = 0; i < t1.size(); i++) {
					credits[i] = 0;
					points[i] = 0;
				}
				
				BufferedReader br2 = new BufferedReader(new FileReader(inFile2));
				while((content=br2.readLine())!=null){
					
					for (int i = 0; i< t1.size(); i++) {
						if (content.substring(0, 8).equals(t1.get(i).substring(0, 8))) {
							credits[i] += Double.parseDouble(content.substring(21, 23));
							points[i] += convertGrade(content.substring(23)) * Double.parseDouble(content.substring(21, 23));
							bw1.write(t1.get(i)+content.substring(8));
							bw1.newLine();
						}
					}

				}
				br2.close();
				for (int i = 0; i< t1.size(); i++) {
					bw2.write(t1.get(i).substring(0, 8)+" "+(double)Math.round((points[i]/credits[i])*10.0)/10.0);
					bw2.newLine();
				}
				t1.clear();
			}
			br1.close();
			bw1.close();
			bw2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

			double c = 0;
			double points = 0;

			while(content2!=null && content1 !=null) {
				if (content1.substring(0, 8).equals(content2.substring(0, 8))) {
					c += Double.parseDouble(content2.substring(21,23));
					points += convertGrade(content2.substring(23)) * Double.parseDouble(content2.substring(21,23));
					// write file content1 + content2
					bw.write(content1+content2.substring(8));
					numberOfIO+=tuppleSize1+tuppleSize2-8;
					bw.newLine();
					
					content2 = br2.readLine();
					numberOfIO+=tuppleSize2;
					
					if (content2 == null) {
						bwGPA.write(content1.substring(0,8) + " "+(double)Math.round((points/c)*10.0)/10.0);
						numberOfIO+=12;
						bwGPA.newLine();
					}
				} else {
					if (content2.substring(0,8).compareTo(content1.substring(0,8))<0) { // if content2 is smaller than content1
						if (c != 0) {
							bwGPA.write(content2.substring(0,8) + " "+(double)Math.round((points/c)*10.0)/10.0);
							numberOfIO+=12;
							bwGPA.newLine();
						}
						content2 = br2.readLine();
						numberOfIO+=tuppleSize2;

					} else { // content1 is smaller than content2
						if (c != 0) {
							bwGPA.write(content1.substring(0,8) + " "+(double)Math.round((points/c)*10.0)/10.0);
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
		String result = files.get(0).substring(0,8)+".txt";
		try {
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(new File(result)));

			for(String file: files) {

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
		} catch (Exception e2) {
			e2.printStackTrace();
		}


		return result;
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
		int bbs1 = bs*15;
		int bbs2 = bs*50;

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
			long loopLoopMergeTime = System.currentTimeMillis()-startTime;
			String sortedT2 = multimergesorter(files2, 27);

			long mergeTime = System.currentTimeMillis()-startTime;
			System.out.println("Multimerge phase: time:"+ mergeTime +" IO:"+numberOfIO/4096 );
			// join

			joinFiles(sortedT1, sortedT2);

			long joinTime = System.currentTimeMillis()-startTime;
			System.out.println("Join phase: time:"+ joinTime +" IO:"+numberOfIO/4096 );
			
			//loop-loop
			long finalLoopTime = System.currentTimeMillis();
			loopLoopMerge( sortedT1);
			
			System.out.println("Loop-Loop phase: time:"+ (loopLoopMergeTime + (System.currentTimeMillis()-finalLoopTime))+" IO:"+numberOfIO/4096 );

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