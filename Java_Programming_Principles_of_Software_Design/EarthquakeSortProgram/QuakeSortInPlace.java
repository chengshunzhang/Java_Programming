
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    private int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i = from + 1; i < quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    private int getLargestDepth(ArrayList<QuakeEntry> quakes, int from) {
        int maxIdx = from;
        for(int i = from + 1; i < quakes.size(); i++) {
            if(quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       for (int i = 0; i < in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        for(int i = 0; i < in.size(); i++) {
            int maxIdx = getLargestDepth(in, i);
            if(maxIdx != i) {
                QuakeEntry qi = in.get(i);
                QuakeEntry qmax = in.get(maxIdx);
                in.set(i, qmax);
                in.set(maxIdx, qi);
            }
        }
    }
    
    private void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for(int i = 0; i + 1 < quakeData.size() - numSorted; i++) {
            if(quakeData.get(i).getMagnitude() > quakeData.get(i + 1).getMagnitude()) {
                QuakeEntry temp = quakeData.get(i);
                quakeData.set(i, quakeData.get(i + 1));
                quakeData.set(i + 1, temp);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for(int i = 0; i + 1 < in.size(); i++) {
            onePassBubbleSort(in, i);
        }
    }
    
    private boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        for(int i = 0; i + 1 < quakes.size(); i++) {
            if(quakes.get(i).getMagnitude() > quakes.get(i + 1).getMagnitude()) {
                return false;
            }
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        for(int i = 0; i + 1 < in.size(); i++) {
            if(checkInSortedOrder(in)) {
                System.out.println("need " + i + " passes");
                return;
            }
            onePassBubbleSort(in, i);
        }
        System.out.println("need " + (in.size() - 1) + " passes");
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < in.size(); i++) {
            if(checkInSortedOrder(in)) {
                System.out.println("need " + i + " passes");
                return;
            }
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry temp = in.get(i);
            in.set(i,in.get(minIdx));
            in.set(minIdx,temp);
        }
        System.out.println("need " + (in.size() - 1) + " passes");
    }
    
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/earthquakeDataSampleSix2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        //sortByMagnitudeWithBubbleSortWithCheck(list);
        sortByMagnitudeWithCheck(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
            qe.getLocation().getLatitude(),
            qe.getLocation().getLongitude(),
            qe.getMagnitude(),
            qe.getInfo());
        }
    }
}
