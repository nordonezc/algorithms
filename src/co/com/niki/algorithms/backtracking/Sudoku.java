package co.com.niki.algorithms.backtracking;

import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

/*
e.g.
3 0 6 5 0 8 4 0 0
5 2 0 0 0 0 0 0 0
0 8 7 0 0 0 0 3 1
0 0 3 0 1 0 0 8 0
9 0 0 8 6 3 0 0 5
0 5 0 0 9 0 6 0 0
1 3 0 0 0 0 2 5 0
0 0 0 0 0 0 0 7 4
0 0 5 2 0 6 3 0 0
 */
public class Sudoku {


    public static void main(String[] args) throws IOException {
        AtomicReference<SudokuSolver> sudokuSolver = new AtomicReference<>(new SudokuSolver());

        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 0);
        HttpContext contextSudoku = httpServer.createContext("/sudoku");

        contextSudoku.setHandler(exchange -> {
            if ("GET".equals(exchange.getRequestMethod())) {
                String response = sudokuSolver.get().showTable();
                System.out.println("Executing get...");
                printBody(exchange, response);
                System.out.println("Finalizing get...");
            } else if ("POST".equals(exchange.getRequestMethod())) {
                var streamBody = exchange.getRequestBody();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(streamBody));
                Integer[][] sudokuTable = new Integer[9][9];
                IntStream.range(0, 9).forEach(each -> {
                    try {
                        sudokuTable[each] = Arrays.stream(
                                        bufferedReader.readLine().split(" "))
                                .map(Integer::parseInt)
                                .toArray(Integer[]::new);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                sudokuSolver.set(new SudokuSolver(sudokuTable));
                boolean hasSolution = sudokuSolver.get().solve(0,0);
                printBody(exchange, String.valueOf(hasSolution));
            } else {
                System.err.println("Executing get...");
                exchange.sendResponseHeaders(405, -1);
            }
            exchange.close();
        });

        contextSudoku.setAuthenticator(new BasicAuthenticator("sudoku") {
            @Override
            public boolean checkCredentials(String username, String password) {
                return username.equals("admin") && password.equals("password");
            }
        });

        httpServer.setExecutor(null);
        httpServer.start();
        System.out.println("Server has start...");
    }

    private static void printBody(HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    private static class SudokuSolver {
        private Integer[][] sudokuTable;

        public SudokuSolver() {
            this.sudokuTable = new Integer[][]{{0, 1}, {0, 1}};
        }

        public SudokuSolver(Integer[][] sudokuTable) {
            this.sudokuTable = sudokuTable;
        }

        public void setSize(final int boardSize) {
            this.sudokuTable = new Integer[boardSize][boardSize];
        }

        public int getSudokuSize() {
            return sudokuTable.length;
        }

        public boolean solve(int x, int y) {
            if (x == 8 && y == 9) {
                return true;
            }
            if (y == 9) {
                return solve(x + 1, 0);
            }
            if(sudokuTable[x][y] != 0){
                return solve(x, y+1);
            }

            for (int k = 1; k <= 9; k++) {
                if (isValid(k, x, y)) {
                    sudokuTable[x][y] = k;
                    boolean solution = solve(x, y+1);
                    if (solution){
                        return true;
                    } else{
                        sudokuTable[x][y] = 0;
                    }
                }
            }

            return false;
        }

        public boolean isValid(int number, int x, int y) {
            for (int i = 0; i < 9; i++) {
                if ((sudokuTable[x][i] != 0 && sudokuTable[x][i].equals(number)) ||
                        (sudokuTable[i][y] != 0 && sudokuTable[i][y].equals(number))) {
                    return false;
                }
            }

            int xBlock = x / 3;
            int yBlock = y / 3;
            for (int i = xBlock*3; i < (xBlock*3)+3; i++) {
                for (int j = yBlock*3; j < (yBlock*3)+3; j++) {
                    if ((sudokuTable[i][j] != 0 && sudokuTable[i][j].equals(number))) {
                        return false;
                    }
                }
            }

            return true;
        }


        public String showTable() {
            StringBuilder answer = new StringBuilder();
            for (Integer[] x : sudokuTable) {
                StringBuilder lineToPrint = new StringBuilder();
                for (Integer y : x) {
                    lineToPrint.append(y).append(" ");
                }
                answer.append(lineToPrint).append("\n");
            }

            return answer.toString();
        }
    }
}
