package com.henry.noticiero.service;

import com.google.gson.Gson;
import com.henry.noticiero.model.response.WeatherResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Service
public class ApiCallService {

    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private static final String API_KEY = "63884236f2802fe43a2740c772615e3b";
    private static final String URL = "https://api.openweathermap.org/data/2.5/weather?q=la%20plata&appid=" + API_KEY;

    public WeatherResponse callAPI() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        final WeatherResponse weatherResponse = new Gson().fromJson(response.body(), WeatherResponse.class);

        System.out.println(weatherResponse);

        return weatherResponse;
    }
}