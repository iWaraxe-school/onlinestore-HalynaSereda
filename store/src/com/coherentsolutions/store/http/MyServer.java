package com.coherentsolutions.store.http;

public class MyServer {
    public static void main(String[] args) throws Exception {
        // Start the HTTP server with basic authentication
        OnlineStoreHttpServer httpServer = new OnlineStoreHttpServer();
        httpServer.startServer(8081);

        System.out.println("HTTPS Server started on port 8081 with basic authentication.");
    }
}