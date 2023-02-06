package kr.jenna.plmography.dtos.post;

import java.util.List;

public class MyPostsDto {
    private List<PostDto> myPosts;

    public MyPostsDto(List<PostDto> myPosts) {
        this.myPosts = myPosts;
    }

    public List<PostDto> getMyPosts() {
        return myPosts;
    }
}
