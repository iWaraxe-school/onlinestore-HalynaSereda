package com.coherentsolutions.store.http;

import com.coherentsolutions.domain.Product;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AddToCartHandler implements HttpHandler {

    private Cart cart;

    public AddToCartHandler(Cart cart) {
        this.cart = cart;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            // Check for Basic Authentication credentials
            if (checkBasicAuth(exchange)) {
                // Parse the request to get the product details
                String requestBody = new String(exchange.getRequestBody().readAllBytes());

                // Parse the request body as JSON
                Product product = parseProductFromRequestBody(requestBody);

                // Add the product to the cart
                cart.addProduct(product);

                // Send a confirmation response
                String response = "Product added to cart successfully.";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                // Unauthorized access, send a 401 response
                exchange.sendResponseHeaders(401, 0); // Unauthorized
            }
        } else {
            // Handle invalid HTTP methods (e.g., GET)
            exchange.sendResponseHeaders(405, 0); // Method Not Allowed
        }
    }

    private boolean checkBasicAuth(HttpExchange exchange) {
        // Get the Authorization header
        String authHeader = exchange.getRequestHeaders().getFirst("Authorization");

        if (authHeader != null && authHeader.startsWith("Basic ")) {
            // Extract the Base64 encoded credentials part
            String encodedCredentials = authHeader.substring("Basic ".length()).trim();

            // Decode the Base64 credentials
            String decodedCredentials = new String(Base64.getDecoder().decode(encodedCredentials), StandardCharsets.UTF_8);

            // Split the decoded credentials into username and password
            String[] credentials = decodedCredentials.split(":", 2);

            // Check if the provided username and password match the hardcoded values
            return credentials.length == 2 && credentials[0].equals("yourUsername") && credentials[1].equals("yourPassword");
        }

        return false;
    }

    private Product parseProductFromRequestBody(String requestBody) {
        try {
            // Create a Gson instance for JSON parsing
            Gson gson = new Gson();

            // Parse the request body into a Product object
            Product product = gson.fromJson(requestBody, Product.class);

            return product;
        } catch (JsonParseException e) {
            // Handle parsing error (e.g., invalid JSON format)
            throw new RuntimeException("Error parsing JSON request body: " + e.getMessage(), e);
        }
    }
}