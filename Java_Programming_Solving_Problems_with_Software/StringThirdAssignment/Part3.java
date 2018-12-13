import edu.duke.*;
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public float cgRatio(String dna) {
        int num = 0;
        for(int i = 0; i < dna.length(); i++) {
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G') {
                num++;
            }
        }
        return (float)num / dna.length();
    }
    
    public void processGenes(StorageResource sr) {
        int numLargerThanNine = 0, numCGRatio = 0, numLargerThanSixty = 0;
        String longest = "";
        for(String s : sr.data()) {
            System.out.println("gene: " + s);
            if(s.length() > 9) {
                numLargerThanNine++;
                System.out.println("Longer Than 9 characters: " + s);
            }
            if(cgRatio(s) > 0.35) {
                numCGRatio++;
                System.out.println("C-G Ratio higher than 0.35: " + s);
            }
            if(s.length() > longest.length()) {
                longest = s;
            }
            if(s.length() > 60) {
                numLargerThanSixty++;
                System.out.println("Longer Than 60 characters: " + s);
            }
        }
        System.out.println("num of genes longer than 9 characters = " + numLargerThanNine);
        System.out.println("num of genes with C-G ratio larger than 0.35 = " + numCGRatio);
        System.out.println("num of genes longer than 60 = " + numLargerThanSixty);
        System.out.println("length of longest gene = " + longest.length());
    }
    
    public int countCodon(String dna, String codon) {
        int pos = dna.indexOf(codon), count = 0;
        while(pos != -1) {
            count++;
            pos = dna.indexOf(codon, pos + 1);
        }
        return count;
    }
    
    public void testProcessGenes() {
        FileResource fr = new FileResource("dna/GRch38dnapart.fa");
        //FileResource fr = new FileResource("dna/brca1line.fa");
        String dna = fr.asString().toUpperCase();
        //String dna = "ATGGTCTACATCTACTACTAAGCAGCAGACTAA";
        Part1 p1 = new Part1();
        StorageResource sr = p1.getAllGenes(dna);
        processGenes(sr);
    }
    
    public void testCountCodon() {
        FileResource fr = new FileResource("dna/GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();
        System.out.println(countCodon(dna, "CTG"));
    }
    
    public static void main(String[] args) {
        Part3 p3 = new Part3();
        p3.testCountCodon();
    }
}
