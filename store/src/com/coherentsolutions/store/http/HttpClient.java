package com.coherentsolutions.store.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

    public static void main(String[] args) {
        // Server URL and endpoint
        String serverUrl = "https://localhost:8080";
        String endpoint = "/add-to-cart";

        try {
            // Create a URL object with the server URL and endpoint
            URL url = new URL(serverUrl + endpoint);

            // Open a connection to the server
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to POST
            connection.setRequestMethod("POST");

            // Enable input and output streams
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // Set the request headers (content type and any others required)
            connection.setRequestProperty("Content-Type", "application/json");

            // Construct the JSON request body
            String jsonRequestBody = "{\"name\":\"Product Name\",\"price\":19.99,\"rate\":4.5}";

            // Write the JSON request body to the output stream
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonRequestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get the response code from the server
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read and process the server's response
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    System.out.println("Server Response: " + response.toString());
                }
            } else {
                System.err.println("Error: HTTP request failed");
            }

            // Close the connection
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
