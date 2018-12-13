
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public float cgRatio(String dna) {
        int num = 0;
        for(int i = 0; i < dna.length(); i++) {
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G') {
                num++;
            }
        }
        return (float)num / dna.length();
    }
    
    public int countCTG(String dna) {
        int count = 0, index = dna.indexOf("CTG");
        while(index != -1) {
            count++;
            index = dna.indexOf("CTG", index + 3);
        }
        return count;
    }
    
    public void testCgRatio() {
        String dna = "ATGCCATAG";
        System.out.println(cgRatio(dna));
    }
    
    public static void main(String[] args) {
        Part2 p2 = new Part2();
        p2.testCgRatio();
    }
}
