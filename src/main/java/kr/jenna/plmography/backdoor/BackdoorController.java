package kr.jenna.plmography.backdoor;

import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.repositories.ContentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/backdoor")
@SuppressWarnings("unchecked")
@Transactional
public class BackdoorController {
    private final RestTemplate restTemplate;
    private JdbcTemplate jdbcTemplate;
    private final ContentRepository contentRepository;
    private PasswordEncoder passwordEncoder;

    @Value("${tmdb.api-key}")
    private String apiKey;

    private LocalDateTime dateTime = LocalDateTime.now();
    private HttpHeaders headers = new HttpHeaders();
    private HttpEntity<?> entity = new HttpEntity<>(headers);

    public BackdoorController(RestTemplate restTemplate,
                              JdbcTemplate jdbcTemplate, ContentRepository contentRepository, PasswordEncoder passwordEncoder) {
        this.restTemplate = restTemplate;
        this.jdbcTemplate = jdbcTemplate;
        this.contentRepository = contentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/setup-database")
    public String setupDatabase() {
        LocalDateTime now = LocalDateTime.now();
        jdbcTemplate.execute("DELETE FROM users");
        jdbcTemplate.execute("DELETE FROM user_follower_ids");
        jdbcTemplate.execute("DELETE FROM user_following_ids");
        jdbcTemplate.execute("DELETE FROM user_bookmark_contents");
        jdbcTemplate.execute("DELETE FROM user_bookmark_themes");
        jdbcTemplate.execute("DELETE FROM user_watched_list");
        jdbcTemplate.execute("DELETE FROM user_favorite_contents");
        jdbcTemplate.execute("DELETE FROM review_like_user_ids");
        jdbcTemplate.execute("DELETE FROM review");
        jdbcTemplate.execute("DELETE FROM comment");
        jdbcTemplate.execute("DELETE FROM recomment");

        jdbcTemplate.update("INSERT INTO users(" +
                        "  id, email, password, nickname," +
                        "  gender, birth_year, profile_image, created_at, updated_at)" +
                        " VALUES(1, 'jenna@gmail.com', ?, '전제나', '여성', 1990, "
                        + "'https://source.boringavatars.com/beam/120/?nickname=jenna', ?, ?)",
                passwordEncoder.encode("Test123!"), now, now
        );

        jdbcTemplate.update("INSERT INTO users(" +
                        "  id, email, password, nickname," +
                        "  gender, birth_year, profile_image, created_at, updated_at)" +
                        " VALUES(2, 'boni@gmail.com', ?, '강보니', '여성', 1990, "
                        + "'https://source.boringavatars.com/beam/120/?nickname=boni', ?, ?)",
                passwordEncoder.encode("Test123!"), now, now
        );

        jdbcTemplate.update("INSERT INTO users(" +
                        "  id, email, password, nickname," +
                        "  gender, birth_year, profile_image, created_at, updated_at)" +
                        " VALUES(3, 'zzezze@gmail.com', ?, '최재원', '여성', 1990, "
                        + "'https://source.boringavatars.com/beam/120/?nickname=zzezze', ?, ?)",
                passwordEncoder.encode("Test123!"), now, now
        );

        jdbcTemplate.update("INSERT INTO users(" +
                        "  id, email, password, nickname," +
                        "  gender, birth_year, profile_image, created_at, updated_at)" +
                        " VALUES(4, 'inu@gmail.com', ?, '황인우', '여성', 1990, "
                        + "'https://source.boringavatars.com/beam/120/?nickname=inu', ?, ?)",
                passwordEncoder.encode("Test123!"), now, now
        );

        jdbcTemplate.update("INSERT INTO users(" +
                        "  id, email, password, nickname," +
                        "  gender, birth_year, profile_image, created_at, updated_at)" +
                        " VALUES(5, 'jo@gmail.com', ?, '조성환', '여성', 1990, "
                        + "'https://source.boringavatars.com/beam/120/?nickname=jo', ?, ?)",
                passwordEncoder.encode("Test123!"), now, now
        );

        jdbcTemplate.update("INSERT INTO review("
                + "  id, user_id, content_id, is_deleted,"
                + "  review_body, star_rate, created_at, updated_at)"
                + " VALUES(1, 1, 899112, ?, '영화가 재미있어요!', 5L, ?, ?)", false, now.minusDays(1), now
        );

        jdbcTemplate.update("INSERT INTO review("
                + "  id, user_id, content_id, is_deleted,"
                + "  review_body, star_rate, created_at, updated_at)"
                + " VALUES(2, 2, 76600, ?, '재미와 감동이 두배', 4L, ?, ?)", false, now.minusHours(2), now
        );

        jdbcTemplate.update("INSERT INTO review("
                + "  id, user_id, content_id, is_deleted,"
                + "  review_body, star_rate, created_at, updated_at)"
                + " VALUES(3, 3, 899112, ?, '지금까지의 서막은 완벽하다. 정주행 재생버튼 누른 이후로 쉬지 않고 다 봤음다. "
                + "곧 나올 시즌 2가 관건이네요ㅋㅋㅋ용두사미일지 용두용미일지.', 4L, ?, ?)", false, now.minusMinutes(2), now
        );

        jdbcTemplate.update("INSERT INTO review("
                + "  id, user_id, content_id, is_deleted,"
                + "  review_body, star_rate, created_at, updated_at)"
                + " VALUES(4, 4, 456, ?, '심슨 가족이랑 이웃하고 싶다', 4L, ?, ?)", false, now.minusHours(1), now
        );

        jdbcTemplate.update("INSERT INTO review("
                + "  id, user_id, content_id, is_deleted,"
                + "  review_body, star_rate, created_at, updated_at)"
                + " VALUES(5, 5, 4057, ?, '이거 안본사람과 겸상하지 않겠다 일단 시즌1까지는', 4L, ?, ?)", false, now.minusDays(3), now
        );

        jdbcTemplate.update("INSERT INTO review("
                + "  id, user_id, content_id, is_deleted,"
                + "  review_body, star_rate, created_at, updated_at)"
                + " VALUES(6, 2, 4057, ?, '지루해여', 2L, ?, ?)", false, now.minusSeconds(1), now
        );

        jdbcTemplate.update("INSERT INTO comment("
                + "  id, user_id, post_id, comment_body,"
                + "  is_deleted, created_at)"
                + " VALUES(1, 1, 2, '저는 별로였음', ?, ?)", false, now.minusDays(1)
        );

        jdbcTemplate.update("INSERT INTO comment("
                + "  id, user_id, post_id, comment_body,"
                + "  is_deleted, created_at)"
                + " VALUES(2, 2, 3, '꿀잼!!!!', ?, ?)", false, now.minusMinutes(1)
        );

        jdbcTemplate.update("INSERT INTO recomment("
                + "  id, comment_id, recomment_body, user_id,"
                + "  post_id, created_at)"
                + " VALUES(1, 1, '동의! 완전 재미있어요', 1, 2, ?)", now
        );

        jdbcTemplate.update("INSERT INTO recomment("
                + "  id, comment_id, recomment_body, user_id,"
                + "  post_id, created_at)"
                + " VALUES(2, 1, '이렇게 생각하신 이유는 뭔가요?', 2, 2, ?)", now.minusMinutes(6)
        );

        jdbcTemplate.update("" +
                        "INSERT INTO review_like_user_ids(" +
                        "  review_id, like_user_id" +
                        ")" +
                        " VALUES(?, ?)",
                4L, 1L
        );

        return "Setup database completed!";
    }

    @GetMapping("/setup-content")
    public String setupContent() {
        jdbcTemplate.execute("DROP TABLE content;");

        jdbcTemplate.execute("CREATE TABLE content ("
                + "id BIGINT AUTO_INCREMENT, tmdb_id VARCHAR, tmdb_genre_id VARCHAR,"
                + "image_url VARCHAR, kor_title VARCHAR, eng_title VARCHAR,"
                + "release_date VARCHAR, popularity VARCHAR, type VARCHAR,"
                + "platform VARCHAR, description VARCHAR(4000), "
                + "created_at VARCHAR);");

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

                contentRepository.save(
                        Content.builder()
                                .tmdbId(data.get("id").toString().replaceAll(match, ""))
                                .tmdbGenreId(data.get("genre_ids").toString().replaceAll(match, ""))
                                .imageUrl(data.get("poster_path") == null
                                        ? "" :
                                        imageUrl + data.get("poster_path").toString().replaceAll(match, ""))
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

                contentRepository.save(
                        Content.builder()
                                .tmdbId(data.get("id").toString().replaceAll(match, ""))
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

    @GetMapping("/setup-platform-type")
    public String setupPlatformAndType() throws IOException {
        jdbcTemplate.update("UPDATE content SET platform='[netflix, wavve, watcha, disney]' WHERE id <= 50");
        jdbcTemplate.update("UPDATE content SET platform='[wavve, tving, apple]' WHERE id > 50 AND id <= 100");
        jdbcTemplate.update("UPDATE content SET platform='[netflix, watcha]' WHERE id > 100 AND id <= 150");
        jdbcTemplate.update("UPDATE content SET platform='[disney, apple]' WHERE id > 150 AND id <= 200");
        jdbcTemplate.update("UPDATE content SET platform='[netflix, wavve, watcha, disney]' WHERE id > 200");

        jdbcTemplate.update("UPDATE content SET type='movie' WHERE id <= 200");
        jdbcTemplate.update("UPDATE content SET type='drama' WHERE id > 200");

        return "Platform completely saved!";
    }
}
