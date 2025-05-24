package ihsan.pertemuan10;
//  ParseInts.java
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParseInts {

    private static final Logger logger = Logger.getLogger(ParseInts.class.getName());

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            parseAndSumIntegers(scan);
        }
    }

    private static void parseAndSumIntegers(Scanner scan) {
        int sum = 0;
        logger.info("Enter a line of text with numbers:");
        String line = scan.nextLine();

        try (Scanner scanLine = new Scanner(line)) {
            while (scanLine.hasNext()) {
                try {
                    sum += scanLine.nextInt();
                } catch (InputMismatchException e) {
                    logger.log(Level.WARNING, "Invalid input: {0}", scanLine.next());
                }
            }
        }
        logger.log(Level.INFO, "The sum of the integers on this line is {0}", sum);
    }
}