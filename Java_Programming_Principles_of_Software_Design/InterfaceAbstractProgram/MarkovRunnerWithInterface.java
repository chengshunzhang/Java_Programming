
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k = 0; k < 3; k++){
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, 1);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, 1);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, 1);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, 1);

    }

    public void testHashMap() {
        String st = "yes-this-is-a-thin-pretty-pink-thistle";
        int size = 50, seed = 42;
        EfficientMarkovModel markov = new EfficientMarkovModel(2);
        markov.setRandom(seed);
        markov.setTraining(st);
    }
    
    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovModel markov = new MarkovModel(2);
        EfficientMarkovModel effMarkov = new EfficientMarkovModel(2);
        int size = 1000, seed = 42;
        long startTime = System.nanoTime();
        runModel(markov, st, size, seed);
        long endTime = System.nanoTime();
        System.out.println("Time for MarkovModel is " + ((endTime - startTime) / 1e9));
        startTime = System.nanoTime();
        runModel(effMarkov, st, size, seed);
        endTime = System.nanoTime();
        System.out.println("Time for EfficientMarkovModel is " + ((endTime - startTime) / 1e9));
    }
    
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
}
