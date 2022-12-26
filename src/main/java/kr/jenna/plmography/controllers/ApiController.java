package kr.jenna.plmography.controllers;

import kr.jenna.plmography.services.GetApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/api")
public class ApiController {
    private GetApiService getApiService;

    @Value("${tmdb.api-key}")
    private String apiKey;

    public ApiController(GetApiService getApiService) {
        this.getApiService = getApiService;
    }

    @GetMapping("/savePopularMovie")
    public String saveApi() throws IOException {
        String result = "";

        try {
            for (int i = 1; i <= 5; i += 1) {
                String apiURL = "https://api.themoviedb.org/3/movie/popular?api_key=" + apiKey
                        + "&language=ko&page=" + i;

                URL url = new URL(apiURL);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-type", "application/json");

                BufferedReader bf = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

                result = bf.readLine();

                getApiService.saveApiInfo(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "ok";
    }
}
