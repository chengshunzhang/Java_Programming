
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printSimilarRatings() {
        FourthRatings fr = new FourthRatings();
        //RaterDatabase.initialize("ratings_short.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Number of raters: " + RaterDatabase.size());
        
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        String raterID = "71";
        int numTopRaters = 20;
        int minimalRaters = 5;
        ArrayList<Rating> similarRatings = fr.getSimilarRatings(raterID, numTopRaters, minimalRaters);
        //for(Rating rt : similarRatings) {
        //    System.out.println(rt.getValue() + " " + MovieDatabase.getTitle(rt.getItem()));
        //}
        Rating topMovie = similarRatings.get(0);
        System.out.println(topMovie.getValue() + " " + MovieDatabase.getTitle(topMovie.getItem()));
    }
    
    public void printSimilarRatingsByGenre() {
        FourthRatings fr = new FourthRatings();
        //RaterDatabase.initialize("ratings_short.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Number of raters: " + RaterDatabase.size());
        
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        String raterID = "964";
        int numTopRaters = 20;
        int minimalRaters = 5;
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterID, numTopRaters, minimalRaters, new GenreFilter("Mystery"));
        //for(Rating rt : similarRatings) {
        //    System.out.println(rt.getValue() + " " + MovieDatabase.getTitle(rt.getItem()));
        //    System.out.println("\t" + MovieDatabase.getGenres(rt.getItem()));
        //}
        Rating topMovie = similarRatings.get(0);
        System.out.println(topMovie.getValue() + " " + MovieDatabase.getTitle(topMovie.getItem()));
    }
    
    public void printSimilarRatingsByDirector() {
        FourthRatings fr = new FourthRatings();
        //RaterDatabase.initialize("ratings_short.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Number of raters: " + RaterDatabase.size());
        
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        String raterID = "120";
        int numTopRaters = 10;
        int minimalRaters = 2;
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterID, numTopRaters, minimalRaters, new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
        //for(Rating rt : similarRatings) {
        //    System.out.println(rt.getValue() + " " + MovieDatabase.getTitle(rt.getItem()));
        //    System.out.println("\t" + MovieDatabase.getDirector(rt.getItem()));
        //}
        Rating topMovie = similarRatings.get(0);
        System.out.println(topMovie.getValue() + " " + MovieDatabase.getTitle(topMovie.getItem()));
    }
    
    public void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings fr = new FourthRatings();
        //RaterDatabase.initialize("ratings_short.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Number of raters: " + RaterDatabase.size());
        
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new GenreFilter("Drama"));
        allFilters.addFilter(new MinutesFilter(80, 160));
        
        String raterID = "168";
        int numTopRaters = 10;
        int minimalRaters = 3;
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterID, numTopRaters, minimalRaters, allFilters);
        //for(Rating rt : similarRatings) {
        //    System.out.println(rt.getValue() + " Minutes: " + MovieDatabase.getMinutes(rt.getItem()) + " " + MovieDatabase.getTitle(rt.getItem()));
        //    System.out.println("\t" + MovieDatabase.getGenres(rt.getItem()));
        //}
        Rating topMovie = similarRatings.get(0);
        System.out.println(topMovie.getValue() + " " + MovieDatabase.getTitle(topMovie.getItem()));
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings fr = new FourthRatings();
        //RaterDatabase.initialize("ratings_short.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Number of raters: " + RaterDatabase.size());
        
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(1975));
        allFilters.addFilter(new MinutesFilter(70, 200));
        
        String raterID = "314";
        int numTopRaters = 10;
        int minimalRaters = 5;
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterID, numTopRaters, minimalRaters, allFilters);
        //for(Rating rt : similarRatings) {
        //    System.out.println(rt.getValue() + " Year: " + MovieDatabase.getYear(rt.getItem()) + " Minutes: " + MovieDatabase.getMinutes(rt.getItem()) + " " + MovieDatabase.getTitle(rt.getItem()));
        //}
        Rating topMovie = similarRatings.get(0);
        System.out.println(topMovie.getValue() + " " + MovieDatabase.getTitle(topMovie.getItem()));
    }
    
    public void printAverageRatings() {
        FourthRatings fr = new FourthRatings();
        //RaterDatabase.initialize("ratings_short.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Number of raters: " + RaterDatabase.size());
        
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        int minimalRaters = 35;
        ArrayList<Rating> avgRatings = fr.getAverageRatings(minimalRaters);
        Collections.sort(avgRatings);
        //for(Rating rt : avgRatings) {
        //    System.out.println(rt.getValue() + " " + MovieDatabase.getTitle(rt.getItem()));
        //}
        System.out.println(avgRatings.size() + " movies have " + minimalRaters + " or more ratings");
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        FourthRatings fr = new FourthRatings();
        //RaterDatabase.initialize("ratings_short.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Number of raters: " + RaterDatabase.size());
        
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        int minimalRaters = 8;
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(1990));
        allFilters.addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> avgRatings = fr.getAverageRatingsByFilter(minimalRaters, allFilters);
        Collections.sort(avgRatings);
        //for(Rating rt : avgRatings) {
        //    System.out.println(rt.getValue() + " " + MovieDatabase.getYear(rt.getItem()) + " " + MovieDatabase.getTitle(rt.getItem()));
        //    System.out.println("\t" + MovieDatabase.getGenres(rt.getItem()));
        //}
        System.out.println(avgRatings.size() + " movies have " + minimalRaters + " or more ratings");
    }
}
