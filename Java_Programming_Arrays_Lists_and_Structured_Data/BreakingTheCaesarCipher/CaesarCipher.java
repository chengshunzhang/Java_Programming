import edu.duke.*;
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shifted = alph.substring(key) + alph.substring(0, key);
        for(int i = 0; i < input.length(); i++) {
            int index = alph.indexOf(Character.toUpperCase(encrypted.charAt(i)));
            if(index != -1) {
                if(Character.isUpperCase(encrypted.charAt(i))) {
                    encrypted.setCharAt(i, shifted.charAt(index));
                } else {
                    encrypted.setCharAt(i, Character.toLowerCase(shifted.charAt(index)));
                }
            }
        }
        return encrypted.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shifted1 = alph.substring(key1) + alph.substring(0, key1);
        String shifted2 = alph.substring(key2) + alph.substring(0, key2);
        for(int i = 0; i < input.length(); i++) {
            int index = alph.indexOf(Character.toUpperCase(encrypted.charAt(i)));
            if(index != -1) {
                if(i % 2 == 0) {
                    if(Character.isUpperCase(encrypted.charAt(i))) {
                        encrypted.setCharAt(i, shifted1.charAt(index));
                    } else {
                        encrypted.setCharAt(i, Character.toLowerCase(shifted1.charAt(index)));
                    }
                } else {
                    if(Character.isUpperCase(encrypted.charAt(i))) {
                        encrypted.setCharAt(i, shifted2.charAt(index));
                    } else {
                        encrypted.setCharAt(i, Character.toLowerCase(shifted2.charAt(index)));
                    }
                }
            }
        }
        return encrypted.toString();
    }
    
    public void testEncrypt() {
        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
    }
    
    public void testCaesar() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 20;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public void testEncryptTwoKeys() {
        System.out.println(encryptTwoKeys("Hfs cpwewloj loks cd Hoto kyg Cyy.", 12, 2));
    }
}
