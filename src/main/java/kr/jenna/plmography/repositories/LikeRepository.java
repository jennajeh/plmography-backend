package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.Like;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.models.vo.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllByPostId(PostId postId);

    List<Like> findAllByUserId(UserId userId);

    Like getReferenceByPostId(PostId postId);

    void deleteAllById(Long id);

    void deleteByPostId(PostId postId);

    boolean existsByPostId(PostId postId);

    boolean existsByUserId(UserId userId);
}
