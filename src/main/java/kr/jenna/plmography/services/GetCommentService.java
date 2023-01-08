package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.CommentDto;
import kr.jenna.plmography.exceptions.CommentNotFound;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetCommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public GetCommentService(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public CommentDto detail(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFound());

        User user = userRepository.findById(comment.getUserId().getValue())
                .orElseThrow(() -> new UserNotFound(comment.getUserId().getValue()));

        return new CommentDto(
                comment.getId(),
                comment.getUserId().getValue(),
                comment.getPostId().getValue(),
                comment.getCommentBody().getValue(),
                comment.isDeleted(),
                comment.getCreatedAt());
    }
}
