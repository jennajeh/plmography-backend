package kr.jenna.plmography.services.post;

import kr.jenna.plmography.dtos.post.MyPostsDto;
import kr.jenna.plmography.dtos.post.PostDto;
import kr.jenna.plmography.dtos.user.WriterDto;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Post;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.UserId;
import kr.jenna.plmography.repositories.PostRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetPostService {
    private PostRepository postRepository;
    private UserRepository userRepository;

    public GetPostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
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
}
