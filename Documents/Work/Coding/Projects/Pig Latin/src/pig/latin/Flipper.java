package pig.latin;

/**
 *This file does the conversion of the input
 * @author Byron
 */
public class Flipper {

    public Flipper() {
    }
/**
 *This method takes the input, then checks if the input starts with a vowel. 
 * After, it converts to pig latin based on the rules using substrings.
 * @author Byron
 */
    public String convert(String input) {
        char[] vowels = "aeiouy".toCharArray();
        for (char c : vowels) {
            if (input.substring(0, 1).equals(String.valueOf(c))) {
                return input + "way";
            } else if (input.length() > 2 && input.substring(1, 2).equals(String.valueOf(c))) {
                return input.substring(1, input.length()) + input.substring(0, 1) + "ay";
            }
        }
        return input.substring(2, input.length()) + input.substring(0, 2) + "ay";
    }
}
