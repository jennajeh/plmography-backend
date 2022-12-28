package kr.jenna.plmography.backdoor;

import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.models.Performer;
import kr.jenna.plmography.repositories.ContentRepository;
import kr.jenna.plmography.repositories.PerformerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BackdoorController {
    private final RestTemplate restTemplate;
    private final ContentRepository contentRepository;
    private final PerformerRepository performerRepository;

    @Value("${tmdb.api-key}")
    private String apiKey;

    LocalDateTime dateTime = LocalDateTime.now();

    public BackdoorController(RestTemplate restTemplate,
                              ContentRepository contentRepository,
                              PerformerRepository performerRepository) {
        this.restTemplate = restTemplate;
        this.contentRepository = contentRepository;
        this.performerRepository = performerRepository;
    }

    @GetMapping("/setupContents")
    public String setupContents() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);

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
                                .tmdbContentId(data.get("id").toString().replaceAll(match, ""))
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

        return "Contents completely saved!";
    }

    @GetMapping("/setupPerformer")
    public String setupPerformer() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);

        List<Content> temp = contentRepository.findAll();
//        List<Integer> tt = new ArrayList<>();

        for (int i = 0; i < temp.size(); i += 1) {

            String url = "/movie/" + temp.get(i).getTmdbContentId()
                    + "/credits?api_key=" + apiKey;

            ResponseEntity<Map> resultMap = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

            LinkedHashMap list = (LinkedHashMap) resultMap.getBody();
//            List<Integer> tt = new ArrayList<>();
//            Integer id = (Integer) list.get("id");
//            tt.add(id);
            ArrayList<Map> cast = (ArrayList<Map>) list.get("cast");
            ArrayList<Map> crew = (ArrayList<Map>) list.get("crew");

            for (int j = 0; j < list.size(); j += 1) {
                String match = "[\"]";

                performerRepository.save(
                        Performer.builder()
//                                .tmdbContentId(resultMap.getBody().get("id").toString().replaceAll(match, ""))
                                .tmdbContentId(list.get("id").toString().replaceAll(match, ""))
                                .originalName(list.get("cast").toString().replaceAll(match, ""))
                                .characterName(list.get("crew").toString().replaceAll(match, ""))
                                .build()
                );
            }

//            for (Map data : cast) {
//                String imageUrl = "https://image.tmdb.org/t/p/original";
//                String match = "[\"]";
//
//                performerRepository.save(
//                        Performer.builder()
//                                .originalName(data.get("original_name").toString().replaceAll(match, ""))
//                                .characterName(data.get("character").toString().replaceAll(match, ""))
//                                .department(data.get("known_for_department").toString().replaceAll(match, ""))
////                                .profileImageUrl(imageUrl + data.get("profile_path") == null
////                                        ? ""
////                                        : imageUrl + data.get("profile_path").toString().replaceAll(match, ""))
//                                .build()
//                );
//            }
//
//            for (Map data : crew) {
//                String imageUrl = "https://image.tmdb.org/t/p/original";
//                String match = "[\"]";
//
//                performerRepository.save(
//                        Performer.builder()
//                                .originalName(data.get("original_name").toString().replaceAll(match, ""))
//                                .department(data.get("known_for_department").toString().replaceAll(match, ""))
//                                .build()
//                );
//            }
        }

        return "Performer completely saved!";
    }
}
