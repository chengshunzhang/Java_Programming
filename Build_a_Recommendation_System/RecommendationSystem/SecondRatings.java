
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
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
        ArrayList<Rating> avgRatings = new ArrayList<Rating> ();
        for(Movie movie : myMovies) {
            double avg = getAverageByID(movie.getID(), minimalRaters);
            if(avg > 0.0) {
                avgRatings.add(new Rating(movie.getID(), avg));
            }
        }
        return avgRatings;
    }
    
    public String getTitle(String id) {
        for(Movie movie : myMovies) {
            if(movie.getID().equals(id)) {
                return movie.getTitle();
            }
        }
        return "ID not found!";
    }
    
    public String getID(String title) {
        for(Movie movie : myMovies) {
            if(movie.getTitle().equals(title)) {
                return movie.getID();
            }
        }
        return "NO SUCH TITLE!";
    }
}