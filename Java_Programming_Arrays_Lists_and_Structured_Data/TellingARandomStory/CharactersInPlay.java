import edu.duke.*;
import java.util.*;
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> freqs;
    
    public CharactersInPlay() {
        names = new ArrayList<String> ();
        freqs = new ArrayList<Integer> ();
    }
    
    private void update(String name) {
        int index = names.indexOf(name);
        if(index != -1) {
            freqs.set(index, freqs.get(index) + 1);
        } else {
            names.add(name);
            freqs.add(1);
        }
    }
    
    private void findAllCharacters() {
        names.clear();
        freqs.clear();
        FileResource fr = new FileResource();
        for(String line : fr.lines()) {
            int periodPos = line.indexOf('.');
            if(periodPos != -1) {
                String name = line.substring(0, periodPos);
                int pos = 0;
                while(name.charAt(pos) == ' ') {
                    pos++;
                }
                update(name.substring(pos, periodPos));
            }
        }
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        System.out.println("characters with # parts greater than or equal to " + num1 + " and less than or equal to " + num2);
        for(int i = 0; i < names.size(); i++) {
            if(freqs.get(i) >= num1 && freqs.get(i) <= num2) {
                System.out.println(names.get(i) + "  " + freqs.get(i));
            }
        }
    }
    
    public void tester() {
        findAllCharacters();
        for(int i = 0; i < names.size(); i++) {
            if(freqs.get(i) > 20) {
                System.out.println(names.get(i) + "  " + freqs.get(i));
            }
        }
        charactersWithNumParts(10, 15);
    }
}
