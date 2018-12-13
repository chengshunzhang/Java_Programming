
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        int pos = stringb.indexOf(stringa);
        if(pos == -1) {
            return false;
        }
        pos = stringb.indexOf(stringa, pos + 1);
        if(pos == -1) {
            return false;
        }
        return true;
    }
    
    public String lastPart(String stringa, String stringb) {
        int first = stringb.indexOf(stringa);
        if(first == -1) {
            return stringb;
        }
        return stringb.substring(first + stringa.length());
    }
    
    public void testing() {
        String stringa = "by", stringb = "A story by Abby Long";
        System.out.println("stringa: " + stringa);
        System.out.println("stringb: " + stringb);
        System.out.println(twoOccurrences(stringa, stringb));
        stringa = "a";
        stringb = "banana";
        System.out.println("stringa: " + stringa);
        System.out.println("stringb: " + stringb);
        System.out.println(twoOccurrences(stringa, stringb));
        stringa = "atg";
        stringb = "ctgtatgta";
        System.out.println("stringa: " + stringa);
        System.out.println("stringb: " + stringb);
        System.out.println(twoOccurrences(stringa, stringb));
        
        stringa = "an";
        stringb = "banana";
        System.out.println("stringa: " + stringa);
        System.out.println("stringb: " + stringb);
        System.out.println(lastPart(stringa, stringb));
        stringa = "zoo";
        stringb = "forest";
        System.out.println("stringa: " + stringa);
        System.out.println("stringb: " + stringb);
        System.out.println(lastPart(stringa, stringb));
    }
    
    public static void main(String[] args) {
        Part3 p3 = new Part3();
        p3.testing();
    }
}
