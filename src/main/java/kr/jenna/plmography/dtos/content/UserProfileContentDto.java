package kr.jenna.plmography.dtos.content;

public class UserProfileContentDto {
    private Long userId;
    private Long contentId;
    private String imageUrl;
    private String korTitle;

    public UserProfileContentDto(Long userId,
                                 Long contentId,
                                 String imageUrl,
                                 String korTitle) {
        this.userId = userId;
        this.contentId = contentId;
        this.imageUrl = imageUrl;
        this.korTitle = korTitle;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getContentId() {
        return contentId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getKorTitle() {
        return korTitle;
    }
}
