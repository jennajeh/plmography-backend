package kr.jenna.plmography.services.postComment;

import kr.jenna.plmography.dtos.page.PagesDto;
import kr.jenna.plmography.dtos.postComment.MyPostCommentsDto;
import kr.jenna.plmography.dtos.postComment.PostCommentDto;
import kr.jenna.plmography.dtos.postComment.PostCommentsDto;
import kr.jenna.plmography.dtos.user.WriterDto;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.PostComment;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.models.vo.UserId;
import kr.jenna.plmography.repositories.PostCommentRepository;
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
public class GetPostCommentsService {
    private final PostCommentRepository postCommentRepository;
    private final UserRepository userRepository;

    public GetPostCommentsService(PostCommentRepository postCommentRepository,
                                  UserRepository userRepository) {
        this.postCommentRepository = postCommentRepository;
        this.userRepository = userRepository;
    }

    public PostCommentsDto list(Long postId, Integer page, Integer size) {
        Sort sort = Sort.by("createdAt").ascending();

        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<PostComment> postComments = postCommentRepository.findAllByPostIdAndIsDeleted(new PostId(postId), pageable);

        List<PostCommentDto> postCommentDtos = postComments.stream()
                .map(comment -> {
                    User user = userRepository.findById(comment.getUserId().getValue())
                            .orElseThrow(() -> new UserNotFound(comment.getUserId().getValue()));

                    return new PostCommentDto(
                            comment.getId(),
                            new WriterDto(
                                    user.getId(),
                                    user.getNickname().getValue(),
                                    user.getProfileImage().getValue()),
                            comment.getPostId().getValue(),
                            comment.getPostCommentBody().getValue(),
                            comment.isDeleted(),
                            comment.getCreatedAt(),
                            comment.getUpdatedAt());
                }).collect(Collectors.toList());

        PagesDto pagesDto = new PagesDto(postComments.getTotalPages());

        return new PostCommentsDto(postCommentDtos, pagesDto);
    }

    public MyPostCommentsDto myComments(Long userId) {
        List<PostComment> comments = postCommentRepository.findAllByUserIdAndIsDeleted(new UserId(userId));

        List<PostCommentDto> postComments = comments.stream()
                .map(comment -> {
                    User user = userRepository.findById(comment.getUserId().getValue())
                            .orElseThrow(() -> new UserNotFound(comment.getUserId().getValue()));

                    return new PostCommentDto(
                            comment.getId(),
                            new WriterDto(
                                    user.getId(),
                                    user.getNickname().getValue(),
                                    user.getProfileImage().getValue()),
                            comment.getPostId().getValue(),
                            comment.getPostCommentBody().getValue(),
                            comment.isDeleted(),
                            comment.getCreatedAt(),
                            comment.getUpdatedAt());
                }).collect(Collectors.toList());

        return new MyPostCommentsDto(postComments);
    }
}
