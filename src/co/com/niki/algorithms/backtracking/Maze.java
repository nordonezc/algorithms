package co.com.niki.algorithms.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Maze {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int mazeXSize = Integer.parseInt(reader.readLine());
        int mazeYSize = Integer.parseInt(reader.readLine());
        Integer[][] givenMaze = new Integer[mazeXSize][mazeYSize];
        IntStream.range(0, mazeXSize).forEach(eachValue -> {
            try {
                givenMaze[eachValue] = Stream.of(reader.readLine().split(" "))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        MazeSolver mazeSolver = new MazeSolver(givenMaze);
        mazeSolver.hasSolution(0, 0);
    }

    private static class MazeSolver {

        private final Integer[][] givenMaze;
        private final Integer[][] moves = {
                {1, 0}, {0, 1},
                {-1, 0}, {0, -1}
        };

        public MazeSolver(Integer[][] givenMaze) {
            this.givenMaze = givenMaze;
        }

        private void hasSolution(int x, int y) {
            givenMaze[x][y] = 1;
            if (resolvingMaze(x, y)) {
                System.err.println("Solved");
                printMaze(ConsoleColors.PURPLE_BRIGHT);
            } else {
                System.out.println("No solution");
            }
        }

        private boolean resolvingMaze(int x, int y) {
            for (Integer[] move : moves) {
                int newX = x + move[0];
                int newY = y + move[1];
                if (validMovement(newX, newY)) {
                    if (givenMaze[newX][newY] == -1) {
                        return true;
                    }

                    System.out.println(ConsoleColors.RED + x + ", " + y);
                    printMaze(ConsoleColors.GREEN_BRIGHT);
                    givenMaze[newX][newY] = 1;
                    if (resolvingMaze(newX, newY)) {
                        return true;
                    } else {
                        givenMaze[newX][newY] = 0;
                    }
                }
            }

            return false;
        }

        private boolean validMovement(int newX, int newY) {
            return newX < givenMaze.length && newY < givenMaze[0].length
                    && newX >= 0 && newY >= 0
                    && (givenMaze[newX][newY] == 0 || givenMaze[newX][newY] == -1);
        }

        private void printMaze(String color) {
            for (Integer[] row : givenMaze) {
                StringBuilder lineToPrint = new StringBuilder();
                for (Integer column : row) {
                    lineToPrint.append(column).append("\t");
                }
                System.out.println(color + lineToPrint);
            }
        }
    }
}
