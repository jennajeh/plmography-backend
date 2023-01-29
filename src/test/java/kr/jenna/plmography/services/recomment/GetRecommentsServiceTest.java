package kr.jenna.plmography.services.recomment;

import kr.jenna.plmography.dtos.recomment.RecommentsDto;
import kr.jenna.plmography.models.Recomment;
import kr.jenna.plmography.repositories.RecommentRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetRecommentsServiceTest {

    @Test
    void recomments() {
        RecommentRepository recommentRepository = mock(RecommentRepository.class);
        GetRecommentsService getRecommentsService = new GetRecommentsService(recommentRepository);

        Recomment recomment = Recomment.fake();

        given(recommentRepository.findAll()).willReturn(List.of(recomment));

        RecommentsDto recommentsDto = getRecommentsService.recomments();

        assertThat(recommentsDto).isNotNull();
        assertThat(recommentsDto.getRecomments().get(0).getRecommentBody())
                .isEqualTo("대댓글");
    }
}
