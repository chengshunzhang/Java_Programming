
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        int times = 0, idx = stringb.indexOf(stringa, 0);
        while(idx != -1) {
            times++;
            idx = stringb.indexOf(stringa, idx + stringa.length());
        }
        return times;
    }
    
    public void testHowMany() {
        String sa = "GAA", sb = "ATGAACGAATTGAATC";
        System.out.println(howMany(sa, sb));
        sa = "AA";
        sb = "ATAAAA";
        System.out.println(howMany(sa, sb));
    }
    
    public static void main(String[] args) {
        Part2 p2 = new Part2();
        p2.testHowMany();
    }
}
