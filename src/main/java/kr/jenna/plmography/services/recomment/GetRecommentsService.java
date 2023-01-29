package kr.jenna.plmography.services.recomment;

import kr.jenna.plmography.dtos.recomment.RecommentDto;
import kr.jenna.plmography.dtos.recomment.RecommentsDto;
import kr.jenna.plmography.models.Recomment;
import kr.jenna.plmography.repositories.RecommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetRecommentsService {
    private final RecommentRepository recommentRepository;

    public GetRecommentsService(RecommentRepository recommentRepository) {
        this.recommentRepository = recommentRepository;
    }

    public RecommentsDto recomments() {
        List<RecommentDto> recomments = recommentRepository.findAll().stream()
                .map(Recomment::toRecommentDto)
                .collect(Collectors.toList());

        return new RecommentsDto(recomments);
    }
}
