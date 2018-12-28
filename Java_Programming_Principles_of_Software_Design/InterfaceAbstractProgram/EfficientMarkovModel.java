import java.util.*;
/**
 * MarkovModel of order N.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EfficientMarkovModel extends AbstractMarkovModel {
    private int N;
    private HashMap<String, ArrayList<String>> myMap;
    
    public EfficientMarkovModel(int numChars) {
        myRandom = new Random();
        N = numChars;
        myMap = new HashMap<String, ArrayList<String>> ();
    }
    
    public void setTraining(String s){
        myText = s.trim();
        buildMap();
        //printHashMapInfo();
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
    
    private void buildMap() {
        for(int i = 0; i < myText.length() - N + 1; i++) {
            String key = myText.substring(i, i + N);
            if(!myMap.containsKey(key)) {
                ArrayList<String> follows = new ArrayList<String> ();
                int index = myText.indexOf(key);
                while(index != -1 && index < myText.length() - key.length()) {
                    follows.add(myText.substring(index + key.length(), index + key.length() + 1));
                    index = myText.indexOf(key, index + key.length());
                }
                myMap.put(key, follows);
            }
        }
    }
    
    @Override
    public ArrayList<String> getFollows(String key) {
        return myMap.get(key);
    }
    
    /*public void printHashMapInfo() {
        for(String key : myMap.keySet()) {
            System.out.print(key + " : ");
            for(String s : myMap.get(key)) {
                System.out.print("\"" + s + "\",");
            }
            System.out.println();
        }
        System.out.println(myMap.size());
        int maxSize = 0;
        for(ArrayList<String> al : myMap.values()) {
            if(al.size() > maxSize) {
                maxSize = al.size();
            }
        }
        System.out.println("largest size is " + maxSize);
        for(String key : myMap.keySet()) {
            if(myMap.get(key).size() == maxSize) {
                System.out.print("\"" + key + "\",");
            }
        }
        System.out.println();
    }*/
    
    public String toString() {
        return "EfficientMarkovModel of order " + N;
    }
}
