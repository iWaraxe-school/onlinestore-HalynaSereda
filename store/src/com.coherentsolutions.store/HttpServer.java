package com.coherentsolutions.store;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpServer {
    public void startServer(int port) throws IOException {
    HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
    server.start();
}
}
