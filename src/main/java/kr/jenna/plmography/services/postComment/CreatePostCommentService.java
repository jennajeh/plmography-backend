package kr.jenna.plmography.services.postComment;

import kr.jenna.plmography.dtos.postComment.PostCommentRegistrationDto;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.PostComment;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.PostCommentBody;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.models.vo.UserId;
import kr.jenna.plmography.repositories.PostCommentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class CreatePostCommentService {
    private final UserRepository userRepository;
    private final PostCommentRepository postCommentRepository;

    public CreatePostCommentService(UserRepository userRepository,
                                    PostCommentRepository postCommentRepository) {
        this.userRepository = userRepository;
        this.postCommentRepository = postCommentRepository;
    }

    public PostComment create(Long userId, PostCommentRegistrationDto postCommentRegistrationDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound(userId));

        PostComment postComment = new PostComment(
                new UserId(user.getId()),
                new PostId(postCommentRegistrationDto.getPostId()),
                new PostCommentBody(postCommentRegistrationDto.getPostCommentBody()),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        postCommentRepository.save(postComment);

        return postComment;
    }
}
