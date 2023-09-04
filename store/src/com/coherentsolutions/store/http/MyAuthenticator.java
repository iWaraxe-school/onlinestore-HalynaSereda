package com.coherentsolutions.store.http;

import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;

public class MyAuthenticator extends BasicAuthenticator {
    public MyAuthenticator(String realm) {
        super(realm);
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        // Hardcoded credentials for authentication
        return username.equals("yourUsername") && password.equals("yourPassword");
    }

    @Override
    public Result authenticate(HttpExchange exchange) {
        // Extract the authorization header from the request
        String authHeader = exchange.getRequestHeaders().getFirst("Authorization");

        // Check if the header is present and starts with "Basic "
        if (authHeader != null && authHeader.startsWith("Basic ")) {
            // Decode the Base64-encoded credentials
            String base64Credentials = authHeader.substring(6);
            String credentials = new String(java.util.Base64.getDecoder().decode(base64Credentials));

            // Split the credentials into username and password
            String[] parts = credentials.split(":", 2);

            if (parts.length == 2 && checkCredentials(parts[0], parts[1])) {
                // Authentication successful
                HttpPrincipal principal = new HttpPrincipal(parts[0], "default");
                return new Authenticator.Success(principal);
            }
        }

        // Authentication failed
        return new Authenticator.Failure(401); // Unauthorized
    }
}