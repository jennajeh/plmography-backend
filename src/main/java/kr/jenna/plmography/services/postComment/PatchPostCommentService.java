package kr.jenna.plmography.services.postComment;

import kr.jenna.plmography.dtos.postComment.PostCommentModificationRequestDto;
import kr.jenna.plmography.exceptions.InvalidUser;
import kr.jenna.plmography.models.PostComment;
import kr.jenna.plmography.models.vo.PostCommentBody;
import kr.jenna.plmography.repositories.PostCommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatchPostCommentService {
    private final PostCommentRepository postCommentRepository;

    public PatchPostCommentService(PostCommentRepository postCommentRepository) {
        this.postCommentRepository = postCommentRepository;
    }

    public PostComment modify(Long userId,
                              PostCommentModificationRequestDto postCommentModificationRequestDto) {
        Long commentId = postCommentModificationRequestDto.getId();
        PostCommentBody postCommentBody = new PostCommentBody(
                postCommentModificationRequestDto.getPostCommentBody());

        PostComment postComment = postCommentRepository.getReferenceById(commentId);

        if (!postComment.isWriter(userId)) {
            throw new InvalidUser();
        }

        postComment.modify(postCommentBody);

        return postComment;
    }
}
