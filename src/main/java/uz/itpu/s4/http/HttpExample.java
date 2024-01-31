package uz.itpu.s4.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class HttpExample {
  public static void main(String[] args) {
    String url = "https://cbu.uz/ru/arkhiv-kursov-valyut/json/";
    try {
      String responseBody = sendSyncHttpGetRequest(url);
      System.out.println("Response:\n" + responseBody);
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      CompletableFuture<String> responseFuture = sendAsyncHttpGetRequest(url);
      responseFuture.thenAccept(responseBody -> {
        System.out.println("Response:\n" + responseBody);
      }).join(); // Block until the CompletableFuture is complete
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  // Sync
  private static String sendSyncHttpGetRequest(String url) throws Exception {
    HttpClient httpClient = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .GET()
        .build();

    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    return response.body();
  }

  // Async
  private static CompletableFuture<String> sendAsyncHttpGetRequest(String url) {
    HttpClient httpClient = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .GET()
        .build();

    return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
        .thenApply(HttpResponse::body);
  }
}
