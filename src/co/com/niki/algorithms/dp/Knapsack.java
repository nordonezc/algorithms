package co.com.niki.algorithms.dp;

import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.StringJoiner;

public class Knapsack {

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8082), 0);
        var httpContext = httpServer.createContext("/knapsack");
        KnapsackSolver knapsackSolver = new KnapsackSolver(new int[]{0,1,3,4,5}, new int[]{0,1,4,5,7},7);
        KnapsackRecursiveSolver recursiveSolver = new KnapsackRecursiveSolver(new int[]{0,1,3,4,5}, new int[]{0,1,4,5,7});

        httpContext.setHandler(exchange -> {
            if ("POST".equals(exchange.getRequestMethod())) {
                var response = knapsackSolver.solve();
                var responseRecursive = recursiveSolver.solve(7);
                printResponse(exchange, response.concat("\n recursive: " + responseRecursive), 200);
            }
        });

        httpContext.setAuthenticator(new BasicAuthenticator("knapsack") {
            @Override
            public boolean checkCredentials(String username, String password) {
                return username.equals("admin") && password.equals("password");
            }
        });

        httpServer.start();
    }

    private static void printResponse(HttpExchange exchange, String response, int httpCode) throws IOException {
        exchange.sendResponseHeaders(httpCode, response.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }

    private static class KnapsackSolver {

        private final int[] weights;
        private final int[] values;
        private final int capacity;
        private int[][] knapSack;

        public KnapsackSolver(int[] weights, int[] values, int capacity) {
            this.weights = weights;
            this.values = values;
            this.capacity = capacity + 1;
            knapSack = new int[weights.length][this.capacity];
        }

        public String solve() {
            for (int i = 1; i < weights.length; i++) {
                for (int w = 1; w < capacity; w++) {
                    int notTakingItem = knapSack[i - 1][w];
                    int takingItem = 0;

                    if (weights[i] <= w) {
                        takingItem = values[i] + knapSack[i - 1][w - weights[i]];
                    }

                    knapSack[i][w] = Math.max(takingItem, notTakingItem);
                }
            }

            return itemsIncluded()
                    .add("Profit: " + knapSack[weights.length - 1][capacity - 1])
                    .toString();
        }

        public StringJoiner itemsIncluded() {
            StringJoiner answer = new StringJoiner("\n");
            for (int i = weights.length - 1, w = capacity - 1; i > 0; i--) {
                if (knapSack[i][w] != 0 && knapSack[i][w] != knapSack[i - 1][w]) {
                    answer.add("Take item " + i);
                }
            }
            return answer;
        }

    }

    private static class KnapsackRecursiveSolver {

        private final int[] weights;
        private final int[] values;

        public KnapsackRecursiveSolver(int[] weights, int[] values) {
            this.weights = weights;
            this.values = values;
        }

        public String solve(int capacity){
            return String.valueOf(solve(capacity, weights.length-1));
        }

        private int solve(int capacity, int numberOfItems) {
            if(capacity == 0 || numberOfItems == 0){
                return 0;
            }
            if (capacity - weights[numberOfItems] < 0){
                return solve(capacity, numberOfItems-1);
            }
            int takingItem = values[numberOfItems] + solve(capacity - weights[numberOfItems], numberOfItems);
            int notTakingItem = solve(capacity,numberOfItems-1);
            return Math.max(notTakingItem, takingItem);
        }


    }

}
