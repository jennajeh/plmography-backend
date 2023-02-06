package kr.jenna.plmography.services.post;

import kr.jenna.plmography.dtos.comment.CommentDto;
import kr.jenna.plmography.dtos.page.PagesDto;
import kr.jenna.plmography.dtos.post.PostDto;
import kr.jenna.plmography.dtos.post.PostsDto;
import kr.jenna.plmography.dtos.user.WriterDto;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Post;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.repositories.PostRepository;
import kr.jenna.plmography.repositories.UserRepository;
import kr.jenna.plmography.specification.PostSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetPostsService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public GetPostsService(PostRepository postRepository,
                           CommentRepository commentRepository,
                           UserRepository userRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public PostsDto list(String keyword, Integer page, Integer size) {
        Sort sort = Sort.by("createdAt").descending();

        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Specification<Post> spec = (root, query, criteriaBuilder) -> null;

        if (keyword != null) {
            spec = spec.and(PostSpecification.likeTitleOrBody(keyword));
        }

        Page<Post> posts = postRepository.findAll(spec, pageable);

        List<PostDto> postDtos = posts.stream()
                .map(post -> {
                    User user = userRepository.findById(post.getUserId().getValue())
                            .orElseThrow(() -> new UserNotFound(post.getUserId().getValue()));

                    List<CommentDto> comments = findComments(post);

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
                }).collect(Collectors.toList());

        PagesDto pagesDto = new PagesDto(posts.getTotalPages());

        return new PostsDto(postDtos, pagesDto);
    }

    private List<CommentDto> findComments(Post post) {
        return commentRepository.findAllByPostId(new PostId(post.getId()))
                .stream()
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
    }

    public PostsDto top3Hit() {
        List<Post> posts = postRepository.findTop3ByOrderByHitDesc();

        List<PostDto> postDtos = posts.stream()
                .map(post -> {
                    User user = userRepository.findById(post.getUserId().getValue())
                            .orElseThrow(() -> new UserNotFound(post.getUserId().getValue()));

                    List<CommentDto> comments = findComments(post);

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
                }).collect(Collectors.toList());

        return new PostsDto(postDtos);
    }
}
