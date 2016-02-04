package pig.latin;
import java.util.Scanner;
/**
 *This program takes a string then converts it into pig latin.
 * @author Byron
 */
public class PigLatin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Flipper flip = new Flipper();
        while(true){
        System.out.println("What is the word you would like to convert to pig latin?");
                String word = sc.nextLine();
               String latinizedWord = flip.convert(word.toLowerCase());
               System.out.println("Your word in pig latin is " +latinizedWord);
        }
    }
    
}
