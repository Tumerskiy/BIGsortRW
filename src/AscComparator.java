import java.util.Comparator;

class AscComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        //return Integer.parseInt(o1.substring(0,8)) - Integer.parseInt(o2.substring(0,8));
        return o1.substring(0,8).compareTo(o2.substring(0,8));

    }
}