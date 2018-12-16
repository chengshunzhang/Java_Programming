import edu.duke.*;
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {
    public void countWordLengths(FileResource fr, int[] counts) {
        for(String word : fr.words()) {
            int len = word.length();
            if(!Character.isLetter(word.charAt(len - 1))) {
                len--;
            }
            if(!Character.isLetter(word.charAt(0))) {
                len--;
            }
            if(len >= counts.length) {
                counts[counts.length - 1]++;
            } else if(len > 0){
                counts[len]++;
            }
        }
    }
    
    public int indexOfMax(int[] values) {
        if(values.length == 0) {
            return -1;
        }
        int max = values[0], index = 0;
        for(int i = 1; i < values.length; i++) {
            if(values[i] > max) {
                max = values[i];
                index = i;
            }
        }
        return index;
    }
    
    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        for(int i = 0; i < counts.length; i++) {
            if(counts[i] != 0) {
                System.out.println(counts[i] + " words of length " + i);
            }
        }
        System.out.println("Most Common Word Length: " + indexOfMax(counts));
    }
}
