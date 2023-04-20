package co.com.niki.algorithms.exercises.Warm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Find number of pairs in a list
 */
public class Socks {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> integerList = Stream.of(bufferedReader.readLine().strip().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        System.out.println(findSocks(integerList));
    }

    private static int findSocks(List<Integer> socks) {
        Map<Integer, Integer> socksPair = new HashMap<>();

        socks.forEach(eachSock -> {
            if (socksPair.containsKey(eachSock)) {
                socksPair.replace(eachSock, socksPair.get(eachSock) + 1);
            } else {
                socksPair.put(eachSock, 1);
            }
        });

        return socksPair
                .values()
                .stream()
                .reduce(0, (numberOfSocks, values) -> numberOfSocks + values / 2);
    }
}
