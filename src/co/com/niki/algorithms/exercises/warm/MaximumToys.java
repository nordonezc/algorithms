package co.com.niki.algorithms.exercises.warm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a list of prices, return the maximum number of items that can be purchased given they money amount
 *
 * @author nicolas ordonez
 */
public class MaximumToys {

    public static void main(String[] args) {
        var input = List.of(1, 2, 3, 4);
        var money = 7;
        Integer result = Resolver.maximumToys(input, money);
        System.out.println(result);
    }

    static class Resolver {

        private Resolver() {
        }

        public static Integer maximumToys(List<Integer> input, int money) {
            var sortedList = new ArrayList<>(input);
            Collections.sort(sortedList);
            int numberOfToys = 0;
            int acc = 0;

            for (Integer integer : sortedList) {
                if (acc + integer <= money) {
                    numberOfToys++;
                    acc += integer;
                } else {
                    break;
                }
            }

            return numberOfToys;
        }
    }
}