package kr.jenna.plmography.services.Recomment;

import kr.jenna.plmography.dtos.Recomment.RecommentDto;
import kr.jenna.plmography.exceptions.InvalidUser;
import kr.jenna.plmography.exceptions.RecommentNotFound;
import kr.jenna.plmography.exceptions.UnmatchedRecommentId;
import kr.jenna.plmography.models.Recomment;
import kr.jenna.plmography.repositories.RecommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatchRecommentService {
    private final RecommentRepository recommentRepository;

    public PatchRecommentService(RecommentRepository recommentRepository) {
        this.recommentRepository = recommentRepository;
    }

    public Recomment modify(Long userId, Long recommentId, RecommentDto recommentDto) {
        Recomment recomment = recommentRepository.findById(recommentId)
                .orElseThrow(() -> new RecommentNotFound());

        if (!recomment.isWriter(userId)) {
            throw new InvalidUser();
        }

        if (!recomment.getId().equals(recommentDto.getId())) {
            throw new UnmatchedRecommentId();
        }

        recomment.modify(recommentDto);

        return recomment;
    }
}
