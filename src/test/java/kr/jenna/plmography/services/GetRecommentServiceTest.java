package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.Recomment.RecommentsDto;
import kr.jenna.plmography.models.Recomment;
import kr.jenna.plmography.repositories.RecommentRepository;
import kr.jenna.plmography.services.Recomment.GetRecommentService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetRecommentServiceTest {

    @Test
    void detail() {
        RecommentRepository recommentRepository = mock(RecommentRepository.class);
        GetRecommentService getRecommentService = new GetRecommentService(recommentRepository);

        given(recommentRepository.findAllByPostId(any(Long.class)))
                .willReturn(List.of(Recomment.fake()));

        RecommentsDto recommentsDto = getRecommentService.detail(1L);

        assertThat(recommentsDto).isNotNull();
        assertThat(recommentsDto.getRecomments().get(0).getRecommentBody())
                .isEqualTo("대댓글");
    }

}
