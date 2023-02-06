package kr.jenna.plmography.services.like;

import kr.jenna.plmography.dtos.like.LikeDto;
import kr.jenna.plmography.dtos.like.LikesDto;
import kr.jenna.plmography.models.Like;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.repositories.LikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetLikesService {
    private final LikeRepository likeRepository;

    public GetLikesService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public LikesDto likes() {
        List<LikeDto> likes = likeRepository.findAll()
                .stream()
                .map(Like::toDto)
                .collect(Collectors.toList());

        return new LikesDto(likes);
    }

    public List<Like> findLike(Long postId) {
        return likeRepository.findAllByPostId(new PostId(postId));
    }
}
