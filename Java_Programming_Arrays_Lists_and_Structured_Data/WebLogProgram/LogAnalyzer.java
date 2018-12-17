
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry> ();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         WebLogParser lp = new WebLogParser();
         for(String line : fr.lines()) {
             records.add(lp.parseEntry(line));
         }
     }
     
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs() {
         ArrayList<String> uniqueIPs = new ArrayList<String> ();
         for(LogEntry le : records) {
             String IP = le.getIpAddress();
             if(!uniqueIPs.contains(IP)) {
                 uniqueIPs.add(IP);
             }
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num) {
         for(LogEntry le : records) {
             if(le.getStatusCode() > num) {
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> result = new ArrayList<String> ();
         for(LogEntry le : records) {
             String time = le.getAccessTime().toString();
             if(time.substring(4, 10).equals(someday) && !result.contains(le.getIpAddress())) {
                 result.add(le.getIpAddress());
             }
         }
         return result;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         int num = 0;
         HashMap<String, Boolean> uniqueIps = new HashMap<String, Boolean> ();
         for(LogEntry le : records) {
             if(le.getStatusCode() >= low && le.getStatusCode() <= high) {
                 if(!uniqueIps.containsKey(le.getIpAddress())) {
                     uniqueIps.put(le.getIpAddress(), true);
                     num++;
                 }
             }
         }
         return num;
     }
     
     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> counts = new HashMap<String, Integer> ();
         for(LogEntry le : records) {
             String ip = le.getIpAddress();
             if(!counts.containsKey(ip)) {
                 counts.put(ip, 1);
             } else {
                 counts.put(ip, counts.get(ip) + 1);
             }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> myMap) {
         int max = 0;
         for(String ip : myMap.keySet()) {
             if(myMap.get(ip) > max) {
                 max = myMap.get(ip);
             }
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> myMap) {
         int maxNum = mostNumberVisitsByIP(myMap);
         ArrayList<String> result = new ArrayList<String> ();
         for(String ip : myMap.keySet()) {
             if(myMap.get(ip) == maxNum) {
                 result.add(ip);
             }
         }
         return result;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>> ();
         for(LogEntry le : records) {
             String day = le.getAccessTime().toString().substring(4, 10);
             if(!result.containsKey(day)) {
                 ArrayList<String> ips = new ArrayList<String> ();
                 ips.add(le.getIpAddress());
                 result.put(day, ips);
             } else {
                 result.get(day).add(le.getIpAddress());
             }
         }
         return result;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dayToIPs) {
         int mostVisits = 0;
         String targetDay = "";
         for(String day : dayToIPs.keySet()) {
             if(dayToIPs.get(day).size() > mostVisits) {
                 mostVisits = dayToIPs.get(day).size();
                 targetDay = day;
             }
         }
         return targetDay;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> myMap, String day) {
         ArrayList<String> IPsThisDay = myMap.get(day);
         HashMap<String, Integer> visitsPerIp = new HashMap<String, Integer> ();
         for(String ip : IPsThisDay) {
             if(!visitsPerIp.containsKey(ip)) {
                 visitsPerIp.put(ip, 1);
             } else {
                 visitsPerIp.put(ip, visitsPerIp.get(ip) + 1);
             }
         }
         return iPsMostVisits(visitsPerIp);
     }
}
