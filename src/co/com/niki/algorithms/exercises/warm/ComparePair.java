package co.com.niki.algorithms.exercises.warm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implement comparable interface to determine a default order for an incoming list
 * input
 * 2
 * first 100
 * third 50
 * second 75
 * fourth 50
 */
public class ComparePair {

    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numberOfInputs = Integer.valueOf(br.readLine());
        List<Pair> pairs = new ArrayList<>();

        for (int i = 0; i < numberOfInputs; i++) {
            String[] pair = br.readLine().split(" ");
            pairs.add(new Pair(pair[0], Integer.valueOf(pair[1])));
        }

        Collections.sort(pairs);

        for (Pair pair : pairs) {
            bw.write(pair.toString());
            bw.newLine();
        }
        br.close();
        bw.close();
    }

    private static class Pair implements Comparable<Pair> {

        private String name;
        private Integer score;

        public Pair(String name, Integer score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return this.name;
        }

        public Integer getScore() {
            return this.score;
        }

        @Override
        public String toString() {
            return this.getName() + " " + this.getScore();
        }

        @Override
        public int compareTo(Pair p) {
            return this.score < p.getScore() ? 1 :
                    this.score > p.getScore() ? -1 :
                            this.name.compareTo(p.getName());
        }
    }
}