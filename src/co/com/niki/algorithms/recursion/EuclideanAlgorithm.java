package co.com.niki.algorithms.recursion;

/**
 * Efficient method for computing the greatest common divisor (GCD) of two integers
 *
 * Principle: GCD of two numbers DOES NOT CHANGE if the larger number
 * is replaced by its difference with the smaller number
 */
public class EuclideanAlgorithm {


    private static final int FIRST_NUMBER = 24;
    private static final int SECOND_NUMBER = 9;

    public static void main(String[] args) {
        System.out.println(recursion(FIRST_NUMBER, SECOND_NUMBER));
    }

    /**
     * Solve GCD for the given numbers
     * @param largerNumber Larger number
     * @param lowerNumber Lower number
     */
    public static int recursion(int largerNumber, int lowerNumber){
        int module = largerNumber % lowerNumber;

        if(module == 0) {
            return lowerNumber;
        }

        return recursion(lowerNumber, module);
    }
}
