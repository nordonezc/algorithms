package co.com.niki.algorithms.dp;

import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import static java.lang.System.nanoTime;
import static java.util.stream.Collectors.toList;

/**
 * Given an array of integers
 * Finds the sum of all the contiguous combinations of numbers in the array
 * with the following formula: Sum of all the max-min of each combination
 */
public class Unbalance {

    private static final String UNBALANCE = "unbalance";
    private static final String POST_METHOD = "POST";
    private static final int HTTP_PORT = 8085;
    private static final int HTTP_OK = 200;

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(HTTP_PORT), 0);
        HttpContext httpContext = httpServer.createContext("/" + UNBALANCE);
        httpContext.setAuthenticator(setAuth());
        httpContext.setHandler(solveUnbalance);
        httpServer.start();
    }

    private static final HttpHandler solveUnbalance = exchange -> {
        if (POST_METHOD.equals(exchange.getRequestMethod())) {
            InputStream inputStream = exchange.getRequestBody();
            var reader = new BufferedReader(new InputStreamReader(inputStream));
            var input = stringToIntArray(reader.readLine());
            var solver = new UnbalanceSolver(input);
            StringJoiner joiner = new StringJoiner("\n");
            joiner.add(solver.getUnbalanceAbs())
                    .add(solver.getUnbalanceLocalMaxAndMin());
            printResponse(exchange,
                    joiner.toString(),
                    HTTP_OK);
        }
    };

    private static BasicAuthenticator setAuth() {
        return new BasicAuthenticator(UNBALANCE) {
            @Override
            public boolean checkCredentials(String username, String password) {
                return username.equals("username") && password.equals("password");
            }
        };
    }

    private static List<Integer> stringToIntArray(String input) {
        return Arrays.stream(input.trim().split(" "))
                .map(Integer::parseInt)
                .collect(toList());
    }

    private static void printResponse(HttpExchange exchange, String body, int httpCode) throws IOException {
        exchange.sendResponseHeaders(httpCode, body.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(body.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }


    private static class UnbalanceSolver {

        private final List<Integer> list;

        public UnbalanceSolver(List<Integer> list) {
            this.list = list;
        }

        public String getUnbalanceAbs() {
            StringJoiner joiner = new StringJoiner("\n");
            long unbalance = 0;

            var startTime = nanoTime();
            for (int i = 0; i < list.size(); i++) {
                int localUnbalance = 0;
                int localMax = list.get(i);
                int localMin = list.get(i);
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(j) > localMax) {
                        localMax = list.get(j);
                        localUnbalance = localMax - localMin;
                    } else if (list.get(j) < localMin) {
                        localMin = list.get(j);
                        localUnbalance = localMax - localMin;
                    }
                    unbalance += localUnbalance;
                }
            }
            var endTime = nanoTime();
            var duration = endTime - startTime;

            return joiner.add("with abs: " + unbalance)
                    .add("duration of " + duration)
                    .toString();
        }

        public String getUnbalanceLocalMaxAndMin() {
            StringJoiner joiner = new StringJoiner("\n");
            long unbalance = 0;

            var startTime = nanoTime();
            for (int i = 0; i < list.size() - 1; i++) {

                long localMin = Math.min(list.get(i), list.get(i + 1));
                long localMax = Math.max(list.get(i), list.get(i + 1));
                unbalance += (localMax - localMin);

                for (int j = i + 2; j < list.size(); j++) {
                    if (list.get(j) > localMax) {
                        localMax = list.get(j);
                    } else if (list.get(j) < localMin) {
                        localMin = list.get(j);
                    }
                    unbalance += (localMax - localMin);
                }
            }
            var endTime = nanoTime();
            var duration = endTime - startTime;

            return joiner.add("with local max and min: " + unbalance)
                    .add("duration of " + duration)
                    .toString();
        }
    }
}
