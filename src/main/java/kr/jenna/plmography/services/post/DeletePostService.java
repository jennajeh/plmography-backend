package kr.jenna.plmography.services.post;

import kr.jenna.plmography.exceptions.InvalidUser;
import kr.jenna.plmography.models.Like;
import kr.jenna.plmography.models.Post;
import kr.jenna.plmography.models.PostComment;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.repositories.LikeRepository;
import kr.jenna.plmography.repositories.PostCommentRepository;
import kr.jenna.plmography.repositories.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeletePostService {
    private final PostRepository postRepository;
    private final PostCommentRepository postCommentRepository;
    private final LikeRepository likeRepository;

    public DeletePostService(PostRepository postRepository,
                             PostCommentRepository postCommentRepository,
                             LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.postCommentRepository = postCommentRepository;
        this.likeRepository = likeRepository;
    }

    public void delete(Long userId, Long postId) {
        Post post = postRepository.getReferenceById(postId);

        if (!post.isWriter(userId)) {
            throw new InvalidUser();
        }

        deleteComments(postId);
        deleteLikes(postId);

        post.delete();
    }

    private void deleteLikes(Long postId) {
        if (likeRepository.existsByPostId(new PostId(postId))) {
            List<Like> likes = likeRepository.findAllByPostId(new PostId(postId));

            for (Like like : likes) {
                likeRepository.deleteAllById(like.getId());
            }
        }
    }

    private void deleteComments(Long postId) {
        if (postCommentRepository.existsByPostId(new PostId(postId))) {
            List<PostComment> postComments =
                    postCommentRepository.findAllByPostIdAndIsDeleted(new PostId(postId));

            for (PostComment postComment : postComments) {
                postComment.delete();
            }
        }
    }
}
