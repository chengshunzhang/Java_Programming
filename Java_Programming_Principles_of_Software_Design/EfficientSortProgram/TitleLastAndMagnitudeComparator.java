import java.util.*;
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        String[] words1 = qe1.getInfo().split(" ");
        String[] words2 = qe2.getInfo().split(" ");
        if(words1[words1.length - 1].compareTo(words2[words2.length - 1]) < 0) {
            return -1;
        } else if(words1[words1.length - 1].compareTo(words2[words2.length - 1]) > 0) {
            return 1;
        } else {
            return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
        }
    }
}
