package kr.jenna.plmography.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.repositories.ContentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@Transactional
public class GetApiService {
    private final ContentRepository contentRepository;
    LocalDateTime dateTime = LocalDateTime.now();

    public GetApiService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public String saveApiInfo(String result) throws IOException {

        JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonArray list = (JsonArray) jsonObject.get("results");

        for (int i = 0; i < list.size(); i += 1) {
            JsonObject contents = (JsonObject) list.get(i);

            String imageUrl = "https://image.tmdb.org/t/p/original";
            String match = "[\"]";

            contentRepository.save(
                    Content.builder()
                            .movie_id(contents.get("id").toString().replaceAll(match, ""))
                            .genre_id(contents.get("genre_ids").toString().replaceAll(match, ""))
                            .imageUrl(imageUrl + contents.get("poster_path").toString().replaceAll(match, ""))
                            .korTitle(contents.get("title").toString().replaceAll(match, ""))
                            .engTitle(contents.get("original_title").toString().replaceAll(match, ""))
                            .releaseDate(contents.get("release_date")
                                    == null ? ""
                                    : contents.get("release_date").toString().replaceAll(match, ""))
                            .popularity(contents.get("popularity").toString().replaceAll(match, ""))
                            .description(contents.get("overview").toString().replaceAll(match, ""))
                            .createdAt(dateTime)
                            .build()
            );
        }
        return "ok";
    }
}
