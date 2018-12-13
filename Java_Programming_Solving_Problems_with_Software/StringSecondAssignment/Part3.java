
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int stopIndex = dna.indexOf(stopCodon, startIndex + 1);
        if((stopIndex - startIndex) % 3 == 0) {
            return stopIndex;
        } else {
            return dna.length();
        }
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
    
    public int countGenes(String dna) {
        int startIndex = 0, count = 0;
        while(true) {
            String currGene = findGene(dna, startIndex);
            if(currGene.isEmpty()) {
                break;
            }
            count++;
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
        return count;
    }
    
    public void testCountGenes() {
        String dna = "ATGTAAGATGCCCTAGT";
        System.out.println(countGenes(dna));
    }
    
    public static void main(String[] args) {
        Part3 p3 = new Part3();
        p3.testCountGenes();
    }
}
