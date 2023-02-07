package kr.jenna.plmography.services.post;

import kr.jenna.plmography.dtos.post.MyPostsDto;
import kr.jenna.plmography.dtos.post.PostDto;
import kr.jenna.plmography.dtos.postComment.PostCommentDto;
import kr.jenna.plmography.dtos.user.WriterDto;
import kr.jenna.plmography.exceptions.PostNotFound;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Post;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.models.vo.UserId;
import kr.jenna.plmography.repositories.PostCommentRepository;
import kr.jenna.plmography.repositories.PostRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetPostService {
    private final PostRepository postRepository;
    private final PostCommentRepository postCommentRepository;
    private final UserRepository userRepository;

    public GetPostService(PostRepository postRepository,
                          PostCommentRepository postCommentRepository,
                          UserRepository userRepository) {
        this.postRepository = postRepository;
        this.postCommentRepository = postCommentRepository;
        this.userRepository = userRepository;
    }

    public MyPostsDto myPost(Long userId) {
        List<Post> posts = postRepository.findAllByUserId(new UserId(userId));

        List<PostDto> postDtos = posts.stream()
                .map(post -> {
                    User user = userRepository.findById(post.getUserId().getValue())
                            .orElseThrow(() -> new UserNotFound(post.getUserId().getValue()));

                    List<PostCommentDto> comments = findComments(post);

                    System.out.println("여기@@@@@@@@@@@@@@@@" + post.getPostBody().getValue());

                    return new PostDto(
                            post.getId(),
                            new WriterDto(
                                    user.getId(),
                                    user.getNickname().getValue(),
                                    user.getProfileImage().getValue()),
                            comments,
                            post.getTitle().getValue(),
                            post.getPostBody() == null
                                    ? ""
                                    : post.getPostBody().getValue(),
                            post.getHit().getValue(),
                            post.getImage() == null
                                    ? ""
                                    : post.getImage().getValue(),
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

        List<PostCommentDto> comments = findComments(post);

        post.updateHit(post.getHit().getValue());

        return new PostDto(
                post.getId(),
                new WriterDto(
                        user.getId(),
                        user.getNickname().getValue(),
                        user.getProfileImage().getValue()),
                comments,
                post.getTitle().getValue(),
                post.getPostBody() == null
                        ? ""
                        : post.getPostBody().getValue(),
                post.getHit().getValue(),
                post.getImage() == null
                        ? ""
                        : post.getImage().getValue(),
                post.getDeleted(),
                post.getCreatedAt(),
                post.getUpdatedAt());
    }

    private List<PostCommentDto> findComments(Post post) {
        if (postCommentRepository.existsByPostId(new PostId(post.getId()))) {
            return postCommentRepository.findAllByPostId(new PostId(post.getId()))
                    .stream()
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
        }

        return null;
    }
}
