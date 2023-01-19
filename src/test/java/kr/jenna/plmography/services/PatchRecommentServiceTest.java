package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.Recomment.RecommentDto;
import kr.jenna.plmography.models.Recomment;
import kr.jenna.plmography.repositories.RecommentRepository;
import kr.jenna.plmography.services.Recomment.PatchRecommentService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class PatchRecommentServiceTest {

    @Test
    void update() {
        RecommentRepository recommentRepository = mock(RecommentRepository.class);
        PatchRecommentService patchRecommentService = new PatchRecommentService(recommentRepository);

        given(recommentRepository.findById(any(Long.class)))
                .willReturn(Optional.of(Recomment.fake()));

        RecommentDto recommentDto = RecommentDto.fake();

        Recomment recomment = patchRecommentService.modify(1L, 1L, recommentDto);

        assertThat(Recomment.fake().getRecommentBody())
                .isNotEqualTo(recommentDto.getRecommentBody());

        assertThat(recomment.getRecommentBody().getValue())
                .isEqualTo(recommentDto.getRecommentBody());
    }
}
