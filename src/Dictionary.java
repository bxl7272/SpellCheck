
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * The purpose of this class is to create a HashMap with all the words in the english dictionary paired
 * with all their definitions
 * and open the template in the editor.
 */
/**
 *
 * @author Byron
 */
public class Dictionary {

    public static HashMap<String, String> map = new HashMap<String, String>(5000000);

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
                if (word.length() > 2) {
                    definition = word;
                    word = word.substring(0, ordinalIndexOf(word, ' ', 1));
                    if (word.charAt(word.length()-1) == ' '){
                        word = word.substring(0, word.length()-1);
                    }
                    definition = definition.substring(definition.indexOf(". ") + 2);
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

    public boolean isWord(String word) {
        if (map.containsKey(word.toLowerCase())) {
            return true;
        } else if (map.containsKey(word.toLowerCase() + "1")) {
            return true;
        } else {
            return false;
        }
    }

    public List<String> closestWords(String input) {
        List<String> suggestedWords = new ArrayList<String>();

        return suggestedWords;
    }

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

    public static int ordinalIndexOf(String str, char c, int n) {
        int pos = str.indexOf(c, 0);
        while (n-- > 0 && pos != -1) {
            pos = str.indexOf(c, pos + 1);
        }
        return pos;
    }
}
