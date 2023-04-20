package co.com.niki.algorithms.exercises.Warm;

import java.io.*;

/**
 * Repeat the given string n times and find how many 'a's are in the repeated string
 */
public class StringOcurrences {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String s = bufferedReader.readLine();

        long n = Long.parseLong(bufferedReader.readLine().trim());

        long result = repeatedString(s, n);

        System.out.println(result);

        bufferedReader.close();
    }

    public static long repeatedString(String s, long n) {

        long charTimes = count(s, s.length(), 'a');
        long repetitions = (n/s.length());
        long charInRepetitions = (repetitions*charTimes);

        long stringLeftLength = n - (repetitions*s.length());
        long charInStringLeft = count(s, (int) stringLeftLength, 'a');

        return charInRepetitions + charInStringLeft;

    }


    private static long count(String s, int stringLength, char characterToCount){
        long appearances = 0;
        for(int i=0; i<stringLength; i++){
            if(s.charAt(i) == characterToCount){
                appearances++;
            }
        }
        return appearances;
    }


    public static long manualProcess(String s, long n) {

        char[] givenString = s.toCharArray();
        int answer = 0;

        for (int i = 0, j = 0; i < n; j++, i++) {
            if (givenString[j] == 'a') {
                answer++;
            }

            if (j + 1 == s.length()) {
                j = -1;
            }
        }

        return answer;
    }
}
