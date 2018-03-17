import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter; 
import java.util.ArrayList;
import java.util.TreeMap;


public class hashMap {

    private static File inFile1 = new File("T1.txt");
    private static File inFile2 = new File("T2.txt");
    private static File premap = new File("premap.txt");
    private static File result = new File("result.txt");

    private static ArrayList<String> buffer;

    private static TreeMap<String, Integer> map = new TreeMap<String, Integer>();



    public static void main(String[] args) {
        //Comparator<String> sAscComp = (s1, s2) -> s1.substring(0, 8).compareTo(s2.substring(0, 8));
        long startTime = System.currentTimeMillis();
        BufferedReader br1 = null;
        BufferedReader br2 = null;
        BufferedWriter bw1 = null;
        BufferedWriter bw2 = null;
        buffer = new ArrayList<>();
        long heapMaxSize = Runtime.getRuntime().maxMemory();
        //System.out.println(heapMaxSize);
        int bs= (int) ((heapMaxSize/10000));
        int bbs = bs*17;
        int sublists = 0;
        int number = 0;
        long numberOfIO = 0;
        
        
        try {
            br1 = new BufferedReader(new FileReader(inFile1));
            br2 = new BufferedReader(new FileReader(inFile2));
            bw1 = new BufferedWriter(new FileWriter(premap));
            String content = null;
            String content1 = null;
            int i=0;
            

            //here is the map landing from 2 files
            while(((content=br1.readLine())!=null) || ((content1=br2.readLine())!=null)){
            	
                if (content!=null) {
                		buffer.add(content);
                		numberOfIO+=100;
                }
                numberOfIO++; 
                for (i = 1;i<bs;i++) {
                    content=br1.readLine();
                    if(content!=null) {
                        buffer.add(content);
                        numberOfIO+=100;
                    }
                }
                for (i = 0;i<buffer.size();i++) {
                	if (map.get(buffer.get(i))==null) {
                		map.put(buffer.get(i), 1);
                	} else {
                		map.put(buffer.get(i), map.get(buffer.get(i))+1);
                	}
                	if (map.size()==bbs) {
                		for (String p: map.keySet()){
                            bw1.write(p+" "+map.get(p)+"\n");
                            numberOfIO+=102;
                        }
                        number+=map.size();
                        map.clear();
                        sublists++;
                	}

                }
                buffer.clear();
                if (content1!=null) {
                		buffer.add(content1);
                		numberOfIO+=100;
                }
                for (i = 1;i<bs;i++) {
                    content=br2.readLine();
                    if(content!=null) {
                        buffer.add(content);
                        numberOfIO+=100;
                    }
                }
                for (i = 0;i<buffer.size();i++) {
                		if (map.get(buffer.get(i))==null) {
                			map.put(buffer.get(i), -1);
                		} else {
                			map.put(buffer.get(i), map.get(buffer.get(i))-1);
                		}
                		if (map.size()==bbs) {
                    		for (String p: map.keySet()){
                                bw1.write(p+" "+map.get(p)+"\n");
                                numberOfIO+=102;
                            }
                            number+=map.size();
                            map.clear();
                            sublists++;
                    	}
                }
                buffer.clear();
            }
            
            for (String p: map.keySet()){
                bw1.write(p+" "+map.get(p)+"\n");
                numberOfIO+=102;
            }
            number+=map.size();
            map.clear();
            sublists++;
            
            //some cleanup
            br1.close();
            br2.close();
            bw1.close();
            
            //merge
            int[] indexesN = new int[sublists];
            String[] compareBuffer = new String[sublists];
            ArrayList<BufferedReader> indexes = new ArrayList<>();
            
            System.out.println(sublists);
            

            for (int j=0;j<sublists;j++) {
				br1 = new BufferedReader(new FileReader(premap));
                for (int skip=0; skip<(bbs)*j;skip++) {
					br1.readLine();
					//numberOfIO+=102;
                }
                indexes.add(br1);
                compareBuffer[j] = (br1.readLine());
                indexesN[j]= 1;
            }
            
            System.out.println("readers!");
            
            bw1 = new BufferedWriter(new FileWriter(result));            
            String min = compareBuffer[0].substring(0,100);
            int minIndex = 0;
            int p = 0;
            int c = 0;
            //System.out.println(heapMaxSize/1000/200);
            while (p<number) {
                c = 0;
                min = "99999999ZZZZZZZZZZZZZZZZZZZZ999999999999999ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ";
                    
                minIndex = 0;
                for (int j = 0;j<sublists;j++) {// find min
                        if (compareBuffer[j]!=null) {
                                if (compareBuffer[j].substring(0,100).compareTo(min.substring(0,100))<0) {
                                    min = compareBuffer[j].substring(0,100);
                                    minIndex = j;
                                }
                            }
                        
                }
                if (compareBuffer[minIndex]==null) break;
                
                
                c = Integer.parseInt(compareBuffer[minIndex].substring(101));
                
                if (indexesN[minIndex]<bbs) {
                        compareBuffer[minIndex] = indexes.get(minIndex).readLine();
                        indexesN[minIndex]++;
                        p++;
                        numberOfIO+=102;
                    }
                else {
                    compareBuffer[minIndex] = null;
                }
                
                for (int j = 0;j<sublists;j++) {
                    if (compareBuffer[j]!=null && min != null)
                        if (min.equals(compareBuffer[j].substring(0,100))){
                            c += Integer.parseInt(compareBuffer[j].substring(101));
                            if (indexesN[j]<bbs) {
                                compareBuffer[j] = indexes.get(j).readLine();
                                indexesN[j]++;
                                p++;
                                numberOfIO+=102;
                            }
                            else {
                                compareBuffer[j] = null;
                            }
                            
                            
                            j--;
                        }
                }
                if (c<0) c=0;
                bw1.write(min.substring(0,100)+" "+c);
                bw1.newLine();
                numberOfIO+=102;
            }
            
            bw1.close();
            for (int j = 0;j<sublists;j++) {
                indexes.get(j).close();
            }
            
            indexes.clear();
            min = null;
            indexesN = null;
            compareBuffer = null;
            minIndex = 0;
            sublists = 0;
            br1.close();
            br2.close();
                       
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            System.out.println("Elapsed time: "+elapsedTime);
//            System.out.println("number of IO: "+numberOfIO);
            System.out.println("Number of blocks IO: "+(long) Math.ceil(numberOfIO / 40.0/4096));
            System.out.println("total number of tuples: "+number);
            System.out.println("Number of blocks used to store all tuples: "+(int) Math.ceil(number / 40.0));
            number = 0;
            numberOfIO = 0;
            heapMaxSize = 0;
            System.out.println("Free memory left: "+Runtime.getRuntime().freeMemory());
            

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



