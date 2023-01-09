package kr.jenna.plmography.services.Comment;

import kr.jenna.plmography.dtos.Comment.CommentDto;
import kr.jenna.plmography.exceptions.CommentNotFound;
import kr.jenna.plmography.exceptions.InvalidUser;
import kr.jenna.plmography.exceptions.UnmatchedCommetId;
import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.models.VO.CommentId;
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

    public Comment update(Long userId, CommentId commentId, CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentId.getValue())
                .orElseThrow(() -> new CommentNotFound());

        if (!comment.isWriter(userId)) {
            throw new InvalidUser();
        }

        if (!commentId.equals(new CommentId(commentDto.getId()))) {
            throw new UnmatchedCommetId();
        }

        comment.modify(commentDto);

        return comment;
    }
}
