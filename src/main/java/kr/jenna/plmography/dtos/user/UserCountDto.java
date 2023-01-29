package kr.jenna.plmography.dtos.User;

public class UserCountDto {
    private Integer countEmail;
    private Integer countNickname;

    public UserCountDto() {
    }

    public UserCountDto(Integer countEmail, Integer countNickname) {
        this.countEmail = countEmail;
        this.countNickname = countNickname;
    }

    public Integer getCountEmail() {
        return countEmail;
    }

    public Integer getCountNickname() {
        return countNickname;
    }
}
