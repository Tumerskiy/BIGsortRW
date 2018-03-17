import java.io.File;
import java.nio.file.Files;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter; 
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Collections; 
import java.nio.charset.Charset;
//

public class MP2 {
	private static File inFile1 = new File("T1.txt");
	private static File inFile2 = new File("T2.txt");
	private static File premap1 = new File("premap1.txt");
	private static File premap2 = new File("premap2.txt");
	private static File result = new File("result.txt");

	private static ArrayList<String> buffer;

	private static TreeMap<String, Integer> map = new TreeMap<String, Integer>();	 
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
        int bbs = bs*15;
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
            int i=0;
           
            while((content=br1.readLine())!=null){
                if (content!=null) {
                		buffer.add(content);
                		numberOfIO+=100;
                }
                for (i = 1;i<bbs;i++) {
                    content=br1.readLine();
                    if(content!=null) {
                        buffer.add(content);
                        numberOfIO+=100;
                    }
                }
                Collections.sort(buffer);
                //buffer.sort(String::compareToIgnoreCase);
                for (String line: buffer) {
                	bw1.write(line+"\n");
                    numberOfIO+=100;
                    number1+=buffer.size();
                }
                sublists1++;
                buffer.clear();
            }
            while((content1=br2.readLine())!=null){
                if (content1!=null) {
                		buffer.add(content1);
                		numberOfIO+=27;
                }
                for (i = 1;i<bbs*3.7;i++) {
                    content1=br2.readLine();
                    if(content1!=null) {
                        buffer.add(content1);
                        numberOfIO+=27;
                    }
                }
                Collections.sort(buffer);
                //buffer.sort(String::compareToIgnoreCase);
                for (String line: buffer) {
                	bw2.write(line+"\n");
                    numberOfIO+=27;
                    number2+=buffer.size(); 
                }
                sublists2++;
                buffer.clear();
            }
            
            
            //some cleanup
            br1.close();
            br2.close();
            bw1.close();
            long time = System.currentTimeMillis()-startTime;
            System.out.println("time:"+ time +" sublists1:"+sublists1+" sublists2:"+sublists2+" number1:"+number1+" number2:"+number2+" IO:"+numberOfIO );
            System.out.println("Free memory left: "+Runtime.getRuntime().freeMemory());
            
         // right join T1 T2 GPA
            
            
            
            
            int[] indexesN1 = new int[sublists1];
            String[] compareBuffer1 = new String[sublists2];
            ArrayList<BufferedReader> indexes1 = new ArrayList<>();
            
            int[] indexesN2 = new int[sublists2];
            String[] compareBuffer2 = new String[sublists2];
            ArrayList<BufferedReader> indexes2 = new ArrayList<>();
            
            for (int j=0;j<sublists1;j++) {
    			br1 = new BufferedReader(new FileReader(premap1));
                for (int skip=0; skip<(bbs)*j;skip++) {
    				br1.readLine();
                }
                indexes1.add(br1);
                compareBuffer1[j] = (br1.readLine());
                indexesN1[j]= 1;
            }
            
            for (int j=0;j<sublists2;j++) {
    			br1 = new BufferedReader(new FileReader(premap2));
                for (int skip=0; skip<(bbs*3.7)*j;skip++) {
    				br1.readLine();
                }
                indexes2.add(br1);
                compareBuffer2[j] = (br1.readLine());
                indexesN2[j]= 1;
            }
            System.out.println("Free memory left: "+Runtime.getRuntime().freeMemory());
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
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
        
	}
}
