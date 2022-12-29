package kr.jenna.plmography.backdoor;

import kr.jenna.plmography.models.Movie;
import kr.jenna.plmography.models.TvDrama;
import kr.jenna.plmography.repositories.MovieRepository;
import kr.jenna.plmography.repositories.TvDramaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BackdoorController {
    private final RestTemplate restTemplate;
    private JdbcTemplate jdbcTemplate;
    private final MovieRepository movieRepository;
    private final TvDramaRepository tvDramaRepository;

    @Value("${tmdb.api-key}")
    private String apiKey;

    private LocalDateTime dateTime = LocalDateTime.now();
    private HttpHeaders headers = new HttpHeaders();
    private HttpEntity<?> entity = new HttpEntity<>(headers);

    public BackdoorController(RestTemplate restTemplate,
                              JdbcTemplate jdbcTemplate, MovieRepository movieRepository, TvDramaRepository tvDramaRepository) {
        this.restTemplate = restTemplate;
        this.jdbcTemplate = jdbcTemplate;
        this.movieRepository = movieRepository;
        this.tvDramaRepository = tvDramaRepository;
    }

    @GetMapping("/reset-database")
    public String resetDatabase() {
        jdbcTemplate.execute("DELETE FROM movie");
        jdbcTemplate.execute("DELETE FROM tv_drama");
        jdbcTemplate.execute("DELETE FROM performer");

        return "Reset completed!";
    }

    @GetMapping("/setup-movie")
    public String setupMovie() throws IOException {
        for (int i = 1; i <= 10; i += 1) {
            String url = "/movie/popular?"
                    + "api_key=" + apiKey + "&language=ko&page=" + i;

            ResponseEntity<Map> resultMap = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

            ArrayList<Map> list = (ArrayList<Map>) resultMap.getBody().get("results");

            for (Map data : list) {
                String imageUrl = "https://image.tmdb.org/t/p/original";
                String match = "[\"]";

                movieRepository.save(
                        Movie.builder()
                                .tmdbMovieId(data.get("id").toString().replaceAll(match, ""))
                                .tmdbGenreId(data.get("genre_ids").toString().replaceAll(match, ""))
                                .imageUrl(imageUrl + data.get("poster_path").toString().replaceAll(match, ""))
                                .korTitle(data.get("title").toString().replaceAll(match, ""))
                                .engTitle(data.get("original_title").toString().replaceAll(match, ""))
                                .releaseDate(data.get("release_date") == null
                                        ? ""
                                        : data.get("release_date").toString().replaceAll(match, ""))
                                .popularity(data.get("popularity").toString().replaceAll(match, ""))
                                .description(data.get("overview").toString().replaceAll(match, ""))
                                .createdAt(dateTime)
                                .build()
                );
            }
        }

        return "Movies completely saved!";
    }

    @GetMapping("/setup-tv")
    public String setupTv() throws IOException {
        for (int i = 1; i <= 10; i += 1) {
            String url = "/tv/popular?"
                    + "api_key=" + apiKey + "&language=ko&page=" + i;

            ResponseEntity<Map> resultMap = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

            ArrayList<Map> list = (ArrayList<Map>) resultMap.getBody().get("results");

            for (Map data : list) {
                String imageUrl = "https://image.tmdb.org/t/p/original";
                String match = "[\"]";

                tvDramaRepository.save(
                        TvDrama.builder()
                                .tmdbTvId(data.get("id").toString().replaceAll(match, ""))
                                .tmdbGenreId(data.get("genre_ids").toString().replaceAll(match, ""))
                                .imageUrl(imageUrl + data.get("poster_path").toString().replaceAll(match, ""))
                                .korTitle(data.get("name").toString().replaceAll(match, ""))
                                .engTitle(data.get("original_name").toString().replaceAll(match, ""))
                                .releaseDate(data.get("first_air_date") == null
                                        ? ""
                                        : data.get("first_air_date").toString().replaceAll(match, ""))
                                .popularity(data.get("popularity").toString().replaceAll(match, ""))
                                .description(data.get("overview").toString().replaceAll(match, ""))
                                .createdAt(dateTime)
                                .build()
                );
            }
        }

        return "TvDrama completely saved!";
    }

    @GetMapping("/setup-movie-platform")
    public String setupMoviePlatform() throws IOException {
        jdbcTemplate.update("UPDATE movie SET platform='[netflix, wavve, watcha, disney]' WHERE id <= 100");
        jdbcTemplate.update("UPDATE tv_drama SET platform='[netflix, wavve, watcha, disney]' WHERE id <= 100");
        jdbcTemplate.update("UPDATE movie SET platform='[wavve, tving, apple]' WHERE id > 100");
        jdbcTemplate.update("UPDATE tv_drama SET platform='[wavve, tving, apple]' WHERE id > 100");

        return "Platform completely saved!";
    }
}
