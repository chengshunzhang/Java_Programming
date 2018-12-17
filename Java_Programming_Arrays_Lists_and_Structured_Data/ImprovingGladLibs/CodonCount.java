import edu.duke.*;
import java.util.*;
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CodonCount {
    private HashMap<String, Integer> myMap;
    
    public CodonCount() {
        myMap = new HashMap<String, Integer> ();
    }
    
    private void buildCodonMap(int start, String dna) {
        myMap.clear();
        for(int i = start; i + 3 <= dna.length(); i += 3) {
            String codon = dna.substring(i, i + 3);
            if(!Character.isLetter(codon.charAt(2))) {
                break;
            }
            if(!myMap.containsKey(codon)) {
                myMap.put(codon, 1);
            } else {
                myMap.put(codon, myMap.get(codon) + 1);
            }
        }
    }
    
    private String getMostCommonCodon() {
        String result = null;
        int maxCount = -1;
        for(String s : myMap.keySet()) {
            if(result == null || myMap.get(s) > maxCount) {
                result = s;
                maxCount = myMap.get(s);
            }
        }
        return result;
    }
    
    private void printCodonCounts(int start, int end) {
        System.out.println("Counts of codon between " + start + " and " + end + " are:");
        for(String s : myMap.keySet()) {
            if(myMap.get(s) >= start && myMap.get(s) <= end) {
                System.out.println(s + "\t" + myMap.get(s));
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase();
        for(int i = 0; i < 1; i++) {
            buildCodonMap(i, dna);
            System.out.println("Reading frame starting with " + i + " results in " + myMap.size() + " unique codons");
            String mcc = getMostCommonCodon();
            System.out.println("Most common codon is " + mcc + " with count " + myMap.get(mcc));
            printCodonCounts(7, 7);
        }
    }
}
