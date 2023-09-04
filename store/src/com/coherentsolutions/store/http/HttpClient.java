package com.coherentsolutions.store.http;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {
    public static void main(String[] args) {
        OnlineStoreHttpServer httpServer = new OnlineStoreHttpServer();
        try {
            httpServer.startServer(8080);
            System.out.println("HTTP Server started on port 8080.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // Create a URL for your server's endpoint
            URL url = new URL("http://localhost:8080/add-to-cart");

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to POST
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            // Enable input/output streams
            connection.setDoOutput(true);

            // Define the JSON request body
            String requestBody = "{\"name\":\"Product Name\",\"price\":19.99,\"rate\":4.5}";

            // Write the JSON data to the request body
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get the HTTP response code
            int responseCode = connection.getResponseCode();

            // Check if the response code is 200 (OK)
            if (responseCode == 200) {
                System.out.println("Product added to cart successfully.");
            } else {
                System.out.println("Failed to add product to cart. Response code: " + responseCode);
            }

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}