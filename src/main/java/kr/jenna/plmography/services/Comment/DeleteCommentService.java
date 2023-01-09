package kr.jenna.plmography.services.Comment;

import kr.jenna.plmography.exceptions.CommentNotFound;
import kr.jenna.plmography.exceptions.InvalidUser;
import kr.jenna.plmography.exceptions.UnmodifiableRecomment;
import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.models.Recomment;
import kr.jenna.plmography.models.VO.CommentId;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.repositories.RecommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public void delete(Long userId, CommentId commentId) {
        Comment comment = commentRepository.findById(commentId.getValue())
                .orElseThrow(() -> new CommentNotFound());

        List<Recomment> recomments = recommentRepository.findAllByCommentId(commentId.getValue());

        if (!comment.isWriter(userId)) {
            throw new InvalidUser();
        }

        if (recomments.size() > 0) {
            throw new UnmodifiableRecomment();
        }

        commentRepository.delete(comment);
    }
}
