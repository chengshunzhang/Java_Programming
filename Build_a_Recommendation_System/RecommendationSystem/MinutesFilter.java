
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter {
    private int myMinLen;
    private int myMaxLen;
    
    public MinutesFilter(int minimum, int maximum) {
        myMinLen = minimum;
        myMaxLen = maximum;
    }
    
    @Override
    public boolean satisfies(String id) {
        int minutes = MovieDatabase.getMinutes(id);
        return minutes >= myMinLen && minutes <= myMaxLen;
    }
}
