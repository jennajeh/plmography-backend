package kr.jenna.plmography.services.post;

import kr.jenna.plmography.dtos.post.MyPostsDto;
import kr.jenna.plmography.dtos.post.PostDto;
import kr.jenna.plmography.dtos.reviewComment.ReviewCommentDto;
import kr.jenna.plmography.dtos.user.WriterDto;
import kr.jenna.plmography.exceptions.PostNotFound;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Post;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.models.vo.UserId;
import kr.jenna.plmography.repositories.PostRepository;
import kr.jenna.plmography.repositories.ReviewCommentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetPostService {
    private final PostRepository postRepository;
    private final ReviewCommentRepository reviewCommentRepository;
    private final UserRepository userRepository;

    public GetPostService(PostRepository postRepository,
                          ReviewCommentRepository reviewCommentRepository,
                          UserRepository userRepository) {
        this.postRepository = postRepository;
        this.reviewCommentRepository = reviewCommentRepository;
        this.userRepository = userRepository;
    }

    public MyPostsDto myPost(Long userId) {
        List<Post> posts = postRepository.findAllByUserId(new UserId(userId));

        List<PostDto> postDtos = posts.stream()
                .map(post -> {
                    User user = userRepository.findById(post.getUserId().getValue())
                            .orElseThrow(() -> new UserNotFound(post.getUserId().getValue()));

                    return new PostDto(
                            post.getId(),
                            new WriterDto(
                                    user.getId(),
                                    user.getNickname().getValue(),
                                    user.getProfileImage().getValue()),
                            post.getTitle().getValue(),
                            post.getHit().getValue(),
                            post.getImage().getValue(),
                            post.getDeleted(),
                            post.getCreatedAt(),
                            post.getUpdatedAt());
                }).collect(Collectors.toList());

        return new MyPostsDto(postDtos);
    }

    public PostDto detail(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFound());

        User user = userRepository.findById(post.getUserId().getValue())
                .orElseThrow(() -> new UserNotFound(post.getUserId().getValue()));

        List<ReviewCommentDto> comments = findComments(post);

        return new PostDto(
                post.getId(),
                new WriterDto(
                        user.getId(),
                        user.getNickname().getValue(),
                        user.getProfileImage().getValue()),
                comments,
                post.getTitle().getValue(),
                post.getPostBody().getValue(),
                post.getHit().getValue(),
                post.getImage().getValue(),
                post.getDeleted(),
                post.getCreatedAt(),
                post.getUpdatedAt());
    }

    private List<ReviewCommentDto> findComments(Post post) {
        return reviewCommentRepository.findAllByPostId(new PostId(post.getId()))
                .stream()
                .map(comment -> {
                    User user = userRepository.findById(comment.getUserId().getValue())
                            .orElseThrow(() -> new UserNotFound(comment.getUserId().getValue()));

                    return new ReviewCommentDto(
                            comment.getId(),
                            new WriterDto(
                                    user.getId(),
                                    user.getNickname().getValue(),
                                    user.getProfileImage().getValue()),
                            comment.getPostId().getValue(),
                            comment.getReviewCommentBody().getValue(),
                            comment.isDeleted(),
                            comment.getCreatedAt(),
                            comment.getUpdatedAt());
                }).collect(Collectors.toList());
    }
}
