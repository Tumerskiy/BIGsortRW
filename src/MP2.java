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
import java.util.TreeSet;
//

public class MP2 {
	private static File inFile1 = new File("T1.txt");
	private static File inFile2 = new File("T2.txt");
	private static File premap1 = new File("premap1.txt");
	private static File premap2 = new File("premap2.txt");
	private static File result = new File("result.txt");

	private static ArrayList<String> buffer;
	private static TreeSet<String> set = new TreeSet<String>();
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
            long time = System.currentTimeMillis()-startTime;
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
