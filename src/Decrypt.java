import java.util.Arrays;
import java.util.HashMap;

public class Decrypt {

    private HashMap<Character, Double> getFrequencies(String s, int n){

        char[] charArray = s.toCharArray();
        int stringLength = s.length();
        double stringFraction = 1/stringLength;
        Tuple<Character, Double>[] freqArray = new Tuple<Character, Double>[];
        for(int i = 0; i < 26; i++){
            freqArray[i] = new Tuple<>((char)('a' + i),0.0);
        }
        int offset = 0;
        do{
            for(int i = offset; i != stringLength; i = (i + n) % stringLength){
                char currentLetter = charArray[i];
                freqArray[currentLetter - 'a'].second += stringFraction;
            }
            if(n % stringLength != 0){
                offset += 1;
            }
        } while (offset % n != 0);
        HashMap<Character, Double> outputHash = new HashMap<>();
        for(Tuple<Character, Double> t:freqArray){
            outputHash.put(t.first, t.second);
        }
        return outputHash;

    }

    private class Tuple<X,Y> {
        X first;
        Y second;
        public Tuple(X x, Y y){
            first = x;
            second = y;
        }
        public Tuple(){
            first = null;
            second = null;
        }
    }

    private class letterFrequency {
        public HashMap<Character, Double> frequencies;

        public letterFrequency(String s, int n){
            frequencies = getFrequencies(s, n);
        }

        public letterFrequency(HashMap<Character, Double> h){
            frequencies = h;
        }

        public int size(){
            return frequencies.size();
        }

        //Compares the relative rates of letter occurrences between two letterFrequencies,
        //and returns the percentage difference--lower numbers are more similar.
        //If the two letterFrequencies have different numbers of characters in them,
        //the function will simply return no correlation.

        public double frequencyNearness(letterFrequency first, letterFrequency second){
            if(first.size() != second.size()){
                return 101; //TODO approximate missing characters as 0 to allow for comparison
            }
            double totalDifference = 0;
            Integer[] firstValues = (Integer[])first.frequencies.values().toArray();
            Integer[] secondValues = (Integer[])second.frequencies.values().toArray();
            Arrays.sort(firstValues);
            Arrays.sort(secondValues);
            for(int i = 0; i < firstValues.length; i++){
                double largest = Math.max(firstValues[i], secondValues[i]);
                double difference = Math.abs(firstValues[i] - secondValues[i]);
                double percentDifference = ((largest / difference) * 100)/first.size();
                totalDifference += percentDifference;
            }
            return totalDifference;
        }
    }

}