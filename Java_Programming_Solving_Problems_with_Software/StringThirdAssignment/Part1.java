import edu.duke.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        while(stopIndex != -1) {
            if((stopIndex - startIndex) % 3 == 0) {
                return stopIndex;
            } else {
                stopIndex = dna.indexOf(stopCodon, stopIndex + 1);
            }
        }
        return dna.length();
    }
    
    public String findGene(String dna, int start) {
        int startIndex = dna.indexOf("ATG", start);
        if(startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int stopIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if(stopIndex == dna.length()) {
            return "";
        }
        return dna.substring(startIndex, stopIndex + 3);
    }
    
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while(true) {
            String currGene = findGene(dna, startIndex);
            if(currGene.isEmpty()) {
                break;
            }
            System.out.println(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
    }
    
    public StorageResource getAllGenes(String dna) {
        int startIndex = 0;
        StorageResource sr = new StorageResource();
        while(true) {
            String currGene = findGene(dna, startIndex);
            if(currGene.isEmpty()) {
                break;
            }
            sr.add(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
        return sr;
    }
    
    public void testFindStopCodon() {
        String dna = "ATGGCTTAC";
        System.out.println(findStopCodon(dna, 0, "TAA"));
        dna = "GATGAGTTTAG";
        System.out.println(findStopCodon(dna, 1, "TAG"));
        dna = "GATGAGTTAAG";
        System.out.println(findStopCodon(dna, 1, "TAA"));
    }
    
    public void testFindGene() {
        String dna = "AGTGCATCGA"; // no "ATG"
        System.out.println("dna strand: " + dna);
        System.out.println("gene: " + findGene(dna, 0));
        dna = "ATGGCATCGTAAA"; // one valid codon
        System.out.println("dna strand: " + dna);
        System.out.println("gene: " + findGene(dna, 0));
        dna = "ATGGCATCGTAATGAA"; // multiple valid codon
        System.out.println("dna strand: " + dna);
        System.out.println("gene: " + findGene(dna, 0));
        dna = "ATGGCATCGTATGCAA"; // no valid codon
        System.out.println("dna strand: " + dna);
        System.out.println("gene: " + findGene(dna, 0));
        dna = "AATGCTAACTAGCTGACTAATATGCGAGATTAG";
        System.out.println("dna strand: " + dna);
        System.out.println("gene: " + findGene(dna, 0));
    }
    
    public void testGetAllGenes() {
        //String dna = "AATGCTAACTAGCTGACTAATATGCGAGATTAG";
        String dna = "CTGONEAtGONEcCCGGGAAAXXXYYYGGGGTAGYYCTGCCCATGENDZZZTAAONEXXXYYYZZZTAAXXxXXTWOATGTWOYYYZZZCCCATGATGENDZZZTAGTWOXXTHREEATGATGTAATHREESTOPTAGAGGGCCCCCFOURATGTAGGXXXFIVEAtgYYYFIVZZZAAAXXXFIVENDZZZTGAFIVESTOPSIXATGSIXCGGGCCGGGATCAAASIXENDTAASEVATGSIXCGGGCCGGGATCAAASEVENDENDtaAEIGSTOPTAGAGLASTONEATgtAACTG";
        StorageResource sr = getAllGenes(dna);
        for(String s : sr.data()) {
            System.out.println(s);
        }
    }
    
    public static void main(String[] args) {
        Part1 p1 = new Part1();
        p1.testGetAllGenes();
    }
}
