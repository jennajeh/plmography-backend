package kr.jenna.plmography.dtos.oauth;

public class SocialLoginResultDto {
    private String accessToken;
    private String refreshToken;
    private String nickname;
    private String email;

    public SocialLoginResultDto() {
    }

    public SocialLoginResultDto(String accessToken,
                                String refreshToken,
                                String nickname,
                                String email) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.nickname = nickname;
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }
}
