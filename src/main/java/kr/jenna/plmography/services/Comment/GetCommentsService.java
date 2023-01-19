package kr.jenna.plmography.services.Comment;

import kr.jenna.plmography.dtos.Comment.CommentDto;
import kr.jenna.plmography.dtos.Comment.CommentsDto;
import kr.jenna.plmography.dtos.User.WriterDto;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetCommentsService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public GetCommentsService(CommentRepository commentRepository,
                              UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public CommentsDto comments(Long userId) {
        List<Comment> comments = commentRepository.findAll();

        List<CommentDto> commentDtos = comments.stream()
                .map(comment -> {
                    User user = userRepository.findById(comment.getUserId().getValue())
                            .orElseThrow(() -> new UserNotFound(comment.getUserId().getValue()));

                    return new CommentDto(
                            comment.getId(),
                            new WriterDto(
                                    user.getId(),
                                    user.getNickname().getValue(),
                                    user.getProfileImage().getValue()),
                            comment.getPostId().getValue(),
                            comment.getCommentBody().getValue(),
                            comment.isDeleted(),
                            comment.getCreatedAt(),
                            comment.getUpdatedAt());
                }).collect(Collectors.toList());

        return new CommentsDto(commentDtos);
    }
}
