
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter {
    private double minDepth;
    private double maxDepth;
    
    public DepthFilter(double minD, double maxD) {
        minDepth = minD;
        maxDepth = maxD;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        double depth = qe.getDepth();
        return (depth > minDepth && depth < maxDepth) || Math.abs(depth - minDepth) < 1e-6 || Math.abs(depth - maxDepth) < 1e-6;
    }
    
    public String getName() {
        return "Depth";
    }
}
