package ihsan.pertemuan10;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MathUtils {

    private static final Logger logger = Logger.getLogger(MathUtils.class.getName());

    private MathUtils() {
        // Private constructor to prevent instantiation
        throw new IllegalStateException("Utility class");
    }

    public static int factorial(int n) throws IllegalArgumentException {
        if (n < 0) {
            logger.log(Level.WARNING, "Attempted to calculate factorial of negative number: {0}", n);
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        if (n > 16) {
            logger.log(Level.WARNING, "Attempted to calculate factorial of too large number: {0}", n);
            throw new IllegalArgumentException("Value too large, will cause overflow (max is 16)");
        }

        logger.log(Level.FINE, "Calculating factorial of: {0}", n);
        int fac = 1;
        for (int i = n; i > 0; i--) {
            fac *= i;
        }
        return fac;
    }
}