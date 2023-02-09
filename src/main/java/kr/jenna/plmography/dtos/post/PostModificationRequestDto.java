package kr.jenna.plmography.dtos.post;

import com.sun.istack.Nullable;

public class PostModificationRequestDto {
    private String title;
    private String postBody;

    @Nullable
    private String image;

    public PostModificationRequestDto() {
    }

    public PostModificationRequestDto(String title, String postBody, String image) {
        this.title = title;
        this.postBody = postBody;
        this.image = image;
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
