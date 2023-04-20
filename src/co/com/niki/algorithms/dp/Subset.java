package co.com.niki.algorithms.dp;

import co.com.niki.algorithms.utils.AlgorithmsConstants;
import co.com.niki.algorithms.utils.rest.RestAuthenticator;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static co.com.niki.algorithms.utils.AlgorithmsConstants.*;
import static java.net.HttpURLConnection.HTTP_OK;

public class Subset {

    private static final String SERVER_CONTEXT = "/subsetsum";
    private static final RestAuthenticator authenticator = new RestAuthenticator(SERVER_CONTEXT);

    public static void main(String[] args) throws IOException {
        AlgorithmsConstants.startServer(SERVER_CONTEXT, postMethod, authenticator.authenticator());
    }

    private static final HttpHandler postMethod = exchange -> {
        if (HTTP_POST.equals(exchange.getRequestMethod())) {
            var requestBody = exchange.getRequestBody();
            var reader = new BufferedReader(new InputStreamReader(requestBody));

            int[] givenSet = toIntArray(reader.readLine());
            var sum = Integer.parseInt(reader.readLine());

            String solution = new SubSetSumSolver(givenSet)
                    .solve(sum);
            printSolution(exchange, solution, HTTP_OK);
        }
    };

    private static class SubSetSumSolver {

        private final int[] givenSet;

        public SubSetSumSolver(int[] givenSet) {
            this.givenSet = givenSet;
        }

        public String solve(int sum) {
            boolean[][] solutions = new boolean[givenSet.length + 1][sum + 1];

            for (int i = 0; i < sum + 1; i++) {
                solutions[0][i] = true;
            }

            for (int row = 1; row < givenSet.length + 1; row++) {
                for (int col = 0; col < sum + 1; col++) {
                    if (col < givenSet[row - 1]){
                        solutions[row][col] = false;
                    }
                }
            }

            return createStringFromArray(answer);
        }

        private
    }

}
