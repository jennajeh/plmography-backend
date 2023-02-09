package kr.jenna.plmography.dtos.postComment;

import kr.jenna.plmography.dtos.page.PagesDto;

import java.util.List;

public class PostCommentsDto {
    private List<PostCommentDto> postComments;
    private PagesDto pages;

    public PostCommentsDto() {
    }

    public PostCommentsDto(List<PostCommentDto> postComments) {
        this.postComments = postComments;
    }

    public PostCommentsDto(List<PostCommentDto> postComments, PagesDto pages) {
        this.postComments = postComments;
        this.pages = pages;
    }

    public List<PostCommentDto> getPostComments() {
        return postComments;
    }

    public PagesDto getPages() {
        return pages;
    }
}
