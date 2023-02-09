package kr.jenna.plmography.models;

import kr.jenna.plmography.exceptions.InvalidPassword;
import kr.jenna.plmography.models.vo.Email;
import kr.jenna.plmography.models.vo.FavoriteContentId;
import kr.jenna.plmography.models.vo.Nickname;
import kr.jenna.plmography.models.vo.Password;
import kr.jenna.plmography.models.vo.ProfileImage;
import kr.jenna.plmography.models.vo.WatchedContentId;
import kr.jenna.plmography.models.vo.WishContentId;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    @Test
    void creation() {
        User user = new User(new Email("jenna@gmail.com"),
                new Password("Test123!"),
                new Nickname("전제나"));

        assertThat(user.getEmail()).isEqualTo(new Email("jenna@gmail.com"));
        assertThat(user.getNickname()).isEqualTo(new Nickname("전제나"));
    }

    @Test
    void authenticate() {
        PasswordEncoder passwordEncoder = new Argon2PasswordEncoder(16, 32, 1, 1 << 14, 2);

        User user = new User();

        user.encodePassword(new Password("Test123!"), passwordEncoder);

        assertThat(user.authenticate(new Password("Test123!"), passwordEncoder)).isTrue();
        assertThrows(InvalidPassword.class, () -> {
            user.authenticate(new Password("xxx"), passwordEncoder);
        });
    }

    @Test
    void update() {
        User user = User.fake();

        String nickname = "강보니";
        String profileImage = "new profile";

        user.changeProfile(new Nickname(nickname), new ProfileImage(profileImage));

        assertThat(user.getNickname()).isEqualTo(new Nickname("강보니"));
        assertThat(user.getProfileImage()).isEqualTo(new ProfileImage("new profile"));
    }

    @Test
    void toggleWish() {
        User user = User.fake();

        WishContentId wishContentId = new WishContentId(1L);

        user.toggleWish(wishContentId);

        assertThat(user.getWishContentIds()).hasSize(1);

        user.toggleWish(wishContentId);

        assertThat(user.getWishContentIds()).hasSize(0);
    }

    @Test
    void toggleWatched() {
        User user = User.fake();

        WatchedContentId watchedContentId = new WatchedContentId(1L);

        user.toggleWatched(watchedContentId);

        assertThat(user.getWatchedContentIds()).hasSize(1);

        user.toggleWatched(watchedContentId);

        assertThat(user.getWatchedContentIds()).hasSize(0);
    }

    @Test
    void toggleFavorite() {
        User user = User.fake();

        FavoriteContentId favoriteContentId = new FavoriteContentId(1L);

        user.toggleFavorite(favoriteContentId);

        assertThat(user.getFavoriteContentIds()).hasSize(1);

        user.toggleFavorite(favoriteContentId);

        assertThat(user.getFavoriteContentIds()).hasSize(0);
    }
}
