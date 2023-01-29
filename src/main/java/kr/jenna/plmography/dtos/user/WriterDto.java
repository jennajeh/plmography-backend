package kr.jenna.plmography.dtos.user;

public class WriterDto {
    private Long id;
    private String nickname;
    private String profileImage;

    public WriterDto() {
    }

    public WriterDto(Long id, String nickname, String profileImage) {
        this.id = id;
        this.nickname = nickname;
        this.profileImage = profileImage;
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
