package co.com.niki.algorithms.utils.rest;

import com.sun.net.httpserver.BasicAuthenticator;

import static co.com.niki.algorithms.utils.AlgorithmsConstants.DEFAULT_PASSWORD;
import static co.com.niki.algorithms.utils.AlgorithmsConstants.DEFAULT_USERNAME;

public class RestAuthenticator {

    public final String serverContext;

    public RestAuthenticator(String serverContext) {
        this.serverContext = serverContext;
    }


    public BasicAuthenticator authenticator() {
        return authenticator(DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

    /**
     * @param user
     * @param pass
     * @return
     */
    public BasicAuthenticator authenticator(String user, String pass) {
        return new BasicAuthenticator(serverContext) {

            @Override
            public boolean checkCredentials(String username, String password) {
                return user.equals(username) && pass.equals(password);
            }
        };
    }

}
