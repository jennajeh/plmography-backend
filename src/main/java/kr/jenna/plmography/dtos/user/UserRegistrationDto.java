package kr.jenna.plmography.dtos.user;

public class UserRegistrationDto {
    private String email;
    private String nickname;
    private String password;
    private String passwordCheck;

    public UserRegistrationDto() {
    }

    public UserRegistrationDto(String email,
                               String nickname,
                               String password,
                               String passwordCheck) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.passwordCheck = passwordCheck;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public String getNickname() {
        return nickname;
    }
}
