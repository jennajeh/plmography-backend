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

        jdbcTemplate.execute("DELETE FROM user_favorite_content_ids");
        jdbcTemplate.execute("DELETE FROM user_watched_content_ids");
        jdbcTemplate.execute("DELETE FROM user_wish_content_ids");
//        jdbcTemplate.execute("DELETE FROM users");
        jdbcTemplate.execute("DELETE FROM subscribe");
        jdbcTemplate.execute("DELETE FROM review_like_user_ids");
        jdbcTemplate.execute("DELETE FROM review");
        jdbcTemplate.execute("DELETE FROM review_comment");
        jdbcTemplate.execute("DELETE FROM article");
        jdbcTemplate.execute("DELETE FROM theme");
        jdbcTemplate.execute("DELETE FROM likes");
        jdbcTemplate.execute("DELETE FROM post");
        jdbcTemplate.execute("DELETE FROM post_comment");

        jdbcTemplate.update("INSERT INTO subscribe(" +
                "  id, user_id, friend_id)" +
                " VALUES(1, 102, 103)"
        );

        jdbcTemplate.update("INSERT INTO subscribe(" +
                "  id, user_id, friend_id)" +
                " VALUES(2, 102, 104)"
        );

        jdbcTemplate.update("INSERT INTO subscribe(" +
                "  id, user_id, friend_id)" +
                " VALUES(3, 102, 105)"
        );

        jdbcTemplate.update("INSERT INTO subscribe(" +
                "  id, user_id, friend_id)" +
                " VALUES(4, 102, 206)"
        );

        jdbcTemplate.update("INSERT INTO subscribe(" +
                "  id, user_id, friend_id)" +
                " VALUES(5, 103, 102)"
        );

        jdbcTemplate.update("INSERT INTO subscribe(" +
                "  id, user_id, friend_id)" +
                " VALUES(6, 103, 104)"
        );

        jdbcTemplate.update("INSERT INTO subscribe(" +
                "  id, user_id, friend_id)" +
                " VALUES(7, 103, 106)"
        );

        jdbcTemplate.update("INSERT INTO review("
                + "  id, user_id, content_id, is_deleted,"
                + "  review_body, star_rate, created_at, updated_at)"
                + " VALUES(1, 102, 76600, ?, '영화가 재미있어요!', 5L, ?, ?)", false, now.minusDays(1), now
        );

        jdbcTemplate.update("INSERT INTO review("
                + "  id, user_id, content_id, is_deleted,"
                + "  review_body, star_rate, created_at, updated_at)"
                + " VALUES(2, 104, 76600, ?, '재미와 감동이 두배', 4L, ?, ?)", false, now.minusHours(2), now
        );

        jdbcTemplate.update("INSERT INTO review("
                + "  id, user_id, content_id, is_deleted,"
                + "  review_body, star_rate, created_at, updated_at)"
                + " VALUES(3, 105, 76600, ?, '지금까지의 서막은 완벽함. 정주행 재생버튼 누른 이후로 쉬지 않고 다 봤음다. "
                + "내년에 나올 속편이 관건이네요ㅋㅋㅋ용두사미일지 용두용미일지.', 4L, ?, ?)", false, now.minusMinutes(2), now
        );

        jdbcTemplate.update("INSERT INTO review("
                + "  id, user_id, content_id, is_deleted,"
                + "  review_body, star_rate, created_at, updated_at)"
                + " VALUES(4, 106, 76600, ?, '영상미가 대박이예요~', 4L, ?, ?)", false, now.minusHours(1), now
        );

        jdbcTemplate.update("INSERT INTO review("
                + "  id, user_id, content_id, is_deleted,"
                + "  review_body, star_rate, created_at, updated_at)"
                + " VALUES(5, 103, 76600, ?, '이거 안 본 사람과는 겸상하지 않겠다', 4L, ?, ?)", false, now.minusDays(3), now
        );

        jdbcTemplate.update("INSERT INTO article(" +
                "  id, user_id, content_id, image," +
                "  title, article_body, created_at)" +
                " VALUES(1, 104, 76600, 'https://image.tmdb.org/t/p/original/z56bVX93oRG6uDeMACR7cXCnAbh.jpg', "
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
                " VALUES(2, 104, 315162, 'https://image.tmdb.org/t/p/original/rKgvctIuPXyuqOzCQ16VGdnHxKx.jpg', "
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
                " VALUES(3, 104, 661374, 'https://image.tmdb.org/t/p/original/lrgmo1qluO9c2qdBduiu412fRDS.jpg', "
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
                " VALUES(4, 104, 338953, 'https://image.tmdb.org/t/p/original/uvQbXjMgC5weQepx4jLJJ60H3N0.jpg', "
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
                " VALUES(5, 104, 119051, 'https://image.tmdb.org/t/p/original/tNWCukAMubqisamYURvo5jw61As.jpg', "
                + "'<웬즈데이> 연쇄 살인 사건', "
                + "'똑똑하고 비꼬는 것에 도가 튼 웬즈데이 아담스. \n"
                + "암울함을 풍기는 그녀가 네버모어 아카데미에서 연쇄 살인 사건을 조사하기 시작합니다. \n"
                + "새 친구도 사귀고, 앙숙도 만들며.', ?)", now.minusDays(5)
        );

        jdbcTemplate.update("INSERT INTO article(" +
                "  id, user_id, content_id, image," +
                "  title, article_body, created_at)" +
                " VALUES(6, 104, 634649, 'https://image.tmdb.org/t/p/original/voddFVdjUoAtfoZZp2RUmuZILDI.jpg', "
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

        jdbcTemplate.update("INSERT INTO theme(" +
                "  id, hit, image, title)" +
                " VALUES(1, 10, "
                + "'https://nujhrcqkiwag1408085.cdn.ntruss.com/static/upload/theme_images/651/f8025d6a-9446-44e6-b6e5-d2be856c5c69.jpg', "
                + "'요즘 핫한 판타지 영화 🔥')"
        );

        jdbcTemplate.update("INSERT INTO theme(" +
                "  id, hit, image, title)" +
                " VALUES(2, 1, "
                + "'https://nujhrcqkiwag1408085.cdn.ntruss.com/static/upload/theme_images/433/172cbbfd-5fa5-4bda-9136-be5513dae0e7.jpeg', "
                + "'밥 먹으면서 보기 좋은 예능/드라마 10선 🍽️')"
        );

        jdbcTemplate.update("INSERT INTO theme(" +
                "  id, hit, image, title)" +
                " VALUES(3, 8, "
                + "'https://nujhrcqkiwag1408085.cdn.ntruss.com/static/upload/theme_images/644/8be06fe0-5a24-440c-9771-0524a96136d1.jpg', "
                + "'놓치면 후회하는 2월 종료 예정작 👀')"
        );

        jdbcTemplate.update("INSERT INTO theme(" +
                "  id, hit, image, title)" +
                " VALUES(4, 30, "
                + "'https://nujhrcqkiwag1408085.cdn.ntruss.com/static/upload/theme_images/641/434de4bc-176e-412f-8083-24a8b52cd3af.jpg', "
                + "'골든 글로브 수상작 💯')"
        );

        jdbcTemplate.update("INSERT INTO theme(" +
                "  id, hit, image, title)" +
                " VALUES(5, 15, "
                + "'https://nujhrcqkiwag1408085.cdn.ntruss.com/static/upload/theme_images/283/d4a60897-aead-46ee-9ae7-8af2ee0e27bf.jpg', "
                + "'눈 내리면 이 영화가 생각나요 ❄️')"
        );

        jdbcTemplate.update("INSERT INTO theme(" +
                "  id, hit, image, title)" +
                " VALUES(6, 14, "
                + "'https://nujhrcqkiwag1408085.cdn.ntruss.com/static/upload/theme_images/498/9388a7cc-b101-449f-b6f1-41de91cb4131.jpg', "
                + "'애니메이션 TV 프로그램️ 🧸')"
        );

        jdbcTemplate.update("INSERT INTO theme(" +
                "  id, hit, image, title)" +
                " VALUES(7, 3, 'https://nujhrcqkiwag1408085.cdn.ntruss.com/static/upload/theme_images/230/ab3a0ed8-a1fd-49cd-b739-51c50d1880f6.jpg', "
                + "'제 95회 미국 아카데미 시상식 후보작')"
        );

        jdbcTemplate.update("INSERT INTO theme(" +
                "  id, hit, image, title)" +
                " VALUES(8, 2, "
                + "'https://nujhrcqkiwag1408085.cdn.ntruss.com/static/upload/theme_images/588/41e17257-ca96-42eb-b3d0-859b93af16b2.jpeg', "
                + "'꼭 봐야할 미드 TOP 5️')"
        );

        jdbcTemplate.update("INSERT INTO theme(" +
                "  id, hit, image, title)" +
                " VALUES(9, 3, "
                + "'https://nujhrcqkiwag1408085.cdn.ntruss.com/static/upload/theme_images/521/a08d0e18-ee5f-4868-8032-2673effa0db4.jpeg', "
                + "'꼭 봐야할 추리/스릴러 모음️')"
        );

        jdbcTemplate.update("INSERT INTO theme(" +
                "  id, hit, image, title)" +
                " VALUES(10, 3, "
                + "'https://nujhrcqkiwag1408085.cdn.ntruss.com/static/upload/theme_images/226/8fc7bffc-4019-4e77-a264-331df8182e04.jpg', "
                + "'제 58회 백상예술대상 후보작️')"
        );

        jdbcTemplate.update("INSERT INTO post(" +
                "  id, user_id, title, post_body, hit, image, is_deleted, created_at, updated_at)" +
                " VALUES(1, 102, '\"아바타 물의 길\", 타이타닉 제치고 해외시장 역대 흥행 3위', "
                + "'\"아바타 물의 길\"이 해외시장 (미북미 제외) 에서 누적 흥행치 15억 3800만 달러로 타이타닉을 제치고 역대 해회시장 흥행 3위 기록했네요. \n"
                + "여전히 1위는 아바타가 21억 달러이고 2위는 어밴저스 엔드게임이 19억 달러라네요.️', "
                + "50, 'https://pbs.twimg.com/media/FnpyhSYXoAAHUg4?format=jpg&name=small', ?, ?, ?)", false, now, now
        );

        jdbcTemplate.update("INSERT INTO post(" +
                "  id, user_id, title, post_body, hit, image, is_deleted, created_at, updated_at)" +
                " VALUES(2, 103, 'CGV 아트하우스 2023 아카데미 기획전!', "
                + "'CGV는 오는 11일부터 다음 달 21일까지 제95회 미국 아카데미 시상식 후보에 오른 영화 17편을 상영하는 \"2023 아카데미 기획전\"을 연다고 6일 밝혔다.', "
                + "45, 'https://file.kinolights.com/l/post_detail/202302/06/286bf71b-9017-485c-8b3c-d80ac6185c34.webp'"
                + ", ?, ? ,?)", false, now.minusDays(1), now
        );

        jdbcTemplate.update("INSERT INTO post(" +
                "  id, user_id, title, post_body, hit, image, is_deleted, created_at, updated_at)" +
                " VALUES(3, 104, '바빌론 보고 왔습니다 ｡° ૮₍°´ᯅ`°₎ა °｡️', "
                + "'아직까지 여운이 안가셔요.. 계속해서 앨범 반복재생 중입니다 😭', "
                + "41, 'https://file.kinolights.com/l/post_detail/202302/05/1201be06-152c-44ce-a2ae-a4278f13be71.webp', "
                + "?, ?, ?)", false, now.minusDays(2), now
        );

        jdbcTemplate.update("INSERT INTO post(" +
                "  id, user_id, title, hit, image, is_deleted, created_at, updated_at)" +
                " VALUES(4, 105, '앤트맨3 외신 첫반응, \"창의적이고 훌륭한 액션 가득, 쿠키영상 2개도 좋다\"', "
                + "60, 'https://file.kinolights.com/l/post_detail/202302/07/7090dbe1-e611-4d7b-bd2c-959683da3df7.webp', "
                + "?, ?, ?)", false, now.minusMinutes(1), now
        );

        jdbcTemplate.update("INSERT INTO post(" +
                "  id, user_id, title, hit, image, is_deleted, created_at, updated_at)" +
                " VALUES(5, 106, '길복순 베를린 공식 스틸️', "
                + "41, 'https://file.kinolights.com/l/post_detail/202302/07/157ed8c8-8754-4468-b0df-48986f4ee070.webp', "
                + "?, ?, ?)", false, now.minusSeconds(5), now
        );

        jdbcTemplate.update("INSERT INTO post(" +
                "  id, user_id, title, post_body, hit, image, is_deleted, created_at, updated_at)" +
                " VALUES(6, 104, '타이타닉 용산 아이맥스관 상영 스펙 정보.jpg', '4k가 아닌게 좀 아쉽네요', "
                + "35, 'https://file.kinolights.com/l/post_detail/202302/07/233307a4-62f8-4a95-bf06-86cd80e94c3c.webp', "
                + "?, ?, ?)", false, now.minusMinutes(10), now
        );

        jdbcTemplate.update("INSERT INTO post(" +
                "  id, user_id, title, hit, image, is_deleted, created_at, updated_at)" +
                " VALUES(7, 102, '이병헌, 박서준 \"콘크리트 유토피아\" 해외 공식 포스터', "
                + "2, 'https://file.kinolights.com/l/post_detail/202302/07/70fb0a36-d411-4411-ae75-b31ee8195b07.webp', "
                + "?, ?, ?)", false, now.minusSeconds(10), now
        );

        jdbcTemplate.update("INSERT INTO post(" +
                "  id, user_id, title, hit, image, is_deleted, created_at, updated_at)" +
                " VALUES(8, 105, '\"화이트 리버\" 로테르담 영화제 초청, 호평 릴레이', "
                + "4, 'https://file.kinolights.com/l/post_detail/202302/07/8cd93270-8296-4604-93ec-bd6dff4f1acb.webp', "
                + "?, ?, ?)", false, now.minusHours(1), now
        );

        jdbcTemplate.update("INSERT INTO post(" +
                "  id, user_id, title, post_body, hit, image, is_deleted, created_at, updated_at)" +
                " VALUES(9, 103, '점심 메뉴 추천좀', '추천해주시면 3대가 행복함', "
                + "2, 'https://file.kinolights.com/l/post_detail/202302/07/8cd93270-8296-4604-93ec-bd6dff4f1acb.webp', "
                + "?, ?, ?)", false, now.minusHours(2), now
        );

        jdbcTemplate.update("INSERT INTO post(" +
                "  id, user_id, title, post_body, hit, image, is_deleted, created_at, updated_at)" +
                " VALUES(10, 106, '존윅4, 4월 국내 개봉 확정', '영화 \"존 윅 4\"(감독 체드 스타헬스키)가 4월 국내 개봉을 확정했다.', "
                + "5, 'https://file.kinolights.com/l/post_detail/202302/07/0f9226f4-c38e-4838-a5f2-e191658350a0.webp', "
                + "?, ?, ?)", false, now.minusDays(2), now
        );

        jdbcTemplate.update("INSERT INTO post(" +
                "  id, user_id, title, post_body, hit, is_deleted, created_at, updated_at)" +
                " VALUES(11, 105, '‼️ 뭐 볼지 랜덤하게 추천해 드립니다! ‼️', "
                + "'요즘 넷플 들어가도 홈에서 수많은 작품들 보기만 하고\n"
                + "고르기 귀찮아서 끄신 적 없으신가요? \n"
                + "좋은 친구들이 있다면 고민할게 없겠지만 전 아싸 개발자라 추천해 줄 친구도 없어 랜덤하게 뽑는 페이지를 만들어 봤습니다. \n"
                + "저 같은 아싸 회원님들에게 도움되기를 바라며 🙏🏻 댓글 남겨주시면 링크 드립니다~', "
                + "40, ?, ?, ?)", false, now.minusDays(2), now
        );

        jdbcTemplate.update("INSERT INTO likes(" +
                "  id, post_id, user_id)" +
                " VALUES(1, 10, 103)"
        );

        jdbcTemplate.update("INSERT INTO likes(" +
                "  id, post_id, user_id)" +
                " VALUES(2, 10, 102)"
        );

        jdbcTemplate.update("INSERT INTO likes(" +
                "  id, post_id, user_id)" +
                " VALUES(3, 10, 104)"
        );

        jdbcTemplate.update("INSERT INTO likes(" +
                "  id, post_id, user_id)" +
                " VALUES(4, 10, 105)"
        );

        jdbcTemplate.update("INSERT INTO likes(" +
                "  id, post_id, user_id)" +
                " VALUES(5, 11, 106)"
        );

        jdbcTemplate.update("INSERT INTO likes(" +
                "  id, post_id, user_id)" +
                " VALUES(6, 11, 102)"
        );

        jdbcTemplate.update("INSERT INTO likes(" +
                "  id, post_id, user_id)" +
                " VALUES(7, 11, 103)"
        );

        jdbcTemplate.update("INSERT INTO likes(" +
                "  id, post_id, user_id)" +
                " VALUES(8, 11, 104)"
        );

        jdbcTemplate.update("INSERT INTO post_comment(" +
                "  id, user_id, post_id, post_comment_body, is_deleted, created_at, updated_at)" +
                " VALUES(1, 102, 11, '본문에 링크로 남기시면 되잖아요!', ?, ?, ?)", false, now, now
        );

        jdbcTemplate.update("INSERT INTO post_comment(" +
                "  id, user_id, post_id, post_comment_body, is_deleted, created_at, updated_at)" +
                " VALUES(2, 103, 11, '와 대단하시네요', ?, ?, ?)", false, now.minusHours(5), now
        );

        jdbcTemplate.update("INSERT INTO post_comment(" +
                "  id, user_id, post_id, post_comment_body, is_deleted, created_at, updated_at)" +
                " VALUES(3, 106, 11, '저 링크 주세요!', ?, ?, ?)", false, now.minusHours(5), now
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
        jdbcTemplate.execute("DELETE FROM content");

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
                                .tmdbId(Long.parseLong(data.get("id").toString().replaceAll(match, "")))
                                .tmdbGenreId(data.get("genre_ids").toString().replaceAll(match, ""))
                                .imageUrl(data.get("poster_path") == null
                                        ? "" :
                                        imageUrl + data.get("poster_path").toString().replaceAll(match, ""))
                                .korTitle(data.get("title").toString().replaceAll(match, ""))
                                .engTitle(data.get("original_title").toString().replaceAll(match, ""))
                                .releaseDate(data.get("release_date") == null
                                        ? 2022
                                        : Integer.parseInt(data.get("release_date").toString().substring(0, 4).replaceAll(match, "")))
                                .popularity(Double.parseDouble(data.get("popularity").toString().replaceAll(match, "")))
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
                                .tmdbId(Long.parseLong(data.get("id").toString().replaceAll(match, "")))
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
                                .popularity(Double.parseDouble(data.get("popularity").toString().replaceAll(match, "")))
                                .description(data.get("overview").toString().replaceAll(match, ""))
                                .createdAt(dateTime)
                                .build()
                );
            }
        }

        return "TvDrama completely saved!";
    }

    @GetMapping("/setup-content-data")
    public String setupPlatformAndType() throws IOException {
        // 플랫폼 설정
        jdbcTemplate.update("UPDATE content SET platform='netflix, wavve, watcha, disneyplus, tv.apple' WHERE id <= 25");
        jdbcTemplate.update("UPDATE content SET platform='tv.apple, wavve, tving' WHERE id <= 50");
        jdbcTemplate.update("UPDATE content SET platform='wavve, tving, tv.apple' WHERE id > 50 AND id <= 75");
        jdbcTemplate.update("UPDATE content SET platform='wavve, watcha' WHERE id > 76 AND id <= 100");
        jdbcTemplate.update("UPDATE content SET platform='tv.apple, disneyplus' WHERE id > 100 AND id <= 125");
        jdbcTemplate.update("UPDATE content SET platform='netflix, wavve, watcha, disneyplus, tv.apple' WHERE id > 125 AND id <= 150");
        jdbcTemplate.update("UPDATE content SET platform='wavve, tving, tv.apple' WHERE id > 150 AND id <= 175");
        jdbcTemplate.update("UPDATE content SET platform='wavve, tving' WHERE id > 175 AND id <= 200");
        jdbcTemplate.update("UPDATE content SET platform='netflix, wavve, watcha, disneyplus' WHERE id > 200 AND id <= 225");

        // 플랫폼 설정
        jdbcTemplate.update("UPDATE content SET platform='wavve, tving, tv.apple' WHERE id > 225 AND id <= 250");
        jdbcTemplate.update("UPDATE content SET platform='wavve, watcha' WHERE id > 250 AND id <= 275");
        jdbcTemplate.update("UPDATE content SET platform='tv.apple, disneyplus' WHERE id > 275 AND id <= 300");
        jdbcTemplate.update("UPDATE content SET platform='netflix, wavve, watcha, disneyplus, tv.apple' WHERE id > 300 AND id <= 325");
        jdbcTemplate.update("UPDATE content SET platform='wavve, tving, tv.apple' WHERE id > 325 AND id <= 350");
        jdbcTemplate.update("UPDATE content SET platform='wavve, tving' WHERE id > 350 AND id <= 375");
        jdbcTemplate.update("UPDATE content SET platform='netflix, wavve, watcha, disneyplus' WHERE id > 375");

        // 타입 설정
        jdbcTemplate.update("UPDATE content SET type='movie' WHERE id <= 200");
        jdbcTemplate.update("UPDATE content SET type='drama' WHERE id > 200");

        // 테마 설정
        jdbcTemplate.update("UPDATE content SET theme_id=1 WHERE tmdb_id = 315162");
        jdbcTemplate.update("UPDATE content SET theme_id=1 WHERE tmdb_id = 76600");
        jdbcTemplate.update("UPDATE content SET theme_id=1 WHERE tmdb_id = 736526");
        jdbcTemplate.update("UPDATE content SET theme_id=1 WHERE tmdb_id = 634649");
        jdbcTemplate.update("UPDATE content SET theme_id=1 WHERE tmdb_id = 299536");
        jdbcTemplate.update("UPDATE content SET theme_id=1 WHERE tmdb_id = 774752");
        jdbcTemplate.update("UPDATE content SET theme_id=1 WHERE tmdb_id = 671");
        jdbcTemplate.update("UPDATE content SET theme_id=1 WHERE tmdb_id = 338953");
        jdbcTemplate.update("UPDATE content SET theme_id=1 WHERE tmdb_id = 616037");

        jdbcTemplate.update("UPDATE content SET theme_id=2 WHERE tmdb_id = 135157");
        jdbcTemplate.update("UPDATE content SET theme_id=2 WHERE tmdb_id = 1399");
        jdbcTemplate.update("UPDATE content SET theme_id=2 WHERE tmdb_id = 112836");
        jdbcTemplate.update("UPDATE content SET theme_id=2 WHERE tmdb_id = 33238");
        jdbcTemplate.update("UPDATE content SET theme_id=2 WHERE tmdb_id = 4057");
        jdbcTemplate.update("UPDATE content SET theme_id=2 WHERE tmdb_id = 1421");
        jdbcTemplate.update("UPDATE content SET theme_id=2 WHERE tmdb_id = 112888");
        jdbcTemplate.update("UPDATE content SET theme_id=2 WHERE tmdb_id = 57243");
        jdbcTemplate.update("UPDATE content SET theme_id=2 WHERE tmdb_id = 2316");
        jdbcTemplate.update("UPDATE content SET theme_id=2 WHERE tmdb_id = 63333");

        jdbcTemplate.update("UPDATE content SET theme_id=3 WHERE tmdb_id = 5920");
        jdbcTemplate.update("UPDATE content SET theme_id=3 WHERE tmdb_id = 99966");

        jdbcTemplate.update("UPDATE content SET theme_id=4 WHERE tmdb_id = 555604");
        jdbcTemplate.update("UPDATE content SET theme_id=4 WHERE tmdb_id = 505642");
        jdbcTemplate.update("UPDATE content SET theme_id=4 WHERE tmdb_id = 94997");
        jdbcTemplate.update("UPDATE content SET theme_id=4 WHERE tmdb_id = 85552");
        jdbcTemplate.update("UPDATE content SET theme_id=4 WHERE tmdb_id = 73586");
        jdbcTemplate.update("UPDATE content SET theme_id=4 WHERE tmdb_id = 113988");

        jdbcTemplate.update("UPDATE content SET theme_id=5 WHERE tmdb_id = 411");
        jdbcTemplate.update("UPDATE content SET theme_id=5 WHERE tmdb_id = 899112");
        jdbcTemplate.update("UPDATE content SET theme_id=5 WHERE tmdb_id = 1001865");
        jdbcTemplate.update("UPDATE content SET theme_id=5 WHERE tmdb_id = 1045944");

        jdbcTemplate.update("UPDATE content SET theme_id=6 WHERE tmdb_id = 95479");
        jdbcTemplate.update("UPDATE content SET theme_id=6 WHERE tmdb_id = 114410");
        jdbcTemplate.update("UPDATE content SET theme_id=6 WHERE tmdb_id = 46260");
        jdbcTemplate.update("UPDATE content SET theme_id=6 WHERE tmdb_id = 30984");
        jdbcTemplate.update("UPDATE content SET theme_id=6 WHERE tmdb_id = 456");
        jdbcTemplate.update("UPDATE content SET theme_id=6 WHERE tmdb_id = 60572");
        jdbcTemplate.update("UPDATE content SET theme_id=6 WHERE tmdb_id = 46298");
        jdbcTemplate.update("UPDATE content SET theme_id=6 WHERE tmdb_id = 843241");

        jdbcTemplate.update("UPDATE content SET theme_id=7 WHERE tmdb_id = 800815");
        jdbcTemplate.update("UPDATE content SET theme_id=7 WHERE tmdb_id = 873126");
        jdbcTemplate.update("UPDATE content SET theme_id=7 WHERE tmdb_id = 1041513");
        jdbcTemplate.update("UPDATE content SET theme_id=7 WHERE tmdb_id = 661374");
        jdbcTemplate.update("UPDATE content SET theme_id=7 WHERE tmdb_id = 829280");

        jdbcTemplate.update("UPDATE content SET theme_id=8 WHERE tmdb_id = 66732");
        jdbcTemplate.update("UPDATE content SET theme_id=8 WHERE tmdb_id = 1399");
        jdbcTemplate.update("UPDATE content SET theme_id=8 WHERE tmdb_id = 1396");
        jdbcTemplate.update("UPDATE content SET theme_id=8 WHERE tmdb_id = 63174");
        jdbcTemplate.update("UPDATE content SET theme_id=8 WHERE tmdb_id = 106541");
        jdbcTemplate.update("UPDATE content SET theme_id=8 WHERE tmdb_id = 106541");

        jdbcTemplate.update("UPDATE content SET theme_id=9 WHERE tmdb_id = 661374");
        jdbcTemplate.update("UPDATE content SET theme_id=9 WHERE tmdb_id = 4614");
        jdbcTemplate.update("UPDATE content SET theme_id=9 WHERE tmdb_id = 99966");
        jdbcTemplate.update("UPDATE content SET theme_id=9 WHERE tmdb_id = 1431");
        jdbcTemplate.update("UPDATE content SET theme_id=9 WHERE tmdb_id = 2288");

        jdbcTemplate.update("UPDATE content SET theme_id=10 WHERE tmdb_id = 93405");
        jdbcTemplate.update("UPDATE content SET theme_id=10 WHERE tmdb_id = 112888");
        jdbcTemplate.update("UPDATE content SET theme_id=10 WHERE tmdb_id = 135157");
        jdbcTemplate.update("UPDATE content SET theme_id=10 WHERE tmdb_id = 112836");

        return "Platform completely saved!";
    }
}
