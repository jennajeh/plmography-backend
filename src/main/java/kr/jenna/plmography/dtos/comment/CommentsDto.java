package kr.jenna.plmography.dtos.Comment;

import java.util.List;

public class CommentsDto {
    private List<CommentDto> comments;

    public CommentsDto(List<CommentDto> comments) {
        this.comments = comments;
    }

    public List<CommentDto> getComments() {
        return comments;
    }
}
