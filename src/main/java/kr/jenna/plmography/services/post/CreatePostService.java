package kr.jenna.plmography.services.post;

import kr.jenna.plmography.dtos.post.PostRegistrationDto;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Post;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.Image;
import kr.jenna.plmography.models.vo.PostBody;
import kr.jenna.plmography.models.vo.Title;
import kr.jenna.plmography.models.vo.UserId;
import kr.jenna.plmography.repositories.PostRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class CreatePostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CreatePostService(PostRepository postRepository,
                             UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post create(Long id, PostRegistrationDto postRegistrationDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound(id));

        UserId userId = new UserId(user.getId());
        Title title = new Title(postRegistrationDto.getTitle());
        PostBody postBody = new PostBody(postRegistrationDto.getPostBody());
        Image image = new Image(postRegistrationDto.getImage());

        Post post = new Post(userId, title, postBody, image, LocalDateTime.now(), LocalDateTime.now());

        postRepository.save(post);

        return post;
    }
}
