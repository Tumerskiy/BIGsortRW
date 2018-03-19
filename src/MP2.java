import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter; 
import java.util.ArrayList;
import java.util.TreeSet;

public class MP2 {
	private static File inFile1 = new File("T1.txt");
	private static File inFile2 = new File("T2.txt");
	private static File premap1 = new File("premap1.txt");
	private static File premap2 = new File("premap2.txt");
	private static File result = new File("result.txt");
	private static File gpaResult = new File("gpa.txt");

	private static ArrayList<String> buffer;
	private static TreeSet<String> set = new TreeSet<String>();
	
	public static long multiMergw(int sublists1, int sublists2, int bbs1, int bbs2, File premapp1,File premapp2, int number2, long numberOfIO) throws IOException {
		BufferedReader br1 = new BufferedReader(new FileReader(inFile1));
		BufferedWriter bw1;
		BufferedWriter bw2;
		int[] indexesN1 = new int[sublists1];
        String[] compareBuffer1 = new String[sublists2];
        ArrayList<BufferedReader> indexes1 = new ArrayList<>();
        
        int[] indexesN2 = new int[sublists2];
        String[] compareBuffer2 = new String[sublists2];
        ArrayList<BufferedReader> indexes2 = new ArrayList<>();
        
        for (int j=0;j<sublists1;j++) {
			br1 = new BufferedReader(new FileReader(premapp1));
            for (int skip=0; skip<(bbs1)*j;skip++) {
				br1.readLine();
            }
            indexes1.add(br1);
            compareBuffer1[j] = (br1.readLine());
            indexesN1[j]= 1;
        }
        
        for (int j=0;j<sublists2;j++) {
			br1 = new BufferedReader(new FileReader(premapp2));
            for (int skip=0; skip<(bbs2)*j;skip++) {
				br1.readLine();
            }
            indexes2.add(br1);
            compareBuffer2[j] = (br1.readLine());
            indexesN2[j]= 1;
        }
        
        int p = 0;
        int minIndex1 = 0;
        int minIndex2 = 0;
        String min1 = null;
        String min2 = null;
        Boolean changeT1 = true;
        boolean changeT2 = true;
        double gpa = 0;
        bw1 = new BufferedWriter(new FileWriter(result));
        bw2 = new BufferedWriter(new FileWriter(gpaResult));
        
        while (p<number2) {
        		if (changeT1 == true) {
        			min1 = "99999999ZZZZZZZZZZZZZZZZZZZZ999999999999999ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ";   
                    minIndex1 = 0;
                    for (int j = 0;j<sublists1;j++) {// find min
                            if (compareBuffer1[j]!=null) {
                            		if (compareBuffer1[j].substring(0,8).compareTo(min1)<0) {
                            			min1 = compareBuffer1[j];
                                     minIndex1 = j;
                            		}
                            }
                    }
                    
                    if (compareBuffer1[minIndex1] == null) break; // this might bugged out
                    
                    if (indexesN1[minIndex1]<bbs1) {
                            compareBuffer1[minIndex1] = indexes1.get(minIndex1).readLine();
                            indexesN1[minIndex1]++;
                            numberOfIO+=100;
                        }
                    else {
                        compareBuffer1[minIndex1] = null;
                    }
                    
                    for (int j = 0;j<sublists1;j++) {
                        if (compareBuffer1[j]!=null && min1 != null)
                            if (min1.equals(compareBuffer1[j])){
                                if (indexesN1[j]<bbs1) {
                                    compareBuffer1[j] = indexes1.get(j).readLine();
                                    indexesN1[j]++;
                                    numberOfIO+=100;
                                }
                                else {
                                    compareBuffer1[j] = null;
                                }
                                j--;
                            }
                    }
        		}
//        		System.out.print(min1.substring(0,8)+" ");
            
            // now all the same min in T1 is cleared is cleared
        		
        		if (changeT2 == true) {// find another min2
                min2 = "99999999ZZZZZZZZ9999999ZZZZ";
                minIndex2 = 0;
                for (int j = 0;j<sublists2;j++) {// find min2
                        if (compareBuffer2[j]!=null) {
                                if (compareBuffer2[j].compareTo(min2)<0) {
                                    min2 = compareBuffer2[j];
                                    minIndex2 = j;
                                }
                        }
                }
                if (compareBuffer2[minIndex2]==null) break;
                if (indexesN2[minIndex2]<bbs2) {
                    compareBuffer2[minIndex2] = indexes2.get(minIndex2).readLine();
                    indexesN2[minIndex2]++;
                    p++;
                    numberOfIO+=27;
        			} else {
            			compareBuffer2[minIndex2] = null;
            		}
        		}
//        		System.out.println(min2.substring(0,8));
                
        		if (min1.substring(0,8).equals(min2.substring(0,8))) {// stud id is the same
//        				System.out.println("the same");
                		changeT2 = true;
                		changeT1 = true;
                		gpa = 0;
            			double c = 0;
                		double points = 0;
                		
                		c += Double.parseDouble(min2.substring(21,23));
//                		c += 1;
                		points += convertGrade(min2.substring(23)) * Double.parseDouble(min2.substring(21,23));
//                		points += convertGrade(min2.substring(23));
                		
                		bw1.write(min1+min2.substring(8));
                		bw1.newLine();
                		
                    for (int j = 0;j<sublists2;j++) {
                        if (compareBuffer2[j]!=null && min2 != null)
                            if (min2.substring(0,8).equals(compareBuffer2[j].substring(0,8))){
                            		bw1.write(min1+compareBuffer2[j].substring(8));
                            		bw1.newLine();
                            		c +=Double.parseDouble(compareBuffer2[j].substring(21,23));
//                            		c +=1;
                            		points += convertGrade(compareBuffer2[j].substring(23)) * Double.parseDouble(compareBuffer2[j].substring(21,23));
//                            		points += convertGrade(compareBuffer2[j].substring(23));
                            		if (indexesN2[j]<bbs2) {
                            			compareBuffer2[j] = indexes2.get(j).readLine();
                            			indexesN2[j]++;
                            			p++;
                            			numberOfIO+=27;
                            		}
                            		else {
                            			compareBuffer2[j] = null;
                            		}
                            		j--;
                            	}
                    	}
                    gpa = (double)Math.round((points/c)*10.0)/10.0;
//                    System.out.println(points+" / "+ c + " = "+ gpa);
                    bw2.write(min1.substring(0,8) +" "+ gpa);
                    bw2.newLine();
                } else { // if not the same
                		if (min2.substring(0,8).compareTo(min1.substring(0,8))<0) { // if min2 is smaller then min1 needs to change
                			System.out.println("min1 change");
                			changeT2 = false;
                			changeT1 = true;
                		} else { // min2 needs to change
                			System.out.println("min2 change");
                			changeT2 = true;
                			changeT1 = false;								
					}
                }
        }
        bw1.close();
        bw2.close();
        br1.close();
        for (int j = 0;j<sublists1;j++) {
            indexes1.get(j).close();
        }
        for (int j = 0;j<sublists2;j++) {
            indexes2.get(j).close();
        }
        return numberOfIO;
	}
	
