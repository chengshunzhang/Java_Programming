import java.util.*;
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovOne extends AbstractMarkovModel {
    public MarkovOne() {
        myRandom = new Random();
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - 1);
        String current = myText.substring(index, index + 1);
        sb.append(current);
        for(int k = 0; k < numChars - 1; k++){
            ArrayList<String> follows = getFollows(current);
            if(follows.isEmpty()) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            current = next;
        }
        return sb.toString();
    }
    
    public String toString() {
        return "MarkovModel of order 1";
    }
}
