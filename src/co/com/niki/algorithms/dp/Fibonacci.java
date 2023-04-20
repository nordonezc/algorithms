package co.com.niki.algorithms.dp;

import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Fibonacci {

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8081), 0);
        var httpContext = httpServer.createContext("/fibonacci");
        FibonacciSolver fibonacciSolver = new FibonacciSolver();

        httpContext.setHandler(exchange -> {
            if (exchange.getRequestMethod().equals("POST")) {
                var inputStreamBody = exchange.getRequestBody();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamBody));
                var fibonacciRequest = Integer.valueOf(bufferedReader.readLine());
                int solution = fibonacciSolver.solveBottomUpTabulation(fibonacciRequest);
                printResponse(exchange, String.valueOf(solution), 200);
            } else {
                printResponse(exchange, "Not operation allowed", 400);
            }
        });

        httpContext.setAuthenticator(new BasicAuthenticator("fibonacci") {
            @Override
            public boolean checkCredentials(String username, String password) {
                return username.equals("admin") && password.equals("password");
            }
        });

        httpServer.start();
    }

    private static void printResponse(HttpExchange exchange, String response, int code) throws IOException {
        exchange.sendResponseHeaders(code, response.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }

    private static class FibonacciSolver {

        private final Map<Integer, Integer> fibonacciSolutions = new TreeMap<>();

        private FibonacciSolver() {
            fibonacciSolutions.put(0, 1);
            fibonacciSolutions.put(1, 1);
        }

        private int solveTopDownMemoization(final Integer n) {
            if (!fibonacciSolutions.containsKey(n)) {
                fibonacciSolutions.put(n,
                        solveTopDownMemoization(n - 2) + solveTopDownMemoization(n - 1));
            }
            return fibonacciSolutions.get(n);
        }

        private int solveBottomUpTabulation(final Integer n) {
            if (!fibonacciSolutions.containsKey(n)) {
                var actualHighest = Collections.max(fibonacciSolutions.keySet());
                for (int i = actualHighest; i < n; i++) {
                    fibonacciSolutions.put(i + 1, fibonacciSolutions.get(i) + fibonacciSolutions.get(i - 1));
                }
            }
            return fibonacciSolutions.get(n);
        }
    }
}
