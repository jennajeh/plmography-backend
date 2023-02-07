package kr.jenna.plmography.services.post;

import kr.jenna.plmography.dtos.post.SelectedPostsDto;
import kr.jenna.plmography.exceptions.InvalidUser;
import kr.jenna.plmography.models.Like;
import kr.jenna.plmography.models.Post;
import kr.jenna.plmography.models.ReviewComment;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.repositories.LikeRepository;
import kr.jenna.plmography.repositories.PostRepository;
import kr.jenna.plmography.repositories.ReviewCommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeletePostService {
    private final PostRepository postRepository;
    private final ReviewCommentRepository reviewCommentRepository;
    private final LikeRepository likeRepository;

    public DeletePostService(PostRepository postRepository,
                             ReviewCommentRepository reviewCommentRepository,
                             LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.reviewCommentRepository = reviewCommentRepository;
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

    public void deletePosts(SelectedPostsDto selectedPostsDto) {
        List<Long> selectedPosts = selectedPostsDto.getPostIds();

        for (Long selectedPost : selectedPosts) {
            deleteLikes(selectedPost);
            deleteComments(selectedPost);

            Post post = postRepository.getReferenceById(selectedPost);

            post.delete();
        }
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
        if (reviewCommentRepository.existsByPostId(new PostId(postId))) {
            List<ReviewComment> reviewComments = reviewCommentRepository.findAllByPostId(new PostId(postId));

            for (ReviewComment reviewComment : reviewComments) {
                reviewComment.delete();
            }
        }
    }
}
