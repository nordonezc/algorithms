package co.com.niki.algorithms.exercises.Warm;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Jump across an array without selecting 1s and you can move maximum of 2 positions
 * return the minimum of positions
 */
public class Clouds {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> c = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = jumpingOnClouds(c);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

    public static int jumpingOnClouds(List<Integer> c) {
        int numberOfJumps = 0;
        for (int i = 0; i < c.size() - 1; i++) {
            if (i + 2 < c.size() && c.get(i + 2) == 0) {
                numberOfJumps++;
                i++;
            } else {
                numberOfJumps++;
            }
        }

        return numberOfJumps;
    }
}
