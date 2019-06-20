
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters) {
        double avg = 0.0;
        int numRaters = 0;
        for(Rater rater : myRaters) {
            if(rater.hasRating(id)) {
                numRaters++;
                avg += rater.getRating(id);
            }
        }
        if(numRaters >= minimalRaters) {
            return avg / numRaters;
        }
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> avgRatings = new ArrayList<Rating> ();
        for(String movie : movies) {
            double avg = getAverageByID(movie, minimalRaters);
            if(avg > 0.0) {
                avgRatings.add(new Rating(movie, avg));
            }
        }
        return avgRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> avgRatings = new ArrayList<Rating> ();
        for(String movie : movies) {
            double avg = getAverageByID(movie, minimalRaters);
            if(avg > 0.0) {
                avgRatings.add(new Rating(movie, avg));
            }
        }
        return avgRatings;
    }
}
