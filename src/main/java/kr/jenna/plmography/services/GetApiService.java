package kr.jenna.plmography.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.repositories.ContentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;

@Service
@Transactional
public class GetApiService {
    private final ContentRepository contentRepository;

    @Value("${tmdb.api-key}")
    private String KEY;

    LocalDateTime dateTime = LocalDateTime.now();

    public GetApiService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public int getApiPages() {
        int page = 0;
        String result = "";

        try {
            URL url = new URL("https://api.themoviedb.org/3/movie/popular?api_key="
                    + KEY + "&language=ko");

            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            result = bf.readLine();

            JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();

            String pages = jsonObject.get("total_pages").toString();

            page = Integer.parseInt(pages);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return page;
    }

    public String saveApiInfo(String result) {

        JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();

        JsonArray list = (JsonArray) jsonObject.get("results");

        for (int i = 0; i < list.size(); i += 1) {
            JsonObject contents = (JsonObject) list.get(i);

            String imageUrl = "https://image.tmdb.org/t/p/original";
            String match = "[\"]";

            contentRepository.save(
                    Content.builder()
                            .description(contents.get("overview").toString().replaceAll(match, ""))
                            .korTitle(contents.get("title").toString().replaceAll(match, ""))
                            .engTitle(contents.get("original_title").toString().replaceAll(match, ""))
                            .releaseDate(contents.get("release_date")
                                    == null ? ""
                                    : contents.get("release_date").toString().replaceAll(match, ""))
                            .genres(contents.get("genre_ids").toString().replaceAll(match, ""))
                            .imageUrl(imageUrl + contents.get("poster_path").toString().replaceAll(match, ""))
                            .createdAt(dateTime)
                            .updatedAt(dateTime)
                            .build()
            );
        }
        return "ok";
    }
}
