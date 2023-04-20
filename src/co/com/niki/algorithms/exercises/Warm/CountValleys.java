package co.com.niki.algorithms.exercises.Warm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given path with Uphills and Downhills, retrieve the number of valleys walked up.
 * valley is consecutive steps below sea level
 */
public class CountValleys {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int steps = 0;
        do{
        steps = Integer.parseInt(bufferedReader.readLine());
        String path = bufferedReader.readLine();
        System.out.println("number of valleys is: " + countingValleys(steps, path));
        } while (steps != 0);

        bufferedReader.close();
    }

    private static int countingValleys(int steps, String path) {
        int numberOfValleys = 0;
        int levelOfTheSea = 0;
        for(int i=0; i<path.length(); i++){
            if(path.charAt(i) == 'U'){
                levelOfTheSea++;
                if(levelOfTheSea == 0){
                    numberOfValleys++;
                }
            } else{
                levelOfTheSea--;
            }
        }

        return numberOfValleys;

    }
}
