package kr.jenna.plmography.services.post;

import kr.jenna.plmography.dtos.post.PostModificationResponseDto;
import kr.jenna.plmography.dtos.post.UpdateHitPostResponseDto;
import kr.jenna.plmography.exceptions.InvalidUser;
import kr.jenna.plmography.models.Post;
import kr.jenna.plmography.models.vo.Image;
import kr.jenna.plmography.models.vo.PostBody;
import kr.jenna.plmography.models.vo.Title;
import kr.jenna.plmography.repositories.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatchPostService {
    private PostRepository postRepository;

    public PatchPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public UpdateHitPostResponseDto updateHit(Long postId) {
        Post post = postRepository.getReferenceById(postId);

        post.updateHit(post.getHit().getValue());

        return new UpdateHitPostResponseDto(post.getId());
    }

    public PostModificationResponseDto modify(
            Long userId, Long postId, Title title, PostBody postBody, Image image) {
        Post post = postRepository.getReferenceById(postId);

        if (!post.isWriter(userId)) {
            throw new InvalidUser();
        }

        post.modify(title.getValue(), postBody.getValue(), image.getValue());

        return new PostModificationResponseDto(post.getId());
    }
}
