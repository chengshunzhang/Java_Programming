import edu.duke.*;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public void findWebLinks() {
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for(String word : ur.words()) {
            String lowercased = word.toLowerCase();
            int pos = lowercased.indexOf("youtube.com");
            if(pos != -1) {
                int openQuot = lowercased.lastIndexOf("\"", pos);
                int closeQuot = lowercased.indexOf("\"", pos + 11);
                System.out.println(word.substring(openQuot, closeQuot + 1));
            }
        }
    }
    
    public static void main(String[] args) {
        Part4 p4 = new Part4();
        p4.findWebLinks();
    }
}
