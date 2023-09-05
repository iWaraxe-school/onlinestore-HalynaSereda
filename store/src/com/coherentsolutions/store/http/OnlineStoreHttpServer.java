package com.coherentsolutions.store.http;

import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import com.sun.net.httpserver.HttpsServer;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.security.KeyStore;
public class OnlineStoreHttpServer {
    public void startServer(int port) throws IOException, Exception {
        String keystorePath = "C:\\Users\\HalynaSereda/keystore.jks";
        String keystorePassword = "2013000";


        // Create an HTTPS context with your keystore
        HttpsServer httpsServer = HttpsServer.create(new InetSocketAddress(port), 0);
        SSLContext sslContext = createSSLContext(keystorePath, keystorePassword);
        httpsServer.setHttpsConfigurator(new HttpsConfigurator(sslContext) {
            public void configure(HttpsParameters params) {
                try {
                    // Initialize the SSL context
                    SSLContext c = SSLContext.getDefault();
                    SSLEngine engine = c.createSSLEngine();
                    params.setNeedClientAuth(false);
                    params.setCipherSuites(engine.getEnabledCipherSuites());
                    params.setProtocols(engine.getEnabledProtocols());

                    // Set the SSL parameters
                    SSLParameters sslParameters = c.getDefaultSSLParameters();
                    params.setSSLParameters(sslParameters);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Register handlers for different paths
        Cart cart = new Cart();
        httpsServer.createContext("/add-to-cart", new AddToCartHandler(cart));
        httpsServer.start();
    }

    private SSLContext createSSLContext(String keystorePath, String keystorePassword) throws Exception {
        // Load keystore
        KeyStore keyStore = KeyStore.getInstance("JKS");
        FileInputStream keyStoreFile = new FileInputStream(keystorePath);
        keyStore.load(keyStoreFile, keystorePassword.toCharArray());

        // Initialize the key manager factory
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        keyManagerFactory.init(keyStore, keystorePassword.toCharArray());

        // Initialize the trust manager factory
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
        trustManagerFactory.init(keyStore);

        // Initialize the SSL context
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

        return sslContext;
    }
}