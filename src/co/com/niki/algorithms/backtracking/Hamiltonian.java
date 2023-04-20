package co.com.niki.algorithms.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Input
 * 0 1 0 0 0 1
 * 1 0 1 0 0 0
 * 0 1 0 0 1 0
 * 0 0 0 0 1 1
 * 0 0 1 1 0 1
 * 1 0 0 1 1 0
 * <p>
 * solution
 * 0 1 2 4 3 5 0
 */
public class Hamiltonian {

    private static final int numberOfVertexes = 6;

    public static void main(String[] args) {
        Integer[][] adjacencyMatrix = fillAdjacencyMatrix();
        Integer[] hamiltonianPath = solveHamiltonianCycle(adjacencyMatrix);
        showResult(Optional.ofNullable(hamiltonianPath).orElse(new Integer[numberOfVertexes]));
    }

    private static Integer[] solveHamiltonianCycle(Integer[][] adjacencyMatrix) {
        Integer[] hamiltonianPath = new Integer[numberOfVertexes];

        hamiltonianPath[0] = 0;

        if (findSolution(1, adjacencyMatrix, hamiltonianPath)) {
            return hamiltonianPath;
        }

        return null;
    }

    private static boolean findSolution(int position, Integer[][] adjacencyMatrix, Integer[] hamiltonianPath) {

        if (position == numberOfVertexes) {
            return adjacencyMatrix[hamiltonianPath[0]][hamiltonianPath[position - 1]] == 1;
        }

        for (int vertex = 1; vertex < numberOfVertexes; vertex++) {
            if (isValid(vertex, position, adjacencyMatrix, hamiltonianPath)) {
                hamiltonianPath[position] = vertex;

                if (findSolution(position + 1, adjacencyMatrix, hamiltonianPath)) {
                    return true;
                }

                hamiltonianPath[position] = null;
            }
        }

        return false;
    }

    private static boolean isValid(
            int vertex,
            int position,
            Integer[][] adjacencyMatrix,
            Integer[] hamiltonianPath) {
        return adjacencyMatrix[hamiltonianPath[position - 1]][vertex] == 1 &&
                IntStream.range(0, position - 1)
                        .allMatch(value -> hamiltonianPath[value] != vertex);
    }

    private static void showResult(Integer[] hamiltonianPath) {
        Arrays.stream(hamiltonianPath).forEach(System.out::print);
        System.out.println();
    }

    private static Integer[][] fillAdjacencyMatrix() {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Integer[][] adjacencyMatrix = new Integer[numberOfVertexes][numberOfVertexes];

        IntStream.range(0, numberOfVertexes).forEach(range -> {
            try {
                adjacencyMatrix[range] = Stream.of(bufferedReader.readLine().split(" "))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return adjacencyMatrix;
    }

}
