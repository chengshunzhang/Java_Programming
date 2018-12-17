import edu.duke.*;
import java.util.*;
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String> ();
        myFreqs = new ArrayList<Integer> ();
    }
    
    private void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for(String word : fr.words()) {
            int index = myWords.indexOf(word.toLowerCase());
            if(index != -1) {
                myFreqs.set(index, myFreqs.get(index) + 1);
            } else {
                myWords.add(word.toLowerCase());
                myFreqs.add(1);
            }
        }
    }
    
    private int findIndexOfMax() {
        int index = -1, max = -1;
        for(int i = 0; i < myFreqs.size(); i++) {
            if(myFreqs.get(i) > max) {
                index = i;
                max = myFreqs.get(i);
            }
        }
        return index;
    }
    
    public void tester() {
        findUnique();
        System.out.println("# of unique words: " + myWords.size());
        //for(int i = 0; i < myWords.size(); i++) {
        //    System.out.println(myFreqs.get(i) + "  " + myWords.get(i));
        //}
        int maxIdx = findIndexOfMax();
        System.out.println("The word that occurs most often is " + myWords.get(maxIdx) + " and its count is " + myFreqs.get(maxIdx));
    }
}
