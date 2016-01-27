
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

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
        try {
            while (word != null) {
                word = in.readLine();
                if (word.length() > 2){
                definition = word;
                word = word.substring(0, word.indexOf(" "));
                System.out.println(word);
                definition = definition.substring(definition.indexOf(". ")+2);
                System.out.println(definition);
                map.put(word, definition);
                }
            }
            System.out.println("test");
            System.out.println(map.get("A-"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            in.close();
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

    public static int ordinalIndexOf(String str, String c, int n) {
        int pos = str.indexOf(c, 0);
        while (n-- > 0 && pos != -1) {
            pos = str.indexOf(c, pos + 1);
        }
        return pos;
    }
}
