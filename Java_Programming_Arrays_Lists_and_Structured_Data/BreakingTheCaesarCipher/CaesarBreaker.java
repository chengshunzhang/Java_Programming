import edu.duke.*;
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarBreaker {
    public void countLetters(String input, int[] letters) {
        String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i = 0; i < input.length(); i++) {
            int index = alph.indexOf(Character.toUpperCase(input.charAt(i)));
            if(index != -1) {
                letters[index]++;
            }
        }
    }
    
    public int maxIndex(int[] arr) {
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
    
    public String decrypt(String input, int key) {
        CaesarCipher cc = new CaesarCipher();
        return cc.encrypt(input, 26 - key);
    }
    
    public String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();
        for(int i = start; i < message.length(); i += 2) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }
    
    public int getKey(String s) {
        int[] counts = new int[26];
        countLetters(s, counts);
        return maxIndex(counts);
    }
    
    public String decryptTwoKeys(String encrypted) {
        String oddString = halfOfString(encrypted, 0), evenString = halfOfString(encrypted, 1);
        int key1 = getKey(oddString) - 4, key2 = getKey(evenString) - 4;
        if(key1 < 0) {
            key1 = key1 + 26;
        }
        if(key2 < 0) {
            key2 = key2 + 26;
        }
        System.out.println("key1: " + key1 + "\t key2: " + key2);
        CaesarCipher cc = new CaesarCipher();
        return cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
    }
    
    public void testDecrypt() {
        CaesarCipher cc = new CaesarCipher();
        String encrypted = cc.encrypt("I want to go", 20);
        System.out.println(decrypt(encrypted, 20));
    }
    
    public void testHalfOfString() {
        System.out.println(halfOfString("Qbkm Zgis", 1));
    }
    
    public void testGetKey() {
        System.out.println(getKey("I want to go"));
    }
    
    public void testDecryptTwoKeys() {
        //String decrypted = decryptTwoKeys("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!");
        FileResource fr = new FileResource();
        String decrypted = decryptTwoKeys(fr.asString());
        System.out.println(decrypted);
    }
}
