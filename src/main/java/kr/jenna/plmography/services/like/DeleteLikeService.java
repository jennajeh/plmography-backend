package kr.jenna.plmography.services.like;

import kr.jenna.plmography.dtos.like.SelectedLikeDto;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.repositories.LikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeleteLikeService {
    private final LikeRepository likeRepository;

    public DeleteLikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public void delete(SelectedLikeDto selectedLikeDto) {
        List<Long> likeIds = selectedLikeDto.getLikeId();

        likeIds.stream().map(PostId::new).forEach(likeRepository::deleteByPostId);
    }
}
