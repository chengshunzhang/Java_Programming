import edu.duke.*;
import java.util.*;
import java.io.*;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> myMap;
    
    public WordsInFiles() {
        myMap = new HashMap<String, ArrayList<String>> ();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        String fName = f.getName();
        for(String word : fr.words()) {
            if(myMap.containsKey(word)) {
                if(myMap.get(word).indexOf(fName) == -1) {
                    myMap.get(word).add(fName);
                }
            } else {
                ArrayList<String> temp = new ArrayList<String> ();
                temp.add(fName);
                myMap.put(word, temp);
            }
        }
    }
    
    private void buildWordFileMap() {
        myMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber() {
        int maxNum = -1;
        for(String s : myMap.keySet()) {
            if(myMap.get(s).size() > maxNum) {
                maxNum = myMap.get(s).size();
            }
        }
        return maxNum;
    }
    
    private ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> result = new ArrayList<String> ();
        for(String s : myMap.keySet()) {
            if(myMap.get(s).size() == number) {
                result.add(s);
            }
        }
        return result;
    }
    
    private void printFilesIn(String word) {
        for(int i = 0; i < myMap.get(word).size(); i++) {
            System.out.println(myMap.get(word).get(i));
        }
    }
    
    public void tester() {
        buildWordFileMap();
        int maxNum = maxNumber();
        System.out.println("maximum number of files any word is in is " + maxNum);
        //ArrayList<String> words = wordsInNumFiles(4);
        //System.out.println(words.size() + " words occur in " + 4 + " files");
        //for(int i = 0; i < words.size(); i++) {
         //   System.out.println(words.get(i) + ":");
          //  printFilesIn(words.get(i));
        //}
        printFilesIn("tree");
    }
}
