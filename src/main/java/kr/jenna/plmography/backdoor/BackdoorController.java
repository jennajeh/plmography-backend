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

        jdbcTemplate.execute("DELETE FROM user_follower_ids");
        jdbcTemplate.execute("DELETE FROM user_following_ids");
        jdbcTemplate.execute("DELETE FROM user_bookmark_contents");
        jdbcTemplate.execute("DELETE FROM user_bookmark_themes");
        jdbcTemplate.execute("DELETE FROM user_watched_list");
        jdbcTemplate.execute("DELETE FROM user_favorite_contents");
        jdbcTemplate.execute("DELETE FROM users");
        jdbcTemplate.execute("DELETE FROM review_like_user_ids");
        jdbcTemplate.execute("DELETE FROM review");
        jdbcTemplate.execute("DELETE FROM comment");
        jdbcTemplate.execute("DELETE FROM recomment");
        jdbcTemplate.execute("DELETE FROM article");

        jdbcTemplate.update("INSERT INTO users(" +
                        "  id, email, password, nickname," +
                        "  gender, birth_year, profile_image, created_at, updated_at)" +
                        " VALUES(1, 'jenna@gmail.com', ?, '전제나', '여성', 1990, "
                        + "'https://source.boringavatars.com/beam/120/nickname=jenna', ?, ?)",
                passwordEncoder.encode("Test123!"), now, now
        );

        jdbcTemplate.update("INSERT INTO users(" +
                        "  id, email, password, nickname," +
                        "  gender, birth_year, profile_image, created_at, updated_at)" +
                        " VALUES(2, 'boni@gmail.com', ?, '강보니', '여성', 1990, "
                        + "'https://source.boringavatars.com/beam/120/nickname=boni', ?, ?)",
                passwordEncoder.encode("Test123!"), now, now
        );

        jdbcTemplate.update("INSERT INTO users(" +
                        "  id, email, password, nickname," +
                        "  gender, birth_year, profile_image, created_at, updated_at)" +
                        " VALUES(3, 'zzezze@gmail.com', ?, '최재원', '여성', 1990, "
                        + "'https://source.boringavatars.com/beam/120/nickname=zzezze', ?, ?)",
                passwordEncoder.encode("Test123!"), now, now
        );

        jdbcTemplate.update("INSERT INTO users(" +
                        "  id, email, password, nickname," +
                        "  gender, birth_year, profile_image, created_at, updated_at)" +
                        " VALUES(4, 'inu@gmail.com', ?, '황인우', '여성', 1990, "
                        + "'https://source.boringavatars.com/beam/120/nickname=inu', ?, ?)",
                passwordEncoder.encode("Test123!"), now, now
        );

        jdbcTemplate.update("INSERT INTO users(" +
                        "  id, email, password, nickname," +
                        "  gender, birth_year, profile_image, created_at, updated_at)" +
                        " VALUES(5, 'jo@gmail.com', ?, '조성환', '여성', 1990, "
                        + "'https://source.boringavatars.com/beam/120/nickname=jo', ?, ?)",
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
                + " VALUES(7, 1, 76600, ?, '영화가 재미있어요!', 5L, ?, ?)", false, now.minusDays(1), now
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

        jdbcTemplate.update("INSERT INTO review_like_user_ids(" +
                "  review_id, like_user_id)" +
                " VALUES(?, ?)", 4L, 1L
        );

        jdbcTemplate.update("INSERT INTO article(" +
                "  id, user_id, content_id, image," +
                "  title, article_body, created_at)" +
                " VALUES(1, 1, 76600, 'https://image.tmdb.org/t/p/original/z56bVX93oRG6uDeMACR7cXCnAbh.jpg', "
                + "'<아바타: 물의 길>, 더 재미있게 볼 수 있는 길', "
                + "'아바타: 물의 길, 2009년 전 세계를 뜨겁게 달궜던 박스오피스 1위, 제임스 카메론 감독 작품\n"
                + "<아바타>의 속편인 <아바타: 물의 길>이 한국에서 세계 최초로 개봉했습니다.\n"
                + "이번 작품의 주요 배경은 바다이며 14년 만에 다시 판도라 행성을 침입한 RDA와\n"
                + "그에 맞서는 제이크 설리 - 네이티리 가족의 스토리를 만나볼 수 있죠. \n"
                + "그뿐만 아니라, 무려 3시간 12분에 달하는 어마무시한 러닝타임에 한 번 놀라고\n"
                + "상영 시간 내내 눈과 귀를 사로잡은 역대급 영상미에 두 번 놀라게 될 작품, \n"
                + "아바타: 물의 길 입니다.', ?)", now.minusDays(1)
        );

        jdbcTemplate.update("INSERT INTO article(" +
                "  id, user_id, content_id, image," +
                "  title, article_body, created_at)" +
                " VALUES(2, 1, 315162, 'https://image.tmdb.org/t/p/original/rKgvctIuPXyuqOzCQ16VGdnHxKx.jpg', "
                + "'<장화신은 고양이: 끝내주는 모험> 같이 떠나보실까요?', "
                + "'로튼토마토 관객지수 98% 끝내주는 호평 세례!\n"
                + "<보스 베이비><드래곤 길들이기> 제작진이 완성한 끝내주는 묘생 블록버스터!\n"
                + "전 세계 관객들을 사로잡은 ‘장화신은 고양이’가 올겨울 극장가를 책임집니다!\n"
                + "영화 <장화신은 고양이: 끝내주는 모험>은 9개의 목숨 중 \n"
                + "단 하나의 목숨만 남은 마성의 히어로 ‘장화신은 고양이’가 \n"
                + "잃어버린 목숨을 찾기 위해 소원별을 찾아 떠나는 끝내주는 액션 어드벤처인데요. \n"
                + "소원을 이루어 주는 소원별을 찾아 잃어버린 목숨을 되찾기 위한 모험을 떠나는 ‘장화신은 고양이’가 \n"
                + "새로운 팀을 꾸려 8번의 삶 동안 해 보지 못한 색다른 경험을 선보여, \n"
                + "완전히 새로운 모험의 세계로 관객들을 인도할 것입니다.', ?)", now.minusDays(2)
        );

        jdbcTemplate.update("INSERT INTO article(" +
                "  id, user_id, content_id, image," +
                "  title, article_body, created_at)" +
                " VALUES(3, 1, 661374, 'https://image.tmdb.org/t/p/original/lrgmo1qluO9c2qdBduiu412fRDS.jpg', "
                + "'<나이브스 아웃: 글래스 어니언> 의 게임은 시작됐다.', "
                + "'게임은 시작됐다: ‘글래스’의 심장.\n"
                + "브누아 블랑이 라이언 존슨 감독의 새로운 살인 추리극에서 겹겹이 쌓인 미스터리를 파헤치러 돌아옵니다. \n"
                + "이 대담한 탐정이 새로운 모험을 펼칠 장소는 그리스 섬의 호화로운 사유지. \n"
                + "그러나 그가 어떻게, 무슨 이유로 이곳에 오게 되었는지부터가 무수히 많은 수수께끼의 출발점! \n"
                + "누군가가 죽은 채로 발견되는 순간, 모두가 용의자가 됩니다. \n"
                + "아카데미상 후보에 오른 라이언 존슨 감독이 전편에 이어 또다시 각본과 연출을 맡은 《나이브스 아웃: 글래스 어니언》 \n"
                + "블랑 역으로 돌아온 다니엘 크레이그를 필두로 에드워드 노튼, 자넬 모네, 캐스린 한, \n"
                + "레슬리 오덤 주니어, 제시카 헤닉, 매들린 클라인, 케이트 허드슨, 데이브 바티스타 등 전편 못지않은 초호화 출연진이 총출동한 \n"
                + "나이브스 아웃: 글래스 어니언. 지금 만나보러 가실까요?', ?)", now.minusDays(3)
        );

        jdbcTemplate.update("INSERT INTO article(" +
                "  id, user_id, content_id, image," +
                "  title, article_body, created_at)" +
                " VALUES(4, 1, 338953, 'https://image.tmdb.org/t/p/original/uvQbXjMgC5weQepx4jLJJ60H3N0.jpg', "
                + "'<신비한 동물들과 덤블도어의 비밀> 세상을 구할 전쟁이 시작된다!', "
                + "'가장 위험한 마법에 맞선, 세상을 구할 전쟁이 시작된다! \n"
                + "1930년대, 제2차 세계대전에 마법사들이 개입하게 되면서 \n"
                + "강력한 어둠의 마법사 그린델왈드의 힘이 급속도로 커집니다. \n"
                + "덤블도어는 뉴트 스캐맨더에게 위대한 마법사 가문 후손, \n"
                + "마법학교의 유능한 교사, 머글 등으로 이루어진 팀에게 임무를 맡기는데요. \n"
                + "이에 뉴트와 친구들은 머글과의 전쟁을 선포한 \n"
                + "그린델왈드와 추종자들, 그의 위험한 신비한 동물들에 맞서 세상을 구할 거대한 전쟁에 나섭니다. \n"
                + "한편 전쟁의 위기가 최고조로 달한 상황 속에서 덤블도어는 \n"
                + "더 이상 방관자로 머물 수 없는 순간을 맞이하고, 서서히 숨겨진 비밀이 드러나게 되는데…', ?)", now.minusDays(4)
        );

        jdbcTemplate.update("INSERT INTO article(" +
                "  id, user_id, content_id, image," +
                "  title, article_body, created_at)" +
                " VALUES(5, 1, 119051, 'https://image.tmdb.org/t/p/original/tNWCukAMubqisamYURvo5jw61As.jpg', "
                + "'<웬즈데이> 연쇄 살인 사건', "
                + "'똑똑하고 비꼬는 것에 도가 튼 웬즈데이 아담스. \n"
                + "암울함을 풍기는 그녀가 네버모어 아카데미에서 연쇄 살인 사건을 조사하기 시작합니다. \n"
                + "새 친구도 사귀고, 앙숙도 만들며.', ?)", now.minusDays(5)
        );

        jdbcTemplate.update("INSERT INTO article(" +
                "  id, user_id, content_id, image," +
                "  title, article_body, created_at)" +
                " VALUES(6, 1, 634649, 'https://image.tmdb.org/t/p/original/voddFVdjUoAtfoZZp2RUmuZILDI.jpg', "
                + "'<스파이더맨: 노 웨이 홈> 멀티버스의 세계', "
                + "'2021년 12월, 스파이더맨의 새로운 시대가 열린다! \n"
                + "기존 스파이더맨 세계관을 확장하는 멀티버스의 본격적인 시작! \n"
                + "미스테리오’의 계략으로 세상에 정체가 탄로난 \n"
                + "스파이더맨 ‘피터 파커’는 하루 아침에 평범한 일상을 잃게 됩니다. \n"
                + "문제를 해결하기 위해 ‘닥터 스트레인지’를 찾아가 도움을 청하지만, \n"
                + "뜻하지 않게 멀티버스가 열리면서 각기 다른 차원의 불청객들이 나타나는데요. \n"
                + "‘닥터 옥토퍼스’를 비롯해 스파이더맨에게 깊은 원한을 가진 숙적들의 강력한 공격에 \n"
                + "‘피터 파커’는 사상 최악의 위기를 맞게 되는데…', ?)", now.minusDays(6)
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
                String match = "[\\[\\]\"]";

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
                                        ? 2022
                                        : Integer.parseInt(data.get("release_date").toString().substring(0, 4).replaceAll(match, "")))
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
                String match = "[\\[\\]\"]";

                contentRepository.save(
                        Content.builder()
                                .tmdbId(data.get("id").toString().replaceAll(match, ""))
                                .tmdbGenreId(data.get("genre_ids").toString().replaceAll(match, ""))
                                .imageUrl(data.get("poster_path") == null
                                        ? ""
                                        : imageUrl + data.get("poster_path").toString().replaceAll(match, ""))
                                .korTitle(data.get("name").toString().replaceAll(match, ""))
                                .engTitle(data.get("original_name").toString().replaceAll(match, ""))
                                .releaseDate(data.get("first_air_date") == null
                                        ? 2022
                                        : data.get("first_air_date").toString().length() > 0
                                        ? Integer.parseInt(data.get("first_air_date").toString().substring(0, 4).replaceAll(match, ""))
                                        : 2022)
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
        jdbcTemplate.update("UPDATE content SET platform='netflix, wavve, watcha, disney' WHERE id <= 50");
        jdbcTemplate.update("UPDATE content SET platform='wavve, tving, apple' WHERE id > 50 AND id <= 100");
        jdbcTemplate.update("UPDATE content SET platform='netflix, watcha' WHERE id > 100 AND id <= 150");
        jdbcTemplate.update("UPDATE content SET platform='disney, apple' WHERE id > 150 AND id <= 200");
        jdbcTemplate.update("UPDATE content SET platform='netflix, wavve, watcha, disney' WHERE id > 200");

        jdbcTemplate.update("UPDATE content SET type='movie' WHERE id <= 200");
        jdbcTemplate.update("UPDATE content SET type='drama' WHERE id > 200");

        return "Platform completely saved!";
    }
}
