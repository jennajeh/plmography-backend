package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.PostComment;
import kr.jenna.plmography.models.vo.UserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
    Page<PostComment> findAllByPostId(Long postId, Pageable pageable);

    List<PostComment> findAllByUserId(UserId userId);
}
