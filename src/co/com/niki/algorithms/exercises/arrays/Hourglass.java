package co.com.niki.algorithms.exercises.arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Find the highest sum in a matrix nxn with a given pattern
 * <p>
 * e.g.
 * abc
 * -d-
 * efg
 * <p>
 * sum numbers in the positions with letters in a nxn matrix
 */
public class Hourglass {


    private static List<List<Integer>> inputMatrix = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        char repeat = 'y';
        do{
        IntStream.range(0, 6).forEach(i -> {
            try {
                inputMatrix.add(Stream
                        .of(bufferedReader.readLine().split(" "))
                        .map(Integer::parseInt)
                        .collect(toList()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("Largest sum is: " + hourglassSum(inputMatrix));
        System.out.print("Continue (y/n)? ");
        repeat = bufferedReader.readLine().charAt(0);
        }while (repeat != 'n');
    }

    public static int hourglassSum(List<List<Integer>> arr) {
        int highestHourGlass = sumHourglass(arr, 0, 0);
        for(int row=0, rowLimit=row+2; rowLimit<arr.size(); row++, rowLimit++){
            for(int col=0, colLimit=2; colLimit<arr.size(); col++, colLimit++){
                int actualHourGlass = sumHourglass(arr, row, col);
                if(actualHourGlass > highestHourGlass){
                    highestHourGlass = actualHourGlass;
                }
            }
        }

        return highestHourGlass;

    }

    private static int sumHourglass(List<List<Integer>> arr, int row, int col){
        int answer = 0;

        for(int i = 0; i<3; i++){
            answer += arr.get(row).get(col+i);
            answer += arr.get(row+2).get(col+i);
        }

        answer += arr.get(row+1).get(col+1);
        return answer;
    }


}
