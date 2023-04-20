package co.com.niki.algorithms.recursion;

public class BasicRecursionExplanation {

    public static void main(String[] args) {
        basicHead(5);
        basicTail(5);
    }

    /**
     * Steps
     * 1. Defining a base case
     * 2. Call recursively
     * 3. Execute operation
     *
     * @param n Value used to determine numbers to sum up
     * @return Total sum
     */
    public static void basicHead(int n) {
        System.out.println("Calling function with n " + n);
        if (n == 1) return;
        basicHead(n - 1);
        System.out.println("Value of n is " + n);
    }

    /**
     * Steps
     * 1. Defining a base case
     * 2. Execute operation
     * 3. Call recursively
     *
     * @param n Value used to determine numbers to sum up
     * @return Total sum
     */
    public static void basicTail(int n) {
        System.out.println("Calling function with n " + n);
        if (n == 1) return;
        System.out.println("Value of n is " + n);
        basicTail(n - 1);
    }
}
