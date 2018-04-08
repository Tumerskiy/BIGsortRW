import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class MP1 {
    private static File inFile1 = new File("T1.txt");
    private static File inFile2 = new File("T2.txt");
    private static File result = new File("LoopResult.txt");
    private static File gpaResult = new File("Loopgpa.txt");
    
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
    
    
    
    
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        BufferedReader br1 = null;
        BufferedReader br2 = null;
        BufferedWriter bw1 = null;
        BufferedWriter bw2 = null;
//        int bbs1 = 8400;
        int heap = (int)Runtime.getRuntime().totalMemory();
        int bbs1 = heap/1153;
        String content = null;
        long numberOfIO = 0;
        HashMap<String, String> t1;
        HashMap<String, Integer> credits;
        HashMap<String, Integer> grades;
        ArrayList<String> t2 = new ArrayList<>();
        
        try {
//              System.out.println("Free memory left: "+Runtime.getRuntime().freeMemory()/1024+"KB");
                br1 = new BufferedReader(new FileReader(inFile1));
                bw2 = new BufferedWriter(new FileWriter(gpaResult));
                bw1 = new BufferedWriter(new FileWriter(result));
                
                while((content=br1.readLine())!=null){
                    t1 = new HashMap<>();
                    credits = new HashMap<>();
                    grades = new HashMap<>();
                    if (content!=null) {
                        t1.put(content.substring(0, 8), content.substring(8));
                        credits.put(content.substring(0, 8), 0);
                        grades.put(content.substring(0, 8), 0);
                        numberOfIO+=100;
                    }
                    for (int i = 1;i<bbs1;i++) {
                        if ((content=br1.readLine())!=null) {
                            t1.put(content.substring(0, 8), content.substring(8));
                            numberOfIO++;
                            credits.put(content.substring(0, 8), 0);
                            grades.put(content.substring(0, 8), 0);
                            numberOfIO+=100;
                        }
                    
                    }
//                  System.out.println(t1.size());
                    br2 = new BufferedReader(new FileReader(inFile2));
                    while((content=br2.readLine())!=null){
                        if (t1.get(content.substring(0, 8))!=null) {
                        	bw1.write(content.substring(0, 8)+t1.get(content.substring(0, 8))+content.substring(8));
                            bw1.newLine();
                            numberOfIO+=119;
                            credits.put(content.substring(0, 8), credits.get(content.substring(0, 8)) + Integer.parseInt(content.substring(21, 23).trim()));
                            grades.put(content.substring(0, 8), grades.get(content.substring(0, 8)) + convertGrade(content.substring(23)) * Integer.parseInt(content.substring(21, 23).trim()));
                        }
                        numberOfIO+=27;
                    }
                    br2.close();
                    // read from credits grades and t1
//              gpa formula (double)Math.round((points/credits)*10.0)/10.0

                    for(String key: t1.keySet()){
                        if(grades.get(key)!=null && credits.get(key)!=null) {
                            bw2.write(key + " " + Math.round(((double)grades.get(key)/(double)credits.get(key)))/10.0+"\n");
//                            bw2.write(key + " " + grades.get(key)/credits.get(key)+"\n");
                            numberOfIO+=12;
                            
                        }
                    }
                }

                t2.clear();
                br2.close();
                br1.close();
                bw1.close();
                bw2.close();
//                System.out.println("Free memory left: "+Runtime.getRuntime().freeMemory()/1024+"KB");
                System.out.println("Nested Loop time:"+ (System.currentTimeMillis() -  startTime) +" IO:"+numberOfIO/4096 );
                
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}