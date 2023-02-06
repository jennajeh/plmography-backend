package kr.jenna.plmography.dtos.post;

public class PostModificationRequestDto {
    private Long postId;
    private String title;
    private String postBody;
    private String image;

    public PostModificationRequestDto() {
    }

    public PostModificationRequestDto(Long postId, String title, String postBody, String image) {
        this.postId = postId;
        this.title = title;
        this.postBody = postBody;
        this.image = image;
    }

    public Long getPostId() {
        return postId;
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
