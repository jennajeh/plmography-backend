package kr.jenna.plmography.models;

import kr.jenna.plmography.models.vo.Image;
import kr.jenna.plmography.models.vo.PostBody;
import kr.jenna.plmography.models.vo.Title;
import kr.jenna.plmography.models.vo.UserId;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest {

    @Test
    void creation() {
        Post post = new Post(
                new UserId(1L), new Title("제목"), new PostBody("내용"), new Image("imageUrl"));

        assertThat(post.getTitle().getValue()).isEqualTo("제목");
    }

    @Test
    void modify() {
        Post post = Post.fake();

        post.modify("오늘의 게시글", "오늘의 게시글 내용", "imageUrl.jpg");

        assertThat(post.getPostBody().getValue())
                .isEqualTo("오늘의 게시글 내용");
    }

    @Test
    void updateHit() {
        Post post = Post.fake();

        post.updateHit(post.getHit().getValue());

        assertThat(post.getHit().getValue()).isEqualTo(1L);
    }

    @Test
    void delete() {
        Post post = Post.fake();

        post.delete();

        assertThat(post.getDeleted()).isTrue();
    }

    @Test
    void isWriter() {
        Post post = Post.fake();

        assertThat(post.isWriter(1L)).isTrue();
        assertThat(post.isWriter(2L)).isFalse();
    }
}
