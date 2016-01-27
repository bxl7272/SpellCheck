
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SpellCheck {

    static String word = "";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException, IOException { //User decides if they want to use dictionary or spellcheck 
        System.out.println("Would you like to use the dictionary or the spellcheck?");
        Dictionary dict = new Dictionary("dictionary.txt");
        int choice = sc.nextInt();
        if (choice == 1) {
            define(word);
        } else {
            spellCheck(word);
        }
    }

    public static void define(String word) {
        if (word == "") {
            System.out.println("What is the word you are trying to spell?");
            word = sc.next();
        }
    }

    public static void spellCheck(String word) {
        while (word.equals("")) {//If this is the first time they are using spellcheck, asks for word they are trying to spell
            System.out.println("What is the word you are trying to spell?");
            word = sc.next();
        }

        isWord k = new isWord();//Uses isWord Class to check for word
        boolean isWord = k.isWord(word);

        if (isWord) {
            System.out.println(word + " is a correctly spelled word!");
        } else {//If it's not a word, then tries to find the closest word to input
            closestWord n = new closestWord();
            String closestWord = n.closestWord(word);
            System.out.println("Is " + closestWord + " the word you are trying to spell?");

        }
    }
}
