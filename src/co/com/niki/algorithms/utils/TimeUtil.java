package co.com.niki.algorithms.utils;

import java.util.function.ToIntFunction;

public class TimeUtil {

    private TimeUtil() {
    }

    public static void printTime(ToIntFunction<Integer[]> subArrayAlgorithm, Integer[] n) {
        var init = System.currentTimeMillis();
        var highestSum = subArrayAlgorithm.applyAsInt(n);
        System.out.println("Result is = " + highestSum);
        var time = System.currentTimeMillis() - init;
        System.out.println("Time: " + time);
    }
}