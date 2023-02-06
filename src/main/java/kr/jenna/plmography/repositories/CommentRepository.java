package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.models.vo.PostId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostId(PostId postId);

    boolean existsByPostId(PostId postId);
}
