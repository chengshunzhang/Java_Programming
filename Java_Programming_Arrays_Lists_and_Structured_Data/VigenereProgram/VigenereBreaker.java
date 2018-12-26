import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i += totalSlices) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for(int i = 0; i < klength; i++) {
            String message = sliceString(encrypted, i, klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            key[i] = cc.getKey(message);
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>> ();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            FileResource dfr = new FileResource(f);
            languages.put(f.getName(), readDictionary(dfr));
            System.out.println("Finish reading " + f.getName());
        }
        breakForAllLangs(encrypted, languages);
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dict = new HashSet<String> ();
        for(String word : fr.lines()) {
            dict.add(word.toLowerCase());
        }
        return dict;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        for(String word : message.split("\\W")) {
            if(dictionary.contains(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        int[] chars = new int[26];
        for(String word : dictionary) {
            for(int i = 0; i < word.length(); i++) {
                if(word.charAt(i) - 'a' >= 0 && word.charAt(i) - 'a' < 26) {
                    chars[word.charAt(i) - 'a']++;
                }
            }
        }
        int maxCount = chars[0];
        char mostCommon = 'a';
        for(int i = 1; i < 26; i++) {
            if(chars[i] > maxCount) {
                maxCount = chars[i];
                mostCommon = (char)('a' + i);
            }
        }
        System.out.println(mostCommon);
        return mostCommon;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int maxCount = -1, keyLen = 0;
        String message = "";
        char mostCommonChar = mostCommonCharIn(dictionary);
        for(int kLen = 1; kLen <= 100; kLen++) {
            int[] key = tryKeyLength(encrypted, kLen, mostCommonChar);
            VigenereCipher vc = new VigenereCipher(key);
            String msg = vc.decrypt(encrypted);
            int curCount = countWords(msg, dictionary);
            if(curCount > maxCount) {
                maxCount = curCount;
                message = msg;
                keyLen = kLen;
            }
        }
        return message;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxCount = 0;
        String language = "", message = "";
        for(String lang : languages.keySet()) {
            System.out.print("most common character in " + lang + " is ");
            String decrypted = breakForLanguage(encrypted, languages.get(lang));
            int curCount = countWords(decrypted, languages.get(lang));
            if(curCount > maxCount) {
                maxCount = curCount;
                language = lang;
                message = decrypted;
            }
        }
        System.out.println("The language is " + language);
        System.out.println("The decrypted message is \n" + message.substring(0, 100));
    }
    
    public void testTryKeyLength() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int keyLen = 4;
        int[] key = tryKeyLength(encrypted, keyLen, 'e');
        for(int i = 0; i < keyLen; i++) {
            System.out.print(key[i] + "  ");
        }
        System.out.println();
    }
}
