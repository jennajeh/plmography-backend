package kr.jenna.plmography.services;

import kr.jenna.plmography.repositories.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class GetApiService {
    private final MovieRepository movieRepository;
    LocalDateTime dateTime = LocalDateTime.now();

    public GetApiService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

//    public String savePopularMovie(String result) throws IOException {
//
//        JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();
//
//        JsonArray list = (JsonArray) jsonObject.get("results");
//
//        for (int i = 0; i < list.size(); i += 1) {
//            JsonObject contents = (JsonObject) list.get(i);
//
//            String imageUrl = "https://image.tmdb.org/t/p/original";
//            String match = "[\"]";
//
//            movieRepository.save(
//                    Movie.builder()
//                            .movieId(contents.get("id").toString().replaceAll(match, ""))
//                            .genreId(contents.get("genre_ids").toString().replaceAll(match, ""))
//                            .imageUrl(imageUrl + contents.get("poster_path").toString().replaceAll(match, ""))
//                            .korTitle(contents.get("title").toString().replaceAll(match, ""))
//                            .engTitle(contents.get("original_title").toString().replaceAll(match, ""))
//                            .releaseDate(contents.get("release_date")
//                                    == null ? ""
//                                    : contents.get("release_date").toString().replaceAll(match, ""))
//                            .popularity(contents.get("popularity").toString().replaceAll(match, ""))
//                            .description(contents.get("overview").toString().replaceAll(match, ""))
//                            .createdAt(dateTime)
//                            .build()
//            );
//        }
//        return "ok";
//    }
}
