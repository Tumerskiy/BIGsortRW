import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class hashMap {

    public static void writeToFile (String s, FileWriter fw) {
        try {
            fw.write(String.format("%s%n", s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static File inFile1 = new File("T1.txt");
    private static File inFile2 = new File("T2.txt");
    private static File premap = new File("premap.txt");
    private static File premap1 = new File("premap1.txt");
    private static File damap = new File("map.txt");

    private static ArrayList<String> buffer;

    private static HashMap<String, Integer> map = new HashMap<String, Integer>();



    public static void main(String[] args) {
        //Comparator<String> sAscComp = (s1, s2) -> s1.substring(0, 8).compareTo(s2.substring(0, 8));
        long startTime = System.currentTimeMillis();
        BufferedReader br1 = null;
        BufferedReader br2 = null;

        BufferedWriter bw1 = null;
        BufferedWriter bw2 = null;
        buffer = new ArrayList<>();
        try {
            br1 = new BufferedReader(new FileReader(inFile1));
            br2 = new BufferedReader(new FileReader(inFile2));
            bw1 = new BufferedWriter(new FileWriter(premap));
            String content = null;
            int i=0;

            //here is the map landing from 2 files
            while(((content=br1.readLine())!=null)){
                if (map.size()>20000){
                    for (String p: map.keySet()){
                        bw1.write(p+":"+map.get(p)+"\n");
                    }
                    map.clear();
                }
                for (i = 0;i<2000;i++) {
                    content=br1.readLine();
                    if(content!=null) {
                        buffer.add(content);
                    }
                }
                for (i = 0;i<buffer.size();i++) {
                    map.merge(buffer.get(i).substring(0,8), 1, Integer::sum);

                }
                buffer.clear();

                for (i = 0;i<2000;i++) {
                    content=br2.readLine();
                    if(content!=null) {
                        buffer.add(content);
                    }
                }
                for (i = 0;i<buffer.size();i++) {
                    if (buffer.get(i).length()<8){
                        System.out.println(buffer.get(i));
                    }
                    map.merge(buffer.get(i).substring(0,8), -1, (k,v) -> v-1);
                }
                buffer.clear();
            }
            //some cleanup
            br1.close();
            br2.close();
            bw1.close();
            br1 = new BufferedReader(new FileReader(premap));
            bw1 = new BufferedWriter(new FileWriter(premap1));
            content=null;
            buffer.clear();
            map.clear();
            Comparator<String> sAscComp = (s1, s2) -> s1.substring(0, 8).compareTo(s2.substring(0, 8));
//this is sort of premap, and as a result you have somewhat presorted map, but with unmerged values
            int numberOfSublist = 0;
            int number = 0;
            while(((content=br1.readLine())!=null)){
            	numberOfSublist ++;
                for (i = 0;i<45000;i++) {
                    content=br1.readLine();
                    if(content!=null) {
                        buffer.add(content);
                        number++;
                        
                    }
                }
                buffer =(ArrayList<String>) buffer
                        .stream()
                        .sorted(sAscComp)
                        .collect(Collectors.toList());

                for (i = 0;i<buffer.size();i++) {                   
                    bw1.write(buffer.get(i));
                    bw1.newLine();
                    

                }
                buffer.clear();
            }
            
            

            //cleanup again
            br1.close();
            bw1.close();
            map.clear();
            content = null;
            
            //merge
            int[] indexesN = new int[numberOfSublist];
            
            String[] compareBuffer = new String[numberOfSublist];
            ArrayList<BufferedReader> indexes = new ArrayList<>();
            for (int j=0;j<numberOfSublist;j++) {
            		br1 = new BufferedReader(new FileReader(premap1));
            		for (int skip=0; skip<45000*j;skip++) {
            			br1.readLine();
            		}
            		indexes.add(br1);
            		compareBuffer[j] = (br1.readLine());
            		indexesN[j]= 1;
            }
            
            bw1 = new BufferedWriter(new FileWriter(damap));
            
            String min = compareBuffer[0].substring(0, 8);
            String in = null;
            int minIndex = 0;
            int p = 0;
            int c = 0;
            while (p<number) {
            		c = 0;
            		//i need to fix this shit
            		if (compareBuffer[0]!=null) min = compareBuffer[0].substring(0, 8);
            		else
            			if (compareBuffer[1]!=null) min = compareBuffer[1].substring(0, 8);
            			else
            				if (compareBuffer[2]!=null) min = compareBuffer[2].substring(0, 8);
            				else
            					if (compareBuffer[3]!=null) min = compareBuffer[3].substring(0, 8);
            		else {
						break;
					}
            		
            		
            		minIndex = 0;
	            for (int j = 0;j<numberOfSublist;j++) {// find min
	            		
//	            		System.out.println(j+") "+compareBuffer[j]);
	            	
		            	if (compareBuffer[j]!=null) {
		            			in = compareBuffer[j].substring(0, 8);
			            		if (Integer.parseInt(min)> Integer.parseInt(in)) {
			            			min = in;
			            			minIndex = j;
			            		}
		            		}
	            		
	            }
	            c = Integer.parseInt(compareBuffer[minIndex].substring(9));
	            
	            if (indexesN[minIndex]<45000) {
    					compareBuffer[minIndex] = indexes.get(minIndex).readLine();
    					indexesN[minIndex]++;
    					p++;
    				}
	            else {
					compareBuffer[minIndex] = null;
				}
	            
	            
	            
//	            System.out.println("min: "+minIndex+" "+min);
	            
	            
	            
//	            System.out.println("replaced:");
	            
//	            for (int j = 0;j<numberOfSublist;j++) {
//	            		System.out.println(" "+j+") "+compareBuffer[j]);	            	
//	            }
	            
	            
	            for (int j = 0;j<numberOfSublist;j++) {
	            	if (compareBuffer[j]!=null && min != null)
	            		if (min.equals(compareBuffer[j].substring(0, 8))){
	            			//System.out.println(min+"<->"+compareBuffer[j]+" "+j);
	            			c += Integer.parseInt(compareBuffer[j].substring(9));
	            			//System.out.println(min+":   "+counter);
	            			if (indexesN[j]<45000) {
	            				compareBuffer[j] = indexes.get(j).readLine();
		            			indexesN[j]++;
		            			p++;	
	            			}
	            			else {
								compareBuffer[j] = null;
							}
	            			
	            			
	            			j--;
	            		}
	            }
	            //System.out.println(p+")"+min+": "+c);	            
	            //min = compareBuffer[1].substring(0, 8);
	            if (c<0) c=0;
	            bw1.write(min+":"+c);
	            bw1.newLine();
	            
            			
            }
            
            bw1.close();
            for (int j = 0;j<numberOfSublist;j++) {
            		indexes.get(j).close();
            }
            indexes.clear();
            min = null;
            in = null;
            indexesN = null;
            compareBuffer = null;
            minIndex = 0;
            number = 0;
            numberOfSublist = 0;
            
            
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            System.out.println(elapsedTime);
            
            
            




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



