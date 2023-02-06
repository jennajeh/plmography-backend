package kr.jenna.plmography.dtos.post;

import java.util.List;

public class SelectedPostsDto {
    public List<Long> postIds;

    public SelectedPostsDto() {
    }

    public SelectedPostsDto(List<Long> postIds) {
        this.postIds = postIds;
    }

    public List<Long> getPostIds() {
        return postIds;
    }

    public static SelectedPostsDto fake() {
        return new SelectedPostsDto(List.of(1L));
    }
}
