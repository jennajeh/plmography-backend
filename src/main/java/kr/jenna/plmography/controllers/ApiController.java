package kr.jenna.plmography.controllers;

import kr.jenna.plmography.services.GetApiService;
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
    private final String KEY = "750fc7b483b1da64b9c19bf813de37ac";

    public ApiController(GetApiService getApiService) {
        this.getApiService = getApiService;
    }

    @GetMapping("/getPages")
    public int getPages() {
        return getApiService.getApiPages();
    }

    @GetMapping("/saveApi")
    public String saveApi() throws IOException {
        String result = "";

        try {
            for (int i = 1; i <= getPages(); i += 1) {
                String apiURL = "https://api.themoviedb.org/3/movie/popular?api_key=" + KEY
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
