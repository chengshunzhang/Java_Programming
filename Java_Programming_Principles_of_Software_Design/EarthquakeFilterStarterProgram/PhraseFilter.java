
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {
    private String where; // start end any
    private String phrase;
    
    public PhraseFilter(String w, String p) {
        where = w;
        phrase = p;
    }

    public boolean satisfies(QuakeEntry qe) {
        if(where.equals("start")) {
            return qe.getInfo().startsWith(phrase);
        } else if(where.equals("end")) {
            return qe.getInfo().endsWith(phrase);
        } else {
            return qe.getInfo().indexOf(phrase) != -1;
        }
    }
    
}
