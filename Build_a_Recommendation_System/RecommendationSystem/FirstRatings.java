
/**
 * Process the movie and ratings data.
 * 
 * @chengshun (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename) {
        FileResource fr = new FileResource("data/" + filename);
        ArrayList<Movie> movies = new ArrayList<Movie> ();
        for(CSVRecord rec : fr.getCSVParser()) {
            movies.add(new Movie(rec.get("id"), rec.get("title"), rec.get("year"),
                                 rec.get("genre"), rec.get("director"),
                                 rec.get("country"), rec.get("poster"),
                                 Integer.parseInt(rec.get("minutes"))));
        }
        return movies;
    }
    
    public void testLoadMovies() {
        //ArrayList<Movie> movies = loadMovies("ratedmovies_short.csv");
        ArrayList<Movie> movies = loadMovies("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + movies.size());
        //for(Movie movie : movies) {
        //    System.out.println(movie);
        //}
        int comedyNum = 0;
        int longerThan150Num = 0;
        int maxNum = 0;
        StringBuilder sb = new StringBuilder();
        HashMap<String, Integer> directMovies = new HashMap<String, Integer> ();
        for(Movie movie : movies) {
            if(movie.getGenres().indexOf("Comedy") >= 0) {
                comedyNum++;
            }
            if(movie.getMinutes() > 150) {
                longerThan150Num++;
            }
            String[] directors = movie.getDirector().split(",");
            for(String director : directors) {
                if(directMovies.containsKey(director)) {
                    directMovies.put(director, directMovies.get(director) + 1);
                } else {
                    directMovies.put(director, 1);
                }
                if(directMovies.get(director) > maxNum) {
                    maxNum = directMovies.get(director);
                    sb = new StringBuilder(director);
                } else if(directMovies.get(director) == maxNum) {
                    sb.append("," + director);
                }
            }
        }
        System.out.println("Number of movies including comedy genre: " + comedyNum);
        System.out.println("Number of movies longer than 150 min: " + longerThan150Num);
        System.out.println("Max number of movies directed by any director: " + maxNum);
        System.out.println("They are: " + sb.toString());
    }
    
    public ArrayList<Rater> loadRaters(String filename) {
        FileResource fr = new FileResource("data/" + filename);
        ArrayList<Rater> raters = new ArrayList<Rater> ();
        HashMap<String, Integer> ids = new HashMap<String, Integer> ();
        for(CSVRecord rec : fr.getCSVParser()) {
            String currId = rec.get("rater_id");
            if(!ids.containsKey(currId)) {
                raters.add(new EfficientRater(currId));
                ids.put(currId, raters.size() - 1);
            }
            Rater rater = raters.get(ids.get(currId));
            rater.addRating(rec.get("movie_id"), Double.parseDouble(rec.get("rating")));
        }
        return raters;
    }
    
    public void testLoadRaters() {
        ArrayList<Rater> raters = loadRaters("ratings.csv");
        //ArrayList<Rater> raters = loadRaters("ratings_short.csv");
        System.out.println("Number of raters: " + raters.size());
        //for(Rater rater : raters) {
        //    System.out.println(rater.getID() + ": " + rater.numRatings() + " ratings");
        //    ArrayList<String> ratedMovies = rater.getItemsRated();
        //    for(String movie : ratedMovies) {
        //        System.out.println(movie + ": " + rater.getRating(movie));
        //    }
        //}
        String targetID = "193";
        int maxNum = 0;
        StringBuilder sb = new StringBuilder();
        String targetMovie = "1798709";
        int numRatings = 0;
        HashSet<String> ratedMovies = new HashSet<String> ();
        for(Rater rater : raters) {
            if(rater.getID().equals(targetID)) {
                System.out.println("Number of ratings for " + targetID + ": " + rater.numRatings());
            }
            
            if(rater.numRatings() == maxNum) {
                sb.append("," + rater.getID());
            } else if(rater.numRatings() > maxNum) {
                sb = new StringBuilder(rater.getID());
                maxNum = rater.numRatings();
            }
            
            if(rater.hasRating(targetMovie)) {
                numRatings++;
            }
            
            ArrayList<String> moviesRated = rater.getItemsRated();
            for(String movie : moviesRated) {
                if(!ratedMovies.contains(movie)) {
                    ratedMovies.add(movie);
                }
            }
        }
        System.out.println("Max number of ratings by any rater: " + maxNum);
        System.out.println("The raters are: " + sb.toString());
        System.out.println("Number of ratings movie " + targetMovie + " has: " + numRatings);
        System.out.println("Number of different movies rated: " + ratedMovies.size());
    }
}
