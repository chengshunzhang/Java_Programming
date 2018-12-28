import java.util.*;
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int N;
    
    public MarkovModel(int numChars) {
        myRandom = new Random();
        N = numChars;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String> ();
        int index = myText.indexOf(key);
        while(index != -1 && index < myText.length() - key.length()) {
            follows.add(myText.substring(index + key.length(), index + key.length() + 1));
            index = myText.indexOf(key, index + key.length());
        }
        return follows;
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - N);
        String current = myText.substring(index, index + N);
        sb.append(current);
        for(int k = 0; k < numChars - N; k++){
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
}
