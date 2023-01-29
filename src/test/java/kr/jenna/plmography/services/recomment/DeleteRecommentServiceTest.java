package kr.jenna.plmography.services.recomment;

import kr.jenna.plmography.models.Recomment;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.repositories.RecommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteRecommentServiceTest {
    private RecommentRepository recommentRepository;
    private CommentRepository commentRepository;
    private DeleteRecommentService deleteRecommentService;

    @BeforeEach
    void setup() {
        recommentRepository = mock(RecommentRepository.class);
        commentRepository = mock(CommentRepository.class);

        deleteRecommentService = new DeleteRecommentService(recommentRepository, commentRepository);
    }

    @Test
    void delete() {
        given(recommentRepository.findById(any()))
                .willReturn(Optional.of(Recomment.fake()));

        Long userId = 1L;
        Long recommentId = 1L;

        assertDoesNotThrow(() -> deleteRecommentService.delete(userId, recommentId));

        verify(recommentRepository).delete(any(Recomment.class));
    }
}
