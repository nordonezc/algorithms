package co.com.niki.algorithms.recursion;

/**
 * Fibonacci is the sum of the two precedings numbers
 * f(n) = f(n-1) + f(n-2)
 */
public class FibonacciAlgorithm {

    private static final int NUMBER_TO_CALCULATE = 8;

    public static void main(String[] args) {
        System.out.println(iteration(NUMBER_TO_CALCULATE));
        System.out.println(headRecursion(NUMBER_TO_CALCULATE));
        System.out.println(tailRecursion(NUMBER_TO_CALCULATE, 0, 1));
    }

    /**
     * Calculate fibonacci of the n integers iteratively
     *
     * @param n Value used to determine the fibonacci number to calculate
     * @return Sum of the n-1 + n-2
     */
    public static int iteration(int n) {
        int previousResult = 1;
        int result = 0;

        for (int num = 0; num < n; num++) {
            int temporalResult = result;
            result += previousResult;
            previousResult = temporalResult;
        }

        return result;
    }

    /**
     * Calculate fibonacci of the n recursively defining a base case
     * and after do some operations executing a backtracking phase
     *
     * @param n Value used to determine numbers to calculate fibonacci
     * @return Total sum
     */
    public static int headRecursion(int n) {

        if (n == 0) return 0;
        if (n == 1) return 1;

        return headRecursion(n - 1) + headRecursion(n - 2);
    }

    /**
     * Calculate fibonacci of the n recursively defining a base case
     * with no unknown values in the recursive call and the
     * operations are done before the recursive call
     *
     * @param n Value used to determine numbers to calculate fibonacci
     * @param firstResult stores the sum of n and the first result
     * @param secondResult stores the sum of the the last 2
     * @return Total sum
     */
    public static int tailRecursion(int n, int firstResult, int secondResult) {

        if (n == 0) return firstResult;
        if (n == 1) return secondResult;

        return tailRecursion(n - 1, secondResult, secondResult + firstResult);
    }
}
