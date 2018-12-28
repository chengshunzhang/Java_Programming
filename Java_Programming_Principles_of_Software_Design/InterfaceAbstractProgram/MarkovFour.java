import java.util.*;
/**
 * Write a description of MarkovFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovFour extends AbstractMarkovModel {
    public MarkovFour() {
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
        int index = myRandom.nextInt(myText.length() - 4);
        String current = myText.substring(index, index + 4);
        sb.append(current);
        for(int k = 0; k < numChars - 4; k++){
            ArrayList<String> follows = getFollows(current);
            if(follows.isEmpty()) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            current = current.substring(1) + next;
        }
        return sb.toString();
    }
    
    public String toString() {
        return "MarkovModel of order 4";
    }
}
