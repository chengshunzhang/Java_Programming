import java.util.*;
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovOne {
    private String myText;
    private Random myRandom;
    
    public MarkovOne() {
        myRandom = new Random();
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
            index = myText.indexOf(key, index + 1);
        }
        return follows;
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
}
