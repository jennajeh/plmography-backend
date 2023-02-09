package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.PostComment;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.models.vo.UserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
    @Query("select p from PostComment p where p.postId = :postId and p.isDeleted = false")
    Page<PostComment> findAllByPostIdAndIsDeleted(@Param("postId") PostId postId, Pageable pageable);

    @Query("select p from PostComment p where p.userId = :userId and p.isDeleted = false")
    List<PostComment> findAllByUserIdAndIsDeleted(@Param("userId") UserId userId);

    @Query("select p from PostComment p where p.postId = :postId and p.isDeleted = false")
    List<PostComment> findAllByPostIdAndIsDeleted(@Param("postId") PostId postId);

    boolean existsByPostId(PostId postId);
}
