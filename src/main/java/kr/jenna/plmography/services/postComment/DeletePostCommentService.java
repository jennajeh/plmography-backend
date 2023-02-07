package kr.jenna.plmography.services.postComment;

import kr.jenna.plmography.exceptions.InvalidUser;
import kr.jenna.plmography.models.PostComment;
import kr.jenna.plmography.repositories.PostCommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeletePostCommentService {
    private final PostCommentRepository postCommentRepository;

    public DeletePostCommentService(PostCommentRepository postCommentRepository) {
        this.postCommentRepository = postCommentRepository;
    }

    public void delete(Long userId, Long commentId) {
        PostComment postComment = postCommentRepository.getReferenceById(commentId);

        if (!postComment.isWriter(userId)) {
            throw new InvalidUser();
        }

        postComment.delete();
    }
}
