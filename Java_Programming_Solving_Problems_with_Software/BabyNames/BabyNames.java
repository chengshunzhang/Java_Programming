import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of BabyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BabyNames {
    public void totalBirths(FileResource fr) {
        int total = 0, girlNames = 0, boyNames = 0;
        for(CSVRecord rec : fr.getCSVParser(false)) {
            total += Integer.parseInt(rec.get(2));
            if (rec.get(1).equals("F")) {
                girlNames++;
            } else {
                boyNames++;
            }
        }
        System.out.println("Total births = " + total);
        System.out.println("Number of girls' names = " + girlNames);
        System.out.println("Number of boys' names = " + boyNames);
    }
    
    public int getRank(int year, String name, String gender) {
        int rank = 1;
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        for(CSVRecord rec : fr.getCSVParser(false)) {
            if(rec.get(0).equals(name) && rec.get(1).equals(gender)) {
                return rank;
            } else if(rec.get(1).equals(gender)) {
                rank++;
            }
        }
        return -1;
    }
    
    public String getName(int year, int rank, String gender) {
        int count = 1;
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        for(CSVRecord rec : fr.getCSVParser(false)) {
            if(rec.get(1).equals(gender) && count == rank) {
                return rec.get(0);
            } else if(rec.get(1).equals(gender)) {
                count++;
            }
        }
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        String newName = getName(newYear, getRank(year, name, gender), gender);
        if(gender.equals("F")) {
            System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear + ".");
        } else {
            System.out.println(name + " born in " + year + " would be " + newName + " if he was born in " + newYear + ".");
        }
        
    }
    
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int whichYear = -1, highestRank = -1;
        for(File f : dr.selectedFiles()) {
            String fileName = f.getName();
            int curYear = Integer.parseInt(fileName.substring(3, 7));
            FileResource fr = new FileResource(f);
            int curRank = getRank(curYear, name, gender);
            if(curRank != -1 && (highestRank == -1 || highestRank > curRank)) {
                highestRank = curRank;
                whichYear = curYear;
            }
        }
        return whichYear;
    }
    
    public double getAverageRank(String name, String gender) {
        int totalRank = 0, count = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            String fileName = f.getName();
            int curYear = Integer.parseInt(fileName.substring(3, 7));
            FileResource fr = new FileResource(f);
            int curRank = getRank(curYear, name, gender);
            if(curRank != -1) {
                totalRank += curRank;
                count++;
            }
        }
        if(count == 0) {
            return 1.;
        } else {
            return (double)totalRank / count;
        }
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int total = 0;
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        for(CSVRecord rec : fr.getCSVParser(false)) {
            if(rec.get(1).equals(gender)) {
                if(rec.get(0).equals(name)) {
                    return total;
                }
                total += Integer.parseInt(rec.get(2));
            }
        }
        return total;
    }
    
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public void testGetRank() {
        System.out.println(getRank(1971, "Frank", "M"));
    }
    
    public void testGetName() {
        System.out.println(getName(1982, 450, "M"));
    }
    
    public void testWhatIsNameInYear() {
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public void testYearOfHighestRank() {
        System.out.println(yearOfHighestRank("Mich", "M"));
    }
    
    public void testGetAverageRank() {
        System.out.println(getAverageRank("Robert", "M"));
    }
    
    public void testGetTotalBirthsRankedHigher() {
        System.out.println(getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }
}
