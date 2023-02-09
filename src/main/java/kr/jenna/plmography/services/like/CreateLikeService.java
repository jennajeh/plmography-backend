package kr.jenna.plmography.services.like;

import kr.jenna.plmography.dtos.like.LikeDto;
import kr.jenna.plmography.models.Like;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.models.vo.UserId;
import kr.jenna.plmography.repositories.LikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CreateLikeService {
    private final LikeRepository likeRepository;

    public CreateLikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public void countLike(LikeDto likeDto) {
        Long userId = likeDto.getUserId();
        Long postId = likeDto.getPostId();

        if (likeRepository.existsByPostId(new PostId(postId))) {
            List<Like> foundLikes = likeRepository.findAllByUserId(new UserId(userId));

            for (Like foundLike : foundLikes) {
                if (foundLike.getUserId().getValue().equals(userId)) {
                    likeRepository.deleteById(foundLike.getId());

                    return;
                }
            }

            Like newLike = new Like(new PostId(postId), new UserId(userId));

            likeRepository.save(newLike);

            return;
        }

        Like newLike = new Like(new PostId(postId), new UserId(userId));

        likeRepository.save(newLike);
    }
}
