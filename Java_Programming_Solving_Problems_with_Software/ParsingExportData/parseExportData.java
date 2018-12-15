import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of parseExportData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class parseExportData {
    public String countryInfo(CSVParser parser, String country) {
        for(CSVRecord record : parser) {
            String currCountry = record.get("Country");
            if(currCountry.equals(country)) {
                return currCountry + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for(CSVRecord record : parser) {
            String exports = record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int num = 0;
        for(CSVRecord record : parser) {
            String exports = record.get("Exports");
            if(exports.contains(exportItem)) {
                num++;
            }
        }
        return num;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for(CSVRecord record : parser) {
            if(record.get("Value (dollars)").length() > amount.length()) {
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser, "Nauru"));
        //listExportersTwoProducts(parser, "cotton", "flowers");
        //System.out.println(numberOfExporters(parser, "cocoa"));
        bigExporters(parser, "$999,999,999,999");
    }
    
    public static void main(String[] args) {
        parseExportData obj = new parseExportData();
        obj.tester();
    }
}
