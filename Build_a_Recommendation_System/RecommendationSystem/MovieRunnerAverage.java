
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {
    public void printAverageRatings() {
        //SecondRatings sr = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
        SecondRatings sr = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        System.out.println("Number of movies: " + sr.getMovieSize());
        System.out.println("Number of raters: " + sr.getRaterSize());
        int minimalRaters = 12;
        ArrayList<Rating> avgRatings = sr.getAverageRatings(minimalRaters);
        Collections.sort(avgRatings);
        //for(Rating rt : avgRatings) {
        //    System.out.println(rt.getValue() + " " + sr.getTitle(rt.getItem()));
        //}
        System.out.println(avgRatings.size() + " movies have " + minimalRaters + " or more ratings");
        System.out.println(sr.getTitle(avgRatings.get(0).getItem()));
    }
    
    public void getAverageRatingOneMovie() {
        //SecondRatings sr = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
        SecondRatings sr = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        String title = "Vacation";
        int minimalRaters = 3;
        double avg = 0.0;
        ArrayList<Rating> avgRatings = sr.getAverageRatings(minimalRaters);
        for(Rating rt : avgRatings) {
            if(sr.getID(title).equals(rt.getItem())) {
                avg = rt.getValue();
                System.out.println("Average rating for movie " + title + " is " + avg);
                break;
            }
        }
    }
}
