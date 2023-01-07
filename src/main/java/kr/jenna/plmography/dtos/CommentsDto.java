package kr.jenna.plmography.dtos;

import java.util.List;

public class CommentsDto {
    private List<CommentDto> comments;
    private PagesDto pages;

    public CommentsDto(List<CommentDto> comments, PagesDto pages) {
        this.comments = comments;
        this.pages = pages;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public PagesDto getPages() {
        return pages;
    }
}
