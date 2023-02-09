package kr.jenna.plmography.dtos.postComment;

import java.util.List;

public class MyPostCommentsDto {
    private List<PostCommentDto> myPostComments;

    public MyPostCommentsDto() {
    }

    public MyPostCommentsDto(List<PostCommentDto> myPostComments) {
        this.myPostComments = myPostComments;
    }

    public List<PostCommentDto> getMyPostComments() {
        return myPostComments;
    }
}
