package kr.jenna.plmography.dtos.post;

public class PostModificationRequestDto {
    private Long id;
    private String title;
    private String postBody;
    private String image;

    public PostModificationRequestDto() {
    }

    public PostModificationRequestDto(Long id, String title, String postBody, String image) {
        this.id = id;
        this.title = title;
        this.postBody = postBody;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPostBody() {
        return postBody;
    }

    public String getImage() {
        return image;
    }
}
