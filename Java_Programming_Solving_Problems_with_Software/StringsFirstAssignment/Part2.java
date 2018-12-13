
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        int start = dna.indexOf(startCodon);
        if(start == -1) {
            return "";
        }
        int stop = dna.indexOf(stopCodon, start + 3);
        if(stop == -1) {
            return "";
        }
        if((stop - start) % 3 == 0) {
            return dna.substring(start, stop + 3);
        } else {
            return "";
        }
    }
    
    public void testSimpleGene(){
        String dna = "AGCTTAACG"; // no ATG
        System.out.println("dna strand: " + dna);
        System.out.println("gene: " + findSimpleGene(dna, "ATG", "TAA"));
        dna = "ATGACGCTAGC"; // no TAA
        System.out.println("dna strand: " + dna);
        System.out.println("gene: " + findSimpleGene(dna, "ATG", "TAA"));
        dna = "ATCACGCTAGC"; // no ATG or TAA
        System.out.println("dna strand: " + dna);
        System.out.println("gene: " + findSimpleGene(dna, "ATG", "TAA"));
        dna = "ATGACGCTAGTAAC"; // has ATG and TAA but not multiple of 3
        System.out.println("dna strand: " + dna);
        System.out.println("gene: " + findSimpleGene(dna, "ATG", "TAA"));
        dna = "CATGACGCAGTAAGC"; // has ATG and TAA and multiple of 3
        System.out.println("dna strand: " + dna);
        System.out.println("gene: " + findSimpleGene(dna, "ATG", "TAA"));
    }
    
    public static void main(String[] args) {
        Part1 p1 = new Part1();
        p1.testSimpleGene();
    }
}
