import java.util.*;
/**
 * Findint the largest magnitude earthquakes.
 * 
 * @author Chengshun Zhang 
 * @version 1.0 12/26/2018
 */
public class LargestQuakes {
    private int indexOfLargest(ArrayList<QuakeEntry> quakeData) {
        int index = 0;
        double max = quakeData.get(0).getMagnitude();
        for(int i = 1; i < quakeData.size(); i++) {
            double curr = quakeData.get(i).getMagnitude();
            if(curr > max) {
                max = curr;
                index = i;
            }
        }
        return index;
    }
    
    private ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry> ();
        ArrayList<QuakeEntry> copy = quakeData;
        if(howMany > copy.size()) {
            howMany = copy.size();
        }
        for(int i = 0; i < howMany; i++) {
            int index = indexOfLargest(copy);
            ret.add(copy.get(index));
            copy.remove(index);
        }
        return ret;
    }
    
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size());
        ArrayList<QuakeEntry> largests = getLargest(list, 5);
        for(QuakeEntry qe : largests) {
            System.out.println(qe);
        }
        
        //for(QuakeEntry qe : list) {
        //    System.out.println(qe);
        //}
        
        //int largestId = indexOfLargest(list);
        //System.out.println("largest earthquake is at location " + largestId + " and has magnitude " + list.get(largestId).getMagnitude());
    }
}
