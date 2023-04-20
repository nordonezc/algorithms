package co.com.niki.algorithms.utils;

import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.StringJoiner;

public class AlgorithmsConstants {

    public static final int SERVER_PORT = 8082;
    public static final String HTTP_POST = "POST";
    public static final String DEFAULT_USERNAME = "username";
    public static final String DEFAULT_PASSWORD = "password";

    private AlgorithmsConstants() {
    }

    public static void printSolution(HttpExchange exchange, String body, int httpCode) throws IOException {
        exchange.sendResponseHeaders(httpCode, body.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(body.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }

    public static int[] toIntArray(String s) {
        return Arrays.stream(s.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static void startServer(String serverContext, HttpHandler postMethod, Authenticator authenticator) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(SERVER_PORT), 0);
        var httpContext = httpServer.createContext(serverContext);
        httpContext.setHandler(postMethod);
        httpContext.setAuthenticator(authenticator);
        httpServer.start();
    }

    public static String createStringFromArray(int[] givenArray) {
        StringJoiner joiner = new StringJoiner(",");
        for (int input : givenArray) {
            joiner.add(String.valueOf(input));
        }

        return joiner.toString();
    }


}
