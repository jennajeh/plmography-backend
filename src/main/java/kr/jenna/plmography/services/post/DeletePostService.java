package kr.jenna.plmography.services.post;

import kr.jenna.plmography.dtos.post.SelectedPostsDto;
import kr.jenna.plmography.exceptions.InvalidUser;
import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.models.Like;
import kr.jenna.plmography.models.Post;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.repositories.LikeRepository;
import kr.jenna.plmography.repositories.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeletePostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    public DeletePostService(PostRepository postRepository,
                             CommentRepository commentRepository,
                             LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
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
        if (commentRepository.existsByPostId(new PostId(postId))) {
            List<Comment> comments = commentRepository.findAllByPostId(new PostId(postId));

            for (Comment comment : comments) {
                comment.delete();
            }
        }
    }
}
