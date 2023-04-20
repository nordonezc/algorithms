package co.com.niki.algorithms.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KnightTour {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        KnightSolution knightSolution = new KnightSolution(Integer.parseInt(reader.readLine()));
        knightSolution.solve(0, 0);
    }

    private static class KnightSolution {

        private Integer[][] chess;
        private static final int[][] moves = {
                {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
                {1, 2}, {1, -2}, {-1, 2}, {-1, -2},
        };

        public KnightSolution(int boardSize) {
            chess = new Integer[boardSize][boardSize];
        }

        public void solve(int initX, int initY) {
            initChess();
            chess[initX][initY] = 1;
            if (solve(1, initX, initY)) {
                System.out.println("Resolved");
                printChess();
            } else {
                System.out.println("No solution");
            }
        }

        private boolean solve(int stepCounter, int x, int y) {
            if (stepCounter == Math.pow(chess.length, 2)) {
                return true;
            }

            for (int i = 0; i < moves.length; i++) {
                int newX = x + moves[i][0];
                int newY = y + moves[i][1];
                if (isValidMovement(newX, newY)) {
                    System.out.println(stepCounter);
                    chess[newX][newY] = stepCounter + 1;
                    if (solve(stepCounter + 1, newX, newY)) {
                        return true;
                    } else {
                        chess[newX][newY] = 0;
                    }
                }
            }

            return false;
        }

        private boolean isValidMovement(int x, int y) {
            return x < chess.length && y < chess.length &&
                    x >= 0 && y >= 0 &&
                    chess[x][y] == 0;
        }

        private void printChess() {
            for (Integer[] eachRow : chess) {
                String lineToPrint = "";
                for (Integer position : eachRow) {
                    lineToPrint += position + " | ";
                }
                System.out.println(lineToPrint);
                System.out.println("-".repeat(lineToPrint.length()));
            }
        }

        private void initChess() {
            for (int i = 0; i < chess.length; i++) {
                for (int j = 0; j < chess.length; j++) {
                    chess[i][j] = 0;
                }
            }
        }

    }
}
