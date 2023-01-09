package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.Recomment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommentRepository extends JpaRepository<Recomment, Long> {
    List<Recomment> findAllByPostId(Long postId);

    List<Recomment> findAllById(Long recommentId);

    List<Recomment> findAllByCommentId(Long commentId);

}
