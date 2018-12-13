
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        int start = dna.indexOf("ATG");
        if(start == -1) {
            return "";
        }
        int stop = dna.indexOf("TAA", start + 3);
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
        System.out.println("gene: " + findSimpleGene(dna));
        dna = "ATGACGCTAGC"; // no TAA
        System.out.println("dna strand: " + dna);
        System.out.println("gene: " + findSimpleGene(dna));
        dna = "ATCACGCTAGC"; // no ATG or TAA
        System.out.println("dna strand: " + dna);
        System.out.println("gene: " + findSimpleGene(dna));
        dna = "ATGACGCTAGTAAC"; // has ATG and TAA but not multiple of 3
        System.out.println("dna strand: " + dna);
        System.out.println("gene: " + findSimpleGene(dna));
        dna = "CATGACGCAGTAAGC"; // has ATG and TAA and multiple of 3
        System.out.println("dna strand: " + dna);
        System.out.println("gene: " + findSimpleGene(dna));
    }
    
    public static void main(String[] args) {
        Part1 p1 = new Part1();
        p1.testSimpleGene();
    }
}
