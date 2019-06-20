
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings() {
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        //ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        int minimalRaters = 1;
        ArrayList<Rating> avgRatings = tr.getAverageRatings(minimalRaters);
        Collections.sort(avgRatings);
        for(Rating rt : avgRatings) {
            System.out.println(rt.getValue() + " " + MovieDatabase.getTitle(rt.getItem()));
        }
        System.out.println(avgRatings.size() + " movies have " + minimalRaters + " or more ratings");
    }
    
    public void printAverageRatingsByYear() {
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        //ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        int minimalRaters = 1;
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, new YearAfterFilter(2000));
        Collections.sort(avgRatings);
        for(Rating rt : avgRatings) {
            System.out.println(rt.getValue() + " " + MovieDatabase.getTitle(rt.getItem()));
        }
        System.out.println(avgRatings.size() + " movies have " + minimalRaters + " or more ratings");
    }
}