	public static double convertGrade(String grade) {
		switch (grade.trim()) {
		case "A+":
//			System.out.println("A+");
			return 4.0;
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
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
        BufferedReader br1 = null;
        BufferedReader br2 = null;
        BufferedWriter bw1 = null;
        BufferedWriter bw2 = null;
        
        buffer = new ArrayList<>();
        long heapMaxSize = Runtime.getRuntime().maxMemory();
        //System.out.println(heapMaxSize);
        int bs= (int) ((heapMaxSize/10000));
        int bbs1 = bs*15;
        int bbs2 = bs*55;
        int sublists1 = 0;
        int sublists2 = 0;
        int number1 = 0;
        int number2 = 0;
        long numberOfIO = 0;
        
        
        try {
            br1 = new BufferedReader(new FileReader(inFile1));
            br2 = new BufferedReader(new FileReader(inFile2));
            bw1 = new BufferedWriter(new FileWriter(premap1));
            bw2 = new BufferedWriter(new FileWriter(premap2));
            
            String content = null;
            String content1 = null;
           
            while((content=br1.readLine())!=null){
                if (set.size() == bbs1) {
                	for(String p: set) {
                		bw1.write(p+"\n");
                        numberOfIO+=100;                        
                	}
                	number1+=bbs1;
                	sublists1++;
                	set.clear();
                }
            	if (content!=null) {
                	set.add(content);
                		numberOfIO+=100;
                }
            }
            for(String p: set) {
        		bw1.write(p+"\n");
                numberOfIO+=100;
                
        	}
            number1+=bbs1;
            sublists1++;
            set.clear();
            while((content1=br2.readLine())!=null){
            	if (set.size() == bbs2) {
                	for(String p: set) {
                		bw2.write(p+"\n");
                        numberOfIO+=27;
                	}
                	number2+=bbs2;
                	sublists2++;
                	set.clear();
                }
            	if (content1!=null) {
                	set.add(content1);
                		numberOfIO+=27;
                }
            }
            for(String p: set) {
        		bw2.write(p+"\n");
                numberOfIO+=27;
        	}
            number2+=bbs2;
            sublists2++;
            set.clear();
            
            
            //some cleanup
            br1.close();
            br2.close();
            bw1.close();
            bw2.close();
            long time = System.currentTimeMillis()-startTime;
            System.out.println("time:"+ time +" sublists1:"+sublists1+" sublists2:"+sublists2+" number1:"+number1+" number2:"+number2+" IO:"+numberOfIO );
            
            // multi-merge
            
            
            numberOfIO = multiMergw(sublists1, sublists2, bbs1, bbs2, premap1, premap2, number2, numberOfIO);
            
            time = System.currentTimeMillis()-startTime;
            System.out.println("time:"+ time +" sublists1:"+sublists1+" sublists2:"+sublists2+" number1:"+number1+" number2:"+number2+" IO:"+numberOfIO );
            
            

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
	            buffer.clear();
	        } catch (Exception e2) {
	            e2.printStackTrace();
	        }
    	}
	}
}
