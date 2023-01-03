package kr.jenna.plmography.dtos;

public class UserRegistrationDto {
    private String email;
    private String password;
    private String passwordCheck;
    private String nickname;
    private String gender;
    private Integer birthYear;

    public UserRegistrationDto() {
    }

    public UserRegistrationDto(String email, String password,
                               String passwordCheck, String nickname, String gender, Integer birthYear) {
        this.email = email;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.nickname = nickname;
        this.gender = gender;
        this.birthYear = birthYear;
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

    public String getGender() {
        return gender;
    }

    public Integer getBirthYear() {
        return birthYear;
    }
}
