import java.util.Arrays;
import java.util.HashMap;

public class Decrypt {
    public HashMap<Character, Integer> getFrequencies(String s){
        return null;
    }

    private class letterFrequency {
        public HashMap<Character, Integer> frequencies;

        public letterFrequency(String s){
            frequencies = getFrequencies(s);
        }

        public letterFrequency(HashMap<Character, Integer> h){
            frequencies = h;
        }

        public int size(){
            return frequencies.size();
        }

        //function to determine if two letter freqeuncies are similar
        //this will be used to see if letter frequencies by character are close to those
        //of standard unencrypted English

        /*
        public boolean approximates(letterFrequency that, int delta){

        }
        */

        //Compares the relative rates of letter occurances between two letterFrequencies,
        //and returns the percentage difference--lower numbers are more similar.
        //If the two letterFrequencies have different numbers of characters in them,
        //the function will simply return no correlation.

        public double frequencyNearness(letterFrequency that){
            if(this.size() != that.size()){
                return 101; //TODO approximate missing characters as 0 to allow for comparison
            }
            double totalDifference = 0;
            Integer[] thisValues = (Integer[])this.frequencies.values().toArray();
            Integer[] thatValues = (Integer[])that.frequencies.values().toArray();
            Arrays.sort(thisValues);
            Arrays.sort(thatValues);
            for(int i = 0; i < thisValues.length; i++){
                double largest = Math.max(thisValues[i], thatValues[i]);
                double difference = Math.abs(thisValues[i] - thatValues[i]);
                double percentDifference = ((largest / difference) * 100)/this.size();
                totalDifference += percentDifference;
            }
            return totalDifference;
        }

    }
}

//custom object for list of frequency mappings

