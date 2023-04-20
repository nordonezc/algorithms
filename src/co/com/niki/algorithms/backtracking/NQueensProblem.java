package co.com.niki.algorithms.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NQueensProblem {

    private static int[][] chessTable;
    private static int numOfQueens;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        do {
            numOfQueens = Integer.parseInt(bufferedReader.readLine());
            chessTable = new int[numOfQueens][numOfQueens];
            setToZeroChess();
            solve();
        } while (numOfQueens != 0);

    }

    private static void solve() {
        if (setQueens(0)) {
            printQueens();
        } else {
            System.out.println(":c");
        }
    }

    private static boolean setQueens(int colIndex) {

        if (colIndex == numOfQueens) {
            return true;
        }

        for (int row = 0; row < numOfQueens; row++) {
            if (isValidPosition(row, colIndex)) {
                chessTable[row][colIndex] = 1;

                if (setQueens(colIndex + 1)) {
                    return true;
                }

                chessTable[row][colIndex] = 0;
            }
        }

        return false;

    }

    private static boolean isValidPosition(int rowIndex, int colIndex) {
        return checkValidRow(rowIndex, colIndex)
                && checkValidDiagonal(rowIndex, colIndex);
    }

    private static boolean checkValidRow(int rowIndex, int colIndex) {
        for (int i = 0; i < colIndex; i++) {
            if (chessTable[rowIndex][i] == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkValidDiagonal(int rowIndex, int colIndex) {
        return isValidDiagonalUp(rowIndex, colIndex)
                && isValidDiagonalDown(rowIndex, colIndex);
    }

    private static boolean isValidDiagonalUp(int rowIndex, int colIndex) {
        for (int i = rowIndex, j = colIndex; i >= 0 && j >= 0; i--, j--) {
            if (chessTable[i][j] == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidDiagonalDown(int rowIndex, int colIndex) {
        for (int i = rowIndex, j = colIndex; i < numOfQueens && j >= 0; i++, j--) {
            if (chessTable[i][j] == 1) {
                return false;
            }
        }
        return true;
    }

    private static void printQueens() {
        System.out.println("");
        for (int i = 0; i < numOfQueens; i++) {
            for (int col : chessTable[i]) {
                System.out.print(col + " | ");
            }
            System.out.println("");
        }
    }

    private static void setToZeroChess() {
        for (int i = 0; i < numOfQueens; i++) {
            for (int col : chessTable[i]) {
                chessTable[i][col] = 0;
            }
        }
    }


}
