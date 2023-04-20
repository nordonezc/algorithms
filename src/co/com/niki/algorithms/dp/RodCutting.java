package co.com.niki.algorithms.dp;

import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.StringJoiner;

public class RodCutting {

    private static int SERVER_PORT = 8082;
    private static int HTTP_OK = 200;
    private static String SERVER_CONTEXT = "/rodcutting";
    private static String HTTP_POST = "POST";

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(SERVER_PORT), 0);
        var httpContext = httpServer.createContext(SERVER_CONTEXT);
        httpContext.setHandler(postMethod);
        httpContext.setAuthenticator(authenticator());
        httpServer.start();
    }

    private static BasicAuthenticator authenticator() {
        return new BasicAuthenticator(SERVER_CONTEXT) {

            @Override
            public boolean checkCredentials(String username, String password) {
                return username.equals("username") && password.equals("password");
            }
        };
    }

    private static final HttpHandler postMethod = exchange -> {
        if (HTTP_POST.equals(exchange.getRequestMethod())) {
            var requestBody = exchange.getRequestBody();
            var reader = new BufferedReader(new InputStreamReader(requestBody));

            int[] prices = toIntArray(reader.readLine());
            int[] sizes = toIntArray(reader.readLine());
            var rodLength = Integer.parseInt(reader.readLine());

            String rodCuttingSolution = new RodCuttingSolver(prices, sizes)
                    .solve(rodLength);
            printSolution(exchange, rodCuttingSolution, HTTP_OK);
        }
    };

    private static int[] toIntArray(String s) {
        return Arrays.stream(s.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static void printSolution(HttpExchange exchange, String body, int httpCode) throws IOException {
        exchange.sendResponseHeaders(httpCode, body.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(body.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }

    private static class RodCuttingSolver {

        private final int[] sizes;
        private final int[] prices;
        private int[][] solutions;

        public RodCuttingSolver(int[] sizes, int[] prices) {
            this.sizes = sizes;
            this.prices = prices;
        }

        public String solve(int rodLength) {
            StringJoiner joiner = new StringJoiner("\n");
            solutions = new int[sizes.length + 1][rodLength + 1];

            for (int i = 1; i <= sizes.length; i++) {
                for (int j = 1; j <= rodLength; j++) {
                    int doNotCut = solutions[i - 1][j];
                    int cut = 0;

                    if (sizes[i-1] <= j) {
                        cut = prices[i-1] + Math.max(solutions[i-1][j - sizes[i-1]], solutions[i][j - sizes[i-1]]);
                    }
                    solutions[i][j] = Math.max(doNotCut, cut);
                }
            }

            return joiner
                    .add(getCuttingProcess(rodLength))
                    .add("optimal value is: " + solutions[sizes.length][rodLength])
                    .toString();
        }

        private String getCuttingProcess(int rodLength) {
            StringJoiner joiner = new StringJoiner("\n");

            for (int i = sizes.length, optimalSolution = solutions[sizes.length][rodLength], length = rodLength;
            i>0 && optimalSolution>0 && length>0; ){
                if (optimalSolution > solutions[i-1][length]) {
                    joiner.add("Take " + sizes[i-1]);
                    optimalSolution -= prices[i-1];
                    length -= sizes[i-1];
                } else{
                    i--;
                }
            }

            return joiner.toString();
        }
    }

}
