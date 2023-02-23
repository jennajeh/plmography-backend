package kr.jenna.plmography.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import kr.jenna.plmography.dtos.oauth.SocialLoginResultDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@Transactional
public class KakaoAuthUtil {
    private final RestTemplate restTemplate;

    @Value("${kakao.api-key}")
    private String apiKey;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @Value("${kakao.client-secret}")
    private String clientSecret;

    private Map<String, String> kakaoUserInformation;

    public KakaoAuthUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        kakaoUserInformation = new LinkedHashMap<>();
    }

    public SocialLoginResultDto process(String code) {
        // 액세스 토큰 받아오기
        String accessToken = getAccessToken(code);

        // 유저 정보 디테일 받아오기
        getDetail(accessToken);

        return new SocialLoginResultDto(
                kakaoUserInformation.get("accessToken"),
                kakaoUserInformation.get("refreshToken"),
                kakaoUserInformation.get("nickname"),
                kakaoUserInformation.get("email")
        );
    }

    private String getAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // HttpBody 객체 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", apiKey);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        params.add("client_secret", clientSecret);

        // HttpHeader와 HttpBody를 하나의 객체에 담기 : restTemplate.exchange 함수에 HttpEntity 를 넣어야 함.
        // body 데이터와 headers 값을 가지고 있는 Entity
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        String url = "https://kauth.kakao.com/oauth/token";

        // 카카오에게 Http 요청하기 (POST 방식) -> response라는 변수에 응답을 받음
        String response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class).getBody();

        JsonElement element = JsonParser.parseString(response);
        String accessToken = element.getAsJsonObject().get("access_token").getAsString();
        String refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();

        kakaoUserInformation.put("accessToken", accessToken);
        kakaoUserInformation.put("refreshToken", refreshToken);

        return accessToken;
    }

    private void getDetail(String accessToken) {
        // HttpHeader
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // HttpHeader 와 HttpBody 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);

        String url = "https://kapi.kakao.com/v2/user/me";

        String response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                kakaoProfileRequest,
                String.class).getBody();

        JsonElement element = JsonParser.parseString(response);
        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
        JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
        String email = kakaoAccount.getAsJsonObject().get("email").getAsString();

        kakaoUserInformation.put("nickname", nickname);
        kakaoUserInformation.put("email", email);
    }
}
