
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt " + index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for(int i = 0; i < length(); i++) {
            ret += myWords[i] + " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if(this.length() != other.length()) {
            return false;
        }
        for(int i = 0; i < this.length(); i++) {
            if(!this.myWords[i].equals(other.myWords[i])) {
                return false;
            }
        }
        return true;
    }

    public WordGram shiftAdd(String word) { 
        WordGram out = new WordGram(myWords, 0, myWords.length);
        for(int i = 0; i + 1 < length(); i++) {
            out.myWords[i] = out.myWords[i + 1];
        }
        out.myWords[length() - 1] = word;
        return out;
    }

}