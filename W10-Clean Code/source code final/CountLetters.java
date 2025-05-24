package ihsan.pertemuan10;
//  CountLetters.java
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CountLetters {

    private static final Logger logger = Logger.getLogger(CountLetters.class.getName());
    private static final int ALPHABET_SIZE = 26;

    public static void main(String[] args) {
        int[] counts = new int[ALPHABET_SIZE];

        try (Scanner scan = new Scanner(System.in)) {
            logger.info("Enter a single word (letters only, please): ");
            String word = scan.nextLine().toUpperCase();

            countLetters(word, counts);
            printFrequencies(counts);

        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Invalid input: ", e);
        }
    }

    private static void countLetters(String word, int[] counts) {
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (Character.isLetter(ch)) {
                counts[ch - 'A']++;
            } else {
                logger.log(Level.WARNING, "Not a letter: {0}", ch);
            }
        }
    }

    private static void printFrequencies(int[] counts) {
        logger.info("Letter frequencies:");
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != 0) {
                logger.log(Level.INFO, "{0}: {1}", new Object[]{(char) (i + 'A'), counts[i]});
            }
        }
    }
}