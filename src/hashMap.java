import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter; 

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class hashMap {

    private static File inFile1 = new File("T1.txt");
    private static File inFile2 = new File("T2.txt");
    private static File premap = new File("premap.txt");
    private static File premap1 = new File("premap1.txt");
    private static File damap = new File("map.txt");
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
        System.out.println(heapMaxSize);
        int bs= (int) ((heapMaxSize/1000)*1.5);
        int sublists = 0;
        int number = 0;
        
        
        try {
            br1 = new BufferedReader(new FileReader(inFile1));
            br2 = new BufferedReader(new FileReader(inFile2));
            bw1 = new BufferedWriter(new FileWriter(premap));
            String content = null;
            int i=0;
            

            //here is the map landing from 2 files
            while(((content=br1.readLine())!=null)){
                 
                for (i = 0;i<bs;i++) {
                    content=br1.readLine();
                    if(content!=null) {
                        buffer.add(content);
                        
                    }
                }
                for (i = 0;i<buffer.size();i++) {
                	if (map.get(buffer.get(i))==null) {
                		map.put(buffer.get(i), 1);
                	} else {
                		map.put(buffer.get(i), map.get(buffer.get(i))+1);
                	}

                }
                buffer.clear();

                for (i = 0;i<bs;i++) {
                    content=br2.readLine();
                    if(content!=null) {
                        buffer.add(content);
                        
                    }
                }
                for (i = 0;i<buffer.size();i++) {
                	if (map.get(buffer.get(i))==null) {
                		map.put(buffer.get(i), -1);
                	} else {
                		map.put(buffer.get(i), map.get(buffer.get(i))-1);
                	}
                }
                buffer.clear();
                for (String p: map.keySet()){
                    bw1.write(p+" "+map.get(p)+"\n");
                }
                number+=map.size();
                map.clear();
                sublists++;
            }
            //some cleanup
            br1.close();
            br2.close();
            bw1.close();
            
//            //merge

       //     System.out.println(numberOfSublist);
            int[] indexesN = new int[sublists];
            String[] compareBuffer = new String[sublists];
            ArrayList<BufferedReader> indexes = new ArrayList<>();

            for (int j=0;j<sublists;j++) {
                    br1 = new BufferedReader(new FileReader(premap));
                    for (int skip=0; skip<(bs*2)*j;skip++) {
                        br1.readLine();
                    }
                    indexes.add(br1);
                    compareBuffer[j] = (br1.readLine());
                    indexesN[j]= 1;
            }
            
            bw1 = new BufferedWriter(new FileWriter(damap));            
            String min = compareBuffer[0].substring(0,100);
            String in = null;
            int minIndex = 0;
            int p = 0;
            int c = 0;

            while (p<number) {
                c = 0;
                min = "zzzzzzzzzJohn   Smiths 111222999999999 1455             Maisonneuve West, Montreal,       QC, H3G 1M8";
                    
                minIndex = 0;
                for (int j = 0;j<sublists;j++) {// find min
                        
//                      System.out.println(j+") "+compareBuffer[j]);
                    
                        if (compareBuffer[j]!=null) {
                                if (compareBuffer[j].substring(0,100).compareTo(min.substring(0,100))<0) {
                                    min = compareBuffer[j];
                                    minIndex = j;
                                }
                            }
                        
                }
                if (compareBuffer[minIndex]==null) break;
                
                
                c = Integer.parseInt(compareBuffer[minIndex].substring(101));
                
                if (indexesN[minIndex]<bs*2) {
                        compareBuffer[minIndex] = indexes.get(minIndex).readLine();
                        indexesN[minIndex]++;
                        p++;
                    }
                else {
                    compareBuffer[minIndex] = null;
                }
                
                
                
//              System.out.println("min: "+minIndex+" "+min);
                
                
                
//              System.out.println("replaced:");
                
//              for (int j = 0;j<numberOfSublist;j++) {
//                      System.out.println(" "+j+") "+compareBuffer[j]);                    
//              }
                
                
                for (int j = 0;j<sublists;j++) {
                    if (compareBuffer[j]!=null && min != null)
                        if (min.equals(compareBuffer[j].substring(0,100))){
                            //System.out.println(min+"<->"+compareBuffer[j]+" "+j);
                            c += Integer.parseInt(compareBuffer[j].substring(101));
                            //System.out.println(min+":   "+counter);
                            if (indexesN[j]<bs*2) {
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
                bw1.write(min.substring(0,100)+" "+c);
                bw1.newLine();
                
                        
            }
            
            bw1.close();
            for (int j = 0;j<sublists;j++) {
                    indexes.get(j).close();
            }
            
            indexes.clear();
            min = null;
            in = null;
            indexesN = null;
            compareBuffer = null;
            minIndex = 0;
            number = 0;
            sublists = 0;
            
            
            //result generator
            br1.close();
            br2.close();
            

            // last phase
            
            
//            
//            br1 = new BufferedReader(new FileReader(damap));
//            bw1 = new BufferedWriter(new FileWriter(result));
//           
//            buffer = null;
//            
//            int mapChunk = 20000;
//            int iChunk = 4000;
//            ArrayList<String>  buff1 = new ArrayList<>();
//            content = null;
//            String fLine = null;
//            HashMap<String, String> sBuff1 = new HashMap<String, String>();
//            
//            while((content=br1.readLine())!=null) {
//                buff1.add(content);
//                for (i=0;i<mapChunk;i++) {
//                    content=br1.readLine();
//                    if(content!=null) {
//                        buff1.add(content);
//                    }                    
//                }
//                //System.out.println("Map chunk is here");
//                br2 = new BufferedReader(new FileReader(inFile1));
//                while((fLine=br2.readLine())!=null){
//                    
//                    for (i=0;i<iChunk;i++) {
//                        fLine = br2.readLine();
//                        if(fLine!=null) {
//                            sBuff1.put(fLine.substring(0,8),fLine);
//                        }
//                    }
//                    //System.out.println("chunk t1 is here");
//                    for(String mapLine : buff1) {
//                        if (sBuff1.get(mapLine.substring(0,8))!=null) {
//                        bw1.write(sBuff1.get(mapLine.substring(0,8))+" "+mapLine.substring(9)+"\n");
//                        }
//
//                    }
//                    sBuff1.clear();
//                    
//                }
//                buff1.clear();
//                
//            }            
//            br1.close();
//            br2.close();
//            bw1.close();
            
            
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



