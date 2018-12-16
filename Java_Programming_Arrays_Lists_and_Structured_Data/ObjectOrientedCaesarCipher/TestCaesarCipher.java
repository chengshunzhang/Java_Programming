import edu.duke.*;
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipher {
    private void countLetters(String input, int[] letters) {
        String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i = 0; i < input.length(); i++) {
            int index = alph.indexOf(Character.toUpperCase(input.charAt(i)));
            if(index != -1) {
                letters[index]++;
            }
        }
    }
    
    private int maxIndex(int[] arr) {
        if(arr.length == 0) {
            return -1;
        }
        int index = 0, max = arr[0];
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] > max) {
                max = arr[i];
                index = i;
            }
        }
        return index;
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        CaesarCipher cc = new CaesarCipher(15);
        String encrypted = cc.encrypt(fr.asString());
        System.out.println("encrypted message: " + encrypted);
        String decrypted = breakCaesarCipher(encrypted);
        System.out.println("decrypted message: " + decrypted);
    }
    
    private String breakCaesarCipher(String input) {
        int[] counts = new int[26];
        countLetters(input, counts);
        int key = maxIndex(counts) - 4;
        if(key < 0) {
            key += 26;
        }
        CaesarCipher cc = new CaesarCipher(key);
        return cc.decrypt(input);
    }
}
