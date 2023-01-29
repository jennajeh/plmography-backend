package kr.jenna.plmography.services.Comment;

import kr.jenna.plmography.exceptions.CommentNotFound;
import kr.jenna.plmography.exceptions.InvalidUser;
import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.models.VO.CommentBody;
import kr.jenna.plmography.repositories.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatchCommentService {
    private final CommentRepository commentRepository;

    public PatchCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment modify(Long userId, Long commentId, CommentBody commentBody) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFound());

        if (!comment.isWriter(userId)) {
            throw new InvalidUser();
        }

        comment.modify(commentBody);

        return comment;
    }
}
