import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;

public class test {
static public void main(String[] args) {
	try {
	BufferedReader t2 = new BufferedReader(new FileReader(new File("result.txt")));
	BufferedReader sortedt2 = new BufferedReader(new FileReader(new File("LoopResult.txt")));
	//ArrayList<String> in = new ArrayList<>();
	HashSet<String> in = new HashSet<>();
	HashSet<String> out = new HashSet<>();
	String content1 = null;
	String content2 = null;
	while ((content1=t2.readLine())!=null) {
		in.add(content1);
	}
	
	while ((content2=sortedt2.readLine())!=null) {
		out.add(content2);
	}
	
	for(String line: in) {
		if (!out.contains(line)) {
			System.out.println(line);
		}
	}
	
	
	} catch (Exception e){
		e.printStackTrace();
	}

}
}
