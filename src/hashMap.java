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
    private static File outFile2 = new File("");

    private static ArrayList<String> buffer;

    private static HashMap<String, Integer> map = new HashMap();



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
            final FileWriter fw = new FileWriter(premap);
            String content = null;
            int i=0;
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
            //TODO:merge premap, to make a proper hashmap of distinct keys
//            br1.close();
//            br2.close();
//            bw1.close();
//            br1 = new BufferedReader(new FileReader(premap));
//            content=null;
//            buffer.clear();
//            while(((content=br1.readLine())!=null)){
//                for (i = 0;i<20000;i++) {
//                    content=br1.readLine();
//                    if(content!=null) {
//                        buffer.add(content);
//                    }
//                }
//            }

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




