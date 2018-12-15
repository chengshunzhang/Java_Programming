import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of ParsingWeatherData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ParsingWeatherData {
    public CSVRecord compareBetweenTwo(CSVRecord coldest, CSVRecord curr) {
        if(coldest == null) {
            return curr;
        } else if(Double.parseDouble(curr.get("TemperatureF")) < Double.parseDouble(coldest.get("TemperatureF"))) {
            return curr;
        } else {
            return coldest;
        }
    }
    
    public CSVRecord compareHumidity(CSVRecord lowest, CSVRecord curr) {
        if(lowest == null) {
            return curr;
        } else if(Double.parseDouble(curr.get("Humidity")) < Double.parseDouble(lowest.get("Humidity"))) {
            return curr;
        } else {
            return lowest;
        }
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldest = null;
        for(CSVRecord curr : parser) {
            if(Double.parseDouble(curr.get("TemperatureF")) < 0) {
                continue;
            }
            coldest = compareBetweenTwo(coldest, curr);
        }
        return coldest;
    }
    
    public String fileWithColdestTemperature() {
        String result = "";
        CSVRecord coldestTemp = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord curr = coldestHourInFile(parser);
            coldestTemp = compareBetweenTwo(coldestTemp, curr);
            if(coldestTemp == curr) {
                result = f.getName();
            }
        }
        return result;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowest = null;
        for(CSVRecord curr : parser) {
            String humidity = curr.get("Humidity");
            if(humidity.equals("N/A")) {
                continue;
            }
            lowest = compareHumidity(lowest, curr);
        }
        return lowest;
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowest = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord curr = lowestHumidityInFile(parser);
            lowest = compareHumidity(lowest, curr);
        }
        return lowest;
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        double sum = 0.;
        int count = 0;
        for(CSVRecord record : parser) {
            sum += Double.parseDouble(record.get("TemperatureF"));
            count++;
        }
        return sum / count;
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double sum = 0.;
        int count = 0;
        for(CSVRecord record : parser) {
            if(Integer.parseInt(record.get("Humidity")) >= value) {
                sum += Double.parseDouble(record.get("TemperatureF"));
                count++;
            }
        }
        if(count != 0) {
            return sum / count;
        } else {
            return 0.;
        }
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestHour = coldestHourInFile(parser);
        System.out.println(coldestHour.get("DateUTC") + " " + coldestHour.get("TemperatureF"));
    }
    
    public void testFileWithColdestTemperature() {
        String file = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + file);
        FileResource fr = new FileResource("nc_weather/2013/" + file);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestHour = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day was " + coldestHour.get("TemperatureF"));
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + record.get("Humidity") + " at " + record.get("DateUTC"));
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + record.get("Humidity") + " at " + record.get("DateUTC"));
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Average temperature in file is " + averageTemperatureInFile(parser));
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureWithHighHumidityInFile(parser, 80);
        if(avg > 0) {
            System.out.println("Average Temp with high Humidity is " + avg);
        } else {
            System.out.println("No temperatures with that humidity");
        }
    }
}
