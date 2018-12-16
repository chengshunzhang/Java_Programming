
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch) {
        char lowercase = Character.toLowerCase(ch);
        if(lowercase == 'a' || lowercase == 'e' || lowercase == 'i' || lowercase == 'o' || lowercase == 'u') {
            return true;
        } else {
            return false;
        }
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        for(int i = 0; i < phrase.length(); i++) {
            if(isVowel(sb.charAt(i))) {
                sb.setCharAt(i, ch);
            }
        }
        return sb.toString();
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        char lowerCh = Character.toLowerCase(ch);
        for(int i = 0; i < phrase.length(); i++) {
            if(Character.toLowerCase(sb.charAt(i)) == lowerCh) {
                if(i % 2 == 0) {
                    sb.setCharAt(i, '*');
                } else {
                    sb.setCharAt(i, '+');
                }
            }
        }
        return sb.toString();
    }
    
    public void testIsVowel() {
        System.out.println(isVowel('a'));
        System.out.println(isVowel('b'));
    }
    
    public void testReplaceVowels() {
        System.out.println(replaceVowels("Hello World", '*'));
    }
    
    public void testEmphasize() {
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }
}