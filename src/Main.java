public class Main {

    // Store string to encrypt
    private static String rawCanterbury = "Whan that Aprille with his shoures sote\n" +
            "\n" +
            "The droghte of Marche hath perced to the rote,\n" +
            "\n" +
            "And bathed every veyne in swich licour,\n" +
            "\n" +
            "Of which vertu engendred is the flour;\n" +
            "\n" +
            "\n" +
            "Whan Zephirus eek with his swete breeth\n" +
            "\n" +
            "Inspired hath in every holt and heeth\n" +
            "\n" +
            "The tendre croppes, and the yonge sonne\n" +
            "\n" +
            "Hath in the Ram his halfe cours y-ronne,\n" +
            "\n" +
            "And smale fowles maken melodye,\n" +
            "\n" +
            "\n" +
            "That slepen al the night with open ye,\n" +
            "\n" +
            "(So priketh hem nature in hir corages):\n" +
            "\n" +
            "Than longen folk to goon on pilgrimages\n" +
            "\n" +
            "(And palmers for to seken straunge strondes)\n" +
            "\n" +
            "To ferne halwes, couthe in sondry londes;\n" +
            "\n" +
            "\n" +
            "And specially, from every shires ende\n" +
            "\n" +
            "Of Engelond, to Caunterbury they wende,\n" +
            "\n" +
            "The holy blisful martir for to seke,\n" +
            "\n" +
            "That hem hath holpen, whan that they were seke.\n" +
            "\n";

    // Test encryption
    private static String rawJabberwocky = "";

    // Clean text - remove punctuation and \n using regex
    private static String stringClean(String raw){
        String out = raw.replaceAll("([^\\w]|[\\\\n])", "");
        out = out.toLowerCase();
        return out;
    }

    // Encryption method
    private static String vignereEncrypt(String input, String key){
        Integer[] keyArray = keyToArray(key);
        int keyLength = keyArray.length;
        char[] inputArray = input.toCharArray();
        char[] outputArray = new char[inputArray.length];

        // Go through each character, encrypting based on keyArray
        for(int i = 0; i < inputArray.length; i++){
            outputArray[i] = characterEncrypt(inputArray[i],keyArray[i % keyLength]);
        }

        return new String(outputArray);
    }

    // Set char to int
    private static int charToInt(char c){
        return c - 'a';
    }

    // Send key to array for encryption use
    private static Integer[] keyToArray(String key){
        char[] charKey = key.toCharArray();
        Integer[] output = new Integer[charKey.length];
        for(int i = 0; i < charKey.length; i++){
            output[i] = charToInt(charKey[i]);
        }
        return output;
    }

    // Rotates characters through the alphabet based on an integer key
    private static char characterEncrypt(char raw, int key){
        int rawInt = charToInt(raw);
        char output = (char)('a' + ((rawInt + key) % 26));
        return output;
    }

    // Main - Will be used to declare the key as well as running encryption
    public static void main(String[] args) {
        String clean = stringClean(rawCanterbury);
        String keyword = "hobnob";
        String encrypted = vignereEncrypt(clean, keyword).toUpperCase();

        // Displays encrypted text
        System.out.println(encrypted);
    }
}