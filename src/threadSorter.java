import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.ArrayList;

class threadSorter implements Callable<ArrayList<String>> {
    ArrayList<String> sorted;
    public threadSorter(ArrayList<String> ar) {
        sorted = ar;
    }

    @Override
    public ArrayList<String> call() throws Exception {
        Collections.sort(sorted, new AscComparator());
        return sorted;
    }


}