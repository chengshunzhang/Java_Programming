
/**
 * Find N-closest quakes
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = quakeData;
        if(howMany > quakeData.size()) {
            howMany = quakeData.size();
        }
        for(int i = 0; i < howMany; i++) {
            double min = copy.get(0).getLocation().distanceTo(current);
            int index = 0;
            for(int idx = 1; idx < copy.size(); idx++) {
                double currDist = copy.get(idx).getLocation().distanceTo(current);
                if(currDist < min) {
                    min = currDist;
                    index = idx;
                }
            }
            ret.add(copy.get(index));
            copy.remove(index);
        }
        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,3);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: " + close.size());
    }
    
}
