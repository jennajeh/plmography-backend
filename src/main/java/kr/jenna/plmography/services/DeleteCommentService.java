package kr.jenna.plmography.services;

import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.repositories.RecommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeleteCommentService {
    private final CommentRepository commentRepository;
    private final RecommentRepository recommentRepository;

    public DeleteCommentService(CommentRepository commentRepository,
                                RecommentRepository recommentRepository) {
        this.commentRepository = commentRepository;
        this.recommentRepository = recommentRepository;
    }

    public void delete(Long commentId) {
        Comment comment = commentRepository.getReferenceById(commentId);

        checkExistedRecomment(commentId, comment);

        if (!recommentRepository.existsByCommentId(commentId)) {
            commentRepository.delete(comment);
        }
    }

    private void checkExistedRecomment(Long commentId, Comment comment) {
        if (recommentRepository.existsByCommentId(commentId)) {
            comment.delete();
        }
    }
}
