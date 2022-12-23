package kr.jenna.plmography.controllers;

import kr.jenna.plmography.services.GetContentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/contents")
public class ContentController {
    private GetContentService getContentService;

    public ContentController(GetContentService getContentService) {
        this.getContentService = getContentService;
    }

    @GetMapping("/api")
    public String callApi() throws IOException {
        StringBuilder result = new StringBuilder();

        String apiUrl = "https://api.themoviedb.org/3/"
                + "movie/popular?api_key=750fc7b483b1da64b9c19bf813de37ac"
                + "&language=ko&page=1";

        URL url = new URL(apiUrl);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        BufferedReader bufferedReader;

        bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

        String returnLine;

        while ((returnLine = bufferedReader.readLine()) != null) {
            result.append(returnLine + "\n\r");
        }

        urlConnection.disconnect();

        return result.toString();
    }
}
