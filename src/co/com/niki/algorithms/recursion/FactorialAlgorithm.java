package co.com.niki.algorithms.recursion;

public class FactorialAlgorithm {

    private static final int NUMBER_TO_CALCULATE = 5;

    public static void main(String[] args) {
        System.out.println(iteration(NUMBER_TO_CALCULATE));
        System.out.println(headRecursion(NUMBER_TO_CALCULATE));
        System.out.println(tailRecursion(NUMBER_TO_CALCULATE, 1));
    }

    /**
     * Calculate factorial of the n
     *
     * @param n Value used to determine numbers multiply up
     * @return Total sum
     */
    public static int iteration(int n) {
        int result = 1;

        for (int num = 1; num < n + 1; num++) {
            result *= num;
        }

        return result;
    }

    /**
     * Calculate factorial of the n recursively defining a base case
     * and after do some operations executing a backtracking phase
     *
     * @param n Value used to determine numbers to multiply up
     * @return Total sum
     */
    public static int headRecursion(int n) {

        if (n == 1) return 1;

        return n * headRecursion(n - 1);
    }

    /**
     * Calculate factorial of the n recursively defining a base case
     * with no unknown values in the recursive call and the
     * operations are done before the recursive call
     *
     * @param n Value used to determine numbers to multiply up
     * @return Total sum
     */
    public static int tailRecursion(int n, int result) {

        if (n == 0) return result;

        return tailRecursion(n - 1, n * result);
    }
}
