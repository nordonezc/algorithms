package co.com.niki.algorithms.exercises.warm;

import static co.com.niki.algorithms.utils.TimeUtil.printTime;

public class MaximumSubarray {

    /*
    Try to fin the maximum subarray sum in O(n^3), O(n^2) and O(n)
     */
    public static void main(String[] args) {
        Integer n[] = {-1, 2, 4, -3, 5, 2, -5, 2};
        printTime(Resolver::exponential3, n);
        printTime(Resolver::exponential2, n);
        printTime(Resolver::linear, n);

    }

    static class Resolver {

        private Resolver() {
        }

        public static int exponential3(Integer[] n) {
            int highestSum = n[0];

            // Sum from i until j in O(n^3)
            for (int i = 0; i < n.length; i++) {
                for (int j = i; j < n.length; j++) {
                    var sum = 0;
                    for (int k = i; k <= j; k++) {
                        sum += n[k];
                    }
                    highestSum = Math.max(highestSum, sum);
                    //System.out.println("sum from " + i + " to " + j + " is " + sum);
                }

            }

            return highestSum;
        }

        public static int exponential2(Integer[] n) {
            int highestSum = n[0];

            // Sum from i until j in O(n^2)
            for (int i = 0; i < n.length; i++) {
                var sum = 0;
                for (int j = i; j < n.length; j++) {
                    sum += n[j];
                    highestSum = Math.max(highestSum, sum);
                    //System.out.println("sum from " + i + " to " + j + " is " + sum);
                }

            }

            return highestSum;
        }

        public static int linear(Integer[] n) {
            int highestSum = n[0];

            // Sum from i until j in O(n)
            var sum = n[0];
            for (int i = 1; i < n.length; i++) {
                sum = n[i] > sum + n[i] ? n[i] : sum + n[i];
                highestSum = highestSum > sum ? highestSum : sum;
                //System.out.println("highest sum from " + i + " and to " + i + " is " + sum);
            }
            return highestSum;
        }
    }
}