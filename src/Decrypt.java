import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Decrypt {

    public static HashMap<Character, Double> engHash = new HashMap<>();
    public static LetterFrequency englishFrequencies;

    public static void frequencyIntialize(){
        engHash.put('a', 8.167);
        engHash.put('b', 1.492);
        engHash.put('c', 2.782);
        engHash.put('d', 4.253);
        engHash.put('e', 12.702);
        engHash.put('f', 2.228);
        engHash.put('g', 2.015);
        engHash.put('h', 6.094);
        engHash.put('i', 6.966);
        engHash.put('j', 0.153);
        engHash.put('k', 0.772);
        engHash.put('l', 4.025);
        engHash.put('m', 2.406);
        engHash.put('n', 6.749);
        engHash.put('o', 7.507);
        engHash.put('p', 1.929);
        engHash.put('q', 0.095);
        engHash.put('r', 5.987);
        engHash.put('s', 6.327);
        engHash.put('t', 9.056);
        engHash.put('u', 2.758);
        engHash.put('v', 0.978);
        engHash.put('w', 2.360);
        engHash.put('x', 0.150);
        engHash.put('y', 1.974);
        engHash.put('z', 0.074);
        englishFrequencies = new LetterFrequency(engHash);
    }

    public static void decrypter(String encryptedText){
        frequencyIntialize();
        // LetterFrequency[] freqArray = (LetterFrequency[]) new Object[8];

        /*
        for(int i = 0; i < 8; i++){
                freqArray[i] = new LetterFrequency(encryptedText, i + 1);
        }
        //Arrays.sort(freqArray, new FrequencyComparator());
        */

        int keyLength = 0;
        double currentDistance = 102; //to be compared to distance, arbitrarily larger placeholder
        for(int i = 1; i < 9; i++){
            // i refers to key length being checked, j refers to the current offset
            double testDistance = 0;
            for(int j = 0; j < i; j++){
                LetterFrequency offsetFrequency = new LetterFrequency(encryptedText, i, j);
                testDistance += offsetFrequency.frequencyNearness(englishFrequencies);
            }
            testDistance = testDistance/i;
            if(testDistance < currentDistance){
                keyLength = i;
                currentDistance = testDistance;
            }

        }

        String key = getKeyByLength(encryptedText, keyLength);
        System.out.println(keyLength);
        String decrypted = decryptByKey(encryptedText, key);
    }

    private static String getKeyByLength(String encrypted, int length){
        return null;
        //check each letterFrequency at each offset
        //from there, find most similar letter counts, and find the letter that would make them closest
        //and append them together into a string
    }

    private static String decryptByKey(String encrypted, String key){
        return null;
        //convert both to charArrays and subtract by character
        //use modulo for both subtraction and looping through the key
    }

    // Get the frequencies at the character count
    private static HashMap<Character, Double> getFrequencies(String s, int n, int offset){

        char[] charArray = s.toCharArray();
        int stringLength = s.length();
        double stringFraction = 1/stringLength;
        Tuple<Character, Double>[] freqArray = null; //FIX need to initialize properly

        for(int i = 0; i < 26; i++){
            freqArray[i] = new Tuple<>((char)('a' + i),0.0);
        }

        // Need to write a loop that checks frequencies for each letter of the key separately - every n characters

        for(int i = offset; i < stringLength; i += n){
            char currentLetter = charArray[i];
            freqArray[currentLetter - 'a'].second += stringFraction;
        }

        HashMap<Character, Double> outputHash = new HashMap<>();
        for(Tuple<Character, Double> t:freqArray){
            outputHash.put(t.first, t.second);
        }
        return outputHash;

    }

    private static class Tuple<X,Y> {
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

    private static class LetterFrequency {
        public HashMap<Character, Double> frequencies;

        public LetterFrequency(String s, int n, int offset){
            frequencies = getFrequencies(s, n, offset);
        }

        public LetterFrequency(HashMap<Character, Double> h){
            frequencies = h;
        }

        public int size(){
            return frequencies.size();
        }

        //Compares the relative rates of letter occurrences between two letterFrequencies,
        //and returns the percentage difference--lower numbers are more similar.
        //If the two letterFrequencies have different numbers of characters in them,
        //the function will simply return no correlation.

        public double frequencyNearness(LetterFrequency second){
            if(this.size() != second.size()){
                return 101;
            }
            double totalDifference = 0;
            Integer[] firstValues = (Integer[])this.frequencies.values().toArray();
            Integer[] secondValues = (Integer[])second.frequencies.values().toArray();
            Arrays.sort(firstValues);
            Arrays.sort(secondValues);
            for(int i = 0; i < firstValues.length; i++){
                double largest = Math.max(firstValues[i], secondValues[i]);
                double difference = Math.abs(firstValues[i] - secondValues[i]);
                double percentDifference = ((largest / difference) * 100)/this.size();
                totalDifference += percentDifference;
            }
            return totalDifference;
        }
    }

    private class FrequencyComparator implements Comparator<LetterFrequency>{
        @Override
        public int compare(LetterFrequency o1, LetterFrequency o2) {
            return (int)((o1.frequencyNearness(englishFrequencies) * 1000) - (o2.frequencyNearness(englishFrequencies) * 1000));
        }
    }

}