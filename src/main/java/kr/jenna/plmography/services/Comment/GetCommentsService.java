package kr.jenna.plmography.services.Comment;

import kr.jenna.plmography.dtos.Comment.CommentDto;
import kr.jenna.plmography.dtos.Comment.CommentsDto;
import kr.jenna.plmography.dtos.Page.PagesDto;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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


    public CommentsDto comments(Long userId, Integer page, Integer size) {
        Sort sort = Sort.by("createdAt").descending();

        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<Comment> comments = commentRepository.findAllByUserId(userId, pageable);

        List<CommentDto> commentDtos = comments.stream()
                .map(comment -> {
                            User user = userRepository.findById(comment.getUserId().getValue())
                                    .orElseThrow(() -> new UserNotFound(comment.getUserId().getValue()));

                            return new CommentDto(
                                    comment.getId(),
                                    comment.getUserId().getValue(),
                                    comment.getPostId().getValue(),
                                    comment.getCommentBody().getValue(),
                                    comment.isDeleted(),
                                    comment.getCreatedAt()
                            );
                        }
                ).collect(Collectors.toList());

        PagesDto pagesDto = new PagesDto(comments.getTotalPages());

        return new CommentsDto(commentDtos, pagesDto);
    }
}
