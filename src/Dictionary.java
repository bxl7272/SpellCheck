
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
        String definition = "";
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
                    word = word.substring(0, word.indexOf(" "));
                    //  System.out.println(word);
                    definition = definition.substring(definition.indexOf(". ") + 2);
                    //   System.out.println(definition);
                    map.put(word, definition);
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
        if (map.get(word) != null) {
            return true;
        } else if (map.get(word + "1") != null) {
            return true;
        } else {
            return false;
        }
    }
    public List<String> closestWords(String input){
    List<String> suggestedWords = new ArrayList<String>();
                
    return suggestedWords;
}
    public String define(String word){
        return map.get(word);
    }
}