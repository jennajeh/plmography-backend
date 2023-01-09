package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.models.VO.PostId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByUserId(Long userId, Pageable pageable);

    List<Comment> findAllByPostId(PostId postId);
}
