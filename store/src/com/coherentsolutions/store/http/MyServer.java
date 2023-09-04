package com.coherentsolutions.store.http;

import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import com.sun.net.httpserver.HttpsServer;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.security.KeyStore;

public class MyServer {
    public static void main(String[] args) throws Exception {
        // Set up the keystore path and password
        String keystorePath = System.getProperty("user.home") + "/keystore.jks";
        String keystorePassword = "yourKeystorePassword"; // Replace with your keystore password

        // Create an SSL context using the keystore
        SSLContext sslContext = createSSLContext(keystorePath, keystorePassword);

        // Create an HTTP server
        HttpsServer httpsServer = HttpsServer.create();

        // Configure SSL/TLS for the server
        httpsServer.setHttpsConfigurator(new HttpsConfigurator(sslContext) {
            @Override
            public void configure(HttpsParameters params) {
                SSLContext sslContext = getSSLContext();
                SSLParameters sslParameters = sslContext.getDefaultSSLParameters();
                params.setSSLParameters(sslParameters);
            }
        });

        Cart cart = new Cart();

        // Provide the Cart object, username, and password when creating AddToCartHandler
        AddToCartHandler addToCartHandler = new AddToCartHandler(cart, "UserName", "2013000");

        // Register the handler with the HTTPS server
        httpsServer.createContext("/add-to-cart", addToCartHandler);

        // Set up basic authentication
        httpsServer.createContext("/secure", (exchange -> {
            String username = exchange.getPrincipal().getUsername();
            exchange.sendResponseHeaders(200, username.getBytes().length);
            exchange.getResponseBody().write(username.getBytes());
            exchange.close();
        })).setAuthenticator(new BasicAuthenticator("MyRealm") {
            @Override
            public boolean checkCredentials(String username, String password) {
                // Implement your authentication logic here
                // For example, you can hardcode the credentials or check against a database
                return "yourUsername".equals(username) && "yourPassword".equals(password);
            }
        });

        // Start the server
        httpsServer.start();

        System.out.println("HTTPS Server started on port 443 with basic authentication.");
    }

    private static SSLContext createSSLContext(String keystorePath, String keystorePassword) throws Exception {
        // Load the keystore
        KeyStore keyStore = KeyStore.getInstance("JKS");
        FileInputStream keystoreStream = new FileInputStream(keystorePath);
        keyStore.load(keystoreStream, keystorePassword.toCharArray());

        // Create and initialize the SSLContext
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        keyManagerFactory.init(keyStore, keystorePassword.toCharArray());

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
        trustManagerFactory.init(keyStore);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

        return sslContext;
    }
}