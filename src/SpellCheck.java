import java.util.*;
import javax.swing.*;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 *This program takes user input and prints definitions.
 * @author Byron
 */
public class SpellCheck {

    static String word = "";
    static Scanner sc = new Scanner(System.in);
    static Dictionary dict;

    public static void main(String[] args) throws FileNotFoundException, IOException { //User decides if they want to use dictionary or spellcheck 
        dict = new Dictionary("dictionary.txt");
        lookup();
    }

    public static void lookup(){
        System.out.println("What is the word you are trying to lookup?");
            word = sc.nextLine();
            check(word);
        }
    
    public static void define(String word) {
        String definition = dict.define(word);
        System.out.println("The definition(s) of "+word.toLowerCase() +" is: "+definition);
    }

    public static void check(String word) {
        while (word.equals("")) {//If this is the first time they are using spellcheck, asks for word they are trying to spell
            word = sc.nextLine();
        }
        boolean isWord = dict.isWord(word.toLowerCase());
        if (isWord) {
            System.out.println(word.toLowerCase() + " is a correctly spelled word!");
            define(word);
        } else {//If it's not a word, then tries to find the closest word to input
            System.out.println("That's not spelled correctly.");
            List<String> closestWords = dict.closestWords(word);
            System.out.println("Are any of these the word you are trying to spell?");
            for (String suggestion : closestWords) {
                System.out.println(suggestion);
            }

        }
        lookup();
    }
}
