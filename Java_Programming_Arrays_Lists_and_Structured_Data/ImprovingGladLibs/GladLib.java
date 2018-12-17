import edu.duke.*;
import java.util.*;

public class GladLib {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedWords;
    private ArrayList<String> usedCategories;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";
    
    public GladLib(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLib(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        myMap = new HashMap<String, ArrayList<String>> ();
        String[] category = {"adjective", "noun", "color", "country", 
                             "name", "animal", "timeframe", "verb", "fruit"};
        for(int i = 0; i < category.length; i++) {
            myMap.put(category[i], readIt(source + "/" + category[i] + ".txt"));
        }
        usedWords = new ArrayList<String> ();
        usedCategories = new ArrayList<String> ();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if(usedCategories.indexOf(label) == -1) {
            usedCategories.add(label);
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        } else if(myMap.containsKey(label)){
            return randomFrom(myMap.get(label));
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while(usedWords.indexOf(sub) != -1) {
            sub = getSubstitute(w.substring(first+1,last));
        }
        usedWords.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println();
        System.out.println("Total number of words replaced: " + usedWords.size());
        System.out.println("Total number of words that are possible to pick from: " + totalWordsInMap());
        System.out.println("Total number of words considered: " + totalWordsConsidered());
    }
    
    private int totalWordsInMap() {
        int total = 0;
        for(String s : myMap.keySet()) {
            total += myMap.get(s).size();
        }
        return total;
    }
    
    private int totalWordsConsidered() {
        int total = 0;
        for(int i = 0; i < usedCategories.size(); i++) {
            if(myMap.containsKey(usedCategories.get(i))) {
                total += myMap.get(usedCategories.get(i)).size();
            }
        }
        return total;
    }
}
