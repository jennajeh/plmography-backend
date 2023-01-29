package kr.jenna.plmography.services.comment;

import kr.jenna.plmography.dtos.comment.CommentRegistrationDto;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.CommentBody;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.models.vo.UserId;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateCommentService {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CreateCommentService(UserRepository userRepository,
                                CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public Comment create(Long userId, CommentRegistrationDto commentRegistrationDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound(userId));

        Comment comment = new Comment(
                new UserId(commentRegistrationDto.getUserId()),
                new PostId(commentRegistrationDto.getPostId()),
                new CommentBody(commentRegistrationDto.getCommentBody()));

        commentRepository.save(comment);

        return comment;
    }
}
