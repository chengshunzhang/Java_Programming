
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
    
    public void printAverageRatingsByGenre() {
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        //ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        int minimalRaters = 1;
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, new GenreFilter("Crime"));
        Collections.sort(avgRatings);
        for(Rating rt : avgRatings) {
            System.out.println(rt.getValue() + " " + MovieDatabase.getTitle(rt.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(rt.getItem()));
        }
        System.out.println(avgRatings.size() + " movies have " + minimalRaters + " or more ratings");
    }
    
    public void printAverageRatingsByMinutes() {
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        //ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        int minimalRaters = 1;
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, new MinutesFilter(110, 170));
        Collections.sort(avgRatings);
        for(Rating rt : avgRatings) {
            System.out.println(rt.getValue() + " " + MovieDatabase.getMinutes(rt.getItem()) + " " + MovieDatabase.getTitle(rt.getItem()));
        }
        System.out.println(avgRatings.size() + " movies have " + minimalRaters + " or more ratings");
    }
    
    public void printAverageRatingsByDirectors() {
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        //ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        int minimalRaters = 1;
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze"));
        Collections.sort(avgRatings);
        for(Rating rt : avgRatings) {
            System.out.println(rt.getValue() + " " + MovieDatabase.getTitle(rt.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(rt.getItem()));
        }
        System.out.println(avgRatings.size() + " movies have " + minimalRaters + " or more ratings");
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        //ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        int minimalRaters = 1;
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(1980));
        allFilters.addFilter(new GenreFilter("Romance"));
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, allFilters);
        Collections.sort(avgRatings);
        for(Rating rt : avgRatings) {
            System.out.println(rt.getValue() + " " + MovieDatabase.getYear(rt.getItem()) + " " + MovieDatabase.getTitle(rt.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(rt.getItem()));
        }
        System.out.println(avgRatings.size() + " movies have " + minimalRaters + " or more ratings");
    }
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        //ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        int minimalRaters = 1;
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new MinutesFilter(30, 170));
        allFilters.addFilter(new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola"));
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, allFilters);
        Collections.sort(avgRatings);
        for(Rating rt : avgRatings) {
            System.out.println(rt.getValue() + " Time: " + MovieDatabase.getMinutes(rt.getItem()) + " " + MovieDatabase.getTitle(rt.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(rt.getItem()));
        }
        System.out.println(avgRatings.size() + " movies have " + minimalRaters + " or more ratings");
    }
}
