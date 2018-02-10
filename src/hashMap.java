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
            while(((content=br1.readLine())!=null)){

                for (i = 0;i<45000;i++) {
                    content=br1.readLine();
                    if(content!=null) {
                        buffer.add(content);
                    }
                }
                buffer =(ArrayList<String>) buffer
                        .stream()
                        .sorted(sAscComp)
                        .collect(Collectors.toList());

                for (i = 0;i<buffer.size();i++) {
                    bw1.newLine();
                    bw1.write(buffer.get(i));

                }
                buffer.clear();
            }

            //cleanup again
            br1.close();
            bw1.close();
            map.clear();
            content = null;
            br1 = new BufferedReader(new FileReader(premap));
            bw1 = new BufferedWriter(new FileWriter(damap));

            //some crazy nonworking shit code, even without strong idea
//            while(((content=br1.readLine())!=null)){
//
//                for (i = 0;i<45000;i++) {
//                    content=br1.readLine();
//                    if(content!=null) {
//                        buffer.add(content);
//                    }
//                }
//                buffer =(ArrayList<String>) buffer
//                        .stream()
//                        .sorted(sAscComp)
//                        .collect(Collectors.toList());
////                for (i = 0;i<buffer.size();i++) {
////                    int value = Integer.parseInt(buffer.get(i).substring(9));
////                    map.merge(buffer.get(i).substring(0,8),value, (k,v)->v+value);
////                }
//                for (i = 0;i<buffer.size();i++) {
//                    bw1.newLine();
//                    bw1.write(buffer.get(i));
//
//                }
//                buffer.clear();
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




