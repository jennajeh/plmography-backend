package kr.jenna.plmography.dtos.postComment;

import java.util.List;

public class SelectedPostCommentDto {
    public List<Long> postCommentIds;

    public SelectedPostCommentDto() {
    }

    public SelectedPostCommentDto(List<Long> postCommentIds) {
        this.postCommentIds = postCommentIds;
    }

    public List<Long> getPostCommentIds() {
        return postCommentIds;
    }
}
