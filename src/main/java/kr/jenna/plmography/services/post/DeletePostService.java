package kr.jenna.plmography.services.post;

import kr.jenna.plmography.exceptions.InvalidUser;
import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.models.Post;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.repositories.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeletePostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public DeletePostService(PostRepository postRepository,
                             CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public void delete(Long userId, Long postId) {
        Post post = postRepository.getReferenceById(postId);

        if (!post.isWriter(userId)) {
            throw new InvalidUser();
        }

        if (commentRepository.existsByPostId(new PostId(postId))) {
            List<Comment> comments = commentRepository.findAllByPostId(new PostId(postId));

            for (int i = 0; i < comments.size(); i += 1) {
                comments.get(i).delete();
            }
        }

        post.delete();
    }
}
