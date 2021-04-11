package com.henry.noticiero.service;

import com.google.gson.Gson;
import com.henry.noticiero.model.response.MeteoRedApi;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import com.henry.noticiero.model.response.OpenWeatherApi;
import com.henry.noticiero.model.response.WeatherResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Service
@Slf4j
public class ApiCallService {

    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private static final String API_KEY = "63884236f2802fe43a2740c772615e3b";
    private static final String URL = "https://api.openweathermap.org/data/2.5/weather?q=la%20plata&appid=" + API_KEY;
    private static final String URL2 = "http://api.meteored.cl/index.php?api_lang=en&localidad=16930&affiliate_id=poxengdu8194&v=3.0";


    @CircuitBreaker(name = "Weather", fallbackMethod = "fallback")
    public WeatherResponse callAPI() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        final OpenWeatherApi openWeatherApi = new Gson().fromJson(response.body(), OpenWeatherApi.class);

        String status = openWeatherApi.getWeather()[0].getMain();
        String temp = String.valueOf(openWeatherApi.getMainResponse().getTemp());
        String tempMin = String.valueOf(openWeatherApi.getMainResponse().getTemp_min());
        String tempMax = String.valueOf(openWeatherApi.getMainResponse().getTemp_max());

        WeatherResponse weatherResponse = new WeatherResponse(status, temp, tempMin, tempMax);

        //Para probar que pasa cuando da error el primer servicio
//        if (true) {
//            throw new IOException("Probando Circuit Breaker");
//        }

        return weatherResponse;
    }

    private WeatherResponse fallback(final Throwable t){

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL2))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try{
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            final MeteoRedApi meteoRedApi = new Gson().fromJson(response.body(), MeteoRedApi.class);
            return WeatherResponse
                    .builder()
                    .status(meteoRedApi.getDay().get("1").getStatus())
                    .temp(meteoRedApi.getDay().get("1").getHour()[4].getTemp())
                    .tempMin(meteoRedApi.getDay().get("1").getTempmin())
                    .tempMax(meteoRedApi.getDay().get("1").getTempmax())
                    .build();
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Intente nuevamente mas tarde");
        }
    }
}