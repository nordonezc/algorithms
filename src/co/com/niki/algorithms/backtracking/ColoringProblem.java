package co.com.niki.algorithms.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * e.g.
 * 0 1 1 1 0 0
 * 1 0 1 0 1 1
 * 1 1 0 1 0 1
 * 1 0 1 0 0 1
 * 0 1 0 0 0 1
 * 0 1 1 1 1 0
 *
 * solution
 * 0
 * 1
 * 2
 * 1
 * 0
 * 3
 */
public class ColoringProblem {

    private static int numberOfVertex;
    private static int numberOfColors;
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        System.out.print("Number of Vertex: ");
        numberOfVertex = Integer.parseInt(bufferedReader.readLine());
        System.out.print("Number of Colors: ");
        numberOfColors = Integer.parseInt(bufferedReader.readLine());
        Integer[][] adjMatrix = fillAdjacencyMatrix();

        ColoringSolution coloringSolution = new ColoringSolution(adjMatrix, numberOfColors);
        coloringSolution.resolve();
    }

    private static Integer[][] fillAdjacencyMatrix() {
        Integer[][] adjMatrix = new Integer[numberOfVertex][numberOfVertex];
        IntStream.range(0, numberOfVertex)
                .forEach(number -> {
                    try {
                        adjMatrix[number] = Arrays.stream(bufferedReader.readLine().split(" "))
                                .map(Integer::parseInt)
                                .toArray(Integer[]::new);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        return adjMatrix;
    }

    public static class ColoringSolution {

        private final Integer[][] adjMatrix;
        private Integer[] colorPath;
        private final int maxNumberOfColors;

        public ColoringSolution(Integer[][] adjMatrix, int maxNumberOfColors) {
            this.adjMatrix = adjMatrix;
            colorPath = new Integer[adjMatrix.length];
            this.maxNumberOfColors = maxNumberOfColors;
        }

        public void resolve() {
            colorPath[0] = 0;
            Integer[] solution = solve(1);
            if (solution != null) {
                showSolution(solve(1));
            } else {
                System.out.println("No solution");
            }
        }

        private Integer[] solve(int position) {
            if (position == colorPath.length) {
                return colorPath;
            }

            for (int i = 0; i < colorPath.length; i++) {
                if (validColor(position, i)) {
                    colorPath[position] = i;
                    Integer[] solution = solve(position + 1);
                    if (solution != null) {
                        return solution;
                    }
                    colorPath[position] = null;
                }
            }

            return null;
        }

        private boolean validColor(int position, int colorIndex) {
            for (int i = 0; i < adjMatrix.length; i++) {
                if (adjMatrix[position][i] == 1) {
                    if (colorPath[i] != null && colorPath[i] == colorIndex) {
                        return false;
                    }
                }
            }
            return true;
        }

        private void showSolution(Integer[] colorPath) {
            Stream.of(colorPath).forEach(System.out::println);
        }
    }
}
