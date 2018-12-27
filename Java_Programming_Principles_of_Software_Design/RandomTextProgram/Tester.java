import java.util.ArrayList;
import edu.duke.*;
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tester {
    public void testGetFollows() {
        MarkovOne markov = new MarkovOne();
        String train = "this is a test yes this is a test.";
        markov.setTraining(train);
        ArrayList<String> follows = markov.getFollows("t");
        System.out.println(follows.size());
        for(String c : follows) {
            System.out.print("\"" + c + "\"" + ",");
        }
    }
    
    public void testGetFollowsWithFile() {
        MarkovOne markov = new MarkovOne();
        FileResource fr = new FileResource();
        String train = fr.asString();
        markov.setTraining(train);
        ArrayList<String> follows = markov.getFollows("t");
        System.out.println(follows.size());
    }
}
