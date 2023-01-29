package kr.jenna.plmography.models;

import kr.jenna.plmography.dtos.recomment.RecommentDto;
import kr.jenna.plmography.models.vo.CommentId;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.models.vo.RecommentBody;
import kr.jenna.plmography.models.vo.UserId;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class RecommentTest {

    @Test
    void creation() {
        Recomment recomment = new Recomment(1L, new CommentId(1L),
                new RecommentBody("recomment"), new UserId(1L), new PostId(1L), LocalDateTime.now());

        assertThat(recomment.getRecommentBody()).isEqualTo(new RecommentBody("recomment"));
    }

    @Test
    void toRecommentDto() {
        Recomment recomment = Recomment.fake();

        RecommentDto recommentDto = recomment.toRecommentDto();

        assertThat(recommentDto).isNotNull();
        assertThat(recommentDto.getRecommentBody())
                .isEqualTo(recomment.getRecommentBody().getValue());
    }

    @Test
    void modify() {
        Recomment recomment = Recomment.fake();

        RecommentDto recommentDto = RecommentDto.fake();

        recomment.modify(recommentDto);

        assertThat(recomment.getRecommentBody().getValue())
                .isEqualTo(recommentDto.getRecommentBody());
    }

    @Test
    void isWriter() {
        Recomment recomment = Recomment.fake();

        assertThat(recomment.isWriter(1L)).isTrue();
        assertThat(recomment.isWriter(2L)).isFalse();
    }
}
