package kr.jenna.plmography.dtos.content;

public class UserProfileContentDto {
    private Long contentId;
    private String imageUrl;
    private String korTitle;

    public UserProfileContentDto(Long contentId, String imageUrl, String korTitle) {
        this.contentId = contentId;
        this.imageUrl = imageUrl;
        this.korTitle = korTitle;
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
