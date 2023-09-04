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
    public void startServer(int port)  throws IOException, Exception {
        Cart cart = new Cart();
        // Create an HTTPS context with your keystore
        HttpsServer httpsServer = HttpsServer.create(new InetSocketAddress(8080), 0);
        SSLContext sslContext = createSSLContext("yourKeystorePath", "yourKeystorePassword");
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
        httpsServer.createContext("/add-to-cart", new AddToCartHandler(cart, "MyRealm","2013000" ));
        httpsServer.start();
    }

    private SSLContext createSSLContext(String keystorePath, String keystorePassword) throws Exception {
        // Load your keystore
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