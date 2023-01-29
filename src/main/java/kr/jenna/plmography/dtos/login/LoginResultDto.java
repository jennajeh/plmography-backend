package kr.jenna.plmography.dtos.login;

public class LoginResultDto {
    String accessToken;

    public LoginResultDto() {
    }

    public LoginResultDto(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
