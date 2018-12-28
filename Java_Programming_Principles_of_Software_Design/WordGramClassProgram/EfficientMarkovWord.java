import java.util.*;
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> myMap;
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        myMap = new HashMap<WordGram, ArrayList<String>> ();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);  // random word to start with
        WordGram kGram = new WordGram(myText, index, myOrder);
        sb.append(kGram);
        sb.append(" ");
        for(int k = 0; k < numWords - myOrder; k++){
            ArrayList<String> follows = getFollows(kGram);
            if(follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            kGram = kGram.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        return myMap.get(kGram);
    }

    private int indexOf(String[] words, WordGram target, int start) {
        for(int i = start; i + target.length() < words.length; i++) {
            WordGram wg = new WordGram(words, i, target.length());
            if(wg.equals(target)) {
                return i;
            }
        }
        return -1;
    }
    
    private void buildMap() {
        for(int i = 0; i + myOrder < myText.length; i++) {
            WordGram wg = new WordGram(myText, i, myOrder);
            if(!myMap.containsKey(wg)) {
               ArrayList<String> follows = new ArrayList<String>(); 
               myMap.put(wg, follows);
            }
            myMap.get(wg).add(myText[i + myOrder]);
        }
        WordGram last = new WordGram(myText, myText.length - myOrder, myOrder);
        if(!myMap.containsKey(last)) {
            ArrayList<String> follows = new ArrayList<String>(); 
            myMap.put(last, follows);
        }
    }
    
    public void printHashMapInfo() {
        System.out.println("Number of keys: " + myMap.size());
        int largestSize = 0;
        for(ArrayList<String> al : myMap.values()) {
            int curr = al.size();
            if(curr > largestSize) {
                largestSize = curr;
            }
        }
        System.out.println("size of largest value is " + largestSize);
        for(WordGram wg : myMap.keySet()) {
            if(myMap.get(wg).size() == largestSize) {
                System.out.print(wg + ",");
            }
        }
        System.out.println();
    }
}
