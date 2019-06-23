
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class FourthRatings {
    
    private double dotProduct(Rater me, Rater r) {
        ArrayList<String> myRatings = r.getItemsRated();
        double product = 0.0;
        for(String movieID : myRatings) {
            if(me.hasRating(movieID)) {
                product += (me.getRating(movieID) - 5) * (r.getRating(movieID) - 5);
            }
        }
        return product;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
        Rater me = RaterDatabase.getRater(id);
        ArrayList<Rating> similarities = new ArrayList<Rating> ();
        for(Rater r : RaterDatabase.getRaters()) {
            String otherID = r.getID();
            if(!otherID.equals(id)) {
                similarities.add(new Rating(otherID, dotProduct(me, r)));
            }
        }
        ArrayList<Rating> positiveSimi = new ArrayList<Rating> ();
        System.out.println(similarities.size());
        for(Rating r : similarities) {
            if(r.getValue() > 0) {
                positiveSimi.add(r);
            }
        }
        Collections.sort(positiveSimi, Collections.reverseOrder());
        return positiveSimi;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> similarRaters = getSimilarities(id);
        ArrayList<Rating> result = new ArrayList<Rating> ();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String movie : movies) {
            double avg = 0.0;
            int numTopRaters = 0;
            for(int i = 0; i < numSimilarRaters; i++) {
                Rating r = similarRaters.get(i);
                Rater similarRater = RaterDatabase.getRater(r.getItem());
                if(similarRater.hasRating(movie)) {
                    numTopRaters++;
                    avg += r.getValue() * similarRater.getRating(movie);
                }
            }
            if(numTopRaters >= minimalRaters) {
                avg = avg / numTopRaters;
                result.add(new Rating(movie, avg));
            }
        }
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        ArrayList<Rating> similarRaters = getSimilarities(id);
        ArrayList<Rating> result = new ArrayList<Rating> ();
        HashMap<String, Double> topRaters = new HashMap<String, Double> ();
        for(int i = 0; i < numSimilarRaters; i++) {
            topRaters.put(similarRaters.get(i).getItem(), similarRaters.get(i).getValue());
        }
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(String movie : movies) {
            double avg = 0.0;
            int numTopRaters = 0;
            for(Rater rater : myRaters) {
                if(topRaters.containsKey(rater.getID()) && rater.hasRating(movie)) {
                    numTopRaters++;
                    avg += topRaters.get(rater.getID()) * rater.getRating(movie);
                }
            }
            if(numTopRaters >= minimalRaters) {
                avg /= numTopRaters;
                result.add(new Rating(movie, avg));
            }
        }
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }
    
    private double getAverageByID(String id, int minimalRaters) {
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
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
