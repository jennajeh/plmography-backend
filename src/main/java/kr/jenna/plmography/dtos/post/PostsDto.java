package kr.jenna.plmography.dtos.post;

import kr.jenna.plmography.dtos.page.PagesDto;

import java.util.List;

public class PostsDto {
    private List<PostDto> posts;
    private PagesDto pages;

    public PostsDto() {
    }

    public PostsDto(List<PostDto> posts) {
        this.posts = posts;
    }

    public PostsDto(List<PostDto> posts, PagesDto pages) {
        this.posts = posts;
        this.pages = pages;
    }

    public List<PostDto> getPosts() {
        return posts;
    }

    public PagesDto getPages() {
        return pages;
    }
}
