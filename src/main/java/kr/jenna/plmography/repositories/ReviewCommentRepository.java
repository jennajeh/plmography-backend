package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.ReviewComment;
import kr.jenna.plmography.models.vo.PostId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {
    List<ReviewComment> findAllByPostId(PostId postId);

    boolean existsByPostId(PostId postId);

    @Query("select count(*) from ReviewComment r where r.postId = :postId and r.isDeleted = false")
    Long countByPostIdAndIsNotDeleted(@Param("postId") PostId postId);
}
