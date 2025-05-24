package ihsan.pertemuan10;
//  Factorials.java
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Factorials {

    private static final Logger logger = Logger.getLogger(Factorials.class.getName());

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            calculateFactorials(scan);
        }
    }

    private static void calculateFactorials(Scanner scan) {
        String keepGoing = "y";
        logger.info("Do you want to calculate factorials? (y/n): ");
        String initialResponse = scan.next();

        if (initialResponse.equalsIgnoreCase("y")) {
            while (keepGoing.equalsIgnoreCase("y")) {
                logger.info("Enter an integer: ");
                int val = scan.nextInt();

                try {
                    logger.log(Level.INFO, "Factorial({0}) = {1}", new Object[]{val, MathUtils.factorial(val)});
                } catch (IllegalArgumentException e) {
                    logger.log(Level.WARNING, "Error: {0}", e.getMessage());
                    logger.log(Level.WARNING, "Error calculating factorial: {0}", e.getMessage());
                }

                logger.info("Another factorial? (y/n) ");
                keepGoing = scan.next();
            }
        } else {
            logger.info("Okay, skipping factorials.");
        }
    }
}