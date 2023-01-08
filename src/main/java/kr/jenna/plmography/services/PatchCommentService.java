package kr.jenna.plmography.services;

import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.models.CommentBody;
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

    public void update(Long commentId, String commentBody) {
        Comment comment = commentRepository.getReferenceById(commentId);

        comment.modify(new CommentBody(commentBody));
    }
}
