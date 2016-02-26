
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

/*
 * The purpose of this class is to create a HashMap with all the words in the english dictionary paired
 * with all their definitions, then checks if user input matches an entry in the map. Then, if there are no matches,
 * uses spellchecking algorithims to suggest the correct spelling of the user input.
 *
 *
 * @author Byron
 */
public class Dictionary {

    public static HashMap<String, String> map = new HashMap<String, String>(5000000);
    public static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    /**
     * Initializes file reader then stores every line in the dictionary into a hashmap
     * paired with its definition. (Also formats the entries)
     *
     * @author Byron
     */
    public Dictionary(String file_path) throws FileNotFoundException, IOException {
        String word = "";
        String definition;
        FileReader f = new FileReader(file_path);
        BufferedReader in = new BufferedReader(f);
        System.out.println("Initializing...");
        try {
            while (word != null) {
                word = in.readLine();
                if (word == null) {
                    break;
                }

                /* Formats the dictionary text file into two strings, then adds them to a map. 
  *             Key is the word, value is the definition. */
                if (word.length() > 2) {
                    definition = word;
                    word = word.substring(0, ordinalIndexOf(word, ' ', 1)); //Checks for the second occurence of space.
                    if (word.charAt(word.length() - 1) == ' ') {  // Removes spaces from the ends of the words.
                        word = word.substring(0, word.length() - 1);
                    }
                    definition = definition.substring(definition.indexOf(". ") + 2); //Formats definition string.
                    map.put(word.toLowerCase(), definition.toLowerCase());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            in.close();
            System.out.println("Done Initializing!");
        }
    }

    /* Simply checks if the input is a word in the map. Also checks if it has multiple definitions
    since the format of some words is "word1". */
    public boolean isWord(String word) {
        if (map.containsKey(word.toLowerCase())) {
            return true;
        } else if (map.containsKey(word.toLowerCase() + "1")) {
            return true;
        } else {
            return false;
        }
    }

    /* Creates possible words then uses isWord function to test
     */
    public List<String> closestWords(String input) {
        List<String> suggestedWords = new ArrayList<String>();
        String wordToCheck = "";
        for (char c : alphabet) {

            if (isWord(c + input)) { //Adds a letter to front and back of word
                suggestedWords.add(c + input);
            }
            if (isWord(input + c)) {
                suggestedWords.add(input + c);
            }
            for (int i = 1; i < input.length(); i++) {
                if (isWord(input.substring(0, i) + c + input.substring(i, input.length()))) { //Adds a letter inbetween the word
                    if (!suggestedWords.contains(input.substring(0, i) + c + input.substring(i, input.length()))) {
                        suggestedWords.add(input.substring(0, i) + c + input.substring(i, input.length()));
                    }
                }

                if (isWord(input.substring(0, i - 1) + c + input.substring(i, input.length()))) {//replaces a letter inbetween the word
                    if (!suggestedWords.contains(input.substring(0, i - 1) + c + input.substring(i, input.length()))) {
                        suggestedWords.add(input.substring(0, i - 1) + c + input.substring(i, input.length()));
                    }
                }
                if ((i < input.length() - 1)) {

                    if (isWord(input.substring(0, i) + input.substring(i + 1, input.length()))) {//Checks for an extra typed letter
                        if (!suggestedWords.contains(input.substring(0, i) + input.substring(i + 1, input.length()))) {
                            suggestedWords.add(input.substring(0, i) + input.substring(i + 1, input.length()));
                        }
                    }

                    char[] swap = input.toCharArray();
                    char temp = swap[i];
                    swap[i] = swap[i + 1];
                    swap[i + 1] = temp;
                    String swappedString = new String(swap);
                    if (isWord(swappedString)) {
                        if (!suggestedWords.contains(swappedString)) {
                            suggestedWords.add(swappedString);
                        }
                    }
                }
            }
        }
        return suggestedWords;
    }
/**
 *This method checks if user input matches any key in the HashMap and returns the definition.
 * @author Byron
 */
    public String define(String word) {
        String definitions = "";
        for (String key : map.keySet()) {
            if (key.contains(word.toLowerCase()) && key.matches(".*\\d.*")) {
                definitions += map.get(key);
            }
            if (key.equals(word.toLowerCase())) {
                definitions += map.get(key);
            }
        }
        return definitions;
    }
/**
 *This program checks for the nth occurence of a char c in a String.
 * @author Byron
 */
    public static int ordinalIndexOf(String str, char c, int n) {
        int pos = str.indexOf(c, 0);
        while (n-- > 0 && pos != -1) {
            pos = str.indexOf(c, pos + 1);
        }
        return pos;
    }
}
