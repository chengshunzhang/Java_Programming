
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter {
    private String myDirectors;
    
    public DirectorsFilter(String directors) {
        myDirectors = directors;
    }
    
    @Override
    public boolean satisfies(String id) {
        String[] drs = myDirectors.split(",");
        String drsInMovie = MovieDatabase.getDirector(id);
        for(String dr : drs) {
            if(drsInMovie.indexOf(dr) >= 0) {
                return true;
            }
        }
        return false;
    }
}
