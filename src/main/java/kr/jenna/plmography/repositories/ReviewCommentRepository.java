package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.ReviewComment;
import kr.jenna.plmography.models.vo.PostId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {
    List<ReviewComment> findAllByPostId(PostId postId);

    boolean existsByPostId(PostId postId);
}
